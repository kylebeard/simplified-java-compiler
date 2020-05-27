package test;

import java.util.Scanner;

public class TemperatureConverter {

    private Scanner in = new Scanner(System.in);

    void start() {
        System.out.print("Enter a temperature in °C: ");
        double C = in.nextDouble();
        double F = (9.0 / 5) * C + 32;
        System.out.println();
        System.out.println(C + "°C is the same as " + F + "°F.");
    }

    public static void main(String[] args) {
        TemperatureConverter tc = new TemperatureConverter();
        tc.start();
    }
}