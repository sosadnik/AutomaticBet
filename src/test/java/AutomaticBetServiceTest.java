import org.automaticbet.dto.Prediction;
import org.automaticbet.service.AutomaticBetService;
import org.automaticbet.service.ExtractData;
import org.automaticbet.service.PredictionService;
import org.automaticbet.service.WebClient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AutomaticBetServiceTest {

    private ExtractData extractData;
    private WebClient webClient;
    private PredictionService predictionService;
    private AutomaticBetService service;

    @Before
    public void setup() {
        extractData = mock(ExtractData.class);
        webClient = mock(WebClient.class);
        predictionService = mock(PredictionService.class);
        service = new AutomaticBetService(extractData, webClient, predictionService);
    }

    @Test
    public void rateOfAllOdds_list_ShouldReturnDoubleTest() {
        Double expected = 4.2336;
        List<Prediction> predictionList = new ArrayList<>();
        predictionList.add(Prediction.builder()
                .odds(1.2)
                .build());
        predictionList.add(Prediction.builder()
                .odds(1.47)
                .build());
        predictionList.add(Prediction.builder()
                .odds(2.4)
                .build());

        Double actual = service.rateOfAllOdds(predictionList);

        assertEquals(expected, actual);

    }

    @Test
    public void rateOfAllOdds_emptyList_ShouldReturnDoubleTest(){
        Double expected = 0.0;
        List<Prediction> predictionList = new ArrayList<>();

        Double actual = service.rateOfAllOdds(predictionList);

        assertEquals(expected, actual);

    }
}

