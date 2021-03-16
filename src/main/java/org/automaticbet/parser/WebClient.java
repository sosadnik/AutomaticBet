package org.automaticbet.parser;

import org.automaticbet.dto.DataResponse;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebClient {

    public Map<String, String> login() throws IOException {
        String loginActionUrl = "https://www.efortuna.pl/client_security_check";
        String username = "agzer";
        String password = "Y5Bl0P4RwZb5XHxNMntA8KBwkBAQkEC4";
        HashMap<String, String> formData = new HashMap<>();

        formData.put("username", username);
        formData.put("password", password);

        Connection.Response homePage = Jsoup.connect(loginActionUrl)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36")
                .execute();

        return homePage.cookies();
    }

    public void addBet(Map<String, String> cookies, DataResponse bet, String prediction) throws IOException {

        String url = "https://www.efortuna.pl/ticket/ajax/M/1/addbet/"
                + bet.getDataInfo() + "/"
                + bet.getDataId().get(getPredictionIndex(prediction)) + "/"
                + bet.getDataValue().get(getPredictionIndex(prediction));

        connection(url, cookies);
    }

    public void changeBetValue(Map<String, String> cookies, int value) throws IOException {

        String url = "https://www.efortuna.pl/ticket/ajax/M/1/changebetval/" + value;

        connection(url, cookies);
    }

    public void placeBet(Map<String, String> cookies) throws IOException {
        String url = "https://www.efortuna.pl/ticket/ajax/M/1/acceptticket/NONE";

        connection(url, cookies);
    }

    public void connection(String url, Map<String, String> cookies) throws IOException {
        Connection.Response homePage = Jsoup.connect(url)
                .cookies(cookies)
                .method(Connection.Method.POST)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.82 Safari/537.36")
                .execute();
    }


    public void logout(Map<String, String> cookies) throws IOException {
        String url = "https://www.efortuna.pl/logout";

        connection(url, cookies);
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
