import server.HttpServer;

public class Main {
public static void main(String [] args){
    HttpServer server = new HttpServer(5000);
    server.start();
}    
}
