package org.ogorodnik.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private final int port;
    private final String webAppPath;

    public Server(int port, String webAppPath) {
        this.port = port;
        this.webAppPath = webAppPath;
    }

    void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    RequestHandler requestHandler = new RequestHandler(socket, webAppPath);
                    Thread serverThread = new Thread(requestHandler);
                    serverThread.start();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
