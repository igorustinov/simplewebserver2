package com.company.handlers;

import com.company.request.HttpRequest;
import com.company.response.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by igoru on 23-Apr-17.
 */
public interface RequestHandler {
    void process(HttpRequest request, HttpResponse response);
}
