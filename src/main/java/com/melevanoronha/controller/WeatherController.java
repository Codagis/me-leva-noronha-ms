package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.WeatherControllerInterface;
import com.melevanoronha.dto.response.WeatherResponse;
import com.melevanoronha.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST responsável pelos endpoints de previsão do tempo.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class WeatherController implements WeatherControllerInterface {

    private final WeatherService weatherService;

    @Override
    public ResponseEntity<WeatherResponse> obterPrevisaoCompleta(
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude) {
        WeatherResponse response = weatherService.obterPrevisaoCompleta(latitude, longitude);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WeatherResponse.CurrentWeather> obterDadosAtuais(
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude) {
        WeatherResponse.CurrentWeather response = weatherService.obterDadosAtuais(latitude, longitude);
        return ResponseEntity.ok(response);
    }
}

