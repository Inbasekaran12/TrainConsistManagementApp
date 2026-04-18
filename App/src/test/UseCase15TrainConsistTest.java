import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase15TrainConsistTest {

    @Test
    void testCargo_SafeAssignment() {
        UseCase15TrainConsist.GoodsBogie bogie =
                new UseCase15TrainConsist.GoodsBogie("Cylindrical");

        bogie.assignCargo("Petroleum");

        assertEquals("Petroleum", bogie.cargo);
    }

    @Test
    void testCargo_UnsafeAssignmentHandled() {
        UseCase15TrainConsist.GoodsBogie bogie =
                new UseCase15TrainConsist.GoodsBogie("Rectangular");

        bogie.assignCargo("Petroleum");

        assertNull(bogie.cargo); // should not assign
    }

    @Test
    void testCargo_CargoNotAssignedAfterFailure() {
        UseCase15TrainConsist.GoodsBogie bogie =
                new UseCase15TrainConsist.GoodsBogie("Rectangular");

        bogie.assignCargo("Petroleum");

        assertNull(bogie.cargo);
    }

    @Test
    void testCargo_ProgramContinuesAfterException() {
        UseCase15TrainConsist.GoodsBogie b1 =
                new UseCase15TrainConsist.GoodsBogie("Rectangular");

        UseCase15TrainConsist.GoodsBogie b2 =
                new UseCase15TrainConsist.GoodsBogie("Cylindrical");

        b1.assignCargo("Petroleum"); // unsafe
        b2.assignCargo("Coal");      // safe

        assertEquals("Coal", b2.cargo);
    }

    @Test
    void testCargo_FinallyBlockExecution() {
        UseCase15TrainConsist.GoodsBogie bogie =
                new UseCase15TrainConsist.GoodsBogie("Rectangular");

        // We cannot directly assert finally print,
        // but method should execute without crash
        assertDoesNotThrow(() -> bogie.assignCargo("Petroleum"));
    }
}