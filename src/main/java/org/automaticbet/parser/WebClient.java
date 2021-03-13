package org.automaticbet.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;

@Component
public class WebClient {

    public void login() throws IOException {
        String loginActionUrl = "https://www.efortuna.pl/client_security_check";
        String username = "agzer";
        String password = "632KJBXr15y5_ExN8pvQ8NCQ0MBwkPAY";
        HashMap<String, String> formData = new HashMap<>();

        formData.put("username", username);
        formData.put("password", password);

        Connection.Response homePage = Jsoup.connect(loginActionUrl)
                .data(formData)
                .method(Connection.Method.POST)
                .execute();
    }

    public void addBet(){

    }

    public void placeBet(){

    }



}
