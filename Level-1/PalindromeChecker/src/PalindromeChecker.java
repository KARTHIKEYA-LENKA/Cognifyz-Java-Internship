import java.util.Scanner;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter word or phrase: ");
        String line = sc.nextLine();
        String cleaned = line.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        if (cleaned.equals(reversed)) {
            System.out.println("It's a palindrome.");
        } else {
            System.out.println("Not a palindrome.");
        }
        sc.close();
    }
}
