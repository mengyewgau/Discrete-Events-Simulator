package cs2030.simulator;

public class LeaveEvent extends Event {
    LeaveEvent(Customer cust) {
        super(cust, cust.getTime(), true);
    }

    @Override
    public Pair<Store, Event> nextEvent(Store s1) {
        return new Pair<Store, Event>(s1, new Final());
    }

    @Override
    public String toString() {
        return String.format("%s leaves", super.toString());
    }
}
