package com.company.handlers;

import com.company.request.HttpRequest;

/**
 * Created by igoru on 23-Apr-17.
 */
public class RequestHandlerFactory {

    public RequestHandler getRequestHandler(HttpRequest request) {
        return new TestRequestHandler();
    }
}
