import server.TCPServer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Forge DB...");

        try {
            TCPServer.runTCPServer();
        } catch (Exception e) {
            System.err.println("Forge DB failed to start.");
            e.printStackTrace();
        }
    }
}