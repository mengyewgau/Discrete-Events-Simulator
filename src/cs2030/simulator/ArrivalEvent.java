package cs2030.simulator;

import java.util.Optional;

public class ArrivalEvent extends Event {
    private static final int ONE = 1;

    ArrivalEvent(Customer c1) {
        super(c1, c1.getTime());
    }

    @Override
    public Occur nextEvent(Store s1) {
        Customer c2 = this.getCust();
        Optional<Integer> avail = s1.firstAvail(c2);

        if (avail.isPresent()) {
            int index = avail.get();
            // Able to serve
            Server server = s1.getServer(index);
            s1 = s1.notAvail(index);
            Event serve = new ServeEvent(c2, server, c2.getTime());


            return new Occur(s1, serve);
        } else {
            avail = s1.firstWait();

            if (avail.isPresent()) { // Transit to wait event

                int index = avail.get();
                Server s  = s1.getServer(index);

                // Available queue for waiting, no serve
                Event wait = new WaitEvent(c2, s, c2.getTime());

                return new Occur(s1, wait);
            } else {
                // If no wait, no serve, customer leaves

                Event leave = new LeaveEvent(this.getCust());
                return new Occur(s1, leave);
            }

        }
    }

    public String toString() {
        return String.format("%s arrives", super.toString());
    }
}
