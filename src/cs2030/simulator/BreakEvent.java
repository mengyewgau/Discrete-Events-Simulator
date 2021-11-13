package cs2030.simulator;

public class BreakEvent extends Event {
    private static final int ONE = 1;

    BreakEvent(Customer c1, Server server, double time) {
        super(c1, server, time);
    }

    @Override
    public Occur nextEvent(Store s1) {

        /*
        //Obsolete code, did not compile correctly after level 4
            for some reason.
        Customer c2 = this.getCust();
        int serverId = this.getServer().getId();

        s1 = s1.joinQueue(serverId, c2);
        // End the wait event


        // The serve time is equal to serve time of customer before
        // the curCust + server::getWhenServe
        Server server = s1.getServers().get(serverId - 1);

        // This returns the serve time of NEXT customer
        double waitTime = server.getWhenServe(c2);

        Event e = new ServeEvent(c2, server,waitTime);
        */

        Event e = new Final();

        return new Occur(s1, e);
    }
}
