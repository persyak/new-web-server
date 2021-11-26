package org.ogorodnik.network;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
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
        Request request = new Request();
        try {
            request = requestParser.parseRequest(socketReader);
        } catch (IOException exception) {
            responseWriter.writeBadRequestResponse(socketWriter);
            exception.printStackTrace();
        }

        ResourceReader resourceReader = new ResourceReader();
        String content = "";

        try {
            content = resourceReader.getResource(request.uri, webAppPath);
        } catch (Exception e) {
            responseWriter.writeNotFoundResponse(socketWriter);
            e.printStackTrace();
        }

        responseWriter.writeSuccessResponse(socketWriter, content);

    }
}
