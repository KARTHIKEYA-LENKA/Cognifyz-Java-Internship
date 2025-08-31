import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of grades: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Invalid number.");
            sc.close();
            return;
        }
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter grade " + i + ": ");
            double g = sc.nextDouble();
            sum += g;
        }
        double avg = sum / n;
        System.out.printf("Average = %.2f%n", avg);
        String remark;
        if (avg >= 90) remark = "A";
        else if (avg >= 75) remark = "B";
        else if (avg >= 60) remark = "C";
        else if (avg >= 40) remark = "D";
        else remark = "F";
        System.out.println("Grade: " + remark);
        sc.close();
    }
}
