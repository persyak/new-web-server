package org.ogorodnik.network;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Server server = new Server(3000, "/Users/alexone/IdeaProjects/new-web-server/resources/webapp");
        server.start();
    }
}
