import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileEncryptionDecryption {
    public static void process(String inFile, String outFile, int key) throws IOException {
        try (FileInputStream fis = new FileInputStream(inFile);
             FileOutputStream fos = new FileOutputStream(outFile)) {
            int b;
            while ((b = fis.read()) != -1) {
                fos.write(b ^ key); // XOR
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Encrypt or Decrypt (e/d): ");
        String mode = sc.nextLine().trim().toLowerCase();
        System.out.print("Input file path: ");
        String in = sc.nextLine().trim();
        System.out.print("Output file path: ");
        String out = sc.nextLine().trim();
        System.out.print("Numeric key (0-255): ");
        int key = sc.nextInt();

        try {
            process(in, out, key);
            System.out.println("Done. Output saved to: " + out);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        sc.close();
    }
}
