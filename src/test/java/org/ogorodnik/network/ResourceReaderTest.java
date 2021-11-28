package org.ogorodnik.network;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceReaderTest {

    @Test
    void getResourceTest() {
        ResourceReader testResourceReader = new ResourceReader();
        assertEquals("testWebAppPath/testPath/index.html",
                testResourceReader.getResource("/index.html", "testWebAppPath/testPath"));
    }
}