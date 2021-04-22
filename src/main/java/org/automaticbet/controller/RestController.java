package org.automaticbet.controller;

import lombok.RequiredArgsConstructor;
import org.automaticbet.service.AutomaticBetService;
import org.automaticbet.service.ExtractData;
import org.automaticbet.service.PredictionService;
import org.automaticbet.service.WebClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
@RequestMapping("/bet")
public class RestController {

    private final ExtractData extractData =new ExtractData();
    private final WebClient webClient = new WebClient();
    private final PredictionService predictionService = new PredictionService();
    private final AutomaticBetService automaticBetService = new AutomaticBetService(extractData, webClient, predictionService);

    @GetMapping("/placeCoupon{username}{password}")
    @ResponseStatus(code = HttpStatus.OK)
    public void placeCoupon(@PathVariable String username, @PathVariable String password) throws IOException, InterruptedException {
        automaticBetService.createCoupon(username, password);
    }


}

