package com.melevanoronha.service;

import com.melevanoronha.dto.response.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Serviço responsável por consumir a API Open-Meteo e fornecer dados de previsão do tempo.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${weather.open-meteo.forecast-url}")
    private String forecastUrl;

    @Value("${weather.location.latitude}")
    private String latitude;

    @Value("${weather.location.longitude}")
    private String longitude;

    @Value("${weather.location.timezone}")
    private String timezone;

    public WeatherResponse obterPrevisaoCompleta(Double latitude, Double longitude) {
        try {
            String lat = latitude != null ? String.valueOf(latitude) : this.latitude;
            String lon = longitude != null ? String.valueOf(longitude) : this.longitude;
            
            Map<String, Object> forecastData = obterDadosPrevisao(lat, lon);
            Map<String, Object> marineData = obterDadosMarinhos(lat, lon);

            return construirWeatherResponse(forecastData, marineData);
        } catch (Exception e) {
            log.error("Erro ao obter previsão do tempo: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao obter dados meteorológicos: " + e.getMessage(), e);
        }
    }

    public WeatherResponse.CurrentWeather obterDadosAtuais(Double latitude, Double longitude) {
        try {
            String lat = latitude != null ? String.valueOf(latitude) : this.latitude;
            String lon = longitude != null ? String.valueOf(longitude) : this.longitude;
            
            Map<String, Object> forecastData = obterDadosPrevisao(lat, lon);
            return construirCurrentWeather(forecastData);
        } catch (Exception e) {
            log.error("Erro ao obter dados atuais: {}", e.getMessage(), e);
            throw new RuntimeException("Falha ao obter dados meteorológicos atuais: " + e.getMessage(), e);
        }
    }

    private Map<String, Object> obterDadosPrevisao(String latitude, String longitude) {
        String url = UriComponentsBuilder.fromHttpUrl(forecastUrl)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current", "temperature_2m,relative_humidity_2m,wind_speed_10m,wind_direction_10m,uv_index")
                .queryParam("current_weather", "true")
                .queryParam("hourly", "temperature_2m,relative_humidity_2m,wind_speed_10m,wind_direction_10m,uv_index")
                .queryParam("daily", "temperature_2m_max,temperature_2m_min,relative_humidity_2m_max,relative_humidity_2m_min,uv_index_max")
                .queryParam("timezone", timezone)
                .queryParam("forecast_days", 7)
                .toUriString();

        return restTemplate.getForObject(url, Map.class);
    }

    private Map<String, Object> obterDadosMarinhos(String latitude, String longitude) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(forecastUrl)
                    .queryParam("latitude", latitude)
                    .queryParam("longitude", longitude)
                    .queryParam("hourly", "wave_height,wave_direction,wave_period")
                    .queryParam("daily", "wave_height_max")
                    .queryParam("timezone", timezone)
                    .queryParam("forecast_days", 7)
                    .toUriString();

            return restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            log.warn("Dados marinhos não disponíveis para esta localização: {}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private WeatherResponse construirWeatherResponse(Map<String, Object> forecastData, Map<String, Object> marineData) {
        WeatherResponse.CurrentWeather current = construirCurrentWeather(forecastData);
        List<WeatherResponse.HourlyData> hourly = construirHourlyForecast(forecastData, marineData);
        List<WeatherResponse.DailyData> daily = construirDailyForecast(forecastData, marineData);
        WeatherResponse.MarineConditions marine = construirMarineConditions(forecastData, marineData);

        return new WeatherResponse(current, hourly, daily, marine);
    }

    @SuppressWarnings("unchecked")
    private WeatherResponse.CurrentWeather construirCurrentWeather(Map<String, Object> forecastData) {
        Map<String, Object> currentWeather = (Map<String, Object>) forecastData.get("current_weather");
        Map<String, Object> hourly = (Map<String, Object>) forecastData.get("hourly");
        Map<String, Object> daily = (Map<String, Object>) forecastData.get("daily");
        
        if (currentWeather == null) {
            throw new RuntimeException("Dados meteorológicos atuais não disponíveis");
        }

        Double temperature = getDoubleValue(currentWeather.get("temperature"));
        Double windSpeed = getDoubleValue(currentWeather.get("windspeed"));
        Integer windDirection = getIntegerValue(currentWeather.get("winddirection"));
        Integer weatherCode = getIntegerValue(currentWeather.get("weathercode"));
        String weatherDescription = obterDescricaoTempo(weatherCode);

        Double humidity = null;
        Double uvIndex = null;
        
        Map<String, Object> current = (Map<String, Object>) forecastData.get("current");
        if (current != null) {
            humidity = getDoubleValue(current.get("relative_humidity_2m"));
            uvIndex = getDoubleValue(current.get("uv_index"));
        }
        
        if (hourly != null) {
            List<String> timeList = (List<String>) hourly.get("time");
            
            if (humidity == null) {
                List<Double> humidityList = convertToDoubleList(hourly.get("relative_humidity_2m"));
                if (humidityList != null && !humidityList.isEmpty()) {
                    humidity = humidityList.get(0);
                }
            }
            
            if (uvIndex == null) {
                List<Double> uvIndexList = convertToDoubleList(hourly.get("uv_index"));
                if (uvIndexList != null && !uvIndexList.isEmpty() && timeList != null && !timeList.isEmpty()) {
                    String currentTime = (String) currentWeather.get("time");
                    if (currentTime != null) {
                        int index = -1;
                        for (int i = 0; i < timeList.size(); i++) {
                            String hourlyTime = timeList.get(i);
                            if (hourlyTime != null && hourlyTime.equals(currentTime)) {
                                index = i;
                                break;
                            }
                        }
                        
                        if (index >= 0 && index < uvIndexList.size()) {
                            uvIndex = uvIndexList.get(index);
                        } else {
                            String currentHourMinute = currentTime.length() >= 16 ? currentTime.substring(11, 16) : currentTime;
                            int closestIndex = -1;
                            int minDiff = Integer.MAX_VALUE;
                            
                            for (int i = 0; i < timeList.size(); i++) {
                                String hourlyTime = timeList.get(i);
                                if (hourlyTime != null && hourlyTime.length() >= 16) {
                                    String hourlyHourMinute = hourlyTime.substring(11, 16);
                                    if (hourlyHourMinute.equals(currentHourMinute)) {
                                        closestIndex = i;
                                        break;
                                    }
                                    try {
                                        int currentMinutes = Integer.parseInt(currentHourMinute.substring(0, 2)) * 60 
                                                              + Integer.parseInt(currentHourMinute.substring(3, 5));
                                        int hourlyMinutes = Integer.parseInt(hourlyHourMinute.substring(0, 2)) * 60 
                                                             + Integer.parseInt(hourlyHourMinute.substring(3, 5));
                                        int diff = Math.abs(currentMinutes - hourlyMinutes);
                                        if (diff < minDiff) {
                                            minDiff = diff;
                                            closestIndex = i;
                                        }
                                    } catch (NumberFormatException e) {
                                    }
                                }
                            }
                            
                            if (closestIndex >= 0 && closestIndex < uvIndexList.size()) {
                                uvIndex = uvIndexList.get(closestIndex);
                            } else {
                                for (int i = 0; i < Math.min(48, uvIndexList.size()); i++) {
                                    Double uv = uvIndexList.get(i);
                                    if (uv != null && uv > 0) {
                                        uvIndex = uv;
                                        break;
                                    }
                                }
                                if (uvIndex == null) {
                                    uvIndex = uvIndexList.get(0);
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < Math.min(24, uvIndexList.size()); i++) {
                            Double uv = uvIndexList.get(i);
                            if (uv != null && uv > 0) {
                                uvIndex = uv;
                                break;
                            }
                        }
                        if (uvIndex == null) {
                            uvIndex = uvIndexList.get(0);
                        }
                    }
                }
            }
        }

        return new WeatherResponse.CurrentWeather(
                temperature,
                humidity,
                windSpeed,
                windDirection,
                uvIndex,
                weatherCode,
                weatherDescription
        );
    }

    @SuppressWarnings("unchecked")
    private List<WeatherResponse.HourlyData> construirHourlyForecast(Map<String, Object> forecastData, Map<String, Object> marineData) {
        Map<String, Object> hourly = (Map<String, Object>) forecastData.get("hourly");
        Map<String, Object> hourlyMarine = marineData != null ? (Map<String, Object>) marineData.get("hourly") : null;

        if (hourly == null) {
            throw new RuntimeException("Dados de previsão horária não disponíveis");
        }

        List<String> time = (List<String>) hourly.get("time");
        List<Double> temperature = convertToDoubleList(hourly.get("temperature_2m"));
        List<Double> humidity = convertToDoubleList(hourly.get("relative_humidity_2m"));
        List<Double> windSpeed = convertToDoubleList(hourly.get("wind_speed_10m"));
        List<Integer> windDirection = convertToIntegerList(hourly.get("wind_direction_10m"));
        List<Double> uvIndex = convertToDoubleList(hourly.get("uv_index"));

        final List<Double> waveHeight = hourlyMarine != null 
                ? convertToDoubleList(hourlyMarine.get("wave_height"))
                : new ArrayList<>();

        if (time == null || time.isEmpty()) {
            return new ArrayList<>();
        }

        return IntStream.range(0, time.size())
                .mapToObj(i -> {
                    Double temp = i < temperature.size() ? temperature.get(i) : null;
                    Double hum = i < humidity.size() ? humidity.get(i) : null;
                    Double wind = i < windSpeed.size() ? windSpeed.get(i) : null;
                    Integer windDir = i < windDirection.size() ? windDirection.get(i) : null;
                    Double uv = i < uvIndex.size() ? uvIndex.get(i) : null;
                    Double wave = i < waveHeight.size() ? waveHeight.get(i) : null;
                    String marineCond = wave != null ? classificarCondicaoMar(wave) : "Indisponível";
                    
                    return new WeatherResponse.HourlyData(
                            time.get(i),
                            temp,
                            hum,
                            wind,
                            windDir,
                            uv,
                            wave,
                            marineCond
                    );
                })
                .toList();
    }

    @SuppressWarnings("unchecked")
    private List<WeatherResponse.DailyData> construirDailyForecast(Map<String, Object> forecastData, Map<String, Object> marineData) {
        Map<String, Object> daily = (Map<String, Object>) forecastData.get("daily");
        Map<String, Object> dailyMarine = marineData != null ? (Map<String, Object>) marineData.get("daily") : null;
        if (daily == null) {
            throw new RuntimeException("Dados de previsão diária não disponíveis");
        }
        List<String> date = (List<String>) daily.get("time");
        List<Double> temperatureMax = convertToDoubleList(daily.get("temperature_2m_max"));
        List<Double> temperatureMin = convertToDoubleList(daily.get("temperature_2m_min"));
        List<Double> humidityMax = convertToDoubleList(daily.get("relative_humidity_2m_max"));
        List<Double> humidityMin = convertToDoubleList(daily.get("relative_humidity_2m_min"));
        List<Double> uvIndexMax = convertToDoubleList(daily.get("uv_index_max"));
        final List<Double> waveHeightMax = dailyMarine != null
                ? convertToDoubleList(dailyMarine.get("wave_height_max"))
                : new ArrayList<>();

        if (date == null || date.isEmpty()) {
            return new ArrayList<>();
        }

        return IntStream.range(0, date.size())
                .mapToObj(i -> {
                    Double tempMax = i < temperatureMax.size() ? temperatureMax.get(i) : null;
                    Double tempMin = i < temperatureMin.size() ? temperatureMin.get(i) : null;
                    Double humMax = i < humidityMax.size() ? humidityMax.get(i) : null;
                    Double humMin = i < humidityMin.size() ? humidityMin.get(i) : null;
                    Double uvMax = i < uvIndexMax.size() ? uvIndexMax.get(i) : null;
                    Double waveMax = i < waveHeightMax.size() ? waveHeightMax.get(i) : null;
                    String marineCond = waveMax != null ? classificarCondicaoMar(waveMax) : "Indisponível";
                    
                    return new WeatherResponse.DailyData(
                            date.get(i),
                            tempMax,
                            tempMin,
                            humMax,
                            humMin,
                            uvMax,
                            waveMax,
                            marineCond
                    );
                })
                .toList();
    }

    @SuppressWarnings("unchecked")
    private WeatherResponse.MarineConditions construirMarineConditions(Map<String, Object> forecastData, Map<String, Object> marineData) {
        Double currentWaveHeight = null;
        String currentCondition = "Indisponível";
        String description = "Dados marinhos não disponíveis";
        if (marineData != null) {
            Map<String, Object> hourlyMarine = (Map<String, Object>) marineData.get("hourly");
            if (hourlyMarine != null) {
                List<Double> waveHeights = convertToDoubleList(hourlyMarine.get("wave_height"));
                if (waveHeights != null && !waveHeights.isEmpty()) {
                    currentWaveHeight = waveHeights.get(0);
                    currentCondition = classificarCondicaoMar(currentWaveHeight);
                    description = obterDescricaoMar(currentCondition);
                }
            }
        }
        return new WeatherResponse.MarineConditions(
                currentWaveHeight,
                currentCondition,
                description
        );
    }

    private String classificarCondicaoMar(Double waveHeight) {
        if (waveHeight == null) {
            return "Indisponível";
        }
        if (waveHeight < 0.5) {
            return "Muito Calmo";
        } else if (waveHeight < 1.0) {
            return "Calmo";
        } else if (waveHeight < 1.5) {
            return "Levemente Agitado";
        } else if (waveHeight < 2.5) {
            return "Moderado";
        } else if (waveHeight < 4.0) {
            return "Agitado";
        } else {
            return "Muito Agitado";
        }
    }

    private String obterDescricaoMar(String condicao) {
        return switch (condicao) {
            case "Muito Calmo" -> "Mar extremamente calmo, ideal para atividades aquáticas";
            case "Calmo" -> "Mar calmo, condições favoráveis para navegação e mergulho";
            case "Levemente Agitado" -> "Mar com pequenas ondas, condições moderadas";
            case "Moderado" -> "Mar com ondas moderadas, cuidado necessário";
            case "Agitado" -> "Mar agitado, condições desfavoráveis para atividades aquáticas";
            case "Muito Agitado" -> "Mar muito agitado, evite atividades aquáticas";
            default -> "Condições do mar não disponíveis";
        };
    }

    private String obterDescricaoTempo(Integer weatherCode) {
        if (weatherCode == null) {
            return "Desconhecido";
        }

        return switch (weatherCode) {
            case 0 -> "Céu limpo";
            case 1 -> "Nuvens dispersas";
            case 2 -> "Parcialmente nublado";
            case 3 -> "Encoberto";
            case 45, 48 -> "Nevoeiro / Nevoeiro com geada";
            case 51, 53, 55 -> "Chuvisco: fraco, moderado ou denso";
            case 56, 57 -> "Chuvisco congelante: fraco ou denso";
            case 61, 63, 65 -> "Chuva: fraca, moderada ou intensa";
            case 66, 67 -> "Chuva congelante: fraca ou intensa";
            case 71, 73, 75 -> "Neve: fraca, moderada ou intensa";
            case 77 -> "Grãos de neve";
            case 80, 81, 82 -> "Pancadas de chuva: fracas, moderadas ou violentas";
            case 85, 86 -> "Pancadas de neve: fracas ou intensas";
            case 95 -> "Trovoada: fraca ou moderada";
            case 96, 99 -> "Trovoada com granizo: fraca ou intensa";
            default -> "Condições variáveis";
        };
    }

    @SuppressWarnings("unchecked")
    private List<Double> convertToDoubleList(Object value) {
        if (value == null) {
            return new ArrayList<>();
        }
        if (value instanceof List) {
            return ((List<?>) value).stream()
                    .map(this::getDoubleValue)
                    .toList();
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    private List<Integer> convertToIntegerList(Object value) {
        if (value == null) {
            return new ArrayList<>();
        }
        if (value instanceof List) {
            return ((List<?>) value).stream()
                    .map(this::getIntegerValue)
                    .toList();
        }
        return new ArrayList<>();
    }

    private Double getDoubleValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private Integer getIntegerValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}

