package org.automaticbet.parser;

import lombok.RequiredArgsConstructor;
import org.automaticbet.dto.DataResponseEntity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class ExtractData {


    public List<DataResponseEntity> extractionDataFromSite(String teamName, String teamName2) throws IOException {
        String url = "https://www.efortuna.pl/wyszukiwanie";
        HashMap<String, String> formData = new HashMap<>();
        formData.put("searchValue", teamName + " " + teamName2);

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
                marketName.add(row.select("span.market-name"));
                dateTime.add(row.select("span.event-datetime"));
            }
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
        List<DataResponseEntity> dataResponseEntityList = checkingDate(dataInfo, marketName, dateTime);

        dataResponseEntityList = dataResponseEntityList.stream()
                .filter(dataResponseEntity -> containsInscription(dataResponseEntity.getEventName(), Arrays.asList(teamName.split(" ")), 1))
                .filter(dataResponseEntity -> containsInscription(dataResponseEntity.getEventName(), Arrays.asList(teamName2.split(" ")), 2))
                .collect(toList());

        return dataResponseEntityList;
    }

    public boolean containsInscription(String marketName, List<String> name, int teamNumber) {
        boolean contains = containsCommonElements(Arrays.asList(marketName.split(" ")), name);
        List<String> teamNames = Arrays.asList(marketName.split("-"));

        if (teamNames.size() != 2) return false;

        if (contains == false) {
            if (teamNumber == 1) {
                contains = containsCommonElements(
                        buildsStringFromFirstLetter(Arrays.asList(teamNames.get(0).split(" "))),
                        buildsStringFromFirstLetter(name));
            } else {
                contains = containsCommonElements(
                        buildsStringFromFirstLetter(Arrays.asList(teamNames.get(1).split(" "))),
                        buildsStringFromFirstLetter(name));
            }
        }
        return contains;
    }

    public List<String> buildsStringFromFirstLetter(List<String> stringList) {
        List<String> firstLetter = new ArrayList<>();
        for (String string : stringList) {
            if (!string.isEmpty()) firstLetter.add(string.substring(0, 1));
        }
        return firstLetter;
    }

    public boolean containsCommonElements(List<String> list, List<String> listTwo) {
        boolean contains = false;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listTwo.size(); j++) {
                if (list.get(i).contains(listTwo.get(j))) contains = true;
            }
        }
        return contains;
    }

    public List<DataResponseEntity> checkingDate(List<Elements> dataInfo, List<Elements> marketName, List<Elements> dateTime) {
        String today = new Date().toString().substring(8, 10);
        List<DataResponseEntity> entities = new ArrayList<>();

        for (int i = 0; i < dateTime.size(); i++) {
            if (dateTime.get(i).text().substring(0, 2).equals(today)) {
                entities.add(buildEntity(dataInfo.get(i), marketName.get(i), dateTime.get(i)));
            }
        }
        return entities;
    }

    public DataResponseEntity buildEntity(Elements dataInfo, Elements marketName, Elements dateTime) {
        DataResponseEntity dataResponseEntity = DataResponseEntity.builder()
                .eventName(marketName.text())
                .dataId((dataInfo.eachAttr("data-id")))
                .dataValue(dataInfo.eachAttr("data-value"))
                .dataInfo(dataInfo.attr("data-info"))
                .date(dateTime.text())
                .build();

        return dataResponseEntity;
    }
}



