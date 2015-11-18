/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.main;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.ssl.internal.www.protocol.https.Handler;
import java.io.File;

import server.HTTPhandlers.DefaultHandler;
import server.HTTPhandlers.GameHandler;
import server.HTTPhandlers.GamesHandler;
import server.HTTPhandlers.MovesHandler;
import server.HTTPhandlers.UserHandler;
import server.facade.IServerFacade;
import server.facade.MockResponderFacade;
import server.facade.ResponderFacade;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import server.HTTPhandlers.SwaggerHandlers;



/**
 *Initializes web server. Maybe this will read games from a file later (phase 4)
 * Create Server listening on the port specified. 
 */
public class main{
    
	
    private static final int DEFAULT_SERVER_PORT_NUMBER = 8081;
    private static final int MAX_WAITING_CONNECTIONS = 10;   
        
    /**
     * Specifies port to listen to and starts the server. 
     * @param args port number
     */
    /**
	 * The main function that receives the port number and starts the server
	 * 
	 * @param args
	 *            The port number
	 */
	public static void main(String[] args) {
	//start server with default port if no argument is given
		if (args.length == 1) {
			new main().run(Integer.parseInt(args[0]));
		} else if (args.length == 0) {
			new main().run(DEFAULT_SERVER_PORT_NUMBER);
		}
	}
	private HttpServer server;

	/**
	 * The run for the HTTP server
	 * 
	 * @param port_number
	 *            The port number
	 */
	public void run(int port_number) {
	//initializes stuff


	//initializes HTTP server
		try {
			server = HttpServer.create(new InetSocketAddress(port_number),
					MAX_WAITING_CONNECTIONS);
			System.out.println("listening on port: " + port_number);
		} catch (IOException e) {
			System.out.println("Could not create HTTP server: "
					+ e.getMessage());
			e.printStackTrace();
			return;
		}
		
		//IServerFacade facade = new MockResponderFacade();
		IServerFacade facade = new ResponderFacade();
	//specify handlers
		server.setExecutor(null); // use the default executor

		server.createContext("/game/", new GameHandler(facade));
		server.createContext("/games/", new GamesHandler(facade));
		server.createContext("/moves/", new MovesHandler(facade));
		server.createContext("/user/", new UserHandler(facade));
		server.createContext("/", new SwaggerHandlers.BasicFile("/docs/api/view"));
		server.createContext("/docs/api/data", new SwaggerHandlers.JSONAppender(""));
        server.createContext("/docs/api/view", new SwaggerHandlers.BasicFile(""));
	//start server
		server.start();
	}
    
}
