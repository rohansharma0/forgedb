package server;

import config.Config;
import store.Store;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static Config config;

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
        try (
                Socket socket = client;
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                )
        ) {
            String request;
            while ((request = reader.readLine()) != null) {
                System.out.println("Received: " + request);
                String[] cmd = request.split(" ");
                response(client,cmd);
            }
        } catch (IOException e) {
            System.err.println("Client connection error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Client disconnected.");
    }

    private static void response(Socket client, String[] cmd) throws Exception {
        switch (cmd[0]){
            case "PING":
                evalPING(client,cmd);
                break;
            case "SET":
                evalSET(client,cmd);
                break;
            case "GET":
                evalGET(client,cmd);
                break;
            default:
                evalPING(client,cmd);
                break;
        }
    }


    private static void evalGET(Socket client, String[] cmd) throws Exception {
        if(cmd.length > 2){
            throw new Exception("error in arguments");
        }
        String v = Store.get(cmd[1]);
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream())
            );
            if(v == null){
                writer.write("(nil)");
            }else{
                writer.write(v);
            }

            writer.newLine();
            writer.flush();
        }catch (Exception e){

        }
    }

    private static void evalSET(Socket client, String[] cmd) throws Exception {
        if(cmd.length <= 2){
            throw new Exception("error in arguments");
        }
        Store.set(cmd[1],cmd[2]);
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream())
            );
            writer.write("OK");
            writer.newLine();
            writer.flush();
        }catch (Exception e){

        }
    }

    private static void evalPING(Socket client, String[] cmd) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(client.getOutputStream())
            );
            if (cmd.length == 1) {
                writer.write("PONG");
                writer.newLine();
            }else{
                String res ="";
                for(int i = 1; i< cmd.length; i++) res += cmd[i];
                writer.write(res);
                writer.newLine();
            }
            writer.flush();
        }catch (Exception e){

        }
    }
}
