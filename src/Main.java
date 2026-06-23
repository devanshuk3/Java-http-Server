import server.HttpServer;

public class Main {
public static void main(String [] args){
    HttpServer server = new HttpServer(8080);
    server.start();
    //just a wrapper to call the server
}    
}
