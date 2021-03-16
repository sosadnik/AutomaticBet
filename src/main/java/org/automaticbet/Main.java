package org.automaticbet;

import lombok.RequiredArgsConstructor;
import org.automaticbet.service.AutomaticBetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class Main implements CommandLineRunner {

    private final AutomaticBetService betController;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        betController.createCoupon();
    }

}

