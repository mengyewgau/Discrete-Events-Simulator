package cs2030.simulator;

import java.util.Optional;

class Customer {


    private final int id;
    private final double time;
    private final double serveTime;
    private final Optional<Generator> gen;
    private final boolean isGreedy;

    private static final double DUMMY = -1.0;

    Customer(int id, double time, double serveTime) {
        this.id = id;
        this.time = time;
        this.serveTime = serveTime;
        this.gen = Optional.empty();
        this.isGreedy = false;
    }

    Customer(int id, double time, Optional<Generator> gen, boolean greed) {
        this.id = id;
        this.time = time;
        this.serveTime = DUMMY;
        this.gen = gen;
        this.isGreedy = greed;
    }

    public int getID() {

        return this.id;
    }

    public double getTime() {

        return this.time;
    }

    public double getServeTime() {
        Optional<Double> time = this.gen.map(x -> x.serveTime());
        return time.orElse(this.serveTime);
    }

    public String toString() {
        if (!this.isGreedy) {
            return String.format("%d", this.id);
        } else {
            return String.format("%d(greedy)", this.id);
        }
    }
}
