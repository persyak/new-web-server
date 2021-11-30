package org.ogorodnik.network;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ContentStreamProvider {
    String content;

    public ContentStreamProvider(String content) {
        this.content = content;
    }

    BufferedInputStream getContentInputStream(String content) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(content));
    }
}
