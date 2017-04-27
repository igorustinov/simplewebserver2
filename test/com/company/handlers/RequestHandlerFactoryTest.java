package com.company.handlers;

import com.company.request.HttpRequest;
import com.company.request.Method;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;

/**
 * Created by igoru on 27-Apr-17.
 */
public class RequestHandlerFactoryTest {

    @Test
    public void testGet() {
        HttpRequest req = new HttpRequest(null);
        req.setMethod(Method.GET);
        RequestHandlerFactory factory = new RequestHandlerFactory();
        final RequestHandler requestHandler = factory.getRequestHandler(req);
        assertTrue(requestHandler instanceof GetFileHandler);
    }

    @Test
    public void testPost() {
        HttpRequest req = new HttpRequest(null);
        req.setMethod(Method.POST);
        RequestHandlerFactory factory = new RequestHandlerFactory();
        final RequestHandler requestHandler = factory.getRequestHandler(req);
        assertTrue(requestHandler instanceof UpdateFileHandler);
    }

    @Test(expected= NotImplementedException.class)
    public void testOptions() {
        HttpRequest req = new HttpRequest(null);
        req.setMethod(Method.OPTIONS);
        RequestHandlerFactory factory = new RequestHandlerFactory();
        final RequestHandler requestHandler = factory.getRequestHandler(req);
    }

}