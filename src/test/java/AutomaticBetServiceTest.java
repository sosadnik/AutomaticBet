import org.automaticbet.service.ExtractData;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AutomaticBetServiceTest {
    private final ExtractData extractData = new ExtractData();

    @Before
    public void setup() {


    }

    @Test
    public void buildsStringFromFirstLetter_ShouldReturnStringTest() {
        //given
        List<String> stringList = List.of("Fie_ld", "C.aard", "FC");

        //when
        List<String> actual = extractData.buildsStringFromFirstLetter(stringList);

        //then
        assertEquals(List.of("F", "C", "F"), actual);

    }

    @Test
    public void containsCommonElements_ShouldReturnTrueTest() {
        //given
        List<String> stringList = List.of("Fie_ld", "C.aard");
        List<String> stringList2 = List.of("Fie_ld", "C.aard");
        //when
        boolean result = extractData.containsCommonElements(stringList, stringList2);
        //then
        assertTrue(result);
    }
    @Test
    public void containsCommonElements_ShouldReturnFalseTest() {
        //given
        List<String> stringList = List.of("Fie_ld", "Elem");
        List<String> stringList2 = List.of("Fie_ld", "C.aard");
        //when
        boolean result = extractData.containsCommonElements(stringList, stringList2);
        //then
        assertFalse(result);
    }

    @Test
    public void containsInscription_ShouldReturnTrueTest() {
        //given
        String marketName = "Lorem Ipsum is simply dummy";
        List<String> stringList = List.of("Lorem", "Ipsum");
        //when
        boolean result = extractData.containsInscription(marketName, stringList, 1);
        //then
        assertFalse(result);

    }

}
