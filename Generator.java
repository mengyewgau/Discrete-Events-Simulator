package cs2030.simulator;



public class Generator {
    private final RandomGenerator generator;

    Generator(int seed, double lambda, double mu, double rho) {
        this.generator = new RandomGenerator(seed, lambda, mu, rho);
    }

    public double interArrivalTime() {
        return this.generator.genInterArrivalTime();
    }

    public double serveTime() {
        return this.generator.genServiceTime();
    }

    public double canRest() {
        return this.generator.genRandomRest();
    }

    public double restTime() {
        return this.generator.genRestPeriod();
    }

    public double custType() {
        return this.generator.genCustomerType();
    }
}
