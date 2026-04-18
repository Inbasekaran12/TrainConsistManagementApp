import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UseCase16TrainConsistTest {

    @Test
    void testSort_BasicSorting() {
        int[] arr = {72, 56, 24, 70, 60};
        UseCase16TrainConsist.bubbleSort(arr);
        assertArrayEquals(new int[]{24, 56, 60, 70, 72}, arr);
    }

    @Test
    void testSort_AlreadySortedArray() {
        int[] arr = {24, 56, 60, 70, 72};
        UseCase16TrainConsist.bubbleSort(arr);
        assertArrayEquals(new int[]{24, 56, 60, 70, 72}, arr);
    }

    @Test
    void testSort_DuplicateValues() {
        int[] arr = {72, 56, 56, 24};
        UseCase16TrainConsist.bubbleSort(arr);
        assertArrayEquals(new int[]{24, 56, 56, 72}, arr);
    }

    @Test
    void testSort_SingleElementArray() {
        int[] arr = {50};
        UseCase16TrainConsist.bubbleSort(arr);
        assertArrayEquals(new int[]{50}, arr);
    }

    @Test
    void testSort_AllEqualValues() {
        int[] arr = {40, 40, 40};
        UseCase16TrainConsist.bubbleSort(arr);
        assertArrayEquals(new int[]{40, 40, 40}, arr);
    }
}