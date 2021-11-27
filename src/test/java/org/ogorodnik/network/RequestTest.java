package org.ogorodnik.network;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    public void testRequestGetAndSet(){
        Request request = new Request();
        request.setHeaders("key", "value");
        request.setUri("index.html");
        request.setHttpMethod(HttpMethod.GET);

        assertEquals("value", request.getHeaders("key"));
        assertEquals("index.html", request.getUri());
        assertEquals(HttpMethod.GET, request.getHttpMethod());
    }

}