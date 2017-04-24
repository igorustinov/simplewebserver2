package com.company.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by igoru on 23-Apr-17.
 */
public interface RequestHandler {
    void process(InputStream in, OutputStream out) throws IOException;
}
