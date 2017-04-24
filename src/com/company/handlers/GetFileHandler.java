package com.company.handlers;

import com.company.request.HttpRequest;
import com.company.response.HttpResponse;
import com.company.response.Responses;

import java.nio.charset.StandardCharsets;

/**
 * Created by igoru on 24-Apr-17.
 */
public class GetFileHandler extends AbstractFileRequestHandler {

    @Override
    public void process(HttpRequest request, HttpResponse response) {
        final String path = request.getUri().getPath();
        final byte[] bytes = getResourceDao().read(path);
        response.append(new String(bytes, StandardCharsets.UTF_8));
        response.setStatusLine(Responses.HTTP_200);
    }
}
