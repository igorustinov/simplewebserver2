package com.company.request;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by igoru on 23-Apr-17.
 */
public class RequestParser {

    private HttpRequest request;
    private StringBuilder sb = new StringBuilder();

    public RequestParser(HttpRequest request) {
        if (request == null) {
            throw new NullPointerException("request");
        }
        this.request = request;
    }

    public boolean parse() {

        final InputStream is = request.getStream();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        try {
            //Request-Line
            String line = bufferedReader.readLine();
            parseRequestLine(line);

            //Header
            line = bufferedReader.readLine();
            while (line.length() > 0) {
                parseHeader(line);
                line = bufferedReader.readLine();
            }

            //Body
            if (bufferedReader.ready()) {
                while(line != null) {
                    parseBody(line);
                    line = bufferedReader.readLine();
                }
                request.setContent(sb.toString().getBytes());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parseRequestLine(String line) {
        line = line.toUpperCase();
        final String[] tokens = line.split(" ");
        if (tokens.length != 3) {
            throw new ParsingException("Invalid request line: " + line);
        }

        try {
            request.setMethod(Method.valueOf(tokens[0]));
        } catch (IllegalArgumentException e) {
            throw new ParsingException("Not supported HTTP method");
        }

        try {
            request.setUri(new URI(tokens[1]));
        } catch (URISyntaxException e) {
            throw new ParsingException("Invalid URI");
        }

        if (HttpRequest.HTTP_1_1.equals(tokens[2])) {
            request.setVersion(HttpRequest.HTTP_1_1);
        } else {
            throw new NotImplementedException();
        }
    }

    private void parseHeader(String line) {
        if (line == null || line.length() == 0) {
            return;
        }
        final int colon = line.indexOf(":");
        if (colon == -1) {
            throw new ParsingException("Invalid header " + line);
        }
        String key = line.substring(0, colon - 1).trim();
        if (key.length() == 0) {
            throw new ParsingException("Invalid header " + line);
        }

        String value = line.substring(colon, line.length() - 1).trim();
        if (value.length() == 0) {
            throw new ParsingException("Invalid header " + line);
        }
    }

    private void parseBody(String line) {
        sb.append(line);
    }

}
