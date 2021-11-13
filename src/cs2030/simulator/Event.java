package cs2030.simulator;


abstract class Event implements Comparable<Event> {

    private final Customer cust;
    private final Server server;
    private final double timestamp;
    private static final int DUMMY_ONE = -1;
    private static final double DOUBLE_DUMMY = -1.0;

    // Arrival events will pass a dummy server
    Event(Customer c1,
          Server s,
          double time) {
        this.cust = c1;
        this.server = s;
        this.timestamp = time;
    }

    Event(Customer c1, double time) {
        this.cust = c1;
        this.server = new Server(DUMMY_ONE, true);
        this.timestamp = time;
    }

    Event() {
        this.cust = new Customer(DUMMY_ONE, DOUBLE_DUMMY, DOUBLE_DUMMY);
        this.server = new Server(DUMMY_ONE, true);
        this.timestamp = DOUBLE_DUMMY;
    }

    // Abstract methods required for implementation //

    public abstract Occur nextEvent(Store s1);

    // Getter Methods//

    public int getID() {
        return this.getCust().getID();
    }

    public Customer getCust() {
        return this.cust;
    }

    public Server getServer() {
        return this.server;
    }

    public double getTime() {
        return this.timestamp;
    }

    public double getWait() {
        return this.timestamp - this.cust.getTime();
    }

    // Implement natural sorting for events//

    public int compareTo(Event e1) {
        if (this.getTime() == e1.getTime()) {
            return this.getID() - e1.getID();
        } else {
            return Double.compare(this.getTime(), e1.getTime());
        }
    }

    public String toString() {
        return String.format("%.3f %s", this.timestamp,
                this.cust);
    }
}
