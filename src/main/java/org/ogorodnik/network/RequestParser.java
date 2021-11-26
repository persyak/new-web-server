package org.ogorodnik.network;

import java.io.BufferedReader;
import java.io.IOException;

class RequestParser {

    public Request parseRequest(BufferedReader socketReader) throws IOException {
        Request request = new Request();
        String requestLine = socketReader.readLine();

        urlAndHttpMethod(request, requestLine);
        headersMethod(request, socketReader);

        return request;
    }

    private void urlAndHttpMethod(Request request, String requestLine){
        String[] split = requestLine.split(" ");
        request.setHttpMethod(HttpMethod.valueOf(split[0]));
        request.setUri(split[1]);
    }

    private void headersMethod(Request request, BufferedReader socketReader) throws IOException {
        String headerLine;
        while((headerLine = socketReader.readLine()) != null){
            String[] headers = headerLine.split(":");
            request.headers.put(headers[0], headers[1]);
        }
    }

}
