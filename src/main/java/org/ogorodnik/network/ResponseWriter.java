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

    void writeBadRequestResponse(BufferedOutputStream socketWriter){

    }

    void writeNotFoundResponse(BufferedOutputStream socketWriter){

    }

    void writeInternalServerErrorResponse(BufferedOutputStream socketWriter){

    }
}
