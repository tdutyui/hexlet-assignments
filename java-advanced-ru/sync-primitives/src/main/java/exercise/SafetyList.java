package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {

    List<Integer> list = new ArrayList<>();

    public synchronized void add(int num) {
        list.add(num);
    }

    public int get(int index) {
       return list.get(index);
    }

    public int getSize() {
        return list.size();
    }
}
