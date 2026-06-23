package server;

import java.io.*;
import java.net.*;
import http.HttpResponse;
public class ClientHandler {
    
    private Socket clientSocket;

    public ClientHandler(Socket socket){
        this.clientSocket = socket;
        //intialize the socket for client comms
    }

    public void handle(){
        try{
            InputStream input = clientSocket.getInputStream(); 
            //reads the input stream for the network sockets
            BufferedReader reader = new BufferedReader (new InputStreamReader(input)); 
            //get data in chunks

            OutputStream output = clientSocket.getOutputStream();
            
            String requestLine = reader.readLine();
            if(requestLine!=null)System.out.println("Request:" + requestLine);

            String headerLine;
             while((headerLine=reader.readLine()) != null && !headerLine.isEmpty()){

             }

             HttpResponse response = new HttpResponse();

             response.setStatus(200, "OK");
             response.setBody("Hello World!");
             response.setHeader("Content-Type", "text/html");

             output.write(response.getBytes());
             output.flush();

             System.out.println("Response sent!");
             System.out.println();

             clientSocket.close();
        }
        catch(IOException e){ 
            System.err.println("Error handling client: " + e.getMessage()); 
            //handle the exceptions
        }
        }
    }
