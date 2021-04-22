import org.automaticbet.dto.DataResponse;
import org.automaticbet.service.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class WebClientTest {
    private final WebClient webClient = new WebClient();
    private final Map<String, String> cookies = new HashMap<>();

    @Mock
    DataResponse dataResponse;


    @Before
    public void setup() {
        cookies.put("login-state", "false");
        cookies.put("__cfduid", "d7a22f0f1f2e4610e608e074b8bd959431618236696");
        cookies.put("KOM_SESSION_ID", "98a46ed7-3b28-486f-b83e-64fe46ffd93a");
        cookies.put("route", "c67a4c48ac53a1529b038363db97a459");
        cookies.put("SESSION", "MTdiNGNmMTEtZmRmMi00ODhhLTg1ODctZWRiZDBlNGUyZmVj");


    }

    @After
    public void teardown() {
        cookies.clear();
    }

    @Test
    public void getPredictionIndex_ShouldReturnIntTest() {
        String prediction = "1";


        int actual = webClient.getPredictionIndex(prediction);


        assertEquals((0), actual);
    }

    @Test
    public void getPredictionIndex_ShouldReturnDefaultValueTest() {
        String prediction = "";

        int actual = webClient.getPredictionIndex(prediction);

        assertEquals(5, actual);
    }

    @Test
    public void login_ShouldReturnMapTest() throws IOException {
        Map<String, String> actualMap;

        actualMap = webClient.login("testUsername", "testPassword");


        assertNotEquals(null, actualMap);
    }


    @Test
    public void placeBet_ShouldReturnHttpResponseCodeOKTest() throws IOException {
        int actual = webClient.placeBet(cookies).statusCode();
        assertEquals(200, actual);
    }

    @Test
    public void changeBetValue_ShouldReturnHttpResponseCodeOKTest() throws IOException {
        int actual = webClient.changeBetValue(cookies).statusCode();

        assertEquals(200, actual);
    }

    @Test
    public void connection_ShouldReturnHttpResponseCodeOKTest() throws IOException {
        String URL = "https://chat.efortuna.pl/chat_web/client";

        int actual = webClient.connection(URL, cookies).statusCode();

        assertEquals(200, actual);
    }

    @Test
    public void addBet_ShouldReturnHttpResponseCodeOKTest() throws IOException {
        Map<String, String> cookies2 = new HashMap<>();
        cookies2.put("route", "c67a4c48ac53a1529b0383632397a459");

        assertThrows(IOException.class, () -> webClient.addBet(
                cookies,
                dataResponse.builder()
                        .eventName("event1")
                        .dataId(List.of("88760554", "88760554"))
                        .dataValue(List.of("3.7", "2.1"))
                        .dataInfo("1770")
                        .date("date")
                        .build(),
                "1"));
    }

}
