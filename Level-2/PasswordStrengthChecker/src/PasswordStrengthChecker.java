import java.util.Scanner;

public class PasswordStrengthChecker {
    public static String strength(String pwd) {
        int score = 0;
        if (pwd.length() >= 8) score++;
        if (pwd.matches(".*[A-Z].*")) score++;
        if (pwd.matches(".*[a-z].*")) score++;
        if (pwd.matches(".*\\d.*")) score++;
        if (pwd.matches(".*[^A-Za-z0-9].*")) score++;

        if (score <= 2) return "Weak";
        if (score == 3) return "Moderate";
        return "Strong";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter password: ");
        String pwd = sc.nextLine();
        System.out.println("Password strength: " + strength(pwd));
        sc.close();
    }
}
