package non_forkjoin;

import java.util.concurrent.Callable;

class SumCalculatorTask implements Callable<Integer> {
    int[] numbers;

    SumCalculatorTask(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        return sum;
    }
}
