package org.automaticbet.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class ExtractData {

    public void extractionDataFromSite(String teamName) throws IOException {
        String url = "https://www.efortuna.pl/wyszukiwanie";
        HashMap<String, String> formData = new HashMap<>();
        formData.put("searchValue", teamName);

        List<Elements> dataInfo = new ArrayList<>();
        List<Elements> marketName = new ArrayList<>();
        List<Elements> dateTime = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url)
                    .data(formData)
                    .method(Connection.Method.GET)
                    .get();

            for (Element row : document.select("div.markets-list")) {
                dataInfo.add(row.getElementsByAttribute("data-info"));
                marketName.add(row.select("span.market-teamName"));
                dateTime.add(row.select("span.event-datetime"));
            }
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }

    }

}



