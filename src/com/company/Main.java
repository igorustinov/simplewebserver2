package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SocketAcceptor socketAcceptor = new SocketAcceptor(Configuration.INSTANCE.getPortNumber());
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        while(in.hasNext()) {
            final String next = in.next();
            if ("start".equalsIgnoreCase(next)) {
                if (!socketAcceptor.isRunning()) {
                    System.out.println("starting...");
                    socketAcceptor.start();
                    System.out.println("server started");
                }
            } else if ("stop".equalsIgnoreCase(next)) {
                if (socketAcceptor.isRunning()) {
                    System.out.println("stopping...");
                    socketAcceptor.stop();
                    System.exit(0);
                }
            }
        }

    }
}
