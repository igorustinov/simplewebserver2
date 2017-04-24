package com.company.response;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by igoru on 24-Apr-17.
 */
public class HttpResponse {

    private OutputStream outputStream;
    private StringBuilder body;
    private String statusLine;
    private Map<String, String> headers;

    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
        headers = new HashMap<>();
    }

    public void write() {
        if (statusLine == null) {
            throw new IllegalStateException();
        }
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println(statusLine);
        for (Map.Entry<String, String> header : headers.entrySet()) {
            printWriter.println(header.getKey() + ": " + header.getValue());
        }
        printWriter.println("");
        if (body != null) {
            printWriter.print(body.toString());
        }
        printWriter.flush();
    }

    public void append(String content) {
        if (body == null) {
            body = new StringBuilder();
        }
        body.append(content);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void setStatusLine(String line) {
        this.statusLine = line;
    }

}
