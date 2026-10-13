
package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ForgeCLI {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("127.0.0.1", 8379);
                Scanner scanner = new Scanner(System.in);
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())
                );
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                )
        ) {
            System.out.println("Connected to Forge DB!");
            System.out.println("Type EXIT to quit.");

            while (true) {
                System.out.print("forge> ");

                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("EXIT")) {
                    break;
                }

                writer.write(command);
                writer.newLine();
                writer.flush();

                String response = reader.readLine();

                if (response == null) {
                    System.out.println("Server disconnected.");
                    break;
                }

                System.out.println(response);
            }

        } catch (IOException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }
}
