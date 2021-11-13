package cs2030.simulator;

class Customer {


    private final int id;
    private final double time;
    private final double serveTime;


    Customer(int id, double time, double serveTime) {
        this.id = id;
        this.time = time;
        this.serveTime = serveTime;
    }

    public int getID() {

        return this.id;
    }

    public double getTime() {

        return this.time;
    }

    public String toString() {
        return String.format("%d", this.id);
    }

    public double getServeTime() {
        return this.serveTime;
    }

}
