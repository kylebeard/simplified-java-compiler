package test;

import java.util.*;

public class PiApprox {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numTrials = 1000000;

        System.out.print("How many trials? (default: " + numTrials + ") ");
        String line = in.nextLine();
        if (!line.isBlank())
            numTrials = new Integer(line).intValue();

        double piApprox = approximatePi(numTrials);
        System.out.println("Approximated value of π: " + piApprox);
        double relError = (Math.PI - piApprox) / Math.PI;
        if (relError < 0)
            relError = -relError;
        System.out.println("Relative error: " + (relError * 100) + "%");
    }

    private static double approximatePi(int numTrials) {
        int count = 0;
        Random random = new Random();
        for (int i = 0; i < numTrials; ++i) {
            double x = random.nextDouble(), y = random.nextDouble();
            if (x * x + y * y < 1)
                ++count;
        }

        return (4.0 * count) / numTrials;
    }
}
