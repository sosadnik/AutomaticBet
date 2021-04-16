package org.automaticbet.service;

import org.automaticbet.dto.DataResponse;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebClient {
    public static String DEFAULT_USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36";

    public Map<String, String> login(String username, String password) throws IOException {
        String loginActionUrl = "https://www.efortuna.pl/client_security_check";
        HashMap<String, String> formData = new HashMap<>();

        formData.put("username", username);
        formData.put("password", password);


        return Jsoup.connect(loginActionUrl)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent(DEFAULT_USER_AGENT_VALUE)
                .execute().cookies();
    }

    public Connection.Response addBet(Map<String, String> cookies, DataResponse bet, String prediction) throws IOException {

        String url = "https://www.efortuna.pl/ticket/ajax/M/1/addbet/"
                + bet.getDataInfo() + "/"
                + bet.getDataId().get(getPredictionIndex(prediction)) + "/"
                + bet.getDataValue().get(getPredictionIndex(prediction));
        return connection(url, cookies);
    }

    public Connection.Response changeBetValue(Map<String, String> cookies) throws IOException {

        String url = "https://www.efortuna.pl/ticket/ajax/M/1/changebetval/2";

        return connection(url, cookies);
    }

    public Connection.Response placeBet(Map<String, String> cookies) throws IOException {
        String url = "https://www.efortuna.pl/ticket/ajax/M/1/acceptticket/NONE";

        return connection(url, cookies);
    }

    public Connection.Response connection(String url, Map<String, String> cookies) throws IOException {
        return Jsoup.connect(url)
                .cookies(cookies)
                .method(Connection.Method.POST)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36")
                .execute();

    }

    public int getPredictionIndex(String prediction) {
        return switch (prediction) {
            case "1" -> 0;
            case "X" -> 1;
            case "2" -> 2;
            case "1X" -> 3;
            case "X2" -> 4;
            default -> 5;
        };
    }

}
