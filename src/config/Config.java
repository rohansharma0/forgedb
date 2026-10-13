package config;

public class Config {
    public static String host = "127.0.0.1";
    public static int port = 7379;

    private Config() {
    }

    public static void configure(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--h":
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Missing value for --h");
                    }
                    Config.host = args[++i];
                    break;

                case "--p":
                    if (i + 1 >= args.length) {
                        throw new IllegalArgumentException("Missing value for --p");
                    }
                    Config.port = Integer.parseInt(args[++i]);
                    break;
            }
        }
    }
}