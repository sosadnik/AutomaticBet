package org.automaticbet;

import lombok.RequiredArgsConstructor;
import org.automaticbet.parser.ExtractData;
import org.automaticbet.parser.WebClient;
import org.automaticbet.service.PredictionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {


    private final ExtractData extractData;
    private final WebClient webClient;
    private final PredictionService service;


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws IOException {
        System.out.println("=======main======");

        //List<DataResponse> entities = extractData.extractionDataFromSite("Excelsior", "Jong Utrecht");
       // entities.forEach(dataResponse -> System.out.println(dataResponse.getEventName()));
        System.out.println(service.getPrediction());;


    }


}

