import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static double getRate(String from, String to) throws Exception {
        String urlStr = "https://api.exchangerate.host/convert?from=" + from + "&to=" + to + "&amount=1";
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) sb.append(line);
        in.close();

        String resp = sb.toString();
        // Debug: check what we got
        // System.out.println(resp);

        // Look for "rate": inside "info"
        int idx = resp.indexOf("\"rate\":");
        if (idx == -1) throw new Exception("Unexpected API response");

        int start = idx + 7;
        while (start < resp.length() &&
              (resp.charAt(start) == ' ' || resp.charAt(start) == '"')) start++;

        int end = start;
        while (end < resp.length() &&
              (Character.isDigit(resp.charAt(end)) || resp.charAt(end) == '.')) end++;

        String val = resp.substring(start, end).trim();
        return Double.parseDouble(val);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("From currency (e.g., USD): ");
        String from = sc.nextLine().trim().toUpperCase();
        System.out.print("To currency (e.g., INR): ");
        String to = sc.nextLine().trim().toUpperCase();
        System.out.print("Amount: ");
        double amount = sc.nextDouble();

        try {
            double rate = getRate(from, to);
            System.out.printf("1 %s = %.6f %s%n", from, rate, to);
            System.out.printf("%.2f %s = %.2f %s%n", amount, from, amount * rate, to);
        } catch (Exception e) {
            System.out.println("Error fetching rate: " + e.getMessage());
        }

        sc.close();
    }
}
