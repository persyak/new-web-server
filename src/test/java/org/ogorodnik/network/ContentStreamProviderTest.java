package org.ogorodnik.network;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ContentStreamProviderTest {

    @Test
    void getContentInputStreamTest() throws IOException {

        assertInstanceOf(BufferedInputStream.class,
                ContentStreamProvider.getContentInputStream("src/test/resources/testSocketFile.txt"));
    }

    @Test
    void testThatBufferedInputStreamReadsProperDataFromInputStream() throws IOException {
        String testContent = "this is a test content";
        try (InputStream testInputStream = new ByteArrayInputStream(testContent.getBytes())) {
            try (BufferedInputStream testBufferedInputStream =
                         ContentStreamProvider.getContentInputStream(testInputStream)) {
                StringBuilder stringBuilder = new StringBuilder();
                byte[] content = testBufferedInputStream.readAllBytes();
                for (byte symbol : content) {
                    if (symbol != -1) {
                        stringBuilder.append((char) symbol);
                    }
                }
                assertEquals(testContent, stringBuilder.toString());
            }
        }
    }
}