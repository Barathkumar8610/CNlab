import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started");

            while (true) {
                // Accept a new client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Create a new thread to handle the client
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            String str;
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader input1 = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            for (int i = 0; i < 4; i++) {
                str = input.readLine();
                System.out.println(str);
                String str1 = input1.readLine();
                output.println(str1);
            }

            // Handle client input and output here

            clientSocket.close(); // Close the client socket when done
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}