package server;

import java.io.*;
import java.net.*;

//this is the driver class to start the server on the port(contains the main function)
public class HttpServer {
    private int port;
    private boolean running;
    
    public HttpServer(int port){
        this.port = port;
        this.running = false;
    }

    public void start(){
        try{
            ServerSocket serverSocket = new ServerSocket(port); //creates a server socket that listens on specified port 
            running = true;
            System.out.println("Server started on port: " + port);
            System.out.println("Full request body and content:");
            System.out.println();
            
            while(running){ //keeps the server running to accept multiple connections
                Socket clientSocket = serverSocket.accept(); //wait for a client to connect
                System.out.println("Client connecteddd!!!!");


                ClientHandler handler = new ClientHandler(clientSocket);
                handler.handle(); //object from the clientHandler to call it's function
            }
            serverSocket.close();
        }
        catch(IOException e){
                System.err.println("Error starting server:" + e.getMessage());
        }
    }
        public void stop(){
            running = false;
            System.out.println("Server stopped");
        }

    public static void main(String []args){
        HttpServer server = new HttpServer(5000); //create and start server on port 5000
        server.start();
    }
}