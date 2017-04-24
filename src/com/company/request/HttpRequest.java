package com.company.request;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by igoru on 23-Apr-17.
 */
public class HttpRequest {

    public final static String HTTP_1_1 = "HTTP/1.1";

    private Method method;
    private byte[] content;
    private HashMap<String,String> headers;
    private String version;
    private URI uri;

    public HttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        headers = new HashMap<>();
    }

    private InputStream inputStream;

    public InputStream getStream() {
        return this.inputStream;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Map<String, String> getHeaders() {
        return new HashMap<>(headers);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
