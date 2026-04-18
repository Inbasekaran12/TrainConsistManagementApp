import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UseCase12TrainConsistTest {

    @Test
    void testSafety_AllBogiesValid() {
        List<UseCase12TrainConsist.GoodsBogie> list = new ArrayList<>();
        list.add(new UseCase12TrainConsist.GoodsBogie("Cylindrical", "Petroleum"));
        list.add(new UseCase12TrainConsist.GoodsBogie("Open", "Coal"));

        assertTrue(UseCase12TrainConsist.isTrainSafe(list));
    }

    @Test
    void testSafety_CylindricalWithInvalidCargo() {
        List<UseCase12TrainConsist.GoodsBogie> list = new ArrayList<>();
        list.add(new UseCase12TrainConsist.GoodsBogie("Cylindrical", "Coal"));

        assertFalse(UseCase12TrainConsist.isTrainSafe(list));
    }

    @Test
    void testSafety_EmptyBogieList() {
        List<UseCase12TrainConsist.GoodsBogie> list = new ArrayList<>();
        assertTrue(UseCase12TrainConsist.isTrainSafe(list));
    }
}