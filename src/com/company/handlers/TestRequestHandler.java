package com.company.handlers;

import java.io.*;
import java.util.stream.Stream;

/**
 * Created by igoru on 23-Apr-17.
 */
public class TestRequestHandler implements RequestHandler {
    @Override
    public void process(InputStream in, OutputStream out) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        final Stream<String> lines = bufferedReader.lines();
        System.out.println("request:");
        lines.forEach(System.out::println);
        out.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: hello world!").getBytes());
    }
}
