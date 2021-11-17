import cs2030.simulator.Simulator;

import java.util.Scanner;
import java.util.ArrayList;

public class Main1 {
    private static final double ZERO = 0;
    private static final double ONE = 1.0;
    private static final int MAX_LENGTH = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();

        ArrayList<Double> arrivalList = new ArrayList<Double>();
        ArrayList<Double> serviceTimes = new ArrayList<Double>();
        ArrayList<Double> restTimes = new ArrayList<>();

        while (sc.hasNextDouble()) {
            arrivalList.add(sc.nextDouble());
            serviceTimes.add(ONE);
            restTimes.add(ZERO);
        }

        Simulator s = new Simulator(numOfServers,
                arrivalList,
                serviceTimes,
                MAX_LENGTH,
                restTimes,
                (int) ZERO);
        s.simulate();

    }
}