package non_forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTest {

    public static final int LENGTH = 1000;
    public static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] numbers = new int[LENGTH];
        //Create  an array with some values.
        for (int i = 0; i < LENGTH; i++) {
            numbers[i] = i * 2;
        }
        Future<Integer> sum = pool.submit(new NumberDividerTask(numbers));
        System.out.println("Sum: " + sum.get());
    }
}
