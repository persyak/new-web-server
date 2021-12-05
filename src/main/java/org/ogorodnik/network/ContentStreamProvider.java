package org.ogorodnik.network;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class ContentStreamProvider {

    static BufferedInputStream getContentInputStream(String content) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(content));
    }
}
