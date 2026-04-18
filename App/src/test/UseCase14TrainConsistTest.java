import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase14TrainConsistTest {

    @Test
    void testException_ValidCapacityCreation() throws Exception {
        UseCase14TrainConsist.PassengerBogie bogie =
                new UseCase14TrainConsist.PassengerBogie("Sleeper", 72);

        assertNotNull(bogie);
        assertEquals(72, bogie.capacity);
    }

    @Test
    void testException_NegativeCapacityThrowsException() {
        Exception ex = assertThrows(
                UseCase14TrainConsist.InvalidCapacityException.class,
                () -> new UseCase14TrainConsist.PassengerBogie("AC", -10)
        );

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ZeroCapacityThrowsException() {
        assertThrows(
                UseCase14TrainConsist.InvalidCapacityException.class,
                () -> new UseCase14TrainConsist.PassengerBogie("AC", 0)
        );
    }

    @Test
    void testException_ExceptionMessageValidation() {
        Exception ex = assertThrows(
                UseCase14TrainConsist.InvalidCapacityException.class,
                () -> new UseCase14TrainConsist.PassengerBogie("AC", 0)
        );

        assertEquals("Capacity must be greater than zero", ex.getMessage());
    }

    @Test
    void testException_ObjectIntegrityAfterCreation() throws Exception {
        UseCase14TrainConsist.PassengerBogie bogie =
                new UseCase14TrainConsist.PassengerBogie("First Class", 24);

        assertEquals("First Class", bogie.type);
        assertEquals(24, bogie.capacity);
    }

    @Test
    void testException_MultipleValidBogiesCreation() throws Exception {
        UseCase14TrainConsist.PassengerBogie b1 =
                new UseCase14TrainConsist.PassengerBogie("Sleeper", 72);

        UseCase14TrainConsist.PassengerBogie b2 =
                new UseCase14TrainConsist.PassengerBogie("AC Chair", 56);

        assertNotNull(b1);
        assertNotNull(b2);
    }
}