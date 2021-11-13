package cs2030.simulator;

public class Final extends Event {

    // REMEMBER TO REMOVE NULL!! IMPORTANT

    Final() {
        super();
    }

    @Override
    public Occur nextEvent(Store s1) {
        return new Occur(s1, this);
    }
}
