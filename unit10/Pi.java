package unit10;

public class Pi {
    static double n;
    public static double piDay(double den, boolean subtract) {
        if (den == 7) {
            return n;
        }
        if (subtract = true){
            n -= 4 / den;
            den += 2;
            piDay(den, false);
        }
        if (subtract = false) {
            n += 4 / den;
            den += 2;
            piDay(den, true);
        }
        return n;
    }

    public static void main(String args[]) {
        double pi = piDay(1, true);
        System.out.println("pi = " + pi);
    }
}