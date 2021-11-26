package org.ogorodnik.network;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

class RequestHandler {
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
        RequestParser requestParser = new RequestParser();
        try {
            Request request = requestParser.parseRequest(socketReader);

            ResourceReader resourceReader = new ResourceReader();

            try {
                String content = resourceReader.getResource(request.uri, webAppPath);

                responseWriter.writeSuccessResponse(socketWriter, content);
            } catch (Exception e) {
                responseWriter.writeNotFoundResponse(socketWriter);
            }
        } catch (IOException exception) {
            responseWriter.writeBadRequestResponse(socketWriter);
        }
    }
}
