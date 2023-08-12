package exercise;

public class MaxThread extends Thread {

    private final int[] numbers;
    private int max;

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
    }

    public int getMax() {
        return max;
    }
}
