package org.ogorodnik.network;

import java.io.BufferedReader;
import java.io.IOException;

class RequestParser {
    private final static String REQUEST_PARSER = " ";
    private final static String REQUEST_END_LINE = "";
    private final static int HEADERS_LENGTH_IDENTIFIER = 2;

    public Request parseRequest(BufferedReader socketReader) throws IOException {
        Request request = new Request();
        String requestLine = socketReader.readLine();

        urlAndHttpMethod(request, requestLine);
        headersMethod(request, socketReader);

        return request;
    }

    void urlAndHttpMethod(Request request, String requestLine){
        String[] split = requestLine.split(REQUEST_PARSER);
        request.setHttpMethod(HttpMethod.valueOf(split[0]));
        request.setUri(split[1]);
    }

    void headersMethod(Request request, BufferedReader socketReader) throws IOException {
        String headerLine;
        while(!(headerLine = socketReader.readLine()).equals(REQUEST_END_LINE)){
            String[] headers = headerLine.split(REQUEST_PARSER);
            StringBuilder headersValue = new StringBuilder(headers[1]);
            if(headers.length>HEADERS_LENGTH_IDENTIFIER){
                for(int i=2; i<headers.length; i++){
                    headersValue.append(REQUEST_PARSER).append(headers[i]);
                }
            }
            String headersValueString = headersValue.toString();
            request.setHeaders(headers[0], headersValueString);
        }
    }

}
