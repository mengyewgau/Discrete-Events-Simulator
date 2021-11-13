package cs2030.simulator;

public class LeaveEvent extends Event {
    public LeaveEvent(Customer cust) {
        super(cust, cust.getTime());
    }

    @Override
    public Occur nextEvent(Store s1) {
        return new Occur(s1, new Final());
    }

    @Override
    public String toString() {
        return String.format("%s leaves", super.toString());
    }
}
