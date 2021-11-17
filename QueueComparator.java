package cs2030.simulator;

import java.util.Comparator;

class QueueComparator implements Comparator<Pair<Integer, Integer>> {

    public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        if (p1.getT() == p2.getT()) {
            return p1.getV() - p2.getV();
        } else {
            return p1.getT() - p2.getT();
        }
    }
}
