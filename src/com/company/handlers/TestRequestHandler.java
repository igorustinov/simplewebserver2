package com.company.handlers;

import com.company.request.HttpRequest;
import com.company.response.HttpResponse;
import com.company.response.Responses;

/**
 * Created by igoru on 23-Apr-17.
 */
public class TestRequestHandler implements RequestHandler {

    @Override
    public void process(HttpRequest request, HttpResponse response) {
        response.setStatusLine(Responses.HTTP_200);
        response.append("test response");
    }
}
