/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.HTTPhandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import shared.exceptions.HTTPBadRequest;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;

/**
 * HTTP Handler for the requests starting with /Games
 * operations on the collection of games active on the server. 
 */
public class GamesHandler extends catanHTTPHandler {

	
	
	public GamesHandler() {
		super();
	}
	
	@Override
    public void handle(HttpExchange exchange) throws IOException {
		
		System.out.println("CLIENT CALLED: " + exchange.getRequestURI().getPath());
		
		try {
			
			//prep command
			URI url = exchange.getRequestURI();
			String newCommand = "games." + url.getPath().replace("/games/", "");
    		
			String content = null;
			String gameId = null;
			
			//NEED TO VALIDATE catain.user COOKIE
			
			//if it is the list (GET)
			if(this.isGet(exchange) && newCommand.equals("games.list")) {
				
			} 
			//if it is post and not list
			else if (this.isPost(exchange) && !newCommand.equals("games.list")) {
				//Convert content (POST) across to String
				content = this.getContent(exchange);
			} 
			//not supported
			else {
				throw new HTTPBadRequest("Error: \"" + exchange.getRequestMethod() + "\" is no supported!");
			}
			
			//Call the facade
			String result = this.sendToFacade(newCommand, content, gameId);
			
			if(result != null) {
				if (newCommand.equals("games.list")) {
					//send in body as json
					this.setJSONResponse(exchange, result);
				} else {
					//create cookie
					this.addCookie(exchange, result);
				}				
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
