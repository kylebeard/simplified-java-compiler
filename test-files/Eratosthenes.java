import java.util.*;

public class Eratosthenes {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Upper limit: ");
        int limit = in.nextInt();

        doSieve(limit);
    }

    private static void doSieve(int limit) {
        boolean[] flags = new boolean[limit + 1];
        for (int i = 0; i < flags.length; ++i)
            flags[i] = true;
        flags[0] = flags[1] = false;

        for (int i = 2; 2 * i <= limit; ++i) {
            for (int j = 2 * i; j <= limit; j += i) {
                flags[j] = false;
            }
        }

        int totalPrimes = 0;
        System.out.print("The primes less than " + limit + " are...");
        for (int i = 0; i < flags.length; ++i)
            if (flags[i]) {
                ++totalPrimes;
                System.out.print(i + " ");
            }
        System.out.println();
        System.out.println("(" + totalPrimes + " primes total)");
    }
}
