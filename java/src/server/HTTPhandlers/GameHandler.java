/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.HTTPhandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import shared.exceptions.HTTPBadRequest;

import java.io.IOException;
import java.net.URI;

/**
 * HTTP Handler for the requests starting with /Game
 * operations on a game active on the server. 
 */
public class GameHandler extends catanHTTPHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
		System.out.println("CLIENT CALLED: " + exchange.getRequestURI().getPath());
		
		try {
			
			//prep command
			URI url = exchange.getRequestURI();
			String newCommand = "game." + url.getPath().replace("/game/", "");
    		
			String content = null;
			String gameId = this.getGameId(exchange);
			String user = ""+this.getPlayerId(exchange);
			
			//if it is the list (GET)
			this.checkisGet(exchange);
			
			//Call the facade
			String result = this.sendToFacade(newCommand, content, gameId, user);
			
			if(result != null) {
					//send in body as json
					this.sendResponseBody(exchange, result);				
			} else {
				//login failed
				throw new HTTPBadRequest("Failed to get list of games");
			}
			
		} catch (HTTPBadRequest e) {
			setBadRequest(exchange,e.getMessage());
			System.out.println(e.getMessage());
		} finally {
			exchange.getResponseBody().close();
		}	
    }
    
}
