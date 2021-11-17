import cs2030.simulator.Simulator5;

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

        Simulator5 s = new Simulator5(seed,
                numOfServers,
                selfCheckOut,
                maxLength,
                numOfCust,
                lambda,
                mu,
                rho,
                pr,
                pg
        );
        s.simulate();

    }
}
