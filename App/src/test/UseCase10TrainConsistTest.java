import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase10TrainConsistTest {

    private List<UseCase10TrainConsist.Bogie> getSampleBogies() {
        List<UseCase10TrainConsist.Bogie> list = new ArrayList<>();

        list.add(new UseCase10TrainConsist.Bogie("Sleeper", 72));
        list.add(new UseCase10TrainConsist.Bogie("AC Chair", 56));
        list.add(new UseCase10TrainConsist.Bogie("First Class", 24));
        list.add(new UseCase10TrainConsist.Bogie("Sleeper", 70));

        return list;
    }

    @Test
    void testReduce_TotalSeatCalculation() {
        int total = UseCase10TrainConsist.calculateTotalCapacity(getSampleBogies());
        assertEquals(222, total);
    }

    @Test
    void testReduce_MultipleBogiesAggregation() {
        int total = UseCase10TrainConsist.calculateTotalCapacity(getSampleBogies());
        assertTrue(total > 0);
    }

    @Test
    void testReduce_SingleBogieCapacity() {
        List<UseCase10TrainConsist.Bogie> list = new ArrayList<>();
        list.add(new UseCase10TrainConsist.Bogie("Sleeper", 72));

        int total = UseCase10TrainConsist.calculateTotalCapacity(list);
        assertEquals(72, total);
    }

    @Test
    void testReduce_EmptyBogieList() {
        int total = UseCase10TrainConsist.calculateTotalCapacity(new ArrayList<>());
        assertEquals(0, total);
    }

    @Test
    void testReduce_CorrectCapacityExtraction() {
        List<UseCase10TrainConsist.Bogie> list = getSampleBogies();

        int total = UseCase10TrainConsist.calculateTotalCapacity(list);
        assertEquals(72 + 56 + 24 + 70, total);
    }

    @Test
    void testReduce_AllBogiesIncluded() {
        List<UseCase10TrainConsist.Bogie> list = getSampleBogies();

        int total = UseCase10TrainConsist.calculateTotalCapacity(list);
        assertEquals(4, list.size());
        assertEquals(222, total);
    }

    @Test
    void testReduce_OriginalListUnchanged() {
        List<UseCase10TrainConsist.Bogie> list = getSampleBogies();
        int sizeBefore = list.size();

        UseCase10TrainConsist.calculateTotalCapacity(list);

        assertEquals(sizeBefore, list.size());
    }
}