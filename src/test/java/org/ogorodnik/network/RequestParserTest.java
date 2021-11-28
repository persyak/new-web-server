package org.ogorodnik.network;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {

    @Test
    void parseRequest() throws IOException {
        RequestParser testRequestParser = new RequestParser();
        BufferedReader testSocketReader =
                new BufferedReader(new FileReader("src/test/resources/testSocketFile.txt"));
        Request testRequest = testRequestParser.parseRequest(testSocketReader);
        assertEquals(HttpMethod.GET, testRequest.getHttpMethod());
        assertEquals("testpage.html", testRequest.getUri());
        assertEquals("ru.wikipedia.org: localhost", testRequest.getHeaders("Host:"));
        assertEquals("Mozilla/5.0 (X11; U; Linux i686; ru; rv:1.9b5) Gecko/2008050509 Firefox/3.0b5",
                testRequest.getHeaders("User-Agent:"));
    }

    @Test
    void urlAndHttpMethodTest() {
        Request testRequest = new Request();
        String testReadLine = "GET testpage.html HTTP/1.1";
        RequestParser testRequestParser = new RequestParser();
        testRequestParser.urlAndHttpMethod(testRequest, testReadLine);
        assertEquals(HttpMethod.GET, testRequest.getHttpMethod());
        assertEquals("testpage.html", testRequest.getUri());
    }

    @Test
    void headersMethodTest() throws IOException {
        RequestParser testRequestParser = new RequestParser();
        Request testRequest = new Request();
        BufferedReader testSocketReader =
                new BufferedReader(new FileReader("src/test/resources/testSocketFile.txt"));
        testSocketReader.readLine();
        testRequestParser.headersMethod(testRequest, testSocketReader);
        assertEquals("ru.wikipedia.org: localhost", testRequest.getHeaders("Host:"));
        assertEquals("Mozilla/5.0 (X11; U; Linux i686; ru; rv:1.9b5) Gecko/2008050509 Firefox/3.0b5",
                testRequest.getHeaders("User-Agent:"));
    }
}