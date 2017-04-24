package com.company;

import com.company.handlers.RequestHandler;
import com.company.handlers.RequestHandlerFactory;
import com.company.request.HttpRequest;
import com.company.request.RequestParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by igoru on 16-Apr-17.
 */
public class Worker extends AbstractWorker {

    private RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory();

    public Worker(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = getClientSocket().getInputStream();
            OutputStream outputStream = getClientSocket().getOutputStream();

            final HttpRequest request = new HttpRequest(inputStream);
            final RequestParser requestParser = new RequestParser(request);
            if (requestParser.parse()) {
                //send 400 ??
            } else {
                RequestHandler handler = requestHandlerFactory.getRequestHandler(request);
                handler.process(inputStream, outputStream);
            }
            outputStream.close();
            inputStream.close();
            System.out.println("processed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
