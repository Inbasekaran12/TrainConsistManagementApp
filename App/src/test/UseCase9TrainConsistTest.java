import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase9TrainConsistTest {

    private List<UseCase9TrainConsist.Bogie> getSampleBogies() {
        List<UseCase9TrainConsist.Bogie> list = new ArrayList<>();

        list.add(new UseCase9TrainConsist.Bogie("Sleeper", 72));
        list.add(new UseCase9TrainConsist.Bogie("AC Chair", 56));
        list.add(new UseCase9TrainConsist.Bogie("First Class", 24));
        list.add(new UseCase9TrainConsist.Bogie("Sleeper", 70));
        list.add(new UseCase9TrainConsist.Bogie("AC Chair", 60));

        return list;
    }

    @Test
    void testGrouping_BogiesGroupedByType() {
        Map<String, List<UseCase9TrainConsist.Bogie>> result =
                UseCase9TrainConsist.groupBogies(getSampleBogies());

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
    }

    @Test
    void testGrouping_MultipleBogiesInSameGroup() {
        Map<String, List<UseCase9TrainConsist.Bogie>> result =
                UseCase9TrainConsist.groupBogies(getSampleBogies());

        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    void testGrouping_DifferentBogieTypes() {
        Map<String, List<UseCase9TrainConsist.Bogie>> result =
                UseCase9TrainConsist.groupBogies(getSampleBogies());

        assertEquals(3, result.keySet().size());
    }

    @Test
    void testGrouping_EmptyBogieList() {
        Map<String, List<UseCase9TrainConsist.Bogie>> result =
                UseCase9TrainConsist.groupBogies(new ArrayList<>());

        assertTrue(result.isEmpty());
    }

    @Test
    void testGrouping_SingleBogieCategory() {
        List<UseCase9TrainConsist.Bogie> list = new ArrayList<>();
        list.add(new UseCase9TrainConsist.Bogie("Sleeper", 72));

        Map<String, List<UseCase9TrainConsist.Bogie>> result =
                UseCase9TrainConsist.groupBogies(list);

        assertEquals(1, result.size());
    }

    @Test
    void testGrouping_MapContainsCorrectKeys() {
        Map<String, List<UseCase9TrainConsist.Bogie>> result =
                UseCase9TrainConsist.groupBogies(getSampleBogies());

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    @Test
    void testGrouping_GroupSizeValidation() {
        Map<String, List<UseCase9TrainConsist.Bogie>> result =
                UseCase9TrainConsist.groupBogies(getSampleBogies());

        assertEquals(2, result.get("AC Chair").size());
    }

    @Test
    void testGrouping_OriginalListUnchanged() {
        List<UseCase9TrainConsist.Bogie> original = getSampleBogies();
        int sizeBefore = original.size();

        UseCase9TrainConsist.groupBogies(original);

        assertEquals(sizeBefore, original.size());
    }
}