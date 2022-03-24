public class Calculator {
    public static void solve(String[] mod, String[] rem) {

    }
    /** checks if all of the mods in a String[] are coprime */
    public static boolean checkCoprime(String[] mod) {
        for (int i = 0; i < mod.length; i++) {
            for (int j = i + 1; j < mod.length; j++) {
                if (!(checkGCD(Integer.parseInt(mod[i]), Integer.parseInt(mod[j])))) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Uses Euclidean algorithm to check if GCD is 1 and the two ints
     * m1 and m2 are coprime.
     */
    static boolean checkGCD(int m1, int m2) {
        if (m1 == m2) {
            return false;
        }
        int comDenom = Math.min(m1, m2) % Math.max(m1, m2);
        if (comDenom == 1) {
            return true;
        } else if (comDenom == 0) {
            return false;
        } else {
            return checkGCD(Math.min(m1, m2), comDenom);
        }
    }
}
