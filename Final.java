package cs2030.simulator;

public class Final extends Event {

    // REMEMBER TO REMOVE NULL!! IMPORTANT

    Final() {
        super();
    }

    @Override
    public Pair<Store, Event> nextEvent(Store s1) {
        return new Pair<Store, Event>(s1, this);
    }

    public String toString() {
        return "Final";
    }
}
