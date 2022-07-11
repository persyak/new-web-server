package org.ogorodnik.network;

import java.io.*;
import java.net.Socket;

class RequestHandler implements Runnable {

    Socket socket;
    String webAppPath;

    public RequestHandler(Socket socket, String webAppPath) {
        this.socket = socket;
        this.webAppPath = webAppPath;
    }

    private void handle() throws IOException {
        try (
                BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedOutputStream socketWriter = new BufferedOutputStream(socket.getOutputStream())) {
            ResponseWriter responseWriter = new ResponseWriter();
            try {
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
            } catch (RuntimeException exception) {
                responseWriter.writeInternalServerErrorResponse(socketWriter);
            }
        }
        socket.close();
    }

    @Override
    public void run() {
        try {
            handle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
