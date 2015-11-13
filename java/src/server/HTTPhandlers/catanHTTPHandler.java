package server.HTTPhandlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.facade.IServerFacade;
import server.facade.MockResponderFacade;
import server.facade.ResponderFacade;
import server.gameinfocontainer.GameInfoContainer;
import shared.exceptions.HTTPBadRequest;
import sun.net.www.protocol.http.HttpURLConnection;

public class catanHTTPHandler implements HttpHandler{

	IServerFacade facade;
	
	public catanHTTPHandler(IServerFacade facade) {
		this.facade = facade;
	}
	
	public catanHTTPHandler() {
		this.facade = new MockResponderFacade();
	}
	
	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub		
	}
	
	protected boolean checkIsPost(HttpExchange exchange) throws HTTPBadRequest {
		if (isPost(exchange))
			return true;
		throw new HTTPBadRequest("Error: \"" + exchange.getRequestMethod() + "\" is no supported!");
	}
	
	protected boolean isPost(HttpExchange exchange) {
		if (exchange.getRequestMethod().toLowerCase().equals("post"))
			return true;
		return false;
	}
	
	protected boolean checkisGet(HttpExchange exchange) throws HTTPBadRequest {
		if (isGet(exchange))
			return true;
		throw new HTTPBadRequest("Error: \"" + exchange.getRequestMethod() + "\" is no supported!");
	}
	
	protected boolean isGet(HttpExchange exchange) {
		if (exchange.getRequestMethod().toLowerCase().equals("get"))
			return true;
		return false;
	}
	
	protected String getContent(HttpExchange exchange) throws IOException {
		String content = IOUtils.toString(exchange.getRequestBody(), "UTF-8");
		IOUtils.closeQuietly(exchange.getRequestBody());
		return content;
	}
	
	/**
	 * send infomation to facade for processing
	 * @param command the move being called
	 * @param content the body of the request
	 * @param GameID the gameID found in cookie (if any)
	 * @throws Throwable 
	 */
	protected String sendToFacade(String command, String content, String gameId, String user) throws HTTPBadRequest {
		return facade.doFunction(command, content, gameId, user);
	}
	
	protected void addCookie(HttpExchange exchange, String cookie) throws IOException {
		List<String> cookies = new ArrayList<String>();
		cookies.add(cookie);
		exchange.getResponseHeaders().put("Set-cookie", cookies);
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
	}
	
	protected void setBadRequest(HttpExchange exchange, String responseMessage) throws IOException {
		//set "Content-Type: text/plain" header
		List<String> contentTypes = new ArrayList<String>();
		String textPlain = "text/plain";
		contentTypes.add(textPlain);
		exchange.getResponseHeaders().put("Content-type", contentTypes);
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
		
		OutputStreamWriter writer = new OutputStreamWriter(
				exchange.getResponseBody());
		writer.write(responseMessage);
		writer.flush();
		writer.close();
	}
	
	protected void setJSONResponse(HttpExchange exchange, String result) {
		// TODO Auto-generated method stub
		try {
			exchange.getResponseHeaders().set("Content-Type","application/json");
			
			exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
			OutputStreamWriter writer = new OutputStreamWriter(
					exchange.getResponseBody());
			writer.write(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	protected boolean validateUser(HttpExchange exchange) {
		List<String> cookies = exchange.getRequestHeaders().get("Cookie");
		for (String cookie : cookies) {
		    if (!cookie.startsWith("catan.user")) {
		    	String username = "";
		    	String password = "";
		    	if (GameInfoContainer.getInstance().login(username, password) > -1) {
		    		return true;
		    	};
		    }
		}
		return false;
	}

}
