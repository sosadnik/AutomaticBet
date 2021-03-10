package org.automaticbet;

import lombok.RequiredArgsConstructor;
import org.automaticbet.parser.ExtractData;
import org.automaticbet.parser.LoginClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {


    private final ExtractData extractData;
    private final LoginClient loginClient;


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws IOException {


        extractData.extractionDataFromSite("inter");
//        loginClient.StsLogin();


    }


}

