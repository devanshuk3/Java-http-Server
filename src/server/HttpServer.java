package server;

import java.io.*;
import java.net.*;

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
            System.out.println("Server started on port" + port);
            
            while(running){ //keeps the server running to accept multiple connections
                Socket clientSocket = serverSocket.accept(); //wait for a client to connect
                System.out.println("Client connecteddd!!!!");

                handleClient(clientSocket); //handle the client request
                clientSocket.close(); //close the client connection after req is fulfilled
            }
            serverSocket.close();
        }
        catch(IOException e){
                System.err.println("Error starting server:" + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {

        OutputStream output = clientSocket.getOutputStream(); //output stream to send response back to client
        PrintWriter writer = new PrintWriter (output, true);

        InputStream input = clientSocket.getInputStream(); //input stream to read the client's req(optional but good for understanding)
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String requestLine = reader.readLine(); //read the first line of the request
        if(requestLine != null){
            System.out.println("Request: " + requestLine);
        }
        //send http res with "client connected" message
        String response = "client connected!";
        //build res with proper headers
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html");
        writer.println("Content-Length: "+ response.length());
        writer.println(); //empty line for seperation
        writer.println(response);
        writer.flush();
        System.out.println("Response set to client!");
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