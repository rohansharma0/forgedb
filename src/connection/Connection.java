package connection;

import java.io.IOException;

public interface Connection {
    String read() throws IOException;

    void write(String response) throws IOException;
}

