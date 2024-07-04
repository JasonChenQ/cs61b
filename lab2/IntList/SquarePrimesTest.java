package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }


    @Test
    public void testSquarePrimesMid() {
        IntList lst = IntList.of(2, 1, 0, 37, 17);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 1 -> 0 -> 1369 -> 289", lst.toString());
        assertTrue(changed);
    }


    @Test
    public void testSquarePrimesHard() {
        IntList lst = IntList.of(17, 1, 0, 16, 23);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("289 -> 1 -> 0 -> 16 -> 529", lst.toString());
        assertTrue(changed);
    }


    @Test
    public void testSquarePrimesUnchanged() {
        IntList lst = IntList.of(4, 6, 8, 10, 12);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 6 -> 8 -> 10 -> 12", lst.toString());
        assertFalse(changed);
    }
}
