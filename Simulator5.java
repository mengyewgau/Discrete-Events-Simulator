package cs2030.simulator;

/** Represents Simulator for Main5
 * @author Gau Meng Yew
 */


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Optional;
import java.util.Queue;

public class Simulator5 {

    private final int seed;
    private final int numOfServers;
    private final int selfCheckout;
    private final int maxLength;
    private final int numOfCusts;
    private final double lambda;
    private final double mu;
    private final double rho;
    private final double pr;
    private final double pg;
    private static final double FIRST = 0.00000;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    /** Simulator5 Constructor.
     * @param seed Seed for RandomGenerator
     * @param numOfServers The number of servers in this simulation
     * @param selfCheckout The number of self checkouts in simulation
     * @param maxLength The maximum allowed length for each queue
     * @param numOfCusts The number of customers in this simulation
     * @param lambda Passed into RandomGenerator to find arrival rate
     * @param mu Passed into RandomGenerator to find service rate
     * @param rho Passed into RandomGenerator to find server resting rate
     * @param pr The probability that a server is allowed to rest
     * @param pg The probability a customer is greedy upon arrival
     */
    public Simulator5(int seed,
                      int numOfServers,
                      int selfCheckout,
                      int maxLength,
                      int numOfCusts,
                      double lambda,
                      double mu,
                      double rho,
                      double pr,
                      double pg) {
        this.seed = seed;
        this.numOfServers = numOfServers;
        this.selfCheckout = selfCheckout;
        this.maxLength = maxLength;
        this.numOfCusts = numOfCusts;
        this.lambda = lambda;
        this.mu = mu;
        this.rho = rho;
        this.pg = pg;
        this.pr = pr;

    }


    /** Executes the simulation.
     *  Main execution for Main5 in here
     */
    public void simulate() {
        Generator gen = new Generator(this.seed,
                this.lambda,
                this.mu,
                this.rho);


        // Simulation Statistics

        double totalWait = ZERO;
        int numServed = ZERO;
        int numLeft = ZERO;

        PriorityQueue<Event> events = new PriorityQueue<>();

        int totalServers = this.numOfServers + this.selfCheckout;
        Store store = new Store(totalServers, this.numOfServers, this.maxLength);


        ArrayList<Double> arrivalTimes = new ArrayList<Double>();
        arrivalTimes.add(FIRST);
        // Create array list of arrivals first
        for (int i = ONE; i < this.numOfCusts; i++) {
            double prevTime = arrivalTimes.get(i - ONE);
            arrivalTimes.add(i, prevTime + gen.interArrivalTime());
        }

        // Create arrival events and append them
        for (int i = ZERO; i < arrivalTimes.size(); i++) {

            double greed = gen.custType();
            Customer c1;

            if (greed > this.pg) {
                c1 = new Customer(i + ONE,
                        arrivalTimes.get(i),
                        Optional.<Generator>of(gen),
                        false);
            } else {
                c1 = new Customer(i + ONE,
                        arrivalTimes.get(i),
                        Optional.<Generator>of(gen),
                        true);
            }
            events.add(new ArrivalEvent(c1));
        }



        while (!events.isEmpty()) {

            // Get current event and print
            // Ok if arrival event, as nextEvent(store) solves serve problem

            Event cur = events.poll();
            if (cur.isCanExecute()) {
                System.out.println(cur);
            }
            // Execute the event and update the store
            Pair<Store, Event> updateBoth = cur.nextEvent(store);


            // Update the store for execution in next line
            store = updateBoth.getT();
            Event e = updateBoth.getV();

            Server eventServer = cur.getServer();
            int curServerID = cur.getServer().getId();
            int index = curServerID - ONE;
            double breakTime = ZERO;
            if (cur.toString().contains("done") &&
                    (eventServer.isHuman())) {
                // Approach:
                // After each done event, execute a new Serve event
                // if there are still customers in the queue
                // Extend this to include Wait events as well

                // Should the server rest?

                if (gen.canRest() <= this.pr) {
                    breakTime = gen.restTime();
                } else {
                    breakTime = ZERO;
                }

                // Update the next serve time of the server
                double nextTime = breakTime + cur.getTime();

                store = store.addSlack(curServerID, nextTime);
                Server curS = store.getServer(index);

                Queue<Customer> queue = store.getQueue(index);
                if (!queue.isEmpty()) {
                    Customer waiting = queue.poll();
                    store = store.updateServeTime(index, nextTime);

                    // Update the serve time
                    Event serverBreak = new BreakEvent(
                            waiting,
                            curS,
                            nextTime);
                    events.add(serverBreak);
                } else {
                    store = store.unSlack(index);
                }

            } else if (cur.toString().contains("done")) {
                // Skip break event because SCO has no rest
                // Get the server
                Server checkout = store.getServer(index);
                Queue<Customer> queue = store.getSCOQueue();

                if (!queue.isEmpty()) {
                    Customer waitingCustomer = queue.poll();
                    double serveEventTime = cur.getTime();
                    Event serveEvent = new ServeEvent(
                            waitingCustomer,
                            checkout,
                            serveEventTime);
                    events.add(serveEvent);
                }
            }


            // Handling break Events
            if (cur.toString().contains("break")) {
                store = store.unSlack(index);
                Event serveEvent = new ServeEvent(
                        cur.getCust(),
                        cur.getServer(),
                        cur.getTime()
                );
                events.add(serveEvent);
            }

            if (cur.toString().contains("serves")) {
                totalWait += cur.getWait();
                numServed++;
            }  else if (cur.toString().contains("leaves")) {
                numLeft++;
            }
            // Adding the executed next event
            if (!(cur.toString().contains("Final"))) {
                events.add(e);
            }
        }

        double averageWait = totalWait / (double) numServed;
        System.out.println(String.format("[%.3f %d %d]", averageWait,
                numServed,
                numLeft));
    }


}
