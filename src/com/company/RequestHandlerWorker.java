package com.company;

import com.company.handlers.RequestHandler;
import com.company.handlers.RequestHandlerFactory;
import com.company.request.HttpRequest;
import com.company.request.RequestParser;
import com.company.response.HttpResponse;
import com.company.response.Responses;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by igoru on 16-Apr-17.
 */
public class RequestHandlerWorker extends AbstractWorker {

    private RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory();

    public RequestHandlerWorker(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = getClientSocket().getInputStream();
            OutputStream outputStream = getClientSocket().getOutputStream();

            handle(inputStream, outputStream);

            outputStream.close();
            inputStream.close();
            System.out.println("processed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(InputStream inputStream, OutputStream outputStream) {
        final HttpRequest request = new HttpRequest(inputStream);
        HttpResponse response;
        try {
            final RequestParser requestParser = new RequestParser(request);
            if (!requestParser.parse()) {
                response = Responses.prepareHttp400(outputStream);
            } else {
                response = Responses.prepareHttpResponse(outputStream);
                RequestHandler handler = requestHandlerFactory.getRequestHandler(request);
                handler.process(request, response);
            }
        } catch (Exception ex) {
            System.out.println(ex);
            response = Responses.prepareHttp500(outputStream);
        }
        response.write();
    }
}
