package server;

import java.io.*;
import java.net.*;
public class ClientHandler {
    
    private Socket clientSocket;

    public ClientHandler(Socket socket){
        this.clientSocket = socket; //intialize the socket for client comms
    }

    public void handle(){
        try{
            InputStream input = clientSocket.getInputStream(); 
            //start the input stream
            BufferedReader reader = new BufferedReader (new InputStreamReader(input)); 
            //get data in chunks

            OutputStream output = clientSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
             //parameters -- output is the data, and true is for autoflush on

            System.out.println("Full HTTP Request");
            System.out.println();

            String line; //iterator to get the value
            int lineCount = 0; //to index the values

            while((line = reader.readLine())!=null){ 
                //read until we encounter an empty line 
                //empty line means the end of headers
                System.out.println(line);
                lineCount++;

                if(line.isEmpty())break;
            }
            System.out.println();     //printing on console
            System.out.println("END OF REQUEST");
            System.out.println("Total lines: " + lineCount);
            System.out.println();

            String responseBody = "<h1>Client Connecteddddd!</h1><p>Check your terminal for the full request.</p>"; //body of the response
            //only the response body is being written on the page
            writer.println("HTTP/1.1 200 OK"); //content headers in the response body
            writer.println("Content-Type: text/html");
            writer.println("Content-Length: " + responseBody.length());
            writer.println();
            writer.println(responseBody);
            writer.flush();

            System.out.println("Response sent to client");
        }
        catch(IOException e){ 
            System.err.println("Error handling client: " + e.getMessage());    //handle the exceptions
        }finally{
            try{
                clientSocket.close();
            }
            catch(IOException e){ //ignore the exceptions which arrive while closing the socket connection

            }
        }
    }
}
