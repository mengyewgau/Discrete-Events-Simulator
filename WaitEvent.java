package cs2030.simulator;

public class WaitEvent extends Event {
    private static final int ONE = 1;

    WaitEvent(Customer c1, Server server, double time) {
        super(c1, server, time, true);
    }

    @Override
    public Pair<Store, Event> nextEvent(Store s1) {
        Customer c2 = this.getCust();
        int serverId = this.getServer().getId();

        s1 = s1.joinQueue(serverId, c2);

        Event e = new Final();
        return new Pair<Store, Event>(s1, e);

    }

    @Override
    public String toString() {
        Server waitServer = this.getServer();

        if (!waitServer.isHuman()) {
            return String.format("%s waits at self-check %s",
                    super.toString(),
                    this.getServer());
        } else {
            return String.format("%s waits at server %s",
                    super.toString(),
                    this.getServer());
        }
    }
}
