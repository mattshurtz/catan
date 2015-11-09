/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.HTTPhandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

//import sun.misc.IOUtils;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.nio.charset.Charset;

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
    	InputStream input = exchange.getRequestBody();
    	URI url = exchange.getRequestURI();
    	System.out.println(url.getPath().replace("/User/", ""));

    	
    	//Convert content across to String
    	//String result = "<HTML><BODY><H1>Hello "+url.getPath().replace("/User/", "")+"!</H1></BODY></HTML>";  
    	String content = IOUtils.toString(exchange.getRequestBody(), "UTF-8");
    	IOUtils.closeQuietly(exchange.getRequestBody());    	
    	
    	//Call the facade
    	String result = facade.doFunction("user." + url.getPath().replace("/User/", ""), content, null);
    	
    	if(result != null){
    		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
    		exchange.getResponseBody().write(result.getBytes());
    		exchange.getResponseBody().close();
    	} else {
    		exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
    		//exchange.getResponseBody().write(result.getBytes());
    		exchange.getResponseBody().close();
    	}
		
    }
    
}
