package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SocketAcceptor socketAcceptor = new SocketAcceptor(8081);
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

//        try {
//            Thread.sleep(20 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("stopping server");
//        socketAcceptor.stop();
    }
}
