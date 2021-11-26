package org.ogorodnik.network;

public class ResourceReader {

    String getResource(String uri, String webAppPath){
        String fullUri = webAppPath + uri;
        return fullUri;
    }
}
