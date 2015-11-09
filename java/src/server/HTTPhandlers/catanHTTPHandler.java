package server.HTTPhandlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IServerFacade;
import server.facade.ResponderFacade;

public class catanHTTPHandler implements HttpHandler{

	IServerFacade facade;
	
	public catanHTTPHandler() {
		facade = new ResponderFacade();
	}
	
	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * send infomation to facade for processing
	 * @param command the move being called
	 * @param content the body of the request
	 * @param GameID the gameID found in cookie (if any)
	 */
	public void sendToFacade(String command, String content, String GameID) {
		
	}
	
	/**
	 * extract the information from cookie to process use
	 * @return the gameid string.
	 */
	public String prepareCookieToClient() {
		return null;
	}

}
