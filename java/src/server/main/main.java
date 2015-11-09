/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.main;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.ssl.internal.www.protocol.https.Handler;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import server.HTTPhandlers.DefaultHandler;
import server.HTTPhandlers.GameHandler;
import server.HTTPhandlers.GamesHandler;
import server.HTTPhandlers.MovesHandler;
import server.HTTPhandlers.UserHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

/**
 *Initializes web server. Maybe this will read games from a file later (phase 4)
 * Create Server listening on the port specified. 
 */
public class main{
    
	
    public main(){
        
    }
    
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
	private static final int DEFAULT_SERVER_PORT_NUMBER = 8081;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	//private static final String DATA_LOCATION = "/data/";

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
		
//	//initializes HTTP handlers
//		Handler handlers = new Handlers();

	//specify handlers
		server.setExecutor(null); // use the default executor

		server.createContext("/Game", new GameHandler());
		server.createContext("/Games", new GamesHandler());
		server.createContext("/Moves", new MovesHandler());
		server.createContext("/User", new UserHandler());
		server.createContext("/", new DefaultHandler());
		
	//start server
		server.start();
	}
    
}
