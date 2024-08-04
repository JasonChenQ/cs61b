package note;

public class implementingfibo {
    public static int fibo(int x) {
        int[] numbers = new int[]{0,1};
        if (x == 0) {
            return 0;
        }
        for (int i = 0; i<x-1; i+=1) {
            int temp = numbers[0] + numbers[1];
            numbers[0] = numbers[1];
            numbers[1] = temp;
        }

        return numbers[1];
    }
    public static int fibo2(int x) {
        if (x==0 | x==1) {
            return x;
        }
        return fibo2(x-1) + fibo2(x-2);
    }
    public static void main(String[] args) {
        System.out.println(fibo2(6));

    }
}
