package org.ogorodnik.network;

import java.io.*;

public class ResponseWriter {

    private final static String LINE_END = "\n";
    private final static String HTTP_OK_RESPONSE = "HTTP/1.1 200 OK";

    void writeSuccessResponse(BufferedOutputStream socketWriter, String content) throws IOException {
        try (BufferedInputStream contentInputStream = new BufferedInputStream(new FileInputStream(content))) {
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
        socketWriter.write("HTTP/1.1 200 OK".getBytes());
        socketWriter.write(LINE_END.getBytes());
        socketWriter.write(LINE_END.getBytes());

        socketWriter.write("400 BAD Request".getBytes());
    }

    void writeNotFoundResponse(BufferedOutputStream socketWriter) throws IOException {
        socketWriter.write("HTTP/1.1 200 OK".getBytes());
        socketWriter.write(LINE_END.getBytes());
        socketWriter.write(LINE_END.getBytes());

        socketWriter.write("404 Page was not found".getBytes());

    }

    void writeInternalServerErrorResponse(BufferedOutputStream socketWriter) throws IOException {
        socketWriter.write("HTTP/1.1 200 OK".getBytes());
        socketWriter.write(LINE_END.getBytes());
        socketWriter.write(LINE_END.getBytes());

        socketWriter.write("500 internal server error".getBytes());
    }
}
