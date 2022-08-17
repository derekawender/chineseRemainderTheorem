import java.util.Arrays;

public class Calculator {
    public static int M = 1;
    public static int solve(String[] mod, String[] rem) {
        if (!checkCoprime(mod)) {
            System.out.println("At least two of the mods you entered aren't coprime with each other.\n" +
                    "Please try entering them again after starting the program again");
            System.exit(0);
        } else{
            M = 1;
            int sum = 0;
            int[] intMods = Arrays.stream(mod)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int [] intrem = Arrays.stream(rem)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            System.out.println(Arrays.toString(intMods));
            System.out.println(Arrays.toString(intrem));

            for (int i = 0; i < intMods.length; i++) {
                M *= intMods[i];
            }
            int[] inverses = findInverses(intMods);
            System.out.println(sum);
            System.out.println(M);
            for (int i = 0; i < inverses.length; i++){
                sum += inverses[i] * (M/intMods[i]) * intrem[i];

            }
            return sum % M;
        }
        return 0;
    }
    /** computes inverses for final computation and returns them in a String array */
    public static int[] findInverses(int[] mods) {
        int[] inverses = new int[mods.length];
        for (int i = 0; i < mods.length; i++) {
            inverses[i] = findInverse(M/mods[i], mods[i]);
        }
        return inverses;
    }
    /** Finds the inverse of M/mi (m1) mod mi (m2) */
    public static int findInverse(int m1, int m2) {
        int remainder = 0;
        int inverse = 0;

        while (remainder != 1) {
            inverse++;
            remainder = (inverse * m1) % m2;
        }



        return inverse;
    }
    /** checks if all of the mods in a String[] are coprime (element-wise) */
    public static boolean checkCoprime(String[] mod) {
        for (int i = 0; i < mod.length - 1; i++) {
            for (int j = i + 1; j < mod.length; j++) {
                if (!(checkGCD(Integer.parseInt(mod[i]), Integer.parseInt(mod[j])))) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Uses Euclidean algorithm to check if GCD is 1 and the two ints
     * m1 and m2 are coprime. Returns false if GCD isn't 1.
     * Uses the fact that GCD(x, y) = Gcd (min(x, y), max(x,y) % min(x, y)).
     */
    static boolean checkGCD(int m1, int m2) {
        if (m1 == m2) {
            return false;
        }
        int comDenom = Math.max(m1, m2) % Math.min(m1, m2);
        if (comDenom == 1) {
            return true;
        } else if (comDenom == 0) {
            return false;
        } else {
            return checkGCD(Math.min(m1, m2), comDenom);
        }
    }

}
