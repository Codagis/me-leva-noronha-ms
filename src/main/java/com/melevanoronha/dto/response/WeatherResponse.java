package com.melevanoronha.dto.response;

import java.util.List;

/**
 * DTO utilizado para devolver os dados de previsão do tempo para os consumidores da API.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public record WeatherResponse(
        CurrentWeather current,
        List<HourlyData> hourly,
        List<DailyData> daily,
        MarineConditions marine
) {
    public record CurrentWeather(
            Double temperature,
            Double humidity,
            Double windSpeed,
            Integer windDirection,
            Double uvIndex,
            Integer weatherCode,
            String weatherDescription
    ) {}

    public record HourlyData(
            String time,
            Double temperature,
            Double humidity,
            Double windSpeed,
            Integer windDirection,
            Double uvIndex,
            Double waveHeight,
            String marineCondition
    ) {}

    public record DailyData(
            String date,
            Double temperatureMax,
            Double temperatureMin,
            Double humidityMax,
            Double humidityMin,
            Double uvIndexMax,
            Double waveHeightMax,
            String marineCondition
    ) {}

    public record MarineConditions(
            Double currentWaveHeight,
            String currentCondition,
            String description
    ) {}
}

