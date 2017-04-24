package com.company.handlers;

import com.company.persistence.ResourceDaoImpl;
import com.company.persistence.ResourcesDao;
import com.company.request.HttpRequest;
import com.company.request.Method;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by igoru on 23-Apr-17.
 */
public class RequestHandlerFactory {

    private ResourcesDao resources = new ResourceDaoImpl("./resources");

    public RequestHandler getRequestHandler(HttpRequest request) {

        if (Method.GET == request.getMethod()) {
            final GetFileHandler getFileHandler = new GetFileHandler();
            getFileHandler.setResourcesDao(resources);
            return getFileHandler;
        } else if (Method.DELETE == request.getMethod()
                || Method.PUT == request.getMethod()
                || Method.POST == request.getMethod()) {
            final UpdateFileHandler updateFileHandler = new UpdateFileHandler();
            updateFileHandler.setResourcesDao(resources);
            return updateFileHandler;
        }

        throw new NotImplementedException();
    }
}
