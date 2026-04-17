import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase8TrainConsistTest {

    private List<UseCase8TrainConsist.Bogie> getBogies() {
        List<UseCase8TrainConsist.Bogie> list = new ArrayList<>();
        list.add(new UseCase8TrainConsist.Bogie("Sleeper", 72));
        list.add(new UseCase8TrainConsist.Bogie("AC Chair", 56));
        list.add(new UseCase8TrainConsist.Bogie("First Class", 24));
        list.add(new UseCase8TrainConsist.Bogie("General", 90));
        return list;
    }

    @Test
    void testFilter_CapacityGreaterThanThreshold() {
        var result = UseCase8TrainConsist.filterBogies(getBogies(), 70);
        assertTrue(result.stream().allMatch(b -> b.capacity > 70));
    }

    @Test
    void testFilter_CapacityEqualToThreshold() {
        var result = UseCase8TrainConsist.filterBogies(getBogies(), 72);
        assertFalse(result.stream().anyMatch(b -> b.capacity == 72));
    }

    @Test
    void testFilter_CapacityLessThanThreshold() {
        var result = UseCase8TrainConsist.filterBogies(getBogies(), 60);
        assertFalse(result.stream().anyMatch(b -> b.capacity <= 60));
    }

    @Test
    void testFilter_MultipleBogiesMatching() {
        var result = UseCase8TrainConsist.filterBogies(getBogies(), 50);
        assertTrue(result.size() >= 2);
    }

    @Test
    void testFilter_NoBogiesMatching() {
        var result = UseCase8TrainConsist.filterBogies(getBogies(), 100);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_AllBogiesMatching() {
        var result = UseCase8TrainConsist.filterBogies(getBogies(), 10);
        assertEquals(4, result.size());
    }

    @Test
    void testFilter_EmptyBogieList() {
        var result = UseCase8TrainConsist.filterBogies(new ArrayList<>(), 50);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_OriginalListUnchanged() {
        var original = getBogies();
        UseCase8TrainConsist.filterBogies(original, 60);
        assertEquals(4, original.size());
    }
}