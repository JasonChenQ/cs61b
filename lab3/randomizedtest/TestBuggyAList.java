package randomizedtest;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testSort() {
      AListNoResizing<Integer> lst1 = new AListNoResizing<Integer>();
      BuggyAList<Integer>      lst2 = new BuggyAList<Integer>();

      int[] mylist = new int[] {4,5,6,7, 9, 10 ,32};

      for (int i = 0; i < mylist.length; i++) {
          lst1.addLast(mylist[i]);
          lst2.addLast(mylist[i]);
      }

      for (int i = 0; i < mylist.length; i++) {
          int num1 = lst1.removeLast();
          int num2 = lst2.removeLast();
          assertEquals(num1, num2);
      }
  }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer>      M = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = edu.princeton.cs.introcs.StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                M.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = M.size();
                System.out.println("size: " + size + " size2: " + size2);
                assertEquals(size, size2);
            } else if (operationNumber == 2 && L.size() != 0 && M.size() != 0) {
                int num = L.getLast();
                int num2 = M.getLast();
                System.out.println("getLast: " + num + " getLast2: " + num2);
                assertEquals(num, num2);
            } else if (operationNumber == 3 && L.size() != 0 && M.size() != 0) {
                int num = L.removeLast();
                int num2 = M.removeLast();
                System.out.println("removeLast: " + num + " removeLast2: " + num2);
                assertEquals(num, num2);
            }
        }
    }
}
