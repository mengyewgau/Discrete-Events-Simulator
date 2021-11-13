package cs2030.simulator;

public class Occur {

    private final Store store; // Stores the shop event is occuring
    private final Event event; // Store the required event

    Occur(Store s, Event e) {
        this.store = s;
        this.event = e;
    }

    public Store getStore() {
        return this.store;
    }

    public Event getEvent() {
        return this.event;
    }
}
