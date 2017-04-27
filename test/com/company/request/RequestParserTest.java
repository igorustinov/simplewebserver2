package com.company.request;

import java.io.ByteArrayInputStream;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by igoru on 27-Apr-17.
 */
public class RequestParserTest {
    @org.junit.Test
    public void parse() throws Exception {

        String requestStr =
                "GET /index.html HTTP/1.1\n" +
                "Host: www.example.com\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                "Accept-Language: en-US,en;q=0.5\n" +
                "Accept-Encoding: gzip, deflate\n" +
                "Connection: keep-alive\n" +
                "Upgrade-Insecure-Requests: 1";
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(requestStr.getBytes());
        HttpRequest request = new HttpRequest(inputStream);
        final RequestParser requestParser = new RequestParser(request);
        assertTrue(requestParser.parse());
    }

    @org.junit.Test
    public void parsePost() throws Exception {

        String requestStr =
                "POST /index.html HTTP/1.1\n" +
                        "Host: www.example.com\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                        "Accept-Language: en-US,en;q=0.5\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: keep-alive\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "\n" +
                        "something interesting";
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(requestStr.getBytes());
        HttpRequest request = new HttpRequest(inputStream);
        final RequestParser requestParser = new RequestParser(request);
        assertTrue(requestParser.parse());
    }

    @org.junit.Test
    public void parsePostNeg1() throws Exception {

        String requestStr =
                        "Host: www.example.com\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                        "Accept-Language: en-US,en;q=0.5\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: keep-alive\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "\n" +
                        "something interesting";
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(requestStr.getBytes());
        HttpRequest request = new HttpRequest(inputStream);
        final RequestParser requestParser = new RequestParser(request);
        assertFalse(requestParser.parse());
    }

    @org.junit.Test
    public void parsePostNeg2() throws Exception {

        String requestStr =
                "POST /index.html HTTP/1.1\n" +
                        "Host: www.example.com\n" +
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0\n" +
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" +
                        "Accept-Language: en-US,en;q=0.5\n" +
                        "Accept-Encoding: gzip, deflate\n" +
                        "Connection: keep-alive\n" +
                        "Upgrade-Insecure-Requests: 1\n" +
                        "something interesting";
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(requestStr.getBytes());
        HttpRequest request = new HttpRequest(inputStream);
        final RequestParser requestParser = new RequestParser(request);
        assertFalse(requestParser.parse());
    }

}