package cs2030.simulator;

public class Pair<T, V> {

    private final T store; // Stores the shop event is occuring
    private final V event; // Store the required event

    Pair(T s, V e) {
        this.store = s;
        this.event = e;
    }

    public T getT() {
        return this.store;
    }

    public V getV() {
        return this.event;
    }
}
