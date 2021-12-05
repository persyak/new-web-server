package org.ogorodnik.network;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ContentStreamProviderTest {

    @Test
    void getContentInputStreamTest() throws FileNotFoundException {

        assertInstanceOf(BufferedInputStream.class,
                ContentStreamProvider.getContentInputStream("src/test/resources/testSocketFile.txt"));
    }
}