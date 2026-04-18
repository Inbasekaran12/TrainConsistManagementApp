import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase13TrainConsistTest {

    @Test
    void testLoopFilteringLogic() {
        List<UseCase13TrainConsist.Bogie> bogies = Arrays.asList(
                new UseCase13TrainConsist.Bogie("A", 50),
                new UseCase13TrainConsist.Bogie("B", 70)
        );

        List<UseCase13TrainConsist.Bogie> result =
                UseCase13TrainConsist.filterWithLoop(bogies);

        assertEquals(1, result.size());
    }

    @Test
    void testStreamFilteringLogic() {
        List<UseCase13TrainConsist.Bogie> bogies = Arrays.asList(
                new UseCase13TrainConsist.Bogie("A", 40),
                new UseCase13TrainConsist.Bogie("B", 80)
        );

        List<UseCase13TrainConsist.Bogie> result =
                UseCase13TrainConsist.filterWithStream(bogies);

        assertEquals(1, result.size());
    }

    @Test
    void testLoopAndStreamResultsMatch() {
        List<UseCase13TrainConsist.Bogie> bogies = Arrays.asList(
                new UseCase13TrainConsist.Bogie("A", 30),
                new UseCase13TrainConsist.Bogie("B", 90)
        );

        assertEquals(
                UseCase13TrainConsist.filterWithLoop(bogies).size(),
                UseCase13TrainConsist.filterWithStream(bogies).size()
        );
    }

    @Test
    void testExecutionTimeMeasurement() {
        List<UseCase13TrainConsist.Bogie> bogies = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            bogies.add(new UseCase13TrainConsist.Bogie("A", i));
        }

        long start = System.nanoTime();
        UseCase13TrainConsist.filterWithLoop(bogies);
        long end = System.nanoTime();

        assertTrue((end - start) > 0);
    }

    @Test
    void testLargeDatasetProcessing() {
        List<UseCase13TrainConsist.Bogie> bogies = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            bogies.add(new UseCase13TrainConsist.Bogie("A", i));
        }

        assertNotNull(UseCase13TrainConsist.filterWithStream(bogies));
    }
}