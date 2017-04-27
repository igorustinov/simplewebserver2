package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static boolean start = false;

    public static void main(String[] args) {

        Arrays.stream(args).forEach(Main::parseArg);

        SocketAcceptor socketAcceptor = new SocketAcceptor(Configuration.INSTANCE.getPortNumber());
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        if (start) {
            go(socketAcceptor);
        }
        while(in.hasNext()) {
            final String next = in.next();
            if ("start".equalsIgnoreCase(next)) {
                go(socketAcceptor);
            } else if ("stop".equalsIgnoreCase(next)) {
                if (socketAcceptor.isRunning()) {
                    System.out.println("stopping...");
                    socketAcceptor.stop();
                    System.exit(0);
                }
            }
        }

    }

    private static void go(SocketAcceptor socketAcceptor) {
        if (!socketAcceptor.isRunning()) {
            System.out.println("starting...");
            socketAcceptor.start();
            System.out.println("server started on " + Configuration.INSTANCE.getPortNumber());
        }
    }

    private static void parseArg(String arg) {
        if (isStart(arg)) {
            start = true;
        } else {
            final int port = tryGetPort(arg);
            if (port != -1) {
                Configuration.INSTANCE.setPortNumber(port);
            }
        }
    }

    private static int tryGetPort(String arg) {
        try {
            int port = Integer.parseInt(arg);
            return port >= 0 ? port : -1;
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private static boolean isStart(String arg) {
        return "start".equalsIgnoreCase(arg);
    }
}
