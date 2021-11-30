package org.ogorodnik.network;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ContentStreamProviderTest {

    @Test
    void getContentInputStreamTest() throws FileNotFoundException {
        ContentStreamProvider testCOntentStreamProvider =
                new ContentStreamProvider("src/test/resources/testSocketFile.txt");

        assertInstanceOf(BufferedInputStream.class,
                testCOntentStreamProvider.getContentInputStream(testCOntentStreamProvider.content));
    }
}