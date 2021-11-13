package cs2030.simulator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Store {

    private final List<Server> servers;
    private final List<Queue<Customer>> allQueues;
    private final int scoQueue; // Index of the SCO queue
    private final int max;
    private static final int ZERO = 0;
    private static final int ONE = 1;

    Store(int servers, int human, int max) {
        // servers is the total num of human + SCO
        // human is num of humans
        // max is max queue length

        // Create human servers
        Stream<Server> humans = IntStream.rangeClosed(ONE, human)
                .mapToObj(x -> new Server(x, true));

        // Create self-checkout servers
        Stream<Server> selfCheck = IntStream.rangeClosed(human + ONE, servers)
                .mapToObj(x -> new Server(x, false));

        // Concat the streams
        this.servers = Stream.concat(humans, selfCheck)
                .collect(Collectors.toList());

        this.allQueues = IntStream.range(ZERO, human)
                .mapToObj(x -> new LinkedList<Customer>())
                .collect(Collectors.toList());

        if (human + ONE > servers) {
            this.scoQueue = Integer.MAX_VALUE;
        } else {
            this.scoQueue = human;
            this.allQueues.add(new LinkedList<Customer>());
        }
        this.max = max;
        // The queue id corresponds to Server ID - ONE
    }

    Store(List<Server> servers,
           List<Queue<Customer>> allQueues,
           int max,
           int scoQueue) {
        this.servers = servers;
        this.allQueues = allQueues;
        this.max = max;
        this.scoQueue = scoQueue;
    }

    // Queue checking Methods //

    public Optional<Integer> firstAvail(Customer c) {
        for (Server s : this.servers) {
            if (s.isAvail(c)) {
                return Optional.of(s.getId() - ONE);
            }
        }
        return Optional.empty();
    }

    // Return the id of the needed server
    public Optional<Integer> firstWait() {
        for (int q = ZERO; q < this.allQueues.size(); q++) {
            Queue<Customer> cur = this.allQueues.get(q);
            Server s = this.getServer(q);

            if (cur.size() < this.max && s.isWorking()) {
                //System.out.println("firstWait length " + cur.size());
                return Optional.of(q);
            } else if (!s.isWorking() && cur.size() < this.max - ONE) {
                return Optional.of(q);
            }
        }
        return Optional.empty();
    }

    // Updating Methods //


    public Store serveNow(int index) {
        List<Server> servers = new ArrayList<>(this.servers);
        List<Queue<Customer>> customers = new ArrayList<>(this.allQueues);


        // Makes sure obtain most updated Server
        Server s = servers.remove(index);
        s = s.serveFirstCust();
        servers.add(index, s);

        // Find server's queue and remove first cust
        if (index >= this.scoQueue) {
            index = this.scoQueue;
        }
        Queue<Customer> change = customers.get(index);
        //change.poll();
        //System.out.println("ServeNow length " + change.size());
        customers.set(index, change);

        return new Store(servers, customers, this.max, this.scoQueue);
    }

    public Store joinQueue(int serverId, Customer c) {
        List<Server> servers = new ArrayList<>(this.servers);
        List<Queue<Customer>> customers = new ArrayList<>(this.allQueues);
        int index = serverId - ONE;

        // Makes sure obtain most updated Server
        Server s = servers.remove(index);

        s = s.notAvail();
        servers.add(index, s);

        // Add the first customer
        if (index >= this.scoQueue) {
            index = this.scoQueue;
        }
        Queue<Customer> change = customers.get(index);
        change.offer(c);
        //System.out.println("Join length " + change.size());
        customers.set(index, change);

        return new Store(servers, customers, this.max, this.scoQueue);
    }


    public Store addSlack(int serverId, double restTime) {
        List<Server> servers = new ArrayList<>(this.servers);
        List<Queue<Customer>> customers = new ArrayList<>(this.allQueues);
        int index = serverId - ONE;

        // Makes sure obtain most updated Server
        Server s = servers.remove(index);
        s = s.slackStatus(restTime);
        servers.add(index, s);

        return new Store(servers, customers, this.max, this.scoQueue);
    }

    public Store unSlack(int index) {
        List<Server> servers = new ArrayList<>(this.servers);
        List<Queue<Customer>> customers = new ArrayList<>(this.allQueues);

        // Makes sure obtain most updated Server
        Server s = servers.remove(index);
        s = s.unSlack();
        servers.add(index, s);

        return new Store(servers, customers, this.max, this.scoQueue);

    }

    public Store notAvail(int index) {
        List<Server> servers = new ArrayList<>(this.servers);
        List<Queue<Customer>> customers = new ArrayList<>(this.allQueues);

        Server s = servers.remove(index);
        s = s.notAvail();
        servers.add(index, s);

        return new Store(servers, customers, this.max, this.scoQueue);
    }

    public Store updateServeTime(int index, double nextTime) {
        List<Server> servers = new ArrayList<>(this.servers);
        List<Queue<Customer>> customers = new ArrayList<>(this.allQueues);

        Server s = servers.remove(index);
        s = s.nextServeTime(nextTime);
        servers.add(index, s);

        return new Store(servers, customers, this.max, this.scoQueue);
    }

    public Server getServer(int index) {
        return this.servers.get(index);
    }

    public Queue<Customer> getSCOQueue() {
        return this.allQueues.get(this.scoQueue);
    }

    public Queue<Customer> getQueue(int index) {
        return this.allQueues.get(index);
    }

}
