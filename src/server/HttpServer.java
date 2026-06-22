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
            ServerSocket serverSocket = new ServerSocket(port);
            running = true;
            System.out.println("Server started on port" + port);
            
            while(running){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecteddd!!!!");

                handleClient(clientSocket);
                clientSocket.close();
            }
            serverSocket.close();
        }
        catch(IOException e){
                System.err.println("Error starting server:" + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {

        OutputStream output = clientSocket.getOutputStream();
        PrintWriter writer = new PrintWriter (output, true);

        InputStream input = clientSocket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String requestLine = reader.readLine();
        if(requestLine != null){
            System.out.println("Request: " + requestLine);
        }

        String response = "client connected!";

        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html");
        writer.println("Content-Length: "+ response.length());
        writer.println();
        writer.println(response);
        writer.flush();
        System.out.println("Response set to client!");
    }

    public void stop(){
        running = false;
        System.out.println("Server stopped");
    }

    public static void main(String []args){
        HttpServer server = new HttpServer(5000);
        server.start();
    }
}
