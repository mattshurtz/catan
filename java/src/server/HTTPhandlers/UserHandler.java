/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.HTTPhandlers;

import com.sun.net.httpserver.HttpExchange;

import shared.exceptions.HTTPBadRequest;
import sun.net.www.protocol.http.HttpURLConnection;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;


/**
 * HTTP Handler for the requests starting with /User
 * operations on the users stored on the server. 
 */
public class UserHandler extends catanHTTPHandler{

	public UserHandler() {
		super();
	}
	
	@Override
    public void handle(HttpExchange exchange) throws IOException {
    	
    	System.out.println("CLIENT CALLED :" + exchange.getRequestURI().getPath());
    	
    	
    	//check if post
    	try {
    		
    		//check if this is a post, else throw error
			this.checkIsPost(exchange);
			
			//prep command
			URI url = exchange.getRequestURI();
			String newCommand = "user." + url.getPath().replace("/user/", "");
			
			//Convert content (POST) across to String
			String content = this.getContent(exchange);   
			
			//Call the facade
			String result = this.sendToFacade(newCommand, content, null);
			
			if(result != null) {
				//login good
				//create cookie
				this.addCookie(exchange, result);
			} else {
				//login failed
				throw new HTTPBadRequest("Login failed - bad password or username");
			}
			
		} catch (HTTPBadRequest e) {
			setBadRequest(exchange,e.getMessage());
			System.out.println(e.getMessage());
		} finally {
			exchange.getResponseBody().close();
		}				
    }



	

	
    
}
