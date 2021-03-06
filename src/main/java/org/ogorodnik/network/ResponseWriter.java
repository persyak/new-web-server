package org.ogorodnik.network;

import java.io.*;

class ResponseWriter {

    private final static String LINE_END = "\n";
    private final static String HTTP_OK_RESPONSE = "HTTP/1.1 200 OK";
    private final static String HTTP_BAD_REQUEST_RESPONSE = "HTTP/1.1 400 BAD_REQUEST";
    private final static String HTTP_NOT_FOUND_RESPONSE = "HTTP/1.1 404 NOT_FOUND";
    private final static String HTTP_INTERNAL_SERVER_ERROR_RESPONSE = "HTTP/1.1 500 INTERNAL_SERVER_ERROR";

    void writeSuccessResponse(BufferedOutputStream socketWriter, String content) throws IOException {

        try (BufferedInputStream contentInputStream = ContentStreamProvider.getContentInputStream(content)) {
            socketWriter.write(HTTP_OK_RESPONSE.getBytes());
            socketWriter.write(LINE_END.getBytes());
            socketWriter.write(LINE_END.getBytes());

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = contentInputStream.read(buffer)) != -1) {
                socketWriter.write(buffer, 0, bytesRead);
            }
            socketWriter.flush();
        }
    }

    void writeBadRequestResponse(BufferedOutputStream socketWriter) throws IOException {
        socketWriter.write(HTTP_BAD_REQUEST_RESPONSE.getBytes());
        socketWriter.write(LINE_END.getBytes());
        socketWriter.write(LINE_END.getBytes());
    }

    void writeNotFoundResponse(BufferedOutputStream socketWriter) throws IOException {
        socketWriter.write(HTTP_NOT_FOUND_RESPONSE.getBytes());
        socketWriter.write(LINE_END.getBytes());
        socketWriter.write(LINE_END.getBytes());
    }

    void writeInternalServerErrorResponse(BufferedOutputStream socketWriter) throws IOException {
        socketWriter.write(HTTP_INTERNAL_SERVER_ERROR_RESPONSE.getBytes());
        socketWriter.write(LINE_END.getBytes());
        socketWriter.write(LINE_END.getBytes());
    }
}
