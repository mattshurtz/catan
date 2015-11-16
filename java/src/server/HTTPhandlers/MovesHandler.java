/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.HTTPhandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.exceptions.HTTPBadRequest;

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
        System.out.println("CLIENT CALLED: " + exchange.getRequestURI().getPath());

            try {
                //check for post method
                this.checkIsPost(exchange);
                
                //prep command
                URI url = exchange.getRequestURI();
                String newCommand = "moves." + url.getPath().replace("/moves/", "");
                                
                //Convert content (POST) across to String
                String content = this.getContent(exchange);
                String user = ""+this.getPlayerId(exchange);
                String gameId = this.getGameId(exchange);
                
                //Call the facade
                String result = this.sendToFacade(newCommand, content, gameId, user);
                
                if(result != null) {
                	this.sendResponseBody(exchange, result);	
                } else {
                    throw new HTTPBadRequest("Invalid Arguments");
                }
 
            } catch (HTTPBadRequest e) {
                setBadRequest(exchange,e.getMessage());
		System.out.println(e.getMessage());            
            } finally {
                exchange.getResponseBody().close();
            }
            
    }
    
}
