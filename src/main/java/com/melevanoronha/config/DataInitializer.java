package com.melevanoronha.config;

import com.melevanoronha.model.TabuaMare;
import com.melevanoronha.model.TaxiPreco;
import com.melevanoronha.model.TaxiTabela;
import com.melevanoronha.repository.TabelaMareRepository;
import com.melevanoronha.repository.TaxiPrecoRepository;
import com.melevanoronha.repository.TaxiTabelaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Componente responsável por inicializar dados de maré de 2025.
 * Atualmente insere as tabelas de janeiro e fevereiro.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final TabelaMareRepository tabelaMareRepository;
    private final TaxiPrecoRepository taxiPrecoRepository;
    private final TaxiTabelaRepository taxiTabelaRepository;

    @Override
    @Transactional
    public void run(String... args) {
        inserirDadosJaneiro2025();
        inserirDadosFevereiro2025();
        inserirDadosMarco2025();
        inserirDadosAbril2025();
        inserirDadosMaio2025();
        inserirDadosJunho2025();
        inserirDadosJulho2025();
        inserirDadosAgosto2025();
        inserirDadosSetembro2025();
        inserirDadosOutubro2025();
        inserirDadosNovembro2025();
        inserirDadosDezembro2025();
        inserirDadosTaxi();
    }

    private void inserirDadosJaneiro2025() {
        if (dadosJaInseridosParaMes(2025, 1)) {
            log.info("Dados de maré de janeiro de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroJaneiro(registros, 1, "00:01", "0.28M");
        adicionarRegistroJaneiro(registros, 1, "06:14", "2.10M");
        adicionarRegistroJaneiro(registros, 1, "12:12", "0.42M");
        adicionarRegistroJaneiro(registros, 1, "18:19", "2.31M");

        adicionarRegistroJaneiro(registros, 2, "00:40", "0.27M");
        adicionarRegistroJaneiro(registros, 2, "06:53", "2.11M");
        adicionarRegistroJaneiro(registros, 2, "12:51", "0.43M");
        adicionarRegistroJaneiro(registros, 2, "19:02", "2.33M");

        adicionarRegistroJaneiro(registros, 3, "01:21", "0.29M");
        adicionarRegistroJaneiro(registros, 3, "07:36", "2.10M");
        adicionarRegistroJaneiro(registros, 3, "13:32", "0.46M");
        adicionarRegistroJaneiro(registros, 3, "19:49", "2.32M");

        adicionarRegistroJaneiro(registros, 4, "02:06", "0.34M");
        adicionarRegistroJaneiro(registros, 4, "08:21", "2.07M");
        adicionarRegistroJaneiro(registros, 4, "14:19", "0.50M");
        adicionarRegistroJaneiro(registros, 4, "20:36", "2.26M");

        adicionarRegistroJaneiro(registros, 5, "02:57", "0.41M");
        adicionarRegistroJaneiro(registros, 5, "09:10", "2.02M");
        adicionarRegistroJaneiro(registros, 5, "15:12", "0.56M");
        adicionarRegistroJaneiro(registros, 5, "21:31", "2.18M");

        adicionarRegistroJaneiro(registros, 6, "03:51", "0.50M");
        adicionarRegistroJaneiro(registros, 6, "10:06", "1.97M");
        adicionarRegistroJaneiro(registros, 6, "16:10", "0.63M");
        adicionarRegistroJaneiro(registros, 6, "22:29", "2.08M");

        adicionarRegistroJaneiro(registros, 7, "04:53", "0.58M");
        adicionarRegistroJaneiro(registros, 7, "11:08", "1.93M");
        adicionarRegistroJaneiro(registros, 7, "17:21", "0.67M");
        adicionarRegistroJaneiro(registros, 7, "23:38", "1.99M");

        adicionarRegistroJaneiro(registros, 8, "05:59", "0.63M");
        adicionarRegistroJaneiro(registros, 8, "12:17", "1.92M");
        adicionarRegistroJaneiro(registros, 8, "18:36", "0.66M");

        adicionarRegistroJaneiro(registros, 9, "00:53", "1.94M");
        adicionarRegistroJaneiro(registros, 9, "07:10", "0.64M");
        adicionarRegistroJaneiro(registros, 9, "13:31", "1.96M");
        adicionarRegistroJaneiro(registros, 9, "19:53", "0.59M");

        adicionarRegistroJaneiro(registros, 10, "02:04", "1.94M");
        adicionarRegistroJaneiro(registros, 10, "08:16", "0.60M");
        adicionarRegistroJaneiro(registros, 10, "14:34", "2.04M");
        adicionarRegistroJaneiro(registros, 10, "20:59", "0.48M");

        adicionarRegistroJaneiro(registros, 11, "03:08", "1.98M");
        adicionarRegistroJaneiro(registros, 11, "09:16", "0.54M");
        adicionarRegistroJaneiro(registros, 11, "15:31", "2.13M");
        adicionarRegistroJaneiro(registros, 11, "21:53", "0.36M");

        adicionarRegistroJaneiro(registros, 12, "04:04", "2.04M");
        adicionarRegistroJaneiro(registros, 12, "10:04", "0.48M");
        adicionarRegistroJaneiro(registros, 12, "16:19", "2.23M");
        adicionarRegistroJaneiro(registros, 12, "22:42", "0.27M");

        adicionarRegistroJaneiro(registros, 13, "04:53", "2.10M");
        adicionarRegistroJaneiro(registros, 13, "10:49", "0.42M");
        adicionarRegistroJaneiro(registros, 13, "17:04", "2.31M");
        adicionarRegistroJaneiro(registros, 13, "23:25", "0.22M");

        adicionarRegistroJaneiro(registros, 14, "05:36", "2.13M");
        adicionarRegistroJaneiro(registros, 14, "11:31", "0.39M");
        adicionarRegistroJaneiro(registros, 14, "17:46", "2.35M");

        adicionarRegistroJaneiro(registros, 15, "00:04", "0.21M");
        adicionarRegistroJaneiro(registros, 15, "06:12", "2.14M");
        adicionarRegistroJaneiro(registros, 15, "12:08", "0.38M");
        adicionarRegistroJaneiro(registros, 15, "18:23", "2.36M");

        adicionarRegistroJaneiro(registros, 16, "00:42", "0.25M");
        adicionarRegistroJaneiro(registros, 16, "06:49", "2.13M");
        adicionarRegistroJaneiro(registros, 16, "12:46", "0.40M");
        adicionarRegistroJaneiro(registros, 16, "19:01", "2.34M");

        adicionarRegistroJaneiro(registros, 17, "01:17", "0.32M");
        adicionarRegistroJaneiro(registros, 17, "07:23", "2.09M");
        adicionarRegistroJaneiro(registros, 17, "13:17", "0.44M");
        adicionarRegistroJaneiro(registros, 17, "19:36", "2.27M");

        adicionarRegistroJaneiro(registros, 18, "01:53", "0.41M");
        adicionarRegistroJaneiro(registros, 18, "08:01", "2.05M");
        adicionarRegistroJaneiro(registros, 18, "13:59", "0.51M");
        adicionarRegistroJaneiro(registros, 18, "20:12", "2.18M");

        adicionarRegistroJaneiro(registros, 19, "02:27", "0.50M");
        adicionarRegistroJaneiro(registros, 19, "08:38", "1.99M");
        adicionarRegistroJaneiro(registros, 19, "14:40", "0.60M");
        adicionarRegistroJaneiro(registros, 19, "20:55", "2.06M");

        adicionarRegistroJaneiro(registros, 20, "03:08", "0.60M");
        adicionarRegistroJaneiro(registros, 20, "09:17", "1.92M");
        adicionarRegistroJaneiro(registros, 20, "15:25", "0.70M");
        adicionarRegistroJaneiro(registros, 20, "21:44", "1.93M");

        adicionarRegistroJaneiro(registros, 21, "03:57", "0.69M");
        adicionarRegistroJaneiro(registros, 21, "10:12", "1.84M");
        adicionarRegistroJaneiro(registros, 21, "16:21", "0.80M");
        adicionarRegistroJaneiro(registros, 21, "22:36", "1.80M");

        adicionarRegistroJaneiro(registros, 22, "04:55", "0.76M");
        adicionarRegistroJaneiro(registros, 22, "11:14", "1.78M");
        adicionarRegistroJaneiro(registros, 22, "17:38", "0.86M");
        adicionarRegistroJaneiro(registros, 22, "23:47", "1.70M");

        adicionarRegistroJaneiro(registros, 23, "06:02", "0.81M");
        adicionarRegistroJaneiro(registros, 23, "12:27", "1.77M");
        adicionarRegistroJaneiro(registros, 23, "18:55", "0.84M");

        adicionarRegistroJaneiro(registros, 24, "01:02", "1.68M");
        adicionarRegistroJaneiro(registros, 24, "07:14", "0.79M");
        adicionarRegistroJaneiro(registros, 24, "13:42", "1.81M");
        adicionarRegistroJaneiro(registros, 24, "20:06", "0.76M");

        adicionarRegistroJaneiro(registros, 25, "02:14", "1.73M");
        adicionarRegistroJaneiro(registros, 25, "08:17", "0.72M");
        adicionarRegistroJaneiro(registros, 25, "14:38", "1.90M");
        adicionarRegistroJaneiro(registros, 25, "21:02", "0.63M");

        adicionarRegistroJaneiro(registros, 26, "03:10", "1.83M");
        adicionarRegistroJaneiro(registros, 26, "09:12", "0.63M");
        adicionarRegistroJaneiro(registros, 26, "15:27", "2.02M");
        adicionarRegistroJaneiro(registros, 26, "21:49", "0.49M");

        adicionarRegistroJaneiro(registros, 27, "03:59", "1.95M");
        adicionarRegistroJaneiro(registros, 27, "09:59", "0.52M");
        adicionarRegistroJaneiro(registros, 27, "16:08", "2.15M");
        adicionarRegistroJaneiro(registros, 27, "22:29", "0.36M");

        adicionarRegistroJaneiro(registros, 28, "04:40", "2.08M");
        adicionarRegistroJaneiro(registros, 28, "10:38", "0.42M");
        adicionarRegistroJaneiro(registros, 28, "16:47", "2.29M");
        adicionarRegistroJaneiro(registros, 28, "23:06", "0.24M");

        adicionarRegistroJaneiro(registros, 29, "05:19", "2.19M");
        adicionarRegistroJaneiro(registros, 29, "11:17", "0.33M");
        adicionarRegistroJaneiro(registros, 29, "17:25", "2.42M");
        adicionarRegistroJaneiro(registros, 29, "23:44", "0.15M");

        adicionarRegistroJaneiro(registros, 30, "05:57", "2.28M");
        adicionarRegistroJaneiro(registros, 30, "11:57", "0.26M");
        adicionarRegistroJaneiro(registros, 30, "18:06", "2.51M");

        adicionarRegistroJaneiro(registros, 31, "00:23", "0.10M");
        adicionarRegistroJaneiro(registros, 31, "06:36", "2.33M");
        adicionarRegistroJaneiro(registros, 31, "12:36", "0.22M");
        adicionarRegistroJaneiro(registros, 31, "18:49", "2.55M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de janeiro de 2025", registros.size());
    }

    private void inserirDadosFevereiro2025() {
        if (dadosJaInseridosParaMes(2025, 2)) {
            log.info("Dados de maré de fevereiro de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroFevereiro(registros, 1, "01:04", "0.11M");
        adicionarRegistroFevereiro(registros, 1, "07:14", "2.32M");
        adicionarRegistroFevereiro(registros, 1, "13:16", "0.23M");
        adicionarRegistroFevereiro(registros, 1, "19:31", "2.52M");

        adicionarRegistroFevereiro(registros, 2, "01:47", "0.17M");
        adicionarRegistroFevereiro(registros, 2, "07:59", "2.27M");
        adicionarRegistroFevereiro(registros, 2, "14:01", "0.30M");
        adicionarRegistroFevereiro(registros, 2, "20:16", "2.42M");

        adicionarRegistroFevereiro(registros, 3, "02:31", "0.29M");
        adicionarRegistroFevereiro(registros, 3, "08:44", "2.17M");
        adicionarRegistroFevereiro(registros, 3, "14:49", "0.41M");
        adicionarRegistroFevereiro(registros, 3, "21:06", "2.25M");

        adicionarRegistroFevereiro(registros, 4, "03:21", "0.45M");
        adicionarRegistroFevereiro(registros, 4, "09:34", "2.04M");
        adicionarRegistroFevereiro(registros, 4, "15:46", "0.55M");
        adicionarRegistroFevereiro(registros, 4, "22:02", "2.05M");

        adicionarRegistroFevereiro(registros, 5, "04:17", "0.62M");
        adicionarRegistroFevereiro(registros, 5, "10:38", "1.91M");
        adicionarRegistroFevereiro(registros, 5, "16:57", "0.67M");
        adicionarRegistroFevereiro(registros, 5, "23:16", "1.86M");

        adicionarRegistroFevereiro(registros, 6, "05:31", "0.75M");
        adicionarRegistroFevereiro(registros, 6, "11:55", "1.83M");
        adicionarRegistroFevereiro(registros, 6, "18:25", "0.72M");

        adicionarRegistroFevereiro(registros, 7, "00:46", "1.76M");
        adicionarRegistroFevereiro(registros, 7, "07:01", "0.80M");
        adicionarRegistroFevereiro(registros, 7, "13:23", "1.83M");
        adicionarRegistroFevereiro(registros, 7, "19:57", "0.67M");

        adicionarRegistroFevereiro(registros, 8, "02:12", "1.77M");
        adicionarRegistroFevereiro(registros, 8, "08:19", "0.76M");
        adicionarRegistroFevereiro(registros, 8, "14:36", "1.92M");
        adicionarRegistroFevereiro(registros, 8, "21:04", "0.55M");

        adicionarRegistroFevereiro(registros, 9, "03:17", "1.87M");
        adicionarRegistroFevereiro(registros, 9, "09:21", "0.66M");
        adicionarRegistroFevereiro(registros, 9, "15:32", "2.05M");
        adicionarRegistroFevereiro(registros, 9, "21:57", "0.43M");

        adicionarRegistroFevereiro(registros, 10, "04:06", "1.97M");
        adicionarRegistroFevereiro(registros, 10, "10:04", "0.55M");
        adicionarRegistroFevereiro(registros, 10, "16:16", "2.18M");
        adicionarRegistroFevereiro(registros, 10, "22:38", "0.33M");

        adicionarRegistroFevereiro(registros, 11, "04:47", "2.06M");
        adicionarRegistroFevereiro(registros, 11, "10:42", "0.47M");
        adicionarRegistroFevereiro(registros, 11, "16:55", "2.28M");
        adicionarRegistroFevereiro(registros, 11, "23:10", "0.28M");

        adicionarRegistroFevereiro(registros, 12, "05:19", "2.13M");
        adicionarRegistroFevereiro(registros, 12, "11:16", "0.40M");
        adicionarRegistroFevereiro(registros, 12, "17:29", "2.35M");
        adicionarRegistroFevereiro(registros, 12, "23:46", "0.26M");

        adicionarRegistroFevereiro(registros, 13, "05:51", "2.18M");
        adicionarRegistroFevereiro(registros, 13, "11:47", "0.35M");
        adicionarRegistroFevereiro(registros, 13, "18:01", "2.38M");

        adicionarRegistroFevereiro(registros, 14, "00:14", "0.27M");
        adicionarRegistroFevereiro(registros, 14, "06:17", "2.20M");
        adicionarRegistroFevereiro(registros, 14, "12:16", "0.34M");
        adicionarRegistroFevereiro(registros, 14, "18:32", "2.37M");

        adicionarRegistroFevereiro(registros, 15, "00:44", "0.31M");
        adicionarRegistroFevereiro(registros, 15, "06:51", "2.19M");
        adicionarRegistroFevereiro(registros, 15, "12:51", "0.36M");
        adicionarRegistroFevereiro(registros, 15, "19:04", "2.32M");

        adicionarRegistroFevereiro(registros, 16, "01:14", "0.36M");
        adicionarRegistroFevereiro(registros, 16, "07:21", "2.16M");
        adicionarRegistroFevereiro(registros, 16, "13:23", "0.42M");
        adicionarRegistroFevereiro(registros, 16, "19:36", "2.22M");

        adicionarRegistroFevereiro(registros, 17, "01:47", "0.44M");
        adicionarRegistroFevereiro(registros, 17, "07:55", "2.09M");
        adicionarRegistroFevereiro(registros, 17, "13:59", "0.51M");
        adicionarRegistroFevereiro(registros, 17, "20:10", "2.08M");

        adicionarRegistroFevereiro(registros, 18, "02:19", "0.54M");
        adicionarRegistroFevereiro(registros, 18, "08:31", "2.00M");
        adicionarRegistroFevereiro(registros, 18, "14:38", "0.62M");
        adicionarRegistroFevereiro(registros, 18, "20:51", "1.92M");

        adicionarRegistroFevereiro(registros, 19, "03:01", "0.65M");
        adicionarRegistroFevereiro(registros, 19, "09:12", "1.89M");
        adicionarRegistroFevereiro(registros, 19, "15:23", "0.74M");
        adicionarRegistroFevereiro(registros, 19, "21:38", "1.76M");

        adicionarRegistroFevereiro(registros, 20, "03:55", "0.78M");
        adicionarRegistroFevereiro(registros, 20, "10:10", "1.77M");
        adicionarRegistroFevereiro(registros, 20, "16:27", "0.85M");
        adicionarRegistroFevereiro(registros, 20, "22:44", "1.63M");

        adicionarRegistroFevereiro(registros, 21, "05:01", "0.87M");
        adicionarRegistroFevereiro(registros, 21, "11:21", "1.70M");
        adicionarRegistroFevereiro(registros, 21, "17:59", "0.89M");

        adicionarRegistroFevereiro(registros, 22, "00:17", "1.58M");
        adicionarRegistroFevereiro(registros, 22, "06:32", "0.90M");
        adicionarRegistroFevereiro(registros, 22, "12:49", "1.71M");
        adicionarRegistroFevereiro(registros, 22, "19:32", "0.82M");

        adicionarRegistroFevereiro(registros, 23, "01:46", "1.65M");
        adicionarRegistroFevereiro(registros, 23, "07:49", "0.82M");
        adicionarRegistroFevereiro(registros, 23, "14:02", "1.82M");
        adicionarRegistroFevereiro(registros, 23, "20:34", "0.67M");

        adicionarRegistroFevereiro(registros, 24, "02:49", "1.80M");
        adicionarRegistroFevereiro(registros, 24, "08:49", "0.69M");
        adicionarRegistroFevereiro(registros, 24, "14:57", "1.99M");
        adicionarRegistroFevereiro(registros, 24, "21:19", "0.50M");

        adicionarRegistroFevereiro(registros, 25, "03:32", "1.98M");
        adicionarRegistroFevereiro(registros, 25, "09:34", "0.52M");
        adicionarRegistroFevereiro(registros, 25, "15:42", "2.19M");
        adicionarRegistroFevereiro(registros, 25, "22:02", "0.32M");

        adicionarRegistroFevereiro(registros, 26, "04:14", "2.17M");
        adicionarRegistroFevereiro(registros, 26, "10:14", "0.35M");
        adicionarRegistroFevereiro(registros, 26, "16:21", "2.40M");
        adicionarRegistroFevereiro(registros, 26, "22:42", "0.15M");

        adicionarRegistroFevereiro(registros, 27, "04:53", "2.33M");
        adicionarRegistroFevereiro(registros, 27, "10:55", "0.20M");
        adicionarRegistroFevereiro(registros, 27, "17:02", "2.57M");
        adicionarRegistroFevereiro(registros, 27, "23:19", "0.03M");

        adicionarRegistroFevereiro(registros, 28, "05:32", "2.45M");
        adicionarRegistroFevereiro(registros, 28, "11:34", "0.09M");
        adicionarRegistroFevereiro(registros, 28, "17:46", "2.68M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de fevereiro de 2025", registros.size());
    }

    private void inserirDadosMarco2025() {
        if (dadosJaInseridosParaMes(2025, 3)) {
            log.info("Dados de maré de março de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroMarco(registros, 1, "06:08", "2.51M");
        adicionarRegistroMarco(registros, 1, "12:12", "0.04M");
        adicionarRegistroMarco(registros, 1, "18:27", "2.70M");

        adicionarRegistroMarco(registros, 2, "00:40", "-0.01M");
        adicionarRegistroMarco(registros, 2, "06:51", "2.50M");
        adicionarRegistroMarco(registros, 2, "12:55", "0.06M");
        adicionarRegistroMarco(registros, 2, "19:08", "2.62M");

        adicionarRegistroMarco(registros, 3, "01:21", "0.09M");
        adicionarRegistroMarco(registros, 3, "07:32", "2.41M");
        adicionarRegistroMarco(registros, 3, "13:40", "0.15M");
        adicionarRegistroMarco(registros, 3, "19:57", "2.45M");

        adicionarRegistroMarco(registros, 4, "02:06", "0.25M");
        adicionarRegistroMarco(registros, 4, "08:17", "2.26M");
        adicionarRegistroMarco(registros, 4, "14:31", "0.31M");
        adicionarRegistroMarco(registros, 4, "20:47", "2.21M");

        adicionarRegistroMarco(registros, 5, "02:55", "0.47M");
        adicionarRegistroMarco(registros, 5, "09:08", "2.08M");
        adicionarRegistroMarco(registros, 5, "15:27", "0.50M");
        adicionarRegistroMarco(registros, 5, "21:49", "1.95M");

        adicionarRegistroMarco(registros, 6, "03:55", "0.69M");
        adicionarRegistroMarco(registros, 6, "10:16", "1.89M");
        adicionarRegistroMarco(registros, 6, "16:47", "0.67M");
        adicionarRegistroMarco(registros, 6, "23:08", "1.74M");

        adicionarRegistroMarco(registros, 7, "05:17", "0.85M");
        adicionarRegistroMarco(registros, 7, "11:44", "1.77M");
        adicionarRegistroMarco(registros, 7, "18:27", "0.74M");

        adicionarRegistroMarco(registros, 8, "00:53", "1.67M");
        adicionarRegistroMarco(registros, 8, "07:02", "0.88M");
        adicionarRegistroMarco(registros, 8, "13:21", "1.79M");
        adicionarRegistroMarco(registros, 8, "20:01", "0.67M");

        adicionarRegistroMarco(registros, 9, "02:19", "1.74M");
        adicionarRegistroMarco(registros, 9, "08:21", "0.80M");
        adicionarRegistroMarco(registros, 9, "14:31", "1.91M");
        adicionarRegistroMarco(registros, 9, "21:01", "0.56M");

        adicionarRegistroMarco(registros, 10, "03:12", "1.86M");
        adicionarRegistroMarco(registros, 10, "09:14", "0.68M");
        adicionarRegistroMarco(registros, 10, "15:23", "2.04M");
        adicionarRegistroMarco(registros, 10, "21:44", "0.46M");

        adicionarRegistroMarco(registros, 11, "03:55", "1.97M");
        adicionarRegistroMarco(registros, 11, "09:53", "0.57M");
        adicionarRegistroMarco(registros, 11, "16:02", "2.16M");
        adicionarRegistroMarco(registros, 11, "22:17", "0.39M");

        adicionarRegistroMarco(registros, 12, "04:27", "2.07M");
        adicionarRegistroMarco(registros, 12, "10:23", "0.48M");
        adicionarRegistroMarco(registros, 12, "16:36", "2.26M");
        adicionarRegistroMarco(registros, 12, "22:49", "0.34M");

        adicionarRegistroMarco(registros, 13, "04:55", "2.15M");
        adicionarRegistroMarco(registros, 13, "10:51", "0.40M");
        adicionarRegistroMarco(registros, 13, "17:04", "2.32M");
        adicionarRegistroMarco(registros, 13, "23:16", "0.31M");

        adicionarRegistroMarco(registros, 14, "05:21", "2.21M");
        adicionarRegistroMarco(registros, 14, "11:19", "0.35M");
        adicionarRegistroMarco(registros, 14, "17:34", "2.35M");
        adicionarRegistroMarco(registros, 14, "23:44", "0.30M");

        adicionarRegistroMarco(registros, 15, "05:51", "2.24M");
        adicionarRegistroMarco(registros, 15, "11:53", "0.32M");
        adicionarRegistroMarco(registros, 15, "18:02", "2.33M");

        adicionarRegistroMarco(registros, 16, "00:10", "0.32M");
        adicionarRegistroMarco(registros, 16, "06:17", "2.24M");
        adicionarRegistroMarco(registros, 16, "12:21", "0.34M");
        adicionarRegistroMarco(registros, 16, "18:34", "2.27M");

        adicionarRegistroMarco(registros, 17, "00:40", "0.36M");
        adicionarRegistroMarco(registros, 17, "06:47", "2.20M");
        adicionarRegistroMarco(registros, 17, "12:53", "0.39M");
        adicionarRegistroMarco(registros, 17, "19:04", "2.17M");

        adicionarRegistroMarco(registros, 18, "01:10", "0.43M");
        adicionarRegistroMarco(registros, 18, "07:17", "2.13M");
        adicionarRegistroMarco(registros, 18, "13:25", "0.47M");
        adicionarRegistroMarco(registros, 18, "19:36", "2.03M");

        adicionarRegistroMarco(registros, 19, "01:46", "0.53M");
        adicionarRegistroMarco(registros, 19, "07:57", "2.03M");
        adicionarRegistroMarco(registros, 19, "14:04", "0.58M");
        adicionarRegistroMarco(registros, 19, "20:14", "1.88M");

        adicionarRegistroMarco(registros, 20, "02:19", "0.66M");
        adicionarRegistroMarco(registros, 20, "08:34", "1.91M");
        adicionarRegistroMarco(registros, 20, "14:49", "0.70M");
        adicionarRegistroMarco(registros, 20, "21:01", "1.72M");

        adicionarRegistroMarco(registros, 21, "03:04", "0.80M");
        adicionarRegistroMarco(registros, 21, "09:23", "1.78M");
        adicionarRegistroMarco(registros, 21, "15:44", "0.81M");
        adicionarRegistroMarco(registros, 21, "22:02", "1.60M");

        adicionarRegistroMarco(registros, 22, "04:10", "0.91M");
        adicionarRegistroMarco(registros, 22, "10:36", "1.69M");
        adicionarRegistroMarco(registros, 22, "17:08", "0.87M");
        adicionarRegistroMarco(registros, 22, "23:40", "1.56M");

        adicionarRegistroMarco(registros, 23, "05:49", "0.95M");
        adicionarRegistroMarco(registros, 23, "12:02", "1.70M");
        adicionarRegistroMarco(registros, 23, "18:42", "0.82M");

        adicionarRegistroMarco(registros, 24, "01:06", "1.65M");
        adicionarRegistroMarco(registros, 24, "07:10", "0.86M");
        adicionarRegistroMarco(registros, 24, "13:21", "1.82M");
        adicionarRegistroMarco(registros, 24, "19:55", "0.67M");

        adicionarRegistroMarco(registros, 25, "02:12", "1.83M");
        adicionarRegistroMarco(registros, 25, "08:14", "0.70M");
        adicionarRegistroMarco(registros, 25, "14:17", "2.02M");
        adicionarRegistroMarco(registros, 25, "20:44", "0.48M");

        adicionarRegistroMarco(registros, 26, "03:01", "2.04M");
        adicionarRegistroMarco(registros, 26, "09:02", "0.50M");
        adicionarRegistroMarco(registros, 26, "15:08", "2.25M");
        adicionarRegistroMarco(registros, 26, "21:27", "0.28M");

        adicionarRegistroMarco(registros, 27, "03:42", "2.25M");
        adicionarRegistroMarco(registros, 27, "09:46", "0.29M");
        adicionarRegistroMarco(registros, 27, "15:55", "2.48M");
        adicionarRegistroMarco(registros, 27, "22:10", "0.10M");

        adicionarRegistroMarco(registros, 28, "04:21", "2.43M");
        adicionarRegistroMarco(registros, 28, "10:25", "0.12M");
        adicionarRegistroMarco(registros, 28, "16:36", "2.64M");
        adicionarRegistroMarco(registros, 28, "22:51", "-0.02M");

        adicionarRegistroMarco(registros, 29, "05:02", "2.55M");
        adicionarRegistroMarco(registros, 29, "11:08", "-0.01M");
        adicionarRegistroMarco(registros, 29, "17:19", "2.72M");
        adicionarRegistroMarco(registros, 29, "23:32", "-0.07M");

        adicionarRegistroMarco(registros, 30, "05:44", "2.60M");
        adicionarRegistroMarco(registros, 30, "11:51", "-0.06M");
        adicionarRegistroMarco(registros, 30, "18:04", "2.70M");

        adicionarRegistroMarco(registros, 31, "00:14", "-0.03M");
        adicionarRegistroMarco(registros, 31, "06:25", "2.57M");
        adicionarRegistroMarco(registros, 31, "12:36", "-0.02M");
        adicionarRegistroMarco(registros, 31, "18:51", "2.58M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de março de 2025", registros.size());
    }

    private void inserirDadosAbril2025() {
        if (dadosJaInseridosParaMes(2025, 4)) {
            log.info("Dados de maré de abril de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroAbril(registros, 1, "00:59", "0.10M");
        adicionarRegistroAbril(registros, 1, "07:10", "2.46M");
        adicionarRegistroAbril(registros, 1, "13:21", "0.09M");
        adicionarRegistroAbril(registros, 1, "19:38", "2.37M");

        adicionarRegistroAbril(registros, 2, "01:46", "0.29M");
        adicionarRegistroAbril(registros, 2, "07:59", "2.29M");
        adicionarRegistroAbril(registros, 2, "14:16", "0.27M");
        adicionarRegistroAbril(registros, 2, "20:34", "2.12M");

        adicionarRegistroAbril(registros, 3, "02:34", "0.52M");
        adicionarRegistroAbril(registros, 3, "08:53", "2.08M");
        adicionarRegistroAbril(registros, 3, "15:17", "0.48M");
        adicionarRegistroAbril(registros, 3, "21:40", "1.87M");

        adicionarRegistroAbril(registros, 4, "03:42", "0.74M");
        adicionarRegistroAbril(registros, 4, "10:02", "1.90M");
        adicionarRegistroAbril(registros, 4, "16:44", "0.64M");
        adicionarRegistroAbril(registros, 4, "23:06", "1.71M");

        adicionarRegistroAbril(registros, 5, "05:10", "0.88M");
        adicionarRegistroAbril(registros, 5, "11:31", "1.80M");
        adicionarRegistroAbril(registros, 5, "18:17", "0.69M");

        adicionarRegistroAbril(registros, 6, "00:42", "1.69M");
        adicionarRegistroAbril(registros, 6, "06:51", "0.88M");
        adicionarRegistroAbril(registros, 6, "13:02", "1.83M");
        adicionarRegistroAbril(registros, 6, "19:40", "0.65M");

        adicionarRegistroAbril(registros, 7, "01:57", "1.77M");
        adicionarRegistroAbril(registros, 7, "07:59", "0.80M");
        adicionarRegistroAbril(registros, 7, "14:06", "1.92M");
        adicionarRegistroAbril(registros, 7, "20:32", "0.58M");

        adicionarRegistroAbril(registros, 8, "02:47", "1.87M");
        adicionarRegistroAbril(registros, 8, "08:49", "0.70M");
        adicionarRegistroAbril(registros, 8, "14:55", "2.03M");
        adicionarRegistroAbril(registros, 8, "21:12", "0.52M");

        adicionarRegistroAbril(registros, 9, "03:23", "1.97M");
        adicionarRegistroAbril(registros, 9, "09:23", "0.60M");
        adicionarRegistroAbril(registros, 9, "15:34", "2.12M");
        adicionarRegistroAbril(registros, 9, "21:47", "0.46M");

        adicionarRegistroAbril(registros, 10, "03:53", "2.07M");
        adicionarRegistroAbril(registros, 10, "09:55", "0.51M");
        adicionarRegistroAbril(registros, 10, "16:04", "2.20M");
        adicionarRegistroAbril(registros, 10, "22:16", "0.41M");

        adicionarRegistroAbril(registros, 11, "04:21", "2.15M");
        adicionarRegistroAbril(registros, 11, "10:23", "0.43M");
        adicionarRegistroAbril(registros, 11, "16:36", "2.24M");
        adicionarRegistroAbril(registros, 11, "22:46", "0.37M");

        adicionarRegistroAbril(registros, 12, "04:53", "2.21M");
        adicionarRegistroAbril(registros, 12, "10:57", "0.38M");
        adicionarRegistroAbril(registros, 12, "17:04", "2.25M");
        adicionarRegistroAbril(registros, 12, "23:10", "0.35M");

        adicionarRegistroAbril(registros, 13, "05:19", "2.24M");
        adicionarRegistroAbril(registros, 13, "11:27", "0.35M");
        adicionarRegistroAbril(registros, 13, "17:36", "2.22M");
        adicionarRegistroAbril(registros, 13, "23:40", "0.36M");

        adicionarRegistroAbril(registros, 14, "05:49", "2.23M");
        adicionarRegistroAbril(registros, 14, "11:59", "0.36M");
        adicionarRegistroAbril(registros, 14, "18:06", "2.16M");

        adicionarRegistroAbril(registros, 15, "00:10", "0.40M");
        adicionarRegistroAbril(registros, 15, "06:17", "2.19M");
        adicionarRegistroAbril(registros, 15, "12:29", "0.40M");
        adicionarRegistroAbril(registros, 15, "18:42", "2.06M");

        adicionarRegistroAbril(registros, 16, "00:46", "0.48M");
        adicionarRegistroAbril(registros, 16, "06:53", "2.12M");
        adicionarRegistroAbril(registros, 16, "13:04", "0.47M");
        adicionarRegistroAbril(registros, 16, "19:14", "1.94M");

        adicionarRegistroAbril(registros, 17, "01:16", "0.58M");
        adicionarRegistroAbril(registros, 17, "07:27", "2.03M");
        adicionarRegistroAbril(registros, 17, "13:42", "0.56M");
        adicionarRegistroAbril(registros, 17, "19:51", "1.82M");

        adicionarRegistroAbril(registros, 18, "01:51", "0.69M");
        adicionarRegistroAbril(registros, 18, "08:04", "1.92M");
        adicionarRegistroAbril(registros, 18, "14:23", "0.67M");
        adicionarRegistroAbril(registros, 18, "20:38", "1.71M");

        adicionarRegistroAbril(registros, 19, "02:34", "0.81M");
        adicionarRegistroAbril(registros, 19, "08:53", "1.82M");
        adicionarRegistroAbril(registros, 19, "15:16", "0.76M");
        adicionarRegistroAbril(registros, 19, "21:38", "1.63M");

        adicionarRegistroAbril(registros, 20, "03:40", "0.91M");
        adicionarRegistroAbril(registros, 20, "10:01", "1.76M");
        adicionarRegistroAbril(registros, 20, "16:34", "0.81M");
        adicionarRegistroAbril(registros, 20, "23:04", "1.62M");

        adicionarRegistroAbril(registros, 21, "05:06", "0.93M");
        adicionarRegistroAbril(registros, 21, "11:19", "1.78M");
        adicionarRegistroAbril(registros, 21, "17:57", "0.77M");

        adicionarRegistroAbril(registros, 22, "00:25", "1.71M");
        adicionarRegistroAbril(registros, 22, "06:32", "0.85M");
        adicionarRegistroAbril(registros, 22, "12:40", "1.90M");
        adicionarRegistroAbril(registros, 22, "19:08", "0.64M");

        adicionarRegistroAbril(registros, 23, "01:29", "1.88M");
        adicionarRegistroAbril(registros, 23, "07:32", "0.68M");
        adicionarRegistroAbril(registros, 23, "13:40", "2.08M");
        adicionarRegistroAbril(registros, 23, "20:02", "0.46M");

        adicionarRegistroAbril(registros, 24, "02:19", "2.08M");
        adicionarRegistroAbril(registros, 24, "08:25", "0.48M");
        adicionarRegistroAbril(registros, 24, "14:32", "2.28M");
        adicionarRegistroAbril(registros, 24, "20:51", "0.28M");

        adicionarRegistroAbril(registros, 25, "03:04", "2.28M");
        adicionarRegistroAbril(registros, 25, "09:12", "0.28M");
        adicionarRegistroAbril(registros, 25, "15:21", "2.46M");
        adicionarRegistroAbril(registros, 25, "21:36", "0.12M");

        adicionarRegistroAbril(registros, 26, "03:49", "2.44M");
        adicionarRegistroAbril(registros, 26, "09:59", "0.10M");
        adicionarRegistroAbril(registros, 26, "16:08", "2.59M");
        adicionarRegistroAbril(registros, 26, "22:21", "0.02M");

        adicionarRegistroAbril(registros, 27, "04:34", "2.55M");
        adicionarRegistroAbril(registros, 27, "10:44", "-0.01M");
        adicionarRegistroAbril(registros, 27, "16:57", "2.62M");
        adicionarRegistroAbril(registros, 27, "23:04", "-0.00M");

        adicionarRegistroAbril(registros, 28, "05:17", "2.59M");
        adicionarRegistroAbril(registros, 28, "11:31", "-0.05M");
        adicionarRegistroAbril(registros, 28, "17:46", "2.57M");
        adicionarRegistroAbril(registros, 28, "23:51", "0.06M");

        adicionarRegistroAbril(registros, 29, "06:04", "2.55M");
        adicionarRegistroAbril(registros, 29, "12:17", "-0.01M");
        adicionarRegistroAbril(registros, 29, "18:34", "2.44M");

        adicionarRegistroAbril(registros, 30, "00:40", "0.18M");
        adicionarRegistroAbril(registros, 30, "06:53", "2.44M");
        adicionarRegistroAbril(registros, 30, "13:10", "0.10M");
        adicionarRegistroAbril(registros, 30, "19:27", "2.25M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de abril de 2025", registros.size());
    }

    private void inserirDadosMaio2025() {
        if (dadosJaInseridosParaMes(2025, 5)) {
            log.info("Dados de maré de maio de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroMaio(registros, 1, "01:27", "0.36M");
        adicionarRegistroMaio(registros, 1, "07:44", "2.29M");
        adicionarRegistroMaio(registros, 1, "14:06", "0.26M");
        adicionarRegistroMaio(registros, 1, "20:25", "2.05M");

        adicionarRegistroMaio(registros, 2, "02:21", "0.56M");
        adicionarRegistroMaio(registros, 2, "08:40", "2.12M");
        adicionarRegistroMaio(registros, 2, "15:10", "0.44M");
        adicionarRegistroMaio(registros, 2, "21:29", "1.87M");

        adicionarRegistroMaio(registros, 3, "03:27", "0.72M");
        adicionarRegistroMaio(registros, 3, "09:49", "1.97M");
        adicionarRegistroMaio(registros, 3, "16:25", "0.58M");
        adicionarRegistroMaio(registros, 3, "22:46", "1.76M");

        adicionarRegistroMaio(registros, 4, "04:42", "0.83M");
        adicionarRegistroMaio(registros, 4, "11:02", "1.89M");
        adicionarRegistroMaio(registros, 4, "17:44", "0.65M");

        adicionarRegistroMaio(registros, 5, "00:02", "1.74M");
        adicionarRegistroMaio(registros, 5, "06:04", "0.85M");
        adicionarRegistroMaio(registros, 5, "12:19", "1.88M");
        adicionarRegistroMaio(registros, 5, "18:53", "0.67M");

        adicionarRegistroMaio(registros, 6, "01:08", "1.78M");
        adicionarRegistroMaio(registros, 6, "07:08", "0.81M");
        adicionarRegistroMaio(registros, 6, "13:21", "1.92M");
        adicionarRegistroMaio(registros, 6, "19:47", "0.64M");

        adicionarRegistroMaio(registros, 7, "01:59", "1.86M");
        adicionarRegistroMaio(registros, 7, "08:01", "0.73M");
        adicionarRegistroMaio(registros, 7, "14:10", "1.98M");
        adicionarRegistroMaio(registros, 7, "20:27", "0.60M");

        adicionarRegistroMaio(registros, 8, "02:38", "1.95M");
        adicionarRegistroMaio(registros, 8, "08:44", "0.65M");
        adicionarRegistroMaio(registros, 8, "14:55", "2.04M");
        adicionarRegistroMaio(registros, 8, "21:04", "0.54M");

        adicionarRegistroMaio(registros, 9, "03:12", "2.04M");
        adicionarRegistroMaio(registros, 9, "09:19", "0.56M");
        adicionarRegistroMaio(registros, 9, "15:32", "2.08M");
        adicionarRegistroMaio(registros, 9, "21:40", "0.49M");

        adicionarRegistroMaio(registros, 10, "03:49", "2.11M");
        adicionarRegistroMaio(registros, 10, "09:57", "0.49M");
        adicionarRegistroMaio(registros, 10, "16:04", "2.11M");
        adicionarRegistroMaio(registros, 10, "22:10", "0.45M");

        adicionarRegistroMaio(registros, 11, "04:23", "2.17M");
        adicionarRegistroMaio(registros, 11, "10:31", "0.43M");
        adicionarRegistroMaio(registros, 11, "16:38", "2.11M");
        adicionarRegistroMaio(registros, 11, "22:42", "0.42M");

        adicionarRegistroMaio(registros, 12, "04:55", "2.19M");
        adicionarRegistroMaio(registros, 12, "11:06", "0.40M");
        adicionarRegistroMaio(registros, 12, "17:14", "2.08M");
        adicionarRegistroMaio(registros, 12, "23:14", "0.43M");

        adicionarRegistroMaio(registros, 13, "05:25", "2.19M");
        adicionarRegistroMaio(registros, 13, "11:44", "0.40M");
        adicionarRegistroMaio(registros, 13, "17:51", "2.04M");
        adicionarRegistroMaio(registros, 13, "23:51", "0.47M");

        adicionarRegistroMaio(registros, 14, "06:01", "2.16M");
        adicionarRegistroMaio(registros, 14, "12:14", "0.42M");
        adicionarRegistroMaio(registros, 14, "18:25", "1.97M");

        adicionarRegistroMaio(registros, 15, "00:25", "0.53M");
        adicionarRegistroMaio(registros, 15, "06:34", "2.10M");
        adicionarRegistroMaio(registros, 15, "12:49", "0.47M");
        adicionarRegistroMaio(registros, 15, "19:02", "1.89M");

        adicionarRegistroMaio(registros, 16, "01:01", "0.61M");
        adicionarRegistroMaio(registros, 16, "07:10", "2.04M");
        adicionarRegistroMaio(registros, 16, "13:27", "0.54M");
        adicionarRegistroMaio(registros, 16, "19:42", "1.82M");

        adicionarRegistroMaio(registros, 17, "01:38", "0.70M");
        adicionarRegistroMaio(registros, 17, "07:53", "1.98M");
        adicionarRegistroMaio(registros, 17, "14:12", "0.61M");
        adicionarRegistroMaio(registros, 17, "20:29", "1.76M");

        adicionarRegistroMaio(registros, 18, "02:23", "0.78M");
        adicionarRegistroMaio(registros, 18, "08:40", "1.92M");
        adicionarRegistroMaio(registros, 18, "15:04", "0.67M");
        adicionarRegistroMaio(registros, 18, "21:23", "1.73M");

        adicionarRegistroMaio(registros, 19, "03:23", "0.83M");
        adicionarRegistroMaio(registros, 19, "09:42", "1.90M");
        adicionarRegistroMaio(registros, 19, "16:08", "0.70M");
        adicionarRegistroMaio(registros, 19, "22:31", "1.74M");

        adicionarRegistroMaio(registros, 20, "04:31", "0.84M");
        adicionarRegistroMaio(registros, 20, "10:49", "1.92M");
        adicionarRegistroMaio(registros, 20, "17:16", "0.67M");
        adicionarRegistroMaio(registros, 20, "23:40", "1.81M");

        adicionarRegistroMaio(registros, 21, "05:46", "0.78M");
        adicionarRegistroMaio(registros, 21, "11:57", "1.99M");
        adicionarRegistroMaio(registros, 21, "18:23", "0.58M");

        adicionarRegistroMaio(registros, 22, "00:42", "1.94M");
        adicionarRegistroMaio(registros, 22, "06:51", "0.65M");
        adicionarRegistroMaio(registros, 22, "13:01", "2.11M");
        adicionarRegistroMaio(registros, 22, "19:21", "0.46M");

        adicionarRegistroMaio(registros, 23, "01:40", "2.09M");
        adicionarRegistroMaio(registros, 23, "07:49", "0.49M");
        adicionarRegistroMaio(registros, 23, "13:59", "2.24M");
        adicionarRegistroMaio(registros, 23, "20:14", "0.33M");

        adicionarRegistroMaio(registros, 24, "02:31", "2.24M");
        adicionarRegistroMaio(registros, 24, "08:44", "0.32M");
        adicionarRegistroMaio(registros, 24, "14:53", "2.34M");
        adicionarRegistroMaio(registros, 24, "21:06", "0.22M");

        adicionarRegistroMaio(registros, 25, "03:19", "2.37M");
        adicionarRegistroMaio(registros, 25, "09:34", "0.17M");
        adicionarRegistroMaio(registros, 25, "15:47", "2.41M");
        adicionarRegistroMaio(registros, 25, "21:57", "0.16M");

        adicionarRegistroMaio(registros, 26, "04:10", "2.46M");
        adicionarRegistroMaio(registros, 26, "10:25", "0.07M");
        adicionarRegistroMaio(registros, 26, "16:38", "2.42M");
        adicionarRegistroMaio(registros, 26, "22:46", "0.15M");

        adicionarRegistroMaio(registros, 27, "04:59", "2.50M");
        adicionarRegistroMaio(registros, 27, "11:16", "0.03M");
        adicionarRegistroMaio(registros, 27, "17:31", "2.38M");
        adicionarRegistroMaio(registros, 27, "23:32", "0.19M");

        adicionarRegistroMaio(registros, 28, "05:49", "2.48M");
        adicionarRegistroMaio(registros, 28, "12:08", "0.05M");
        adicionarRegistroMaio(registros, 28, "18:23", "2.29M");

        adicionarRegistroMaio(registros, 29, "00:23", "0.28M");
        adicionarRegistroMaio(registros, 29, "06:40", "2.41M");
        adicionarRegistroMaio(registros, 29, "13:02", "0.13M");
        adicionarRegistroMaio(registros, 29, "19:16", "2.17M");

        adicionarRegistroMaio(registros, 30, "01:12", "0.40M");
        adicionarRegistroMaio(registros, 30, "07:31", "2.31M");
        adicionarRegistroMaio(registros, 30, "13:57", "0.25M");
        adicionarRegistroMaio(registros, 30, "20:12", "2.05M");

        adicionarRegistroMaio(registros, 31, "02:06", "0.53M");
        adicionarRegistroMaio(registros, 31, "08:23", "2.19M");
        adicionarRegistroMaio(registros, 31, "14:53", "0.39M");
        adicionarRegistroMaio(registros, 31, "21:06", "1.93M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de maio de 2025", registros.size());
    }

    private void inserirDadosJunho2025() {
        if (dadosJaInseridosParaMes(2025, 6)) {
            log.info("Dados de maré de junho de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroJunho(registros, 1, "03:01", "0.65M");
        adicionarRegistroJunho(registros, 1, "09:21", "2.08M");
        adicionarRegistroJunho(registros, 1, "15:51", "0.53M");
        adicionarRegistroJunho(registros, 1, "22:04", "1.85M");

        adicionarRegistroJunho(registros, 2, "04:01", "0.74M");
        adicionarRegistroJunho(registros, 2, "10:19", "1.99M");
        adicionarRegistroJunho(registros, 2, "16:53", "0.63M");
        adicionarRegistroJunho(registros, 2, "23:04", "1.80M");

        adicionarRegistroJunho(registros, 3, "05:02", "0.80M");
        adicionarRegistroJunho(registros, 3, "11:21", "1.92M");
        adicionarRegistroJunho(registros, 3, "17:49", "0.69M");

        adicionarRegistroJunho(registros, 4, "00:02", "1.80M");
        adicionarRegistroJunho(registros, 4, "06:06", "0.81M");
        adicionarRegistroJunho(registros, 4, "12:23", "1.90M");
        adicionarRegistroJunho(registros, 4, "18:46", "0.70M");

        adicionarRegistroJunho(registros, 5, "00:57", "1.84M");
        adicionarRegistroJunho(registros, 5, "07:04", "0.77M");
        adicionarRegistroJunho(registros, 5, "13:17", "1.90M");
        adicionarRegistroJunho(registros, 5, "19:36", "0.68M");

        adicionarRegistroJunho(registros, 6, "01:49", "1.91M");
        adicionarRegistroJunho(registros, 6, "07:59", "0.71M");
        adicionarRegistroJunho(registros, 6, "14:08", "1.91M");
        adicionarRegistroJunho(registros, 6, "20:17", "0.63M");

        adicionarRegistroJunho(registros, 7, "02:34", "1.98M");
        adicionarRegistroJunho(registros, 7, "08:47", "0.64M");
        adicionarRegistroJunho(registros, 7, "14:55", "1.94M");
        adicionarRegistroJunho(registros, 7, "21:01", "0.57M");

        adicionarRegistroJunho(registros, 8, "03:14", "2.05M");
        adicionarRegistroJunho(registros, 8, "09:29", "0.56M");
        adicionarRegistroJunho(registros, 8, "15:38", "1.96M");
        adicionarRegistroJunho(registros, 8, "21:42", "0.53M");

        adicionarRegistroJunho(registros, 9, "03:57", "2.10M");
        adicionarRegistroJunho(registros, 9, "10:10", "0.49M");
        adicionarRegistroJunho(registros, 9, "16:16", "1.98M");
        adicionarRegistroJunho(registros, 9, "22:17", "0.49M");

        adicionarRegistroJunho(registros, 10, "04:34", "2.13M");
        adicionarRegistroJunho(registros, 10, "10:53", "0.44M");
        adicionarRegistroJunho(registros, 10, "16:59", "1.99M");
        adicionarRegistroJunho(registros, 10, "22:59", "0.49M");

        adicionarRegistroJunho(registros, 11, "05:08", "2.15M");
        adicionarRegistroJunho(registros, 11, "11:29", "0.41M");
        adicionarRegistroJunho(registros, 11, "17:40", "1.98M");
        adicionarRegistroJunho(registros, 11, "23:34", "0.51M");

        adicionarRegistroJunho(registros, 12, "05:46", "2.15M");
        adicionarRegistroJunho(registros, 12, "12:04", "0.41M");
        adicionarRegistroJunho(registros, 12, "18:16", "1.96M");

        adicionarRegistroJunho(registros, 13, "00:12", "0.54M");
        adicionarRegistroJunho(registros, 13, "06:21", "2.14M");
        adicionarRegistroJunho(registros, 13, "12:40", "0.43M");
        adicionarRegistroJunho(registros, 13, "18:53", "1.94M");

        adicionarRegistroJunho(registros, 14, "00:51", "0.58M");
        adicionarRegistroJunho(registros, 14, "07:01", "2.13M");
        adicionarRegistroJunho(registros, 14, "13:19", "0.46M");
        adicionarRegistroJunho(registros, 14, "19:32", "1.91M");

        adicionarRegistroJunho(registros, 15, "01:29", "0.63M");
        adicionarRegistroJunho(registros, 15, "07:44", "2.11M");
        adicionarRegistroJunho(registros, 15, "14:02", "0.50M");
        adicionarRegistroJunho(registros, 15, "20:17", "1.89M");

        adicionarRegistroJunho(registros, 16, "02:12", "0.67M");
        adicionarRegistroJunho(registros, 16, "08:27", "2.09M");
        adicionarRegistroJunho(registros, 16, "14:51", "0.54M");
        adicionarRegistroJunho(registros, 16, "21:04", "1.88M");

        adicionarRegistroJunho(registros, 17, "03:02", "0.70M");
        adicionarRegistroJunho(registros, 17, "09:19", "2.07M");
        adicionarRegistroJunho(registros, 17, "15:44", "0.57M");
        adicionarRegistroJunho(registros, 17, "22:01", "1.88M");

        adicionarRegistroJunho(registros, 18, "04:01", "0.71M");
        adicionarRegistroJunho(registros, 18, "10:16", "2.05M");
        adicionarRegistroJunho(registros, 18, "16:44", "0.58M");
        adicionarRegistroJunho(registros, 18, "23:01", "1.91M");

        adicionarRegistroJunho(registros, 19, "05:04", "0.69M");
        adicionarRegistroJunho(registros, 19, "11:19", "2.05M");
        adicionarRegistroJunho(registros, 19, "17:44", "0.56M");

        adicionarRegistroJunho(registros, 20, "00:02", "1.96M");
        adicionarRegistroJunho(registros, 20, "06:12", "0.63M");
        adicionarRegistroJunho(registros, 20, "12:27", "2.06M");
        adicionarRegistroJunho(registros, 20, "18:47", "0.52M");

        adicionarRegistroJunho(registros, 21, "01:04", "2.04M");
        adicionarRegistroJunho(registros, 21, "07:17", "0.54M");
        adicionarRegistroJunho(registros, 21, "13:31", "2.10M");
        adicionarRegistroJunho(registros, 21, "19:47", "0.46M");

        adicionarRegistroJunho(registros, 22, "02:04", "2.13M");
        adicionarRegistroJunho(registros, 22, "08:23", "0.42M");
        adicionarRegistroJunho(registros, 22, "14:34", "2.14M");
        adicionarRegistroJunho(registros, 22, "20:46", "0.40M");

        adicionarRegistroJunho(registros, 23, "03:01", "2.23M");
        adicionarRegistroJunho(registros, 23, "09:19", "0.29M");
        adicionarRegistroJunho(registros, 23, "15:34", "2.19M");
        adicionarRegistroJunho(registros, 23, "21:42", "0.35M");

        adicionarRegistroJunho(registros, 24, "03:57", "2.32M");
        adicionarRegistroJunho(registros, 24, "10:16", "0.19M");
        adicionarRegistroJunho(registros, 24, "16:29", "2.22M");
        adicionarRegistroJunho(registros, 24, "22:32", "0.31M");

        adicionarRegistroJunho(registros, 25, "04:49", "2.39M");
        adicionarRegistroJunho(registros, 25, "11:10", "0.13M");
        adicionarRegistroJunho(registros, 25, "17:23", "2.23M");
        adicionarRegistroJunho(registros, 25, "23:21", "0.31M");

        adicionarRegistroJunho(registros, 26, "05:38", "2.41M");
        adicionarRegistroJunho(registros, 26, "12:01", "0.11M");
        adicionarRegistroJunho(registros, 26, "18:12", "2.21M");

        adicionarRegistroJunho(registros, 27, "00:10", "0.33M");
        adicionarRegistroJunho(registros, 27, "06:27", "2.41M");
        adicionarRegistroJunho(registros, 27, "12:49", "0.16M");
        adicionarRegistroJunho(registros, 27, "19:01", "2.16M");

        adicionarRegistroJunho(registros, 28, "00:57", "0.39M");
        adicionarRegistroJunho(registros, 28, "07:14", "2.36M");
        adicionarRegistroJunho(registros, 28, "13:36", "0.25M");
        adicionarRegistroJunho(registros, 28, "19:47", "2.09M");

        adicionarRegistroJunho(registros, 29, "01:40", "0.46M");
        adicionarRegistroJunho(registros, 29, "07:59", "2.28M");
        adicionarRegistroJunho(registros, 29, "14:19", "0.37M");
        adicionarRegistroJunho(registros, 29, "20:31", "2.02M");

        adicionarRegistroJunho(registros, 30, "02:25", "0.55M");
        adicionarRegistroJunho(registros, 30, "08:44", "2.18M");
        adicionarRegistroJunho(registros, 30, "15:04", "0.49M");
        adicionarRegistroJunho(registros, 30, "21:14", "1.94M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de junho de 2025", registros.size());
    }

    private void inserirDadosJulho2025() {
        if (dadosJaInseridosParaMes(2025, 7)) {
            log.info("Dados de maré de julho de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroJulho(registros, 1, "03:10", "0.64M");
        adicionarRegistroJulho(registros, 1, "09:31", "2.07M");
        adicionarRegistroJulho(registros, 1, "15:51", "0.60M");
        adicionarRegistroJulho(registros, 1, "22:01", "1.88M");

        adicionarRegistroJulho(registros, 2, "04:02", "0.72M");
        adicionarRegistroJulho(registros, 2, "10:17", "1.96M");
        adicionarRegistroJulho(registros, 2, "16:40", "0.69M");
        adicionarRegistroJulho(registros, 2, "22:55", "1.84M");

        adicionarRegistroJulho(registros, 3, "05:01", "0.78M");
        adicionarRegistroJulho(registros, 3, "11:14", "1.86M");
        adicionarRegistroJulho(registros, 3, "17:32", "0.74M");
        adicionarRegistroJulho(registros, 3, "23:53", "1.83M");

        adicionarRegistroJulho(registros, 4, "06:04", "0.80M");
        adicionarRegistroJulho(registros, 4, "12:17", "1.80M");
        adicionarRegistroJulho(registros, 4, "18:34", "0.75M");

        adicionarRegistroJulho(registros, 5, "00:55", "1.84M");
        adicionarRegistroJulho(registros, 5, "07:12", "0.78M");
        adicionarRegistroJulho(registros, 5, "13:19", "1.78M");
        adicionarRegistroJulho(registros, 5, "19:32", "0.71M");

        adicionarRegistroJulho(registros, 6, "01:55", "1.90M");
        adicionarRegistroJulho(registros, 6, "08:14", "0.71M");
        adicionarRegistroJulho(registros, 6, "14:21", "1.80M");
        adicionarRegistroJulho(registros, 6, "20:27", "0.66M");

        adicionarRegistroJulho(registros, 7, "02:47", "1.96M");
        adicionarRegistroJulho(registros, 7, "09:06", "0.61M");
        adicionarRegistroJulho(registros, 7, "15:14", "1.85M");
        adicionarRegistroJulho(registros, 7, "21:17", "0.60M");

        adicionarRegistroJulho(registros, 8, "03:34", "2.03M");
        adicionarRegistroJulho(registros, 8, "09:55", "0.52M");
        adicionarRegistroJulho(registros, 8, "16:02", "1.91M");
        adicionarRegistroJulho(registros, 8, "22:02", "0.55M");

        adicionarRegistroJulho(registros, 9, "04:16", "2.10M");
        adicionarRegistroJulho(registros, 9, "10:38", "0.44M");
        adicionarRegistroJulho(registros, 9, "16:46", "1.97M");
        adicionarRegistroJulho(registros, 9, "22:42", "0.51M");

        adicionarRegistroJulho(registros, 10, "04:53", "2.16M");
        adicionarRegistroJulho(registros, 10, "11:14", "0.38M");
        adicionarRegistroJulho(registros, 10, "17:25", "2.02M");
        adicionarRegistroJulho(registros, 10, "23:19", "0.48M");

        adicionarRegistroJulho(registros, 11, "05:29", "2.22M");
        adicionarRegistroJulho(registros, 11, "11:49", "0.34M");
        adicionarRegistroJulho(registros, 11, "18:02", "2.06M");
        adicionarRegistroJulho(registros, 11, "23:59", "0.46M");

        adicionarRegistroJulho(registros, 12, "06:06", "2.27M");
        adicionarRegistroJulho(registros, 12, "12:25", "0.32M");
        adicionarRegistroJulho(registros, 12, "18:38", "2.08M");

        adicionarRegistroJulho(registros, 13, "00:38", "0.46M");
        adicionarRegistroJulho(registros, 13, "06:47", "2.30M");
        adicionarRegistroJulho(registros, 13, "13:04", "0.32M");
        adicionarRegistroJulho(registros, 13, "19:16", "2.09M");

        adicionarRegistroJulho(registros, 14, "01:12", "0.46M");
        adicionarRegistroJulho(registros, 14, "07:25", "2.30M");
        adicionarRegistroJulho(registros, 14, "13:46", "0.35M");
        adicionarRegistroJulho(registros, 14, "19:57", "2.08M");

        adicionarRegistroJulho(registros, 15, "01:55", "0.49M");
        adicionarRegistroJulho(registros, 15, "08:08", "2.27M");
        adicionarRegistroJulho(registros, 15, "14:27", "0.40M");
        adicionarRegistroJulho(registros, 15, "20:40", "2.05M");

        adicionarRegistroJulho(registros, 16, "02:40", "0.53M");
        adicionarRegistroJulho(registros, 16, "08:57", "2.21M");
        adicionarRegistroJulho(registros, 16, "15:14", "0.47M");
        adicionarRegistroJulho(registros, 16, "21:27", "2.01M");

        adicionarRegistroJulho(registros, 17, "03:31", "0.58M");
        adicionarRegistroJulho(registros, 17, "09:51", "2.12M");
        adicionarRegistroJulho(registros, 17, "16:08", "0.54M");
        adicionarRegistroJulho(registros, 17, "22:23", "1.96M");

        adicionarRegistroJulho(registros, 18, "04:32", "0.63M");
        adicionarRegistroJulho(registros, 18, "10:51", "2.01M");
        adicionarRegistroJulho(registros, 18, "17:08", "0.61M");
        adicionarRegistroJulho(registros, 18, "23:27", "1.93M");

        adicionarRegistroJulho(registros, 19, "05:47", "0.65M");
        adicionarRegistroJulho(registros, 19, "12:02", "1.93M");
        adicionarRegistroJulho(registros, 19, "18:19", "0.65M");

        adicionarRegistroJulho(registros, 20, "00:40", "1.93M");
        adicionarRegistroJulho(registros, 20, "07:04", "0.62M");
        adicionarRegistroJulho(registros, 20, "13:17", "1.91M");
        adicionarRegistroJulho(registros, 20, "19:32", "0.63M");

        adicionarRegistroJulho(registros, 21, "01:53", "2.00M");
        adicionarRegistroJulho(registros, 21, "08:19", "0.52M");
        adicionarRegistroJulho(registros, 21, "14:32", "1.94M");
        adicionarRegistroJulho(registros, 21, "20:40", "0.58M");

        adicionarRegistroJulho(registros, 22, "02:57", "2.10M");
        adicionarRegistroJulho(registros, 22, "09:21", "0.39M");
        adicionarRegistroJulho(registros, 22, "15:36", "2.02M");
        adicionarRegistroJulho(registros, 22, "21:40", "0.49M");

        adicionarRegistroJulho(registros, 23, "03:55", "2.22M");
        adicionarRegistroJulho(registros, 23, "10:16", "0.28M");
        adicionarRegistroJulho(registros, 23, "16:29", "2.10M");
        adicionarRegistroJulho(registros, 23, "22:27", "0.41M");

        adicionarRegistroJulho(registros, 24, "04:44", "2.32M");
        adicionarRegistroJulho(registros, 24, "11:04", "0.20M");
        adicionarRegistroJulho(registros, 24, "17:16", "2.17M");
        adicionarRegistroJulho(registros, 24, "23:12", "0.36M");

        adicionarRegistroJulho(registros, 25, "05:27", "2.39M");
        adicionarRegistroJulho(registros, 25, "11:47", "0.17M");
        adicionarRegistroJulho(registros, 25, "17:59", "2.20M");
        adicionarRegistroJulho(registros, 25, "23:53", "0.33M");

        adicionarRegistroJulho(registros, 26, "06:08", "2.42M");
        adicionarRegistroJulho(registros, 26, "12:27", "0.20M");
        adicionarRegistroJulho(registros, 26, "18:34", "2.19M");

        adicionarRegistroJulho(registros, 27, "00:31", "0.34M");
        adicionarRegistroJulho(registros, 27, "06:49", "2.40M");
        adicionarRegistroJulho(registros, 27, "13:04", "0.26M");
        adicionarRegistroJulho(registros, 27, "19:10", "2.16M");

        adicionarRegistroJulho(registros, 28, "01:06", "0.38M");
        adicionarRegistroJulho(registros, 28, "07:23", "2.34M");
        adicionarRegistroJulho(registros, 28, "13:42", "0.36M");
        adicionarRegistroJulho(registros, 28, "19:49", "2.10M");

        adicionarRegistroJulho(registros, 29, "01:46", "0.46M");
        adicionarRegistroJulho(registros, 29, "08:02", "2.23M");
        adicionarRegistroJulho(registros, 29, "14:14", "0.46M");
        adicionarRegistroJulho(registros, 29, "20:25", "2.04M");

        adicionarRegistroJulho(registros, 30, "02:25", "0.55M");
        adicionarRegistroJulho(registros, 30, "08:42", "2.10M");
        adicionarRegistroJulho(registros, 30, "14:53", "0.57M");
        adicionarRegistroJulho(registros, 30, "21:04", "1.96M");

        adicionarRegistroJulho(registros, 31, "03:08", "0.65M");
        adicionarRegistroJulho(registros, 31, "09:25", "1.95M");
        adicionarRegistroJulho(registros, 31, "15:38", "0.67M");
        adicionarRegistroJulho(registros, 31, "21:51", "1.88M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de julho de 2025", registros.size());
    }

    private void inserirDadosAgosto2025() {
        if (dadosJaInseridosParaMes(2025, 8)) {
            log.info("Dados de maré de agosto de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroAgosto(registros, 1, "04:01", "0.75M");
        adicionarRegistroAgosto(registros, 1, "10:14", "1.81M");
        adicionarRegistroAgosto(registros, 1, "16:29", "0.75M");
        adicionarRegistroAgosto(registros, 1, "22:51", "1.81M");

        adicionarRegistroAgosto(registros, 2, "05:12", "0.83M");
        adicionarRegistroAgosto(registros, 2, "11:21", "1.70M");
        adicionarRegistroAgosto(registros, 2, "17:34", "0.81M");

        adicionarRegistroAgosto(registros, 3, "00:01", "1.77M");
        adicionarRegistroAgosto(registros, 3, "06:31", "0.84M");
        adicionarRegistroAgosto(registros, 3, "12:42", "1.66M");
        adicionarRegistroAgosto(registros, 3, "18:53", "0.81M");

        adicionarRegistroAgosto(registros, 4, "01:17", "1.80M");
        adicionarRegistroAgosto(registros, 4, "07:49", "0.77M");
        adicionarRegistroAgosto(registros, 4, "13:55", "1.70M");
        adicionarRegistroAgosto(registros, 4, "20:01", "0.75M");

        adicionarRegistroAgosto(registros, 5, "02:19", "1.87M");
        adicionarRegistroAgosto(registros, 5, "08:47", "0.66M");
        adicionarRegistroAgosto(registros, 5, "14:57", "1.80M");
        adicionarRegistroAgosto(registros, 5, "20:57", "0.66M");

        adicionarRegistroAgosto(registros, 6, "03:12", "1.98M");
        adicionarRegistroAgosto(registros, 6, "09:32", "0.54M");
        adicionarRegistroAgosto(registros, 6, "15:44", "1.91M");
        adicionarRegistroAgosto(registros, 6, "21:44", "0.57M");

        adicionarRegistroAgosto(registros, 7, "03:53", "2.10M");
        adicionarRegistroAgosto(registros, 7, "10:14", "0.42M");
        adicionarRegistroAgosto(registros, 7, "16:23", "2.03M");
        adicionarRegistroAgosto(registros, 7, "22:21", "0.47M");

        adicionarRegistroAgosto(registros, 8, "04:31", "2.23M");
        adicionarRegistroAgosto(registros, 8, "10:51", "0.31M");
        adicionarRegistroAgosto(registros, 8, "17:02", "2.14M");
        adicionarRegistroAgosto(registros, 8, "23:01", "0.39M");

        adicionarRegistroAgosto(registros, 9, "05:06", "2.35M");
        adicionarRegistroAgosto(registros, 9, "11:25", "0.23M");
        adicionarRegistroAgosto(registros, 9, "17:38", "2.22M");
        adicionarRegistroAgosto(registros, 9, "23:38", "0.32M");

        adicionarRegistroAgosto(registros, 10, "05:46", "2.44M");
        adicionarRegistroAgosto(registros, 10, "12:02", "0.18M");
        adicionarRegistroAgosto(registros, 10, "18:12", "2.28M");

        adicionarRegistroAgosto(registros, 11, "00:14", "0.27M");
        adicionarRegistroAgosto(registros, 11, "06:25", "2.49M");
        adicionarRegistroAgosto(registros, 11, "12:40", "0.17M");
        adicionarRegistroAgosto(registros, 11, "18:49", "2.29M");

        adicionarRegistroAgosto(registros, 12, "00:51", "0.26M");
        adicionarRegistroAgosto(registros, 12, "07:04", "2.48M");
        adicionarRegistroAgosto(registros, 12, "13:17", "0.20M");
        adicionarRegistroAgosto(registros, 12, "19:29", "2.26M");

        adicionarRegistroAgosto(registros, 13, "01:31", "0.30M");
        adicionarRegistroAgosto(registros, 13, "07:47", "2.41M");
        adicionarRegistroAgosto(registros, 13, "14:01", "0.29M");
        adicionarRegistroAgosto(registros, 13, "20:12", "2.20M");

        adicionarRegistroAgosto(registros, 14, "02:17", "0.38M");
        adicionarRegistroAgosto(registros, 14, "08:34", "2.26M");
        adicionarRegistroAgosto(registros, 14, "14:47", "0.42M");
        adicionarRegistroAgosto(registros, 14, "21:01", "2.09M");

        adicionarRegistroAgosto(registros, 15, "03:08", "0.49M");
        adicionarRegistroAgosto(registros, 15, "09:27", "2.08M");
        adicionarRegistroAgosto(registros, 15, "15:40", "0.57M");
        adicionarRegistroAgosto(registros, 15, "21:57", "1.96M");

        adicionarRegistroAgosto(registros, 16, "04:12", "0.62M");
        adicionarRegistroAgosto(registros, 16, "10:32", "1.90M");
        adicionarRegistroAgosto(registros, 16, "16:46", "0.71M");
        adicionarRegistroAgosto(registros, 16, "23:06", "1.86M");

        adicionarRegistroAgosto(registros, 17, "05:40", "0.69M");
        adicionarRegistroAgosto(registros, 17, "12:01", "1.78M");
        adicionarRegistroAgosto(registros, 17, "18:12", "0.79M");

        adicionarRegistroAgosto(registros, 18, "00:34", "1.83M");
        adicionarRegistroAgosto(registros, 18, "07:12", "0.67M");
        adicionarRegistroAgosto(registros, 18, "13:29", "1.77M");
        adicionarRegistroAgosto(registros, 18, "19:40", "0.76M");

        adicionarRegistroAgosto(registros, 19, "01:59", "1.92M");
        adicionarRegistroAgosto(registros, 19, "08:29", "0.56M");
        adicionarRegistroAgosto(registros, 19, "14:46", "1.86M");
        adicionarRegistroAgosto(registros, 19, "20:49", "0.66M");

        adicionarRegistroAgosto(registros, 20, "03:01", "2.05M");
        adicionarRegistroAgosto(registros, 20, "09:25", "0.42M");
        adicionarRegistroAgosto(registros, 20, "15:38", "1.98M");
        adicionarRegistroAgosto(registros, 20, "21:40", "0.54M");

        adicionarRegistroAgosto(registros, 21, "03:51", "2.19M");
        adicionarRegistroAgosto(registros, 21, "10:12", "0.32M");
        adicionarRegistroAgosto(registros, 21, "16:21", "2.09M");
        adicionarRegistroAgosto(registros, 21, "22:19", "0.44M");

        adicionarRegistroAgosto(registros, 22, "04:32", "2.31M");
        adicionarRegistroAgosto(registros, 22, "10:51", "0.25M");
        adicionarRegistroAgosto(registros, 22, "16:59", "2.17M");
        adicionarRegistroAgosto(registros, 22, "22:57", "0.37M");

        adicionarRegistroAgosto(registros, 23, "05:08", "2.39M");
        adicionarRegistroAgosto(registros, 23, "11:25", "0.23M");
        adicionarRegistroAgosto(registros, 23, "17:32", "2.22M");
        adicionarRegistroAgosto(registros, 23, "23:29", "0.32M");

        adicionarRegistroAgosto(registros, 24, "05:44", "2.41M");
        adicionarRegistroAgosto(registros, 24, "11:59", "0.24M");
        adicionarRegistroAgosto(registros, 24, "18:02", "2.23M");

        adicionarRegistroAgosto(registros, 25, "00:02", "0.31M");
        adicionarRegistroAgosto(registros, 25, "06:16", "2.39M");
        adicionarRegistroAgosto(registros, 25, "12:27", "0.29M");
        adicionarRegistroAgosto(registros, 25, "18:34", "2.22M");

        adicionarRegistroAgosto(registros, 26, "00:34", "0.34M");
        adicionarRegistroAgosto(registros, 26, "06:51", "2.33M");
        adicionarRegistroAgosto(registros, 26, "12:59", "0.35M");
        adicionarRegistroAgosto(registros, 26, "19:04", "2.18M");

        adicionarRegistroAgosto(registros, 27, "01:06", "0.39M");
        adicionarRegistroAgosto(registros, 27, "07:19", "2.22M");
        adicionarRegistroAgosto(registros, 27, "13:29", "0.44M");
        adicionarRegistroAgosto(registros, 27, "19:38", "2.11M");

        adicionarRegistroAgosto(registros, 28, "01:44", "0.48M");
        adicionarRegistroAgosto(registros, 28, "07:57", "2.08M");
        adicionarRegistroAgosto(registros, 28, "14:04", "0.53M");
        adicionarRegistroAgosto(registros, 28, "20:14", "2.02M");

        adicionarRegistroAgosto(registros, 29, "02:23", "0.60M");
        adicionarRegistroAgosto(registros, 29, "08:38", "1.92M");
        adicionarRegistroAgosto(registros, 29, "14:46", "0.65M");
        adicionarRegistroAgosto(registros, 29, "20:59", "1.91M");

        adicionarRegistroAgosto(registros, 30, "03:10", "0.72M");
        adicionarRegistroAgosto(registros, 30, "09:27", "1.76M");
        adicionarRegistroAgosto(registros, 30, "15:38", "0.77M");
        adicionarRegistroAgosto(registros, 30, "21:57", "1.79M");

        adicionarRegistroAgosto(registros, 31, "04:16", "0.83M");
        adicionarRegistroAgosto(registros, 31, "10:34", "1.63M");
        adicionarRegistroAgosto(registros, 31, "16:47", "0.87M");
        adicionarRegistroAgosto(registros, 31, "23:06", "1.71M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de agosto de 2025", registros.size());
    }

    private void inserirDadosSetembro2025() {
        if (dadosJaInseridosParaMes(2025, 9)) {
            log.info("Dados de maré de setembro de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroSetembro(registros, 1, "05:47", "0.87M");
        adicionarRegistroSetembro(registros, 1, "12:04", "1.58M");
        adicionarRegistroSetembro(registros, 1, "18:12", "0.90M");

        adicionarRegistroSetembro(registros, 2, "00:34", "1.72M");
        adicionarRegistroSetembro(registros, 2, "07:14", "0.81M");
        adicionarRegistroSetembro(registros, 2, "13:27", "1.65M");
        adicionarRegistroSetembro(registros, 2, "19:31", "0.83M");

        adicionarRegistroSetembro(registros, 3, "01:47", "1.82M");
        adicionarRegistroSetembro(registros, 3, "08:16", "0.69M");
        adicionarRegistroSetembro(registros, 3, "14:31", "1.79M");
        adicionarRegistroSetembro(registros, 3, "20:31", "0.71M");

        adicionarRegistroSetembro(registros, 4, "02:40", "1.97M");
        adicionarRegistroSetembro(registros, 4, "09:02", "0.53M");
        adicionarRegistroSetembro(registros, 4, "15:14", "1.95M");
        adicionarRegistroSetembro(registros, 4, "21:16", "0.56M");

        adicionarRegistroSetembro(registros, 5, "03:23", "2.14M");
        adicionarRegistroSetembro(registros, 5, "09:44", "0.38M");
        adicionarRegistroSetembro(registros, 5, "15:55", "2.12M");
        adicionarRegistroSetembro(registros, 5, "21:57", "0.41M");

        adicionarRegistroSetembro(registros, 6, "04:02", "2.32M");
        adicionarRegistroSetembro(registros, 6, "10:19", "0.24M");
        adicionarRegistroSetembro(registros, 6, "16:32", "2.26M");
        adicionarRegistroSetembro(registros, 6, "22:32", "0.28M");

        adicionarRegistroSetembro(registros, 7, "04:40", "2.48M");
        adicionarRegistroSetembro(registros, 7, "10:57", "0.12M");
        adicionarRegistroSetembro(registros, 7, "17:06", "2.38M");
        adicionarRegistroSetembro(registros, 7, "23:08", "0.17M");

        adicionarRegistroSetembro(registros, 8, "05:17", "2.59M");
        adicionarRegistroSetembro(registros, 8, "11:32", "0.05M");
        adicionarRegistroSetembro(registros, 8, "17:44", "2.45M");
        adicionarRegistroSetembro(registros, 8, "23:47", "0.10M");

        adicionarRegistroSetembro(registros, 9, "05:59", "2.63M");
        adicionarRegistroSetembro(registros, 9, "12:10", "0.04M");
        adicionarRegistroSetembro(registros, 9, "18:19", "2.46M");

        adicionarRegistroSetembro(registros, 10, "00:27", "0.09M");
        adicionarRegistroSetembro(registros, 10, "06:42", "2.58M");
        adicionarRegistroSetembro(registros, 10, "12:53", "0.11M");
        adicionarRegistroSetembro(registros, 10, "19:02", "2.41M");

        adicionarRegistroSetembro(registros, 11, "01:10", "0.15M");
        adicionarRegistroSetembro(registros, 11, "07:25", "2.44M");
        adicionarRegistroSetembro(registros, 11, "13:36", "0.24M");
        adicionarRegistroSetembro(registros, 11, "19:49", "2.29M");

        adicionarRegistroSetembro(registros, 12, "01:59", "0.28M");
        adicionarRegistroSetembro(registros, 12, "08:14", "2.23M");
        adicionarRegistroSetembro(registros, 12, "14:21", "0.43M");
        adicionarRegistroSetembro(registros, 12, "20:38", "2.12M");

        adicionarRegistroSetembro(registros, 13, "02:55", "0.44M");
        adicionarRegistroSetembro(registros, 13, "09:14", "1.99M");
        adicionarRegistroSetembro(registros, 13, "15:19", "0.64M");
        adicionarRegistroSetembro(registros, 13, "21:40", "1.94M");

        adicionarRegistroSetembro(registros, 14, "04:06", "0.61M");
        adicionarRegistroSetembro(registros, 14, "10:29", "1.79M");
        adicionarRegistroSetembro(registros, 14, "16:36", "0.81M");
        adicionarRegistroSetembro(registros, 14, "23:02", "1.82M");

        adicionarRegistroSetembro(registros, 15, "05:47", "0.70M");
        adicionarRegistroSetembro(registros, 15, "12:10", "1.71M");
        adicionarRegistroSetembro(registros, 15, "18:23", "0.87M");

        adicionarRegistroSetembro(registros, 16, "00:42", "1.82M");
        adicionarRegistroSetembro(registros, 16, "07:21", "0.65M");
        adicionarRegistroSetembro(registros, 16, "13:42", "1.76M");
        adicionarRegistroSetembro(registros, 16, "19:47", "0.79M");

        adicionarRegistroSetembro(registros, 17, "01:59", "1.93M");
        adicionarRegistroSetembro(registros, 17, "08:29", "0.54M");
        adicionarRegistroSetembro(registros, 17, "14:46", "1.89M");
        adicionarRegistroSetembro(registros, 17, "20:47", "0.67M");

        adicionarRegistroSetembro(registros, 18, "02:55", "2.06M");
        adicionarRegistroSetembro(registros, 18, "09:16", "0.43M");
        adicionarRegistroSetembro(registros, 18, "15:27", "2.01M");
        adicionarRegistroSetembro(registros, 18, "21:29", "0.55M");

        adicionarRegistroSetembro(registros, 19, "03:38", "2.19M");
        adicionarRegistroSetembro(registros, 19, "09:55", "0.36M");
        adicionarRegistroSetembro(registros, 19, "16:02", "2.10M");
        adicionarRegistroSetembro(registros, 19, "22:02", "0.45M");

        adicionarRegistroSetembro(registros, 20, "04:12", "2.29M");
        adicionarRegistroSetembro(registros, 20, "10:27", "0.31M");
        adicionarRegistroSetembro(registros, 20, "16:34", "2.18M");
        adicionarRegistroSetembro(registros, 20, "22:32", "0.38M");

        adicionarRegistroSetembro(registros, 21, "04:46", "2.34M");
        adicionarRegistroSetembro(registros, 21, "10:57", "0.29M");
        adicionarRegistroSetembro(registros, 21, "17:02", "2.23M");
        adicionarRegistroSetembro(registros, 21, "23:02", "0.33M");

        adicionarRegistroSetembro(registros, 22, "05:14", "2.35M");
        adicionarRegistroSetembro(registros, 22, "11:23", "0.30M");
        adicionarRegistroSetembro(registros, 22, "17:31", "2.25M");
        adicionarRegistroSetembro(registros, 22, "23:34", "0.31M");

        adicionarRegistroSetembro(registros, 23, "05:46", "2.32M");
        adicionarRegistroSetembro(registros, 23, "11:51", "0.32M");
        adicionarRegistroSetembro(registros, 23, "17:59", "2.25M");

        adicionarRegistroSetembro(registros, 24, "00:04", "0.32M");
        adicionarRegistroSetembro(registros, 24, "06:14", "2.25M");
        adicionarRegistroSetembro(registros, 24, "12:19", "0.37M");
        adicionarRegistroSetembro(registros, 24, "18:27", "2.21M");

        adicionarRegistroSetembro(registros, 25, "00:36", "0.37M");
        adicionarRegistroSetembro(registros, 25, "06:49", "2.15M");
        adicionarRegistroSetembro(registros, 25, "12:53", "0.44M");
        adicionarRegistroSetembro(registros, 25, "19:01", "2.14M");

        adicionarRegistroSetembro(registros, 26, "01:10", "0.46M");
        adicionarRegistroSetembro(registros, 26, "07:21", "2.01M");
        adicionarRegistroSetembro(registros, 26, "13:25", "0.54M");
        adicionarRegistroSetembro(registros, 26, "19:38", "2.04M");

        adicionarRegistroSetembro(registros, 27, "01:49", "0.57M");
        adicionarRegistroSetembro(registros, 27, "07:59", "1.86M");
        adicionarRegistroSetembro(registros, 27, "14:02", "0.66M");
        adicionarRegistroSetembro(registros, 27, "20:16", "1.92M");

        adicionarRegistroSetembro(registros, 28, "02:34", "0.69M");
        adicionarRegistroSetembro(registros, 28, "08:47", "1.71M");
        adicionarRegistroSetembro(registros, 28, "14:51", "0.80M");
        adicionarRegistroSetembro(registros, 28, "21:08", "1.79M");

        adicionarRegistroSetembro(registros, 29, "03:29", "0.81M");
        adicionarRegistroSetembro(registros, 29, "09:53", "1.59M");
        adicionarRegistroSetembro(registros, 29, "16:01", "0.92M");
        adicionarRegistroSetembro(registros, 29, "22:21", "1.70M");

        adicionarRegistroSetembro(registros, 30, "04:59", "0.87M");
        adicionarRegistroSetembro(registros, 30, "11:32", "1.56M");
        adicionarRegistroSetembro(registros, 30, "17:36", "0.96M");
        adicionarRegistroSetembro(registros, 30, "23:47", "1.70M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de setembro de 2025", registros.size());
    }

    private void inserirDadosOutubro2025() {
        if (dadosJaInseridosParaMes(2025, 10)) {
            log.info("Dados de maré de outubro de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroOutubro(registros, 1, "06:31", "0.83M");
        adicionarRegistroOutubro(registros, 1, "12:55", "1.65M");
        adicionarRegistroOutubro(registros, 1, "18:59", "0.88M");

        adicionarRegistroOutubro(registros, 2, "01:06", "1.81M");
        adicionarRegistroOutubro(registros, 2, "07:40", "0.70M");
        adicionarRegistroOutubro(registros, 2, "13:55", "1.81M");
        adicionarRegistroOutubro(registros, 2, "19:57", "0.73M");

        adicionarRegistroOutubro(registros, 3, "02:01", "1.98M");
        adicionarRegistroOutubro(registros, 3, "08:25", "0.53M");
        adicionarRegistroOutubro(registros, 3, "14:42", "2.00M");
        adicionarRegistroOutubro(registros, 3, "20:44", "0.56M");

        adicionarRegistroOutubro(registros, 4, "02:47", "2.18M");
        adicionarRegistroOutubro(registros, 4, "09:04", "0.36M");
        adicionarRegistroOutubro(registros, 4, "15:17", "2.18M");
        adicionarRegistroOutubro(registros, 4, "21:21", "0.37M");

        adicionarRegistroOutubro(registros, 5, "03:29", "2.38M");
        adicionarRegistroOutubro(registros, 5, "09:44", "0.19M");
        adicionarRegistroOutubro(registros, 5, "15:57", "2.36M");
        adicionarRegistroOutubro(registros, 5, "22:01", "0.20M");

        adicionarRegistroOutubro(registros, 6, "04:08", "2.55M");
        adicionarRegistroOutubro(registros, 6, "10:23", "0.06M");
        adicionarRegistroOutubro(registros, 6, "16:34", "2.49M");
        adicionarRegistroOutubro(registros, 6, "22:42", "0.06M");

        adicionarRegistroOutubro(registros, 7, "04:51", "2.64M");
        adicionarRegistroOutubro(registros, 7, "11:02", "-0.01M");
        adicionarRegistroOutubro(registros, 7, "17:14", "2.56M");
        adicionarRegistroOutubro(registros, 7, "23:21", "-0.02M");

        adicionarRegistroOutubro(registros, 8, "05:34", "2.64M");
        adicionarRegistroOutubro(registros, 8, "11:46", "-0.00M");
        adicionarRegistroOutubro(registros, 8, "17:57", "2.56M");

        adicionarRegistroOutubro(registros, 9, "00:06", "-0.02M");
        adicionarRegistroOutubro(registros, 9, "06:19", "2.56M");
        adicionarRegistroOutubro(registros, 9, "12:27", "0.09M");
        adicionarRegistroOutubro(registros, 9, "18:40", "2.48M");

        adicionarRegistroOutubro(registros, 10, "00:53", "0.07M");
        adicionarRegistroOutubro(registros, 10, "07:08", "2.39M");
        adicionarRegistroOutubro(registros, 10, "13:14", "0.26M");
        adicionarRegistroOutubro(registros, 10, "19:27", "2.33M");

        adicionarRegistroOutubro(registros, 11, "01:47", "0.22M");
        adicionarRegistroOutubro(registros, 11, "08:04", "2.16M");
        adicionarRegistroOutubro(registros, 11, "14:04", "0.47M");
        adicionarRegistroOutubro(registros, 11, "20:21", "2.15M");

        adicionarRegistroOutubro(registros, 12, "02:49", "0.41M");
        adicionarRegistroOutubro(registros, 12, "09:08", "1.93M");
        adicionarRegistroOutubro(registros, 12, "15:10", "0.68M");
        adicionarRegistroOutubro(registros, 12, "21:31", "1.97M");

        adicionarRegistroOutubro(registros, 13, "04:08", "0.57M");
        adicionarRegistroOutubro(registros, 13, "10:31", "1.77M");
        adicionarRegistroOutubro(registros, 13, "16:34", "0.83M");
        adicionarRegistroOutubro(registros, 13, "22:59", "1.86M");

        adicionarRegistroOutubro(registros, 14, "05:44", "0.64M");
        adicionarRegistroOutubro(registros, 14, "12:06", "1.74M");
        adicionarRegistroOutubro(registros, 14, "18:14", "0.85M");

        adicionarRegistroOutubro(registros, 15, "00:29", "1.87M");
        adicionarRegistroOutubro(registros, 15, "07:06", "0.61M");
        adicionarRegistroOutubro(registros, 15, "13:25", "1.81M");
        adicionarRegistroOutubro(registros, 15, "19:29", "0.78M");

        adicionarRegistroOutubro(registros, 16, "01:38", "1.96M");
        adicionarRegistroOutubro(registros, 16, "08:06", "0.54M");
        adicionarRegistroOutubro(registros, 16, "14:21", "1.91M");
        adicionarRegistroOutubro(registros, 16, "20:23", "0.68M");

        adicionarRegistroOutubro(registros, 17, "02:29", "2.06M");
        adicionarRegistroOutubro(registros, 17, "08:51", "0.49M");
        adicionarRegistroOutubro(registros, 17, "15:01", "2.01M");
        adicionarRegistroOutubro(registros, 17, "21:02", "0.58M");

        adicionarRegistroOutubro(registros, 18, "03:12", "2.14M");
        adicionarRegistroOutubro(registros, 18, "09:25", "0.44M");
        adicionarRegistroOutubro(registros, 18, "15:32", "2.09M");
        adicionarRegistroOutubro(registros, 18, "21:36", "0.50M");

        adicionarRegistroOutubro(registros, 19, "03:47", "2.20M");
        adicionarRegistroOutubro(registros, 19, "09:59", "0.40M");
        adicionarRegistroOutubro(registros, 19, "16:02", "2.16M");
        adicionarRegistroOutubro(registros, 19, "22:06", "0.43M");

        adicionarRegistroOutubro(registros, 20, "04:17", "2.23M");
        adicionarRegistroOutubro(registros, 20, "10:25", "0.38M");
        adicionarRegistroOutubro(registros, 20, "16:32", "2.21M");
        adicionarRegistroOutubro(registros, 20, "22:36", "0.38M");

        adicionarRegistroOutubro(registros, 21, "04:47", "2.23M");
        adicionarRegistroOutubro(registros, 21, "10:53", "0.37M");
        adicionarRegistroOutubro(registros, 21, "17:01", "2.24M");
        adicionarRegistroOutubro(registros, 21, "23:08", "0.35M");

        adicionarRegistroOutubro(registros, 22, "05:17", "2.20M");
        adicionarRegistroOutubro(registros, 22, "11:19", "0.37M");
        adicionarRegistroOutubro(registros, 22, "17:31", "2.24M");
        adicionarRegistroOutubro(registros, 22, "23:44", "0.35M");

        adicionarRegistroOutubro(registros, 23, "05:51", "2.14M");
        adicionarRegistroOutubro(registros, 23, "11:51", "0.41M");
        adicionarRegistroOutubro(registros, 23, "18:01", "2.21M");

        adicionarRegistroOutubro(registros, 24, "00:14", "0.39M");
        adicionarRegistroOutubro(registros, 24, "06:23", "2.04M");
        adicionarRegistroOutubro(registros, 24, "12:23", "0.48M");
        adicionarRegistroOutubro(registros, 24, "18:34", "2.14M");

        adicionarRegistroOutubro(registros, 25, "00:47", "0.46M");
        adicionarRegistroOutubro(registros, 25, "06:59", "1.94M");
        adicionarRegistroOutubro(registros, 25, "12:59", "0.57M");
        adicionarRegistroOutubro(registros, 25, "19:10", "2.05M");

        adicionarRegistroOutubro(registros, 26, "01:25", "0.55M");
        adicionarRegistroOutubro(registros, 26, "07:36", "1.82M");
        adicionarRegistroOutubro(registros, 26, "13:36", "0.69M");
        adicionarRegistroOutubro(registros, 26, "19:53", "1.94M");

        adicionarRegistroOutubro(registros, 27, "02:08", "0.65M");
        adicionarRegistroOutubro(registros, 27, "08:25", "1.71M");
        adicionarRegistroOutubro(registros, 27, "14:21", "0.81M");
        adicionarRegistroOutubro(registros, 27, "20:40", "1.84M");

        adicionarRegistroOutubro(registros, 28, "03:04", "0.75M");
        adicionarRegistroOutubro(registros, 28, "09:23", "1.63M");
        adicionarRegistroOutubro(registros, 28, "15:27", "0.91M");
        adicionarRegistroOutubro(registros, 28, "21:46", "1.76M");

        adicionarRegistroOutubro(registros, 29, "04:14", "0.82M");
        adicionarRegistroOutubro(registros, 29, "10:44", "1.61M");
        adicionarRegistroOutubro(registros, 29, "16:46", "0.95M");
        adicionarRegistroOutubro(registros, 29, "23:01", "1.76M");

        adicionarRegistroOutubro(registros, 30, "05:34", "0.80M");
        adicionarRegistroOutubro(registros, 30, "12:02", "1.69M");
        adicionarRegistroOutubro(registros, 30, "18:08", "0.89M");

        adicionarRegistroOutubro(registros, 31, "00:14", "1.85M");
        adicionarRegistroOutubro(registros, 31, "06:44", "0.69M");
        adicionarRegistroOutubro(registros, 31, "13:06", "1.83M");
        adicionarRegistroOutubro(registros, 31, "19:08", "0.75M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de outubro de 2025", registros.size());
    }

    private void inserirDadosNovembro2025() {
        if (dadosJaInseridosParaMes(2025, 11)) {
            log.info("Dados de maré de novembro de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroNovembro(registros, 1, "01:12", "2.01M");
        adicionarRegistroNovembro(registros, 1, "07:38", "0.54M");
        adicionarRegistroNovembro(registros, 1, "13:57", "2.01M");
        adicionarRegistroNovembro(registros, 1, "20:01", "0.57M");

        adicionarRegistroNovembro(registros, 2, "02:04", "2.19M");
        adicionarRegistroNovembro(registros, 2, "08:23", "0.36M");
        adicionarRegistroNovembro(registros, 2, "14:40", "2.20M");
        adicionarRegistroNovembro(registros, 2, "20:47", "0.37M");

        adicionarRegistroNovembro(registros, 3, "02:55", "2.36M");
        adicionarRegistroNovembro(registros, 3, "09:08", "0.20M");
        adicionarRegistroNovembro(registros, 3, "15:21", "2.37M");
        adicionarRegistroNovembro(registros, 3, "21:31", "0.18M");

        adicionarRegistroNovembro(registros, 4, "03:42", "2.50M");
        adicionarRegistroNovembro(registros, 4, "09:53", "0.08M");
        adicionarRegistroNovembro(registros, 4, "16:04", "2.50M");
        adicionarRegistroNovembro(registros, 4, "22:16", "0.04M");

        adicionarRegistroNovembro(registros, 5, "04:27", "2.56M");
        adicionarRegistroNovembro(registros, 5, "10:38", "0.03M");
        adicionarRegistroNovembro(registros, 5, "16:49", "2.57M");
        adicionarRegistroNovembro(registros, 5, "23:02", "-0.04M");

        adicionarRegistroNovembro(registros, 6, "05:14", "2.55M");
        adicionarRegistroNovembro(registros, 6, "11:21", "0.05M");
        adicionarRegistroNovembro(registros, 6, "17:34", "2.57M");
        adicionarRegistroNovembro(registros, 6, "23:51", "-0.04M");

        adicionarRegistroNovembro(registros, 7, "06:04", "2.46M");
        adicionarRegistroNovembro(registros, 7, "12:08", "0.14M");
        adicionarRegistroNovembro(registros, 7, "18:23", "2.50M");

        adicionarRegistroNovembro(registros, 8, "00:42", "0.04M");
        adicionarRegistroNovembro(registros, 8, "06:59", "2.31M");
        adicionarRegistroNovembro(registros, 8, "13:01", "0.29M");
        adicionarRegistroNovembro(registros, 8, "19:16", "2.37M");

        adicionarRegistroNovembro(registros, 9, "01:40", "0.18M");
        adicionarRegistroNovembro(registros, 9, "07:57", "2.13M");
        adicionarRegistroNovembro(registros, 9, "13:55", "0.47M");
        adicionarRegistroNovembro(registros, 9, "20:12", "2.21M");

        adicionarRegistroNovembro(registros, 10, "02:44", "0.35M");
        adicionarRegistroNovembro(registros, 10, "09:02", "1.96M");
        adicionarRegistroNovembro(registros, 10, "14:59", "0.65M");
        adicionarRegistroNovembro(registros, 10, "21:19", "2.06M");

        adicionarRegistroNovembro(registros, 11, "03:55", "0.50M");
        adicionarRegistroNovembro(registros, 11, "10:14", "1.83M");
        adicionarRegistroNovembro(registros, 11, "16:12", "0.77M");
        adicionarRegistroNovembro(registros, 11, "22:34", "1.96M");

        adicionarRegistroNovembro(registros, 12, "05:12", "0.59M");
        adicionarRegistroNovembro(registros, 12, "11:32", "1.79M");
        adicionarRegistroNovembro(registros, 12, "17:36", "0.81M");
        adicionarRegistroNovembro(registros, 12, "23:51", "1.93M");

        adicionarRegistroNovembro(registros, 13, "06:25", "0.63M");
        adicionarRegistroNovembro(registros, 13, "12:42", "1.81M");
        adicionarRegistroNovembro(registros, 13, "18:47", "0.79M");

        adicionarRegistroNovembro(registros, 14, "00:59", "1.95M");
        adicionarRegistroNovembro(registros, 14, "07:25", "0.62M");
        adicionarRegistroNovembro(registros, 14, "13:38", "1.87M");
        adicionarRegistroNovembro(registros, 14, "19:42", "0.73M");

        adicionarRegistroNovembro(registros, 15, "01:53", "1.99M");
        adicionarRegistroNovembro(registros, 15, "08:10", "0.59M");
        adicionarRegistroNovembro(registros, 15, "14:21", "1.95M");
        adicionarRegistroNovembro(registros, 15, "20:29", "0.66M");

        adicionarRegistroNovembro(registros, 16, "02:38", "2.02M");
        adicionarRegistroNovembro(registros, 16, "08:49", "0.56M");
        adicionarRegistroNovembro(registros, 16, "14:59", "2.03M");
        adicionarRegistroNovembro(registros, 16, "21:06", "0.58M");

        adicionarRegistroNovembro(registros, 17, "03:16", "2.05M");
        adicionarRegistroNovembro(registros, 17, "09:23", "0.52M");
        adicionarRegistroNovembro(registros, 17, "15:32", "2.09M");
        adicionarRegistroNovembro(registros, 17, "21:42", "0.50M");

        adicionarRegistroNovembro(registros, 18, "03:51", "2.08M");
        adicionarRegistroNovembro(registros, 18, "09:55", "0.47M");
        adicionarRegistroNovembro(registros, 18, "16:06", "2.15M");
        adicionarRegistroNovembro(registros, 18, "22:16", "0.44M");

        adicionarRegistroNovembro(registros, 19, "04:23", "2.08M");
        adicionarRegistroNovembro(registros, 19, "10:25", "0.44M");
        adicionarRegistroNovembro(registros, 19, "16:40", "2.19M");
        adicionarRegistroNovembro(registros, 19, "22:53", "0.40M");

        adicionarRegistroNovembro(registros, 20, "05:01", "2.07M");
        adicionarRegistroNovembro(registros, 20, "10:59", "0.44M");
        adicionarRegistroNovembro(registros, 20, "17:10", "2.20M");
        adicionarRegistroNovembro(registros, 20, "23:27", "0.38M");

        adicionarRegistroNovembro(registros, 21, "05:36", "2.03M");
        adicionarRegistroNovembro(registros, 21, "11:32", "0.46M");
        adicionarRegistroNovembro(registros, 21, "17:44", "2.18M");

        adicionarRegistroNovembro(registros, 22, "00:01", "0.40M");
        adicionarRegistroNovembro(registros, 22, "06:10", "1.98M");
        adicionarRegistroNovembro(registros, 22, "12:08", "0.51M");
        adicionarRegistroNovembro(registros, 22, "18:17", "2.14M");

        adicionarRegistroNovembro(registros, 23, "00:34", "0.45M");
        adicionarRegistroNovembro(registros, 23, "06:47", "1.91M");
        adicionarRegistroNovembro(registros, 23, "12:47", "0.58M");
        adicionarRegistroNovembro(registros, 23, "18:57", "2.08M");

        adicionarRegistroNovembro(registros, 24, "01:14", "0.51M");
        adicionarRegistroNovembro(registros, 24, "07:25", "1.84M");
        adicionarRegistroNovembro(registros, 24, "13:21", "0.67M");
        adicionarRegistroNovembro(registros, 24, "19:36", "2.01M");

        adicionarRegistroNovembro(registros, 25, "01:57", "0.59M");
        adicionarRegistroNovembro(registros, 25, "08:10", "1.78M");
        adicionarRegistroNovembro(registros, 25, "14:04", "0.76M");
        adicionarRegistroNovembro(registros, 25, "20:19", "1.95M");

        adicionarRegistroNovembro(registros, 26, "02:44", "0.66M");
        adicionarRegistroNovembro(registros, 26, "09:01", "1.74M");
        adicionarRegistroNovembro(registros, 26, "14:55", "0.83M");
        adicionarRegistroNovembro(registros, 26, "21:14", "1.90M");

        adicionarRegistroNovembro(registros, 27, "03:40", "0.71M");
        adicionarRegistroNovembro(registros, 27, "09:59", "1.72M");
        adicionarRegistroNovembro(registros, 27, "15:59", "0.86M");
        adicionarRegistroNovembro(registros, 27, "22:14", "1.89M");

        adicionarRegistroNovembro(registros, 28, "04:46", "0.71M");
        adicionarRegistroNovembro(registros, 28, "11:06", "1.77M");
        adicionarRegistroNovembro(registros, 28, "17:10", "0.83M");
        adicionarRegistroNovembro(registros, 28, "23:21", "1.93M");

        adicionarRegistroNovembro(registros, 29, "05:49", "0.66M");
        adicionarRegistroNovembro(registros, 29, "12:08", "1.86M");
        adicionarRegistroNovembro(registros, 29, "18:17", "0.74M");

        adicionarRegistroNovembro(registros, 30, "00:27", "2.01M");
        adicionarRegistroNovembro(registros, 30, "06:51", "0.55M");
        adicionarRegistroNovembro(registros, 30, "13:08", "1.99M");
        adicionarRegistroNovembro(registros, 30, "19:17", "0.59M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de novembro de 2025", registros.size());
    }

    private void inserirDadosDezembro2025() {
        if (dadosJaInseridosParaMes(2025, 12)) {
            log.info("Dados de maré de dezembro de 2025 já existem. Pulando inserção.");
            return;
        }

        List<TabuaMare> registros = new ArrayList<>();

        adicionarRegistroDezembro(registros, 1, "01:25", "2.13M");
        adicionarRegistroDezembro(registros, 1, "07:46", "0.43M");
        adicionarRegistroDezembro(registros, 1, "14:02", "2.15M");
        adicionarRegistroDezembro(registros, 1, "20:14", "0.41M");

        adicionarRegistroDezembro(registros, 2, "02:23", "2.24M");
        adicionarRegistroDezembro(registros, 2, "08:36", "0.30M");
        adicionarRegistroDezembro(registros, 2, "14:53", "2.30M");
        adicionarRegistroDezembro(registros, 2, "21:06", "0.24M");

        adicionarRegistroDezembro(registros, 3, "03:17", "2.34M");
        adicionarRegistroDezembro(registros, 3, "09:27", "0.21M");
        adicionarRegistroDezembro(registros, 3, "15:42", "2.42M");
        adicionarRegistroDezembro(registros, 3, "21:59", "0.10M");

        adicionarRegistroDezembro(registros, 4, "04:10", "2.40M");
        adicionarRegistroDezembro(registros, 4, "10:17", "0.15M");
        adicionarRegistroDezembro(registros, 4, "16:32", "2.50M");
        adicionarRegistroDezembro(registros, 4, "22:51", "0.01M");

        adicionarRegistroDezembro(registros, 5, "05:04", "2.40M");
        adicionarRegistroDezembro(registros, 5, "11:06", "0.15M");
        adicionarRegistroDezembro(registros, 5, "17:21", "2.53M");
        adicionarRegistroDezembro(registros, 5, "23:44", "-0.01M");

        adicionarRegistroDezembro(registros, 6, "05:59", "2.36M");
        adicionarRegistroDezembro(registros, 6, "11:59", "0.20M");
        adicionarRegistroDezembro(registros, 6, "18:12", "2.50M");

        adicionarRegistroDezembro(registros, 7, "00:36", "0.04M");
        adicionarRegistroDezembro(registros, 7, "06:51", "2.28M");
        adicionarRegistroDezembro(registros, 7, "12:51", "0.29M");
        adicionarRegistroDezembro(registros, 7, "19:06", "2.43M");

        adicionarRegistroDezembro(registros, 8, "01:31", "0.14M");
        adicionarRegistroDezembro(registros, 8, "07:46", "2.16M");
        adicionarRegistroDezembro(registros, 8, "13:42", "0.41M");
        adicionarRegistroDezembro(registros, 8, "20:01", "2.32M");

        adicionarRegistroDezembro(registros, 9, "02:25", "0.28M");
        adicionarRegistroDezembro(registros, 9, "08:42", "2.04M");
        adicionarRegistroDezembro(registros, 9, "14:36", "0.55M");
        adicionarRegistroDezembro(registros, 9, "20:55", "2.20M");

        adicionarRegistroDezembro(registros, 10, "03:23", "0.43M");
        adicionarRegistroDezembro(registros, 10, "09:36", "1.93M");
        adicionarRegistroDezembro(registros, 10, "15:32", "0.67M");
        adicionarRegistroDezembro(registros, 10, "21:55", "2.07M");

        adicionarRegistroDezembro(registros, 11, "04:23", "0.57M");
        adicionarRegistroDezembro(registros, 11, "10:36", "1.85M");
        adicionarRegistroDezembro(registros, 11, "16:32", "0.76M");
        adicionarRegistroDezembro(registros, 11, "22:55", "1.97M");

        adicionarRegistroDezembro(registros, 12, "05:23", "0.67M");
        adicionarRegistroDezembro(registros, 12, "11:36", "1.81M");
        adicionarRegistroDezembro(registros, 12, "17:42", "0.81M");
        adicionarRegistroDezembro(registros, 12, "23:59", "1.89M");

        adicionarRegistroDezembro(registros, 13, "06:23", "0.72M");
        adicionarRegistroDezembro(registros, 13, "12:34", "1.81M");
        adicionarRegistroDezembro(registros, 13, "18:44", "0.80M");

        adicionarRegistroDezembro(registros, 14, "01:01", "1.86M");
        adicionarRegistroDezembro(registros, 14, "07:17", "0.72M");
        adicionarRegistroDezembro(registros, 14, "13:31", "1.86M");
        adicionarRegistroDezembro(registros, 14, "19:44", "0.76M");

        adicionarRegistroDezembro(registros, 15, "01:55", "1.86M");
        adicionarRegistroDezembro(registros, 15, "08:04", "0.68M");
        adicionarRegistroDezembro(registros, 15, "14:17", "1.93M");
        adicionarRegistroDezembro(registros, 15, "20:34", "0.68M");

        adicionarRegistroDezembro(registros, 16, "02:46", "1.88M");
        adicionarRegistroDezembro(registros, 16, "08:49", "0.63M");
        adicionarRegistroDezembro(registros, 16, "15:02", "2.01M");
        adicionarRegistroDezembro(registros, 16, "21:17", "0.59M");

        adicionarRegistroDezembro(registros, 17, "03:29", "1.92M");
        adicionarRegistroDezembro(registros, 17, "09:31", "0.57M");
        adicionarRegistroDezembro(registros, 17, "15:46", "2.07M");
        adicionarRegistroDezembro(registros, 17, "22:02", "0.50M");

        adicionarRegistroDezembro(registros, 18, "04:08", "1.95M");
        adicionarRegistroDezembro(registros, 18, "10:08", "0.52M");
        adicionarRegistroDezembro(registros, 18, "16:25", "2.13M");
        adicionarRegistroDezembro(registros, 18, "22:44", "0.44M");

        adicionarRegistroDezembro(registros, 19, "04:49", "1.98M");
        adicionarRegistroDezembro(registros, 19, "10:46", "0.49M");
        adicionarRegistroDezembro(registros, 19, "17:01", "2.17M");
        adicionarRegistroDezembro(registros, 19, "23:17", "0.39M");

        adicionarRegistroDezembro(registros, 20, "05:27", "2.00M");
        adicionarRegistroDezembro(registros, 20, "11:21", "0.48M");
        adicionarRegistroDezembro(registros, 20, "17:32", "2.18M");
        adicionarRegistroDezembro(registros, 20, "23:53", "0.38M");

        adicionarRegistroDezembro(registros, 21, "06:02", "2.00M");
        adicionarRegistroDezembro(registros, 21, "12:01", "0.50M");
        adicionarRegistroDezembro(registros, 21, "18:08", "2.18M");

        adicionarRegistroDezembro(registros, 22, "00:27", "0.40M");
        adicionarRegistroDezembro(registros, 22, "06:40", "1.98M");
        adicionarRegistroDezembro(registros, 22, "12:38", "0.54M");
        adicionarRegistroDezembro(registros, 22, "18:46", "2.17M");

        adicionarRegistroDezembro(registros, 23, "01:02", "0.43M");
        adicionarRegistroDezembro(registros, 23, "07:14", "1.95M");
        adicionarRegistroDezembro(registros, 23, "13:10", "0.58M");
        adicionarRegistroDezembro(registros, 23, "19:21", "2.15M");

        adicionarRegistroDezembro(registros, 24, "01:42", "0.48M");
        adicionarRegistroDezembro(registros, 24, "07:53", "1.93M");
        adicionarRegistroDezembro(registros, 24, "13:46", "0.63M");
        adicionarRegistroDezembro(registros, 24, "20:01", "2.12M");

        adicionarRegistroDezembro(registros, 25, "02:19", "0.52M");
        adicionarRegistroDezembro(registros, 25, "08:32", "1.90M");
        adicionarRegistroDezembro(registros, 25, "14:29", "0.68M");
        adicionarRegistroDezembro(registros, 25, "20:46", "2.08M");

        adicionarRegistroDezembro(registros, 26, "03:04", "0.57M");
        adicionarRegistroDezembro(registros, 26, "09:17", "1.88M");
        adicionarRegistroDezembro(registros, 26, "15:17", "0.71M");
        adicionarRegistroDezembro(registros, 26, "21:38", "2.03M");

        adicionarRegistroDezembro(registros, 27, "04:01", "0.61M");
        adicionarRegistroDezembro(registros, 27, "10:14", "1.87M");
        adicionarRegistroDezembro(registros, 27, "16:17", "0.73M");
        adicionarRegistroDezembro(registros, 27, "22:36", "2.00M");

        adicionarRegistroDezembro(registros, 28, "04:59", "0.62M");
        adicionarRegistroDezembro(registros, 28, "11:16", "1.89M");
        adicionarRegistroDezembro(registros, 28, "17:29", "0.71M");
        adicionarRegistroDezembro(registros, 28, "23:44", "1.98M");

        adicionarRegistroDezembro(registros, 29, "06:04", "0.61M");
        adicionarRegistroDezembro(registros, 29, "12:21", "1.94M");
        adicionarRegistroDezembro(registros, 29, "18:40", "0.63M");

        adicionarRegistroDezembro(registros, 30, "00:55", "2.00M");
        adicionarRegistroDezembro(registros, 30, "07:10", "0.55M");
        adicionarRegistroDezembro(registros, 30, "13:31", "2.04M");
        adicionarRegistroDezembro(registros, 30, "19:51", "0.51M");

        adicionarRegistroDezembro(registros, 31, "02:02", "2.05M");
        adicionarRegistroDezembro(registros, 31, "08:14", "0.47M");
        adicionarRegistroDezembro(registros, 31, "14:32", "2.16M");
        adicionarRegistroDezembro(registros, 31, "20:55", "0.35M");

        tabelaMareRepository.saveAll(registros);
        log.info("Inseridos {} registros de maré de dezembro de 2025", registros.size());
    }

    private boolean dadosJaInseridosParaMes(int ano, int mes) {
        return tabelaMareRepository.existsByData(LocalDate.of(ano, mes, 1));
    }

    private void adicionarRegistroJaneiro(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 1, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroFevereiro(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 2, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroMarco(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 3, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroAbril(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 4, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroMaio(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 5, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroJunho(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 6, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroJulho(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 7, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroAgosto(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 8, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroSetembro(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 9, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroOutubro(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 10, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroNovembro(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 11, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void adicionarRegistroDezembro(List<TabuaMare> registros, int dia, String horario, String metro) {
        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(LocalDate.of(2025, 12, dia));
        tabuaMare.setHorario(horario);
        tabuaMare.setMetro(metro);
        registros.add(tabuaMare);
    }

    private void inserirDadosTaxi() {
        // Inserir tabelas de táxi (com números de WhatsApp) - apenas uma vez
        inserirTabelasTaxi();

        log.info("Verificando e inserindo dados de preços de táxi...");
        List<TaxiPreco> registros = new ArrayList<>();

        String origem = "Vila dos Remédios";

        // Adicionando todos os destinos com seus respectivos valores
        adicionarTaxiPreco(registros, origem, "Vila do Trinta", 28.00, 32.00);
        adicionarTaxiPreco(registros, origem, "Vila do Porto", 32.00, 35.00);
        adicionarTaxiPreco(registros, origem, "Vila Floresta Nova / Velha", 28.00, 32.00);
        adicionarTaxiPreco(registros, origem, "Vila da Vacaria / Dolphin", 30.00, 35.00);
        adicionarTaxiPreco(registros, origem, "Vila dos 3 Paus", 32.00, 35.00);
        adicionarTaxiPreco(registros, origem, "Vila do Bolbro/ICMbio", 35.00, 38.00);
        adicionarTaxiPreco(registros, origem, "Vila da Basinha", 35.00, 40.00);
        adicionarTaxiPreco(registros, origem, "Vila da Corea", 38.00, 42.00);
        adicionarTaxiPreco(registros, origem, "Vila do Aeroporto", 40.00, 45.00);
        adicionarTaxiPreco(registros, origem, "Vila Estrada Velha do Suest", 45.00, 48.00);
        adicionarTaxiPreco(registros, origem, "Vila do Sueste", 42.00, 45.00);
        adicionarTaxiPreco(registros, origem, "Praia do Meio / Conceição", 30.00, 35.00);
        adicionarTaxiPreco(registros, origem, "Praia do Boldró", 40.00, 45.00);
        adicionarTaxiPreco(registros, origem, "Praia do Bode", 40.00, 45.00);
        adicionarTaxiPreco(registros, origem, "Praia da Cacimba do Padre", 45.00, 48.00);
        adicionarTaxiPreco(registros, origem, "Praia do Sancho", 45.00, 48.00);
        adicionarTaxiPreco(registros, origem, "Praia do Leão / caracas", 50.00, 55.00);
        adicionarTaxiPreco(registros, origem, "Trilha do Capim Açu", 50.00, 55.00);
        adicionarTaxiPreco(registros, origem, "Forte dos Remédios", 28.00, 32.00);
        adicionarTaxiPreco(registros, origem, "Air France", 35.00, 38.00);
        adicionarTaxiPreco(registros, origem, "Abreus", 45.00, 48.00);

        // Nova origem: Vila do Boldró/ICMbio
        String origemBoldro = "Vila do Boldró/ICMbio";
        adicionarTaxiPreco(registros, origemBoldro, "Vila dos Remédios", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila do Trinta", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila do Porto", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila Floresta Nova / Velha", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila da Vacaria / Dolphin", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila da Basinha", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila da Corea", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila do Aeroporto", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila Estrada Velha do Suest", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila do Sueste", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBoldro, "Praia do Meio / Conceição", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBoldro, "Praia do Boldró", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBoldro, "Praia do Bode", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemBoldro, "Praia da Cacimba do Padre", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBoldro, "Praia do Sancho", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBoldro, "Praia do Leão / caracas", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemBoldro, "Trilha do Capim Açu", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemBoldro, "Vila dos 3 Paus", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBoldro, "Forte dos Remédios", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBoldro, "Abreus", 40.00, 45.00);
        // Air France não possui valores especificados na tabela fornecida

        // Nova origem: Vila da Basinha
        String origemBasinha = "Vila da Basinha";
        adicionarTaxiPreco(registros, origemBasinha, "Vila dos Remédios", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila do Trinta", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila do Porto", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila Floresta Nova / Velha", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila da Vacaria / Dolphin", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila do Boldró/ICMbio", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila da Corea", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila do Aeroporto", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila Estrada Velha do Suest", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila do Sueste", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBasinha, "Praia do Meio / Conceição", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBasinha, "Praia do Boldró", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemBasinha, "Praia do Bode", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBasinha, "Praia da Cacimba do Padre", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBasinha, "Praia do Sancho", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBasinha, "Praia do Leão / caracas", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBasinha, "Trilha do Capim Açu", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemBasinha, "Vila dos 3 Paus", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBasinha, "Forte dos Remédios", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBasinha, "Air France", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemBasinha, "Abreus", 35.00, 40.00);

        // Nova origem: Vila do Porto
        String origemPorto = "Vila do Porto";
        adicionarTaxiPreco(registros, origemPorto, "Vila do Trinta", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila dos Remédios", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila Floresta Nova / Velha", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila da Vacaria / Dolphin", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila dos 3 Paus", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila do Boldró/ICMbio", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila da Basinha", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila da Corea", 42.00, 48.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila do Aeroporto", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila Estrada Velha do Suest", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPorto, "Vila do Sueste", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemPorto, "Praia do Meio / Conceição", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemPorto, "Praia do Boldró", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemPorto, "Praia do Bode", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemPorto, "Praia da Cacimba do Padre", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPorto, "Praia do Sancho", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPorto, "Praia do Leão / caracas", 55.00, 60.00);
        adicionarTaxiPreco(registros, origemPorto, "Trilha do Capim Açu", 55.00, 60.00);
        adicionarTaxiPreco(registros, origemPorto, "Forte dos Remédios", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemPorto, "Air France", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemPorto, "Abreus", 50.00, 55.00);

        // Nova origem: Vila da Floresta Nova / Velha
        String origemFloresta = "Vila da Floresta Nova / Velha";
        adicionarTaxiPreco(registros, origemFloresta, "Vila dos Remédios", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila do Trinta", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila do Porto", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila da Vacaria / Dolphin", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila dos 3 Paus", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila do Boldró/ICMbio", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila da Basinha", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila da Corea", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila do Aeroporto", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila Estrada Velha do Suest", 44.00, 48.00);
        adicionarTaxiPreco(registros, origemFloresta, "Vila do Sueste", 42.00, 46.00);
        adicionarTaxiPreco(registros, origemFloresta, "Praia do Meio / Conceição", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemFloresta, "Praia do Boldró", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemFloresta, "Praia do Bode", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemFloresta, "Praia da Cacimba do Padre", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemFloresta, "Praia do Sancho", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemFloresta, "Praia do Leão / caracas", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemFloresta, "Trilha do Capim Açu", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemFloresta, "Forte dos Remédios", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemFloresta, "Air France", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemFloresta, "Abreus", 45.00, 48.00);

        // Nova origem: Vila da Vacaria / Dolphin
        String origemVacaria = "Vila da Vacaria / Dolphin";
        adicionarTaxiPreco(registros, origemVacaria, "Vila dos Remédios", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila do Trinta", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila do Porto", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila Floresta Nova / Velha", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila do Boldró/ICMbio", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila da Basinha", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila da Corea", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila do Aeroporto", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila Estrada Velha do Suest", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila do Sueste", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemVacaria, "Praia do Meio / Conceição", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemVacaria, "Praia do Boldró", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemVacaria, "Praia do Bode", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemVacaria, "Praia da Cacimba do Padre", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemVacaria, "Praia do Sancho", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemVacaria, "Praia do Leão / caracas", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemVacaria, "Trilha do Capim Açu", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemVacaria, "Vila dos 3 Paus", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemVacaria, "Forte dos Remédios", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemVacaria, "Air France", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemVacaria, "Abreus", 40.00, 45.00);

        // Nova origem: Vila do Boldró / ICMbio (com espaços)
        // Nota: Se já existir "Vila do Boldró/ICMbio" (sem espaços), os registros serão inseridos apenas se não existirem
        String origemBoldroComEspacos = "Vila do Boldró / ICMbio";
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila dos Remédios", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila do Trinta", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila do Porto", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila Floresta Nova / Velha", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila da Vacaria / Dolphin", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila da Basinha", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila da Corea", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila do Aeroporto", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila Estrada Velha do Suest", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila do Sueste", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Praia do Meio / Conceição", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Praia do Boldró", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Praia do Bode", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Praia da Cacimba do Padre", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Praia do Sancho", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Praia do Leão / caracas", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Trilha do Capim Açu", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Vila dos 3 Paus", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Forte dos Remédios", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Air France", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemBoldroComEspacos, "Abreus", 40.00, 45.00);

        // Nova origem: Vila do Aeroporto
        String origemAeroporto = "Vila do Aeroporto";
        adicionarTaxiPreco(registros, origemAeroporto, "Vila dos Remédios", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila do Trinta", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila do Porto", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila Floresta Nova / Velha", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila da Vacaria / Dolphin", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila do Boldró", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila da Basinha", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila Estrada Velha do Suest", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila do Sueste", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Praia do Meio / Conceição", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Praia do Boldró", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Praia do Bode", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Praia da Cacimba do Padre", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Praia do Sancho", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Praia do Leão / caracas", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Trilha do Capim Açu", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila dos 3 Paus", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Forte dos Remédios", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Air France", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Abreus", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemAeroporto, "Vila da Coreia", 28.00, 32.00);

        // Nova origem: Vila Estrada Velha do Sueste
        String origemEstradaVelha = "Vila Estrada Velha do Sueste";
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila dos Remédios", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila do Trinta", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila do Porto", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila Floresta Nova / Velha", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila da Vacaria / Dolphin", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila do Boldró/ICMbio", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila da Basinha", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila da Corea", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila do Aeroporto", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila do Sueste", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Praia do Meio / Conceição", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Praia do Boldró", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Praia do Bode", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Praia da Cacimba do Padre", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Praia do Sancho", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Praia do Leão / caracas", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Trilha do Capim Açu", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Vila dos 3 Paus", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Forte dos Remédios", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Air France", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemEstradaVelha, "Abreus", 28.00, 32.00);

        // Nova origem: Praia do Boldró
        String origemPraiaBoldro = "Praia do Boldró";
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Praia do Meio / Conceição", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Praia do Bode", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Praia da Cacimba do Padre", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Praia do Sancho", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Praia do Sueste", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Praia do Leão / caracas", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Estrada Velha do Sueste", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Trilha do Capim Açu", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Vila dos 3 Paus", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Forte dos Remédios", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Air France", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaBoldro, "Trilha do Abreus", 45.00, 48.00);

        // Nova origem: Praia da Cacimba do Padre
        String origemCacimba = "Praia da Cacimba do Padre";
        adicionarTaxiPreco(registros, origemCacimba, "Praia do Meio / Conceição", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemCacimba, "Praia do Boldró", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemCacimba, "Praia do Bode", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemCacimba, "Praia do Sancho", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemCacimba, "Praia do Sueste", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemCacimba, "Praia do Leão / caracas", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemCacimba, "Estrada Velha do Sueste", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemCacimba, "Trilha do Capim Açu", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemCacimba, "Vila dos 3 Paus", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemCacimba, "Forte dos Remédios", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemCacimba, "Air France", 55.00, 58.00);
        adicionarTaxiPreco(registros, origemCacimba, "Trilha do Abreus", 48.00, 52.00);

        // Nova origem: Praia do Meio/Conceição
        String origemPraiaMeio = "Praia do Meio/Conceição";
        adicionarTaxiPreco(registros, origemPraiaMeio, "Praia do Boldró", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Praia do Bode", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Praia da Cacimba do Padre", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Praia do Sancho", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Praia do Sueste", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Praia do Leão / caracas", 55.00, 58.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Estrada Velha do Sueste", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Trilha do Capim Açu", 58.00, 62.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Vila dos 3 Paus", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Forte dos Remédios", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Air France", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaMeio, "Trilha do Abreus", 55.00, 58.00);

        // Nova origem: Praia do Sueste
        String origemPraiaSueste = "Praia do Sueste";
        adicionarTaxiPreco(registros, origemPraiaSueste, "Praia do Meio / Conceição", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Praia do Boldró", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Praia do Bode", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Praia da Cacimba do Padre", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Praia do Sancho", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Praia do Leão / caracas", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Estrada Velha do Sueste", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Trilha do Capim Açu", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Vila dos 3 Paus", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Forte dos Remédios", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Air France", 52.00, 55.00);
        adicionarTaxiPreco(registros, origemPraiaSueste, "Trilha do Abreus", 30.00, 35.00);

        // Nova origem: Praia do Bode
        String origemPraiaBode = "Praia do Bode";
        adicionarTaxiPreco(registros, origemPraiaBode, "Praia do Meio / Conceição", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Praia do Boldró", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Praia da Cacimba do Padre", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Praia do Sancho", 35.00, 38.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Praia do Sueste", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Praia do Leão / caracas", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Estrada Velha do Sueste", 38.00, 42.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Trilha do Capim Açu", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Vila dos 3 Paus", 32.00, 35.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Forte dos Remédios", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Air France", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemPraiaBode, "Trilha do Abreus", 42.00, 45.00);

        // Nova origem: Praia do Sancho
        String origemPraiaSancho = "Praia do Sancho";
        adicionarTaxiPreco(registros, origemPraiaSancho, "Praia do Meio / Conceição", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Praia do Boldró", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Praia do Bode", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Praia da Cacimba do Padre", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Praia do Sueste", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Praia do Leão / caracas", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Estrada Velha do Sueste", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Trilha do Capim Açu", 28.00, 32.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Vila dos 3 Paus", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Forte dos Remédios", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Air France", 52.00, 55.00);
        adicionarTaxiPreco(registros, origemPraiaSancho, "Trilha do Abreus", 38.00, 42.00);

        // Nova origem: Trilha do Abreus
        String origemTrilhaAbreus = "Trilha do Abreus";
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila do Porto", 50.00, 55.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila do Trinta", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila dos Remédios", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila Floresta Nova / Velha", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila da Vacaria / Dolphin", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila do Boldró / ICMbio", 40.00, 45.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila da Basinha", 35.00, 40.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila do Aeroporto / Corea", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Vila dos 3 Paus", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Forte dos Remédios", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemTrilhaAbreus, "Air France", 52.00, 55.00);

        // Nova origem: Praia do Leão/Caracas
        String origemPraiaLeao = "Praia do Leão/Caracas";
        adicionarTaxiPreco(registros, origemPraiaLeao, "Praia do Meio / Conceição", 55.00, 58.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Praia do Boldró", 45.00, 48.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Praia do Bode", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Praia da Cacimba do Padre", 48.00, 52.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Praia do Sancho", 42.00, 45.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Praia do Sueste", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Estrada Velha do Sueste", 30.00, 35.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Trilha do Capim Açu", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Vila dos 3 Paus", 45.00, 50.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Forte dos Remédios", 55.00, 58.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Air France", 58.00, 62.00);
        adicionarTaxiPreco(registros, origemPraiaLeao, "Trilha do Abreus", 42.00, 45.00);

        if (!registros.isEmpty()) {
            taxiPrecoRepository.saveAll(registros);
            log.info("Inseridos {} novos registros de preços de táxi.", registros.size());
        } else {
            log.info("Todos os registros de preços de táxi já existem. Nenhum novo registro inserido.");
        }
    }

    private void inserirTabelasTaxi() {
        if (taxiTabelaRepository.count() > 0) {
            log.info("Tabelas de táxi já existem. Pulando inserção.");
            return;
        }

        log.info("Inserindo tabelas de táxi com números de WhatsApp...");
        
        TaxiTabela tabela1 = new TaxiTabela();
        tabela1.setNome("Tabela 1");
        tabela1.setNumeroWhatsapp("5584999999999"); // Substitua pelo número real da Tabela 1
        
        TaxiTabela tabela2 = new TaxiTabela();
        tabela2.setNome("Tabela 2");
        tabela2.setNumeroWhatsapp("5584999999998"); // Substitua pelo número real da Tabela 2
        
        taxiTabelaRepository.save(tabela1);
        taxiTabelaRepository.save(tabela2);
        
        log.info("Tabelas de táxi inseridas com sucesso.");
    }

    private void adicionarTaxiPreco(List<TaxiPreco> registros, String origem, String destino, double valorTabela1, double valorTabela2) {
        // Verifica se o registro já existe antes de adicionar
        if (!taxiPrecoRepository.existsByOrigemIgnoreCaseAndDestinoIgnoreCase(origem, destino)) {
            TaxiPreco taxiPreco = new TaxiPreco();
            taxiPreco.setOrigem(origem);
            taxiPreco.setDestino(destino);
            taxiPreco.setValorTabela1(BigDecimal.valueOf(valorTabela1));
            taxiPreco.setValorTabela2(BigDecimal.valueOf(valorTabela2));
            registros.add(taxiPreco);
        } else {
            log.debug("Registro já existe: {} -> {}", origem, destino);
        }
    }
}

