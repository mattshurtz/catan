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
 * HTTP Handler for the requests starting with /Moves
 * operations on the Model active on the server. 
 */
public class MovesHandler extends catanHTTPHandler {

	public MovesHandler() {
		super();
	}
    
	@Override
    public void handle(HttpExchange exchange) throws IOException {
        
        //check for post method
            this.checkIsPost(exchange);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
