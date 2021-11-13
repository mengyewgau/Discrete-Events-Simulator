package cs2030.simulator;

public class DoneEvent extends Event {
    private static final int ONE = 1;

    public DoneEvent(Customer c1, Server s, double time) {
        super(c1, s, time);
    }

    @Override
    public Occur nextEvent(Store s1) {
        // Customer is already in the shop queue
        // Update the server's status to remove cur Customer
        int index = this.getServer().getId() - ONE;
        s1 = s1.serveNow(index);

        //System.out.println(s.getAvail());
        //System.out.println("Done Time " + s.getWhenServe());

        return new Occur(s1, new Final());
    }

    @Override
    public String toString() {
        Server doneServer = this.getServer();

        if (!doneServer.isHuman()) {
            return String.format("%s done serving by self-check %s",
                    super.toString(),
                    this.getServer());
        } else {
            return String.format("%s done serving by server %s",
                    super.toString(),
                    this.getServer());
        }
    }
}
