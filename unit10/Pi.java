package unit10;

public class Pi {
    static int den;

    public static double piDay(double n, boolean subtract) {
        den = 1;
        if (den == 501) {
            return n;
        }
        if (subtract = true) {
            n -= 4 / den;
            den += 2;
            piDay(n, false);
        }
        if (subtract = false) {
            n += 4 / den;
            den += 2;
            piDay(n, true);
        }
        return n;
    }

    public static void main(String args[]) {
        double pi = piDay(1, false);
        System.out.println("pi = " + pi);
    }
}