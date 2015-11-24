/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.HTTPhandlers;

import com.sun.net.httpserver.HttpExchange;

import server.facade.IServerFacade;

import java.io.IOException;
import java.net.URI;
import shared.exceptions.HTTPBadRequest;

/**
 * HTTP Handler for the requests starting with /Games
 * operations on the collection of games active on the server. 
 */
public class GamesHandler extends catanHTTPHandler {

	
	
	public GamesHandler(IServerFacade facade) {
		super(facade);
	}
	
	@Override
    public void handle(HttpExchange exchange) throws IOException {
		
		System.out.println("CLIENT CALLED: " + exchange.getRequestURI().getPath());
        try {
            //System.out.println("Player Id: " + this.getPlayerId( exchange ) );
            //System.out.println("Player name: " + this.getPlayerName( exchange ) );
			//prep command
			URI url = exchange.getRequestURI();
			String newCommand = "games." + url.getPath().replace("/games/", "");
    		
			String content = null;
			int playerId = this.getPlayerId(exchange);
			
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
			String result = this.sendToFacade(newCommand, content, 0, playerId);
			
			if (result != null) {
				if ( newCommand.equals("games.join") ) {
					//create cookie
					this.addCookie(exchange, result);
                    this.sendSuccess(exchange);
				} else {
					//send in body as json
					this.setJsonHeader(exchange);
					this.sendResponseBody(exchange, result);
				}				
			} else {
				//login failed
				throw new HTTPBadRequest("Failed to get list of games");
			}
			
		} catch (HTTPBadRequest e) {
			setBadRequest(exchange,e.getMessage());
			System.err.println("HTTPBadRequest: " + e.getMessage());
            //e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
		} finally {
            exchange.getResponseBody().close();
        }
		
	}
    
}
