import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UseCase20TrainConsistTest {

    @Test
    void testSearch_ThrowsExceptionWhenEmpty() {
        String[] data = {};

        assertThrows(IllegalStateException.class, () -> {
            UseCase20TrainConsist.searchBogie(data, "BG101");
        });
    }

    @Test
    void testSearch_AllowsSearchWhenDataExists() {
        String[] data = {"BG101", "BG205"};

        assertDoesNotThrow(() -> {
            UseCase20TrainConsist.searchBogie(data, "BG101");
        });
    }

    @Test
    void testSearch_BogieFoundAfterValidation() {
        String[] data = {"BG101", "BG205", "BG309"};

        boolean result = UseCase20TrainConsist.searchBogie(data, "BG205");

        assertTrue(result);
    }

    @Test
    void testSearch_BogieNotFoundAfterValidation() {
        String[] data = {"BG101", "BG205", "BG309"};

        boolean result = UseCase20TrainConsist.searchBogie(data, "BG999");

        assertFalse(result);
    }

    @Test
    void testSearch_SingleElementValidCase() {
        String[] data = {"BG101"};

        boolean result = UseCase20TrainConsist.searchBogie(data, "BG101");

        assertTrue(result);
    }
}