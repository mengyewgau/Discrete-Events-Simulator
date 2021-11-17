package cs2030.simulator;

public class BreakEvent extends Event {
    private static final int ONE = 1;

    BreakEvent(Customer c1, Server server, double time) {
        super(c1, server, time, false);
    }

    @Override
    public Pair<Store, Event> nextEvent(Store s1) {

        Event e = new Final();

        return new Pair<Store, Event>(s1, e);
    }

    public String toString() {
        return "break";
    }
}
