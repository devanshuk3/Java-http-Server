package http;

import java.io.*;
import java.util.*;

public class HttpResponse {
    private int statusCode;
    private String statusMessage;
    private Map<String, String> headers;
    private String body;
    
    public HttpResponse(){
        this.headers = new HashMap<>();;
        this.statusCode = 200;
        this.statusMessage = "OK";
        setDefaultHeaders();
    }

    private void setDefaultHeaders(){
        headers.put("Content-type", "text/html");
        headers.put("Connection", "close");
    }

    public void setStatus(int code, String message){
        this.statusCode = code;
        this.statusMessage = message;
    }

    public void setHeader(String key, String value){
        headers.put(key, value);
    }
    public void setBody(String body){
        this.body = body;
        headers.put("Content-Length", String.valueOf(body.length()));
    }

    public String build(){
        StringBuilder response = new StringBuilder();

        response.append("HTTP/ 1.1 ")
        .append(statusCode)
        .append(" ")
        .append(statusMessage)
        .append("\r\n");

        //headers
        for (Map.Entry<String, String> entry : headers.entrySet()){
            response.append(entry.getKey())
            .append(": ")
            .append(entry.getValue())
            .append("\r\n");
        }
        response.append("\r\n");

        if(body!=null)response.append(body);

        return response.toString();
    }

    public byte[] getBytes(){
        return build().getBytes();
    }
}
