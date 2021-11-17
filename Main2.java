import cs2030.simulator.Simulator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    private static final double ZERO = 0.0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int maxLength = sc.nextInt();

        ArrayList<Double> arrivalList = new ArrayList<>();
        ArrayList<Double> serviceTimes = new ArrayList<>();
        ArrayList<Double> restTimes = new ArrayList<>();

        while (sc.hasNextDouble()) {
            arrivalList.add(sc.nextDouble());
            serviceTimes.add(sc.nextDouble());
            restTimes.add(ZERO);
        }

        Simulator s = new Simulator(numOfServers,
                arrivalList,
                serviceTimes,
                maxLength,
                restTimes,
                (int) ZERO);
        s.simulate();
    }
}
