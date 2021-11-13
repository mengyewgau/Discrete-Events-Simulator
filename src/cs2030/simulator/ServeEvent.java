package cs2030.simulator;

public class ServeEvent extends Event {

    private static final int ONE = 1;

    public ServeEvent(Customer c1, Server server, double time) {
        super(c1, server, time);
    }

    @Override
    public Occur nextEvent(Store s1) {

        // Goes to a DONE event
        //System.out.println("Transit to Done");
        Customer c2 = this.getCust();
        int index = this.getServer().getId() - ONE;

        s1 = s1.notAvail(index);
        Server s = s1.getServer(index);

        // Finish serving
        double time = this.getTime() + c2.getServeTime();

        Event done = new DoneEvent(c2, s, time);

        return new Occur(s1, done);
    }

    public String toString() {

        Server server = this.getServer();

        if (!server.isHuman()) {
            return String.format("%s serves by self-check %s",
                    super.toString(),
                    this.getServer());
        } else {
            return String.format("%s serves by server %s",
                    super.toString(),
                    this.getServer());
        }
    }
}
