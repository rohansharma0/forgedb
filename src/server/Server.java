package server;

import config.Config;
import connection.Connection;
import connection.SocketConnection;
import executor.CommandExecutor;
import protocol.Cmd;
import protocol.CommandParser;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("Starting Forge DB...");
        Config.configure(args);
        try {
            runServer();
        } catch (Exception e) {
            System.err.println("Forge DB failed to start : "+ e.getMessage());
        }
    }

    private static void runServer() {

        try(ServerSocket server = new ServerSocket()) {
            server.bind(new InetSocketAddress(Config.host, Config.port));

            System.out.println("Forge DB listening on " + Config.host + ":" + Config.port);
            while (true) {
                Socket client = server.accept();
                System.out.println("Client connected: " + client.getRemoteSocketAddress());
                handleClient(client);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void handleClient(Socket client) {
        try (client) {
            Connection c = new SocketConnection(client);
            String request;
            while ((request = c.read()) != null) {
                System.out.println("Received: " + request);
                Cmd cmd = CommandParser.parse(request);
                String res = CommandExecutor.execute(cmd);
                c.write(res);
            }
        } catch (IOException e) {
            System.err.println("Client connection error: " + e.getMessage());
        }
        System.out.println("Client disconnected.");
    }
}
