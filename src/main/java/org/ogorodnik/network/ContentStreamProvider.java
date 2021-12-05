package org.ogorodnik.network;

import java.io.*;

class ContentStreamProvider {

    static BufferedInputStream getContentInputStream(String content) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(content);
        return getContentInputStream(fileInputStream);

    }

    static BufferedInputStream getContentInputStream(InputStream inputStream) {
        return new BufferedInputStream(inputStream);
    }
}
