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

    public void createCoupon() throws IOException, InterruptedException {
        List<Prediction> bet = new ArrayList<>();

        for (Prediction coupon : service.getPrediction()) {
            if (rateOfAllOdds(bet) > 2.0 & bet.size() > 1) {
                placeCoupon(bet);
                bet.clear();

            } else if (coupon.getStatus().equals("pending") & !extractData.extractionDataFromSite(coupon.getHomeTeam(), coupon.getAwayTeam()).isEmpty()) {
                bet.add(coupon);
            }
        }
        if (rateOfAllOdds(bet) > 2.0 & bet.size() > 1) {
            placeCoupon(bet);
        }
    }

    public void placeCoupon(List<Prediction> list) throws IOException, InterruptedException {
        Map<String, String> cookies = webClient.login();
        Thread.sleep(2458);
        for (Prediction bet : list) {
            webClient.addBet(cookies, extractData.extractionDataFromSite(bet.getHomeTeam(), bet.getAwayTeam()).get(0), bet.getPrediction());
            Thread.sleep(2100);
        }
        Thread.sleep(3458);
        webClient.changeBetValue(cookies);
        Thread.sleep(2622);
        webClient.placeBet(cookies);
    }

    public Double rateOfAllOdds(List<Prediction> list) {
        if (list.isEmpty()) return 0.0;
        return list.stream()
                .map(Prediction::getOdds)
                .reduce((odds1, odds2) -> odds1 * odds2)
                .get();
    }


}
