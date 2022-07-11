package org.ogorodnik.network;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpMethodTest {

    @Test
    public void testHttpMethod(){
        assertEquals(HttpMethod.GET, HttpMethod.valueOf("GET"));
        assertEquals(HttpMethod.POST, HttpMethod.valueOf("POST"));
        assertThrows(IllegalArgumentException.class, () -> HttpMethod.valueOf("VALUE"));
    }
}