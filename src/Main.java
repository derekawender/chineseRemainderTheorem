
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please list the numbers you would like to mod by. (Space separated): \n");
        String[] mods = input.nextLine().split(" ");
        System.out.println("\nPlease list the remainder when modding by each of the numbers already listed respectively. (Space separated):\n");
        String[] remainders = input.nextLine().split(" ");
        int result = Calculator.solve(mods, remainders);
        System.out.println("The answer is: " + result);
    }
}
