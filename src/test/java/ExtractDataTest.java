import org.automaticbet.service.ExtractData;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExtractDataTest {
    private ExtractData extractData;

    @Before
    public void setup() {
        extractData = new ExtractData();

    }

    @Test
    public void buildsStringFromFirstLetter_ShouldReturnStringTest() {
        List<String> stringList = List.of("Fie_ld", "C.aard", "FC");

        List<String> actual = extractData.buildsStringFromFirstLetter(stringList);

        assertEquals(List.of("F", "C", "F"), actual);

    }

    @Test
    public void containsCommonElements_ShouldReturnTrueTest() {
        List<String> stringList = List.of("Fie_ld", "C.aard");
        List<String> stringList2 = List.of("Fie_ld", "C.aard");

        boolean result = extractData.containsCommonElements(stringList, stringList2);

        assertTrue(result);
    }
    @Test
    public void containsCommonElements_ShouldReturnFalseTest() {
        List<String> stringList = List.of("Fie_ld", "Elem");
        List<String> stringList2 = List.of("Fie_ld", "C.aard");

        boolean result = extractData.containsCommonElements(stringList, stringList2);

        assertFalse(result);
    }

    @Test
    public void containsInscription_ShouldReturnFalseTest() {

        String marketName = "Lorem Ipsum is simply dummy";
        List<String> stringList = List.of("Lorem", "Ipsum");

        boolean result = extractData.containsInscription(marketName, stringList, 1);

        assertFalse(result);
    }

}
