import java.security.SecureRandom;
import java.util.Scanner;

public class RandomPasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{};:<>/?";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SecureRandom rand = new SecureRandom();

        System.out.print("Password length: ");
        int length = sc.nextInt();
        sc.nextLine();

        System.out.print("Include uppercase? (y/n): ");
        boolean up = sc.nextLine().trim().equalsIgnoreCase("y");
        System.out.print("Include lowercase? (y/n): ");
        boolean low = sc.nextLine().trim().equalsIgnoreCase("y");
        System.out.print("Include digits? (y/n): ");
        boolean dig = sc.nextLine().trim().equalsIgnoreCase("y");
        System.out.print("Include special chars? (y/n): ");
        boolean sp = sc.nextLine().trim().equalsIgnoreCase("y");

        StringBuilder pool = new StringBuilder();
        if (up) pool.append(UPPER);
        if (low) pool.append(LOWER);
        if (dig) pool.append(DIGITS);
        if (sp) pool.append(SPECIAL);

        if (pool.length() == 0) {
            System.out.println("No character types selected. Exiting.");
            sc.close();
            return;
        }

        StringBuilder pwd = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int idx = rand.nextInt(pool.length());
            pwd.append(pool.charAt(idx));
        }

        System.out.println("Generated password: " + pwd.toString());
        sc.close();
    }
}
