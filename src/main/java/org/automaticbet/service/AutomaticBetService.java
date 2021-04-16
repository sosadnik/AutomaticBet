package org.automaticbet.service;

import lombok.RequiredArgsConstructor;
import org.automaticbet.dto.Prediction;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AutomaticBetService {

    private final ExtractData extractData;
    private final WebClient webClient;
    private final PredictionService service;


    public void createCoupon(String username, String password) throws IOException, InterruptedException {
        List<Prediction> bet = new ArrayList<>();
        for (Prediction coupon : service.getPrediction()) {
            if (rateOfAllOdds(bet) > 2.0 & bet.size() > 1) {
                placeCoupon(bet, username, password);
                bet.clear();
            } else if (coupon.getStatus().equals("pending") & !extractData.extractionDataFromSite(
                    coupon.getHomeTeam(),
                    coupon.getAwayTeam()).isEmpty()) {
                bet.add(coupon);
            }
        }
        if (rateOfAllOdds(bet) > 2.0 & bet.size() > 1) {
            placeCoupon(bet, username, password);
        }
    }

    public void placeCoupon(List<Prediction> list, String username, String password) throws IOException, InterruptedException {
        Map<String, String> cookies = webClient.login(username, password);
        Thread.sleep(getRandomLong());

        for (Prediction bet : list) {
            webClient.addBet(cookies, extractData.extractionDataFromSite(
                    bet.getHomeTeam(),
                    bet.getAwayTeam()).get(0),
                    bet.getPrediction());
            Thread.sleep(getRandomLong());
        }
        Thread.sleep(getRandomLong());

        webClient.changeBetValue(cookies);
        Thread.sleep(getRandomLong());

        webClient.placeBet(cookies);
    }

    public long getRandomLong() {
        return (long) (2000 + (Math.random() * (4000 - 2000)));
    }

    public Double rateOfAllOdds(List<Prediction> list) {
        if (list.isEmpty()) return 0.0;
        return list.stream()
                .map(Prediction::getOdds)
                .reduce((odds1, odds2) -> odds1 * odds2)
                .get();
    }
}
