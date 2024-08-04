package note;

public class Sort {
    //bubble sort: The ones at the back are those sorted earlier.
    public static void bubblesort(String[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - 1 - i; j++) {
                if (input[j].compareTo(input[j+1]) > 0) {
                    String tmp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = tmp;
                }
            }
        }
    }

    public static void selectionsort(String[] input) {
        selectionsort(input, 0);
    }
    //selection sort: using recursion
    private static void selectionsort(String[] input, int first) {
        //base case
        if (input.length - first == 1) {
            return;
        }
        String minimum = input[first];
        int index = first;
        for (int i = first+1; i < input.length; i++) {
            if (input[i].compareTo(minimum) < 0) {
                minimum = input[i];
                index = i;
            }
        }
        input[index] = input[first];
        input[first] = minimum;
        selectionsort(input, first+1);
    }
}
