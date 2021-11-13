package cs2030.simulator;

public class WaitEvent extends Event {
    private static final int ONE = 1;

    public WaitEvent(Customer c1, Server server, double time) {
        super(c1, server, time);
    }

    @Override
    public Occur nextEvent(Store s1) {
        Customer c2 = this.getCust();
        int serverId = this.getServer().getId();

        s1 = s1.joinQueue(serverId, c2);

        Event e = new Final();
        return new Occur(s1, e);

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
