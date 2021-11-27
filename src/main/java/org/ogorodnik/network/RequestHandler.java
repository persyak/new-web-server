package org.ogorodnik.network;

import java.io.*;

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
                String content = resourceReader.getResource(request.getUri(), webAppPath);

                responseWriter.writeSuccessResponse(socketWriter, content);
            } catch (FileNotFoundException e) {
                responseWriter.writeNotFoundResponse(socketWriter);
            }
        } catch (IOException exception) {
            responseWriter.writeBadRequestResponse(socketWriter);
        }
    }
}
