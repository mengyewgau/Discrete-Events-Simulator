package cs2030.simulator;

import java.util.Optional;

public class ArrivalEvent extends Event {
    private static final int ONE = 1;
    private static final int NEGATIVE_ONE = -1;

    ArrivalEvent(Customer c1) {
        super(c1, c1.getTime(), true);
    }

    @Override
    public Pair<Store, Event> nextEvent(Store s1) {
        Customer c2 = this.getCust();
        Optional<Integer> avail = s1.firstAvail(c2);

        int index = avail.orElseGet(() -> NEGATIVE_ONE);

        if (index != NEGATIVE_ONE) {
            // Able to serve
            Server server = s1.getServer(index);

            s1 = s1.notAvail(index);
            Event serve = new ServeEvent(c2, server, c2.getTime());

            return new Pair<Store, Event>(s1, serve);
        } else {
            avail = s1.firstWait();
            index = avail.orElseGet(() -> NEGATIVE_ONE);

            if (index != NEGATIVE_ONE) {
                Server s  = s1.getServer(index);

                if (c2.toString().contains("greedy")) {
                    s = s1.findShortest();
                }

                // Available queue for waiting, no serve
                Event wait = new WaitEvent(c2, s, c2.getTime());

                return new Pair<Store, Event>(s1, wait);
            } else {
                // If no wait, no serve, customer leaves

                Event leave = new LeaveEvent(this.getCust());
                return new Pair<Store, Event>(s1, leave);
            }

        }
    }

    public String toString() {
        return String.format("%s arrives", super.toString());
    }
}
