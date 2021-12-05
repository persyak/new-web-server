package org.ogorodnik.network;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.*;

class RequestHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

    BufferedReader socketReader;
    BufferedOutputStream socketWriter;
    String webAppPath;
    ResponseWriter responseWriter;

    public RequestHandler(BufferedReader socketReader, BufferedOutputStream socketWriter, String webAppPath, ResponseWriter responseWriter) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
        this.webAppPath = webAppPath;
        this.responseWriter = responseWriter;
    }

    void handle() throws IOException {
        try {
            Request request = RequestParser.parseRequest(socketReader);

            ResourceReader resourceReader = new ResourceReader();

            try {
                String content = resourceReader.getResource(request.getUri(), webAppPath);

                responseWriter.writeSuccessResponse(socketWriter, content);
            } catch (FileNotFoundException e) {
                responseWriter.writeNotFoundResponse(socketWriter);
            }
        } catch (Exception exception) {
            responseWriter.writeBadRequestResponse(socketWriter);
        }
    }
}
