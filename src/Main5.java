import cs2030.simulator.Simulator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main5 {
    private static final int ZERO = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int seed = sc.nextInt();
        int numOfServers = sc.nextInt();
        int selfCheckOut = sc.nextInt();
        int maxLength = sc.nextInt();
        int numOfCust = sc.nextInt();
        double lambda = sc.nextDouble();
        double mu = sc.nextDouble();
        double rho = sc.nextDouble();
        double pr = sc.nextDouble();
        double pg = sc.nextDouble();

        ArrayList<Double> arrivalList = new ArrayList<>();
        ArrayList<Double> serviceTimes = new ArrayList<>();
        ArrayList<Double> restTimes = new ArrayList<>();

        for (int i = ZERO; i < numOfCust; i++) {
            arrivalList.add(sc.nextDouble());
            serviceTimes.add(sc.nextDouble());
        }

        for (int i = ZERO; i < numOfCust; i++) {
            restTimes.add(sc.nextDouble());
        }


    }
}
