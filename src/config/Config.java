package config;

public class Config {
    public static String host;
    public static int port;

    private Config() {
    }

    public static void configure(String[] args) {
        String host = "127.0.0.1";
        int port = 7379;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--h":
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Missing value for --h");
                    }
                    host = args[++i];
                    break;

                case "--p":
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Missing value for --p");
                    }
                    port = Integer.parseInt(args[++i]);
                    break;
            }
        }
        Config.host = host;
        Config.port = port;
    }
}