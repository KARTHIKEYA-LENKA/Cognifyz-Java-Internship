import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server running on port 5000...");
        Socket client = server.accept();
        System.out.println("Client connected: " + client.getInetAddress());

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        // Simple echo loop with manual send from server console
        Thread reader = new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("Client: " + line);
                }
            } catch (IOException e) { /* ignore */ }
        });
        reader.start();

        String toSend;
        while ((toSend = console.readLine()) != null) {
            out.println(toSend);
        }

        client.close();
        server.close();
    }
}
