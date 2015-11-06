/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.HTTPhandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

/**
 * HTTP Handler for the requests operations on the collection of games
 * active on the server. 
 */
public class DefaultHandler extends catanHTTPHandler {

    /**
     * The handle method calls the correct command through the CommandFacade
     * @param he
     * @throws IOException 
     */
    @Override
    public void handle(HttpExchange he) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
