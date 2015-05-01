package non_forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

class NumberDividerTask implements Callable<Integer> {

    int[] numbers;

    NumberDividerTask(int[] numbers) {
        this.numbers = numbers;
    }

    /*
     * If the array has less than 20 elements, then
     * just compute the sum, else split the array further.
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        List<Future<Integer>> forks = new ArrayList<Future<Integer>>();
        if (numbers.length > 20) {
            NumberDividerTask numberDividerTaskFirst =
                    new NumberDividerTask(Arrays
                            .copyOfRange(numbers, 0, numbers.length / 2));
            NumberDividerTask numberDividerTaskSecond =
                    new NumberDividerTask(Arrays
                            .copyOfRange(numbers, numbers.length / 2, numbers.length));
            forks.add(ExecutorTest.pool.submit(numberDividerTaskFirst));
            forks.add(ExecutorTest.pool.submit(numberDividerTaskSecond));
        } else {

            SumCalculatorTask sumCalculatorTask = new SumCalculatorTask(numbers);
            forks.add(ExecutorTest.pool.submit(sumCalculatorTask));
        }

        //Combine the result from all the tasks
        for (Future<Integer> task : forks) {
            sum += task.get();
        }
        return sum;
    }
}
