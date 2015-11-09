/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.HTTPhandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * HTTP Handler for managing the swagger page operations.
 */
public class DefaultHandler extends catanHTTPHandler {

    /**
     * The handle method calls the correct command through the CommandFacade
     * @param he
     * @throws IOException 
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
       
        System.out.println(exchange.getRequestURI().toString());
        
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
    }
    
}
