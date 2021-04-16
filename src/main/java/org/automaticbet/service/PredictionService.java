package org.automaticbet.service;

import lombok.RequiredArgsConstructor;
import org.automaticbet.dto.Prediction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PredictionService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String PREDICTION_URL = "http://localhost:8080/api/getPrediction";

    public List<Prediction> getPrediction() {
        Prediction[] response = restTemplate.getForObject(
                PREDICTION_URL,
                Prediction[].class);
        assert response != null;
        return Arrays.asList(response);
    }

}
