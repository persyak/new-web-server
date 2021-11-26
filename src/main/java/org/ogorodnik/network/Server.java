package org.ogorodnik.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private String webAppPath;

    public Server(int port, String webAppPath) {
        this.port = port;
        this.webAppPath = webAppPath;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            while(true){
                try(Socket socket = serverSocket.accept();
                    BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedOutputStream socketWriter = new BufferedOutputStream(socket.getOutputStream())){
                    ResponseWriter responseWriter = new ResponseWriter();
                    RequestHandler requestHandler =
                            new RequestHandler(socketReader, socketWriter, webAppPath, responseWriter);
                    try{
                        requestHandler.handle();
                    } catch (RuntimeException exception) {
                        responseWriter.writeInternalServerErrorResponse(socketWriter);
                    }
                }
            }
        }
    }
}
