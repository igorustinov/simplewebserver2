package com.company.handlers;

import com.company.request.HttpRequest;
import com.company.request.Method;
import com.company.response.HttpResponse;
import com.company.response.Responses;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by igoru on 24-Apr-17.
 */
public class UpdateFileHandler extends AbstractFileRequestHandler{
    @Override
    public void process(HttpRequest request, HttpResponse response) {

        final String path = request.getUri().getPath();
        if (request.getMethod() == Method.POST) {
            getResourceDao().create(path, request.getContent());
        } else if (request.getMethod() == Method.PUT) {
            getResourceDao().update(path, request.getContent());
        } else if (request.getMethod() == Method.DELETE) {
            getResourceDao().delete(path);
        } else {
            throw new NotImplementedException();
        }
        response.setStatusLine(Responses.HTTP_200);
    }
}
