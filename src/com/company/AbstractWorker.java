package com.company;

import java.net.Socket;

/**
 * Created by igoru on 23-Apr-17.
 */
public abstract class AbstractWorker implements Runnable {

    private Socket clientSocket = null;

    public AbstractWorker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    protected Socket getClientSocket() {
        return clientSocket;
    }
}
