package org.automaticbet.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashMap;

@Component
public class LoginClient {

    public void loginSts() throws IOException {
        final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +
                "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";
        String loginFormUrl = "https://www.efortuna.pl/";
        String loginActionUrl = "https://www.efortuna.pl/client_security_check";
        String username = "agzer";
        String password = "632KJBXr15y5_ExN8pvQ8NCQ0MBwkPAY";
        HashMap<String, String> formData = new HashMap<>();
        /*
        HashMap<String, String> cookies = new HashMap<>();
        Connection.Response loginForm = Jsoup.connect(loginFormUrl)
                .method(Connection.Method.GET)
                .execute();
        cookies.putAll(loginForm.cookies());
         */

        formData.put("username", username);
        formData.put("password", password);

        Connection.Response homePage = Jsoup.connect(loginActionUrl)
                .data(formData)
                .method(Connection.Method.POST)
                .execute();

        System.out.println(homePage.parse().html());
    }

}
