/**Test the Body class*/
public class TestBody {
    /**
     * test pairforce
     */
    public static void main(String[] args) {
        checkPairForce();
    }

    /**
     * Checks whether two Doubles are equal and prints the result.
     *
     * @param expected Expected double
     * @param actual   Actual double
     * @param label    Label for the "test" case.
     * @param eps      Tolerance for the double comparison
     */
    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.println("Fail: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("Pass: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("Fail: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     * Checks the Body class to make sure it works
     */
    private static void checkPairForce() {
        System.out.println("Checking Body class...");

        Body a = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");

        Body b = new Body(2.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");

        double pairforce = a.calcForceExertedBy(b);

        checkEquals(8.33e-10, pairforce, "pairforce", 0.01);
    }
}