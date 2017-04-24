package com.company.response;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by igoru on 24-Apr-17.
 */
public class Responses {
    public static String HTTP_400 = "HTTP/1.1 400 Bad Request";
    public static String HTTP_500 = "HTTP/1.1 500";
    public static String HTTP_200 = "HTTP/1.1 200 OK";

    public static HttpResponse prepareHttp200(OutputStream os) {
        HttpResponse response = prepareHttpResponse(os);
        response.setStatusLine(HTTP_200);
        return response;
    }

    public static HttpResponse prepareHttp400(OutputStream os) {
        HttpResponse response = prepareHttpResponse(os);
        response.setStatusLine(HTTP_400);
        return response;
    }

    public static HttpResponse prepareHttp500(OutputStream os) {
        HttpResponse response = prepareHttpResponse(os);
        response.setStatusLine(HTTP_500);
        return response;
    }

    public static HttpResponse prepareHttpResponse(OutputStream os) {
        HttpResponse response = new HttpResponse(os);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        response.addHeader("Date", simpleDateFormat.format(new Date()));
        response.addHeader("Content-type", "text/plain");
        return response;
    }
}
