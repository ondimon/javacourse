package Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


class UtilsTest {

    @ParameterizedTest
    @MethodSource("arrayProvider")
    public void getArrayAfterFour(int[] resultArray, int[] testArray) {

        Assertions.assertArrayEquals(resultArray, Utils.getArrayAfterFour(testArray));
    }

    @Test
    public void getArrayAfterFourNotFour() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Utils.getArrayAfterFour(new int[]{1,2,3,5});
        });

    }

    @Test
    public void getArrayAfterFourEmptyArray() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            Utils.getArrayAfterFour(new int[]{});
        });

    }

    static Stream<Arguments> arrayProvider() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[]{5,6}, new int[]{4,5,6}));
        out.add(Arguments.arguments(new int[]{5,6}, new int[]{1,2,4,3,4,4,5,6}));
        out.add(Arguments.arguments(new int[]{}, new int[]{4,5,6,4}));
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("arrayCorrectProvider")
    public void arrayCorrect(boolean result, int[] testArray) {
        Assertions.assertEquals(result, Utils.arrayCorrect(testArray));
    }

    static Stream<Arguments> arrayCorrectProvider() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(true, new int[]{1, 1, 1, 4, 4, 1, 4, 4}));
        out.add(Arguments.arguments(false, new int[]{1, 1, 1, 1, 1}));
        out.add(Arguments.arguments(false, new int[]{4, 4, 4, 4, 4}));
        out.add(Arguments.arguments(false, new int[]{1, 1, 1, 4, 4, 1, 4, 4, 3}));
        out.add(Arguments.arguments(false, new int[]{3, 2, 5, 6}));
        return out.stream();
    }
}