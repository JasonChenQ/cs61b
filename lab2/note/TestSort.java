package note;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

public class TestSort {
    @Test
    public void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};

        Sort.selectionsort(input);
        System.out.println(Arrays.toString(input));

        org.junit.Assert.assertArrayEquals(expected, input);

    }

    /*
    public static void main(String[] args) {
        testSort();
    }

    */
}
