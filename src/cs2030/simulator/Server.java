package cs2030.simulator;


public class Server {

    private final int id;
    private final boolean avail; // Is available
    private final double whenServe; // when to serve waiting customer
    private final boolean working;
    private final boolean human; // denotes if its SCO

    private static final double ZERO = 0.0;
    private static final int ONE = 1;

    // First construction

    Server(int id, boolean human) {
        this.id = id;
        this.avail = true;
        this.whenServe = ZERO;
        this.working = true;
        this.human = human;
    }

    Server(int id,
           boolean avail,
           double serve,
           boolean working,
           boolean human) {
        this.id = id;
        this.avail = avail;
        this.whenServe = serve;
        this.working = working;
        this.human = human;
    }


    // Update the serving status
    public Server serveFirstCust() {
        // Removal is now done in the store class
        return new Server(this.id,
                true,
                this.whenServe,
                true,
                this.human);
    }

    // Getters //

    public int getId() {
        return this.id;
    }

    public boolean isAvail(Customer c) {
        double arrivalCustomer = c.getTime();
        double nextServeTime = this.whenServe;
        return this.avail &&
                nextServeTime <= arrivalCustomer;
    }

    public Server slackStatus(double time) {
        // Gives the next serve time of the server
        return new Server(this.id,
                this.avail,
                time,
                this.working,
                this.human);
    }

    public Server unSlack() {
        return new Server(this.id,
                this.avail,
                this.whenServe,
                true,
                this.human);
    }

    public Server nextServeTime(double time) {
        return new Server(this.id,
                this.avail,
                time,
                false,
                this.human);
    }

    public Server notAvail() {
        return new Server(this.id,
                false,
                this.whenServe,
                this.working,
                this.human);
    }

    public String toString() {
        return String.format("%d", this.id);
    }

    public boolean isHuman() {
        return this.human;
    }

    public boolean isWorking() {
        return this.working;
    }

    public double getWhenServe() {
        return this.whenServe;
    }

    public boolean getAvail() {
        return this.avail;
    }
}
