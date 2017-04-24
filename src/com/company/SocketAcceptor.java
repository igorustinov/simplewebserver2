package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by igoru on 16-Apr-17.
 */
public class SocketAcceptor implements Runnable {

    private int portNum = 8080;
    private ServerSocket socket;
    private boolean stopped = false;
    private boolean started = false;

    public SocketAcceptor(int portNum) {
        this.portNum = portNum;
    }

    @Override
    public void run() {

        try {
            this.socket = new ServerSocket(portNum);
            started = true;

            Socket clientSocket;
            while (!stopped) {
                clientSocket = this.socket.accept();
                new Thread(new Worker(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
    }


    public void stop() {
        this.stopped = true;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isRunning() {
        return started && !stopped;
    }

    public boolean isStopped() {
        return stopped;
    }

    public boolean isStarted() {
        return started;
    }
}
