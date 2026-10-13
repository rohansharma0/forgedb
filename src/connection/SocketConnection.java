package connection;

import java.io.*;
import java.net.Socket;

public class SocketConnection implements Connection {

    private final BufferedWriter writer;
    private final BufferedReader reader;

    public SocketConnection(Socket socket) throws IOException {
        this.reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
        this.writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())
        );
    }

    @Override
    public String read() throws IOException {
        return reader.readLine();
    }

    @Override
    public void write(String response) throws IOException {
        writer.write(response);
        writer.newLine();
        writer.flush();
    }
}
