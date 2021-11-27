package org.ogorodnik.network;

public class ResourceReader {

    String getResource(String uri, String webAppPath){
        return webAppPath + uri;
    }
}
