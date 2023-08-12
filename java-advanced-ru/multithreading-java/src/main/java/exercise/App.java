package exercise;

import java.util.HashMap;
import java.util.Map;

class App {

    public static Map<String, Integer> getMinMax(int[] numbers) {
        Map<String, Integer> map = new HashMap<>();
        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);
        maxThread.start();
        minThread.start();
        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        map.put("max", maxThread.getMax());
        map.put("min", minThread.getMin());
        return map;
    }
}
