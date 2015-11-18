package server.HTTPhandlers;

import com.sun.net.httpserver.Headers;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.net.HttpURLConnection;
import java.util.Map.Entry;
import server.data.PlayerInfoCookie;

import server.facade.IServerFacade;
import server.facade.ResponderFacade;
import shared.exceptions.HTTPBadRequest;
import shared.json.Deserializer;

public class catanHTTPHandler implements HttpHandler{

	IServerFacade facade;
    
    private Deserializer deserializer = new Deserializer();
	
	public catanHTTPHandler(IServerFacade facade) {
		this.facade = facade;
	}
	
	public catanHTTPHandler() {
		//this.facade = new ResponderFacade();
	}
	
	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub		
	}
	
	protected boolean checkIsPost(HttpExchange exchange) throws HTTPBadRequest {
//		if (isPost(exchange))
			return true;
//		throw new HTTPBadRequest("Error: \"" + exchange.getRequestMethod() + "\" is no supported!");
	}
	
	protected boolean isPost(HttpExchange exchange) {
		if (exchange.getRequestMethod().toLowerCase().equals("post"))
			return true;
		return false;
	}
	
	protected boolean checkisGet(HttpExchange exchange) throws HTTPBadRequest {
//		if (isGet(exchange))
			return true;
//		throw new HTTPBadRequest("Error: \"" + exchange.getRequestMethod() + "\" is no supported!");
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
	protected String sendToFacade(String command, String content, int gameId, int playerId) throws HTTPBadRequest {
		return facade.doFunction(command, content, gameId, playerId);
	}
	
	protected void addCookie(HttpExchange exchange, String cookie) throws IOException {
		List<String> cookies = new ArrayList<String>();
		cookies.add(cookie);
                                
		exchange.getResponseHeaders().put("Set-cookie", cookies);
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
    
    protected void setJsonHeader( HttpExchange exchange ) {
        exchange.getResponseHeaders().set("Content-Type","application/json");
    }
    
    protected void setTextHeader( HttpExchange exchange ) {
        exchange.getResponseHeaders().set("Content-Type","text/plain");
    }
    
	protected void sendResponseBody(HttpExchange exchange, String result) {
		// TODO Auto-generated method stub
		try {
			//System.out.println("headers: " + printHeaders( exchange.getResponseHeaders() ) );
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
    
    static String printHeaders( Headers h ) {
        StringBuilder str = new StringBuilder();
        for ( Entry<String, List<String> > e : h.entrySet() ) {
            str.append( e.getKey() ).append(": ").append( e.getValue() ).append("\n");
        }
        return str.toString();
    }
    
    protected void sendSuccess( HttpExchange exchange ) {
        setTextHeader(exchange);
        sendResponseBody( exchange, "Success" );
    }
    
    protected void sendFailure( HttpExchange exchange ) {
        setTextHeader(exchange);
        sendResponseBody( exchange, "Failure" );
    }
    
    protected String getPlayerName ( HttpExchange exchange ) throws HTTPBadRequest {
        PlayerInfoCookie pic = this.getPlayerInfoCookie(exchange);
        if(pic != null) {
        	return pic.getName();
        } else {
        	return null;
        }       
        
    }
	
	protected int getPlayerId (HttpExchange exchange) throws HTTPBadRequest {
		PlayerInfoCookie pic = this.getPlayerInfoCookie(exchange);
        if(pic != null) {
        	return pic.getId();
        } else {
        	return -1;
        }
		
	}
	
	protected int getGameId (HttpExchange exchange) throws HTTPBadRequest {
		String cookie = this.getCookie( exchange, "catan.game" );
        if (cookie != null) {
        	cookie = cookie.replaceAll("[^\\d]", "");
        	int gameId = Integer.parseInt( cookie );
        	return gameId;
        } else {
        	return -1;
        }
		
	}
    
    private PlayerInfoCookie getPlayerInfoCookie( HttpExchange exchange ) {
        String cookie = this.getCookie( exchange, "catan.user" );
        PlayerInfoCookie pic = deserializer.toPlayerInfoCookie( cookie );
        return pic;
    }
    
    private String getCookie( HttpExchange exchange, String cookieName ) {
        List<String> cookies = exchange.getRequestHeaders().get("Cookie");
       
        if (cookies != null) {
        	for ( String cookieStr : cookies ) {
                String[] chocoChips = cookieStr.split(";");
                for ( String crumb : chocoChips ) {
                    String milk = cookieName + "=";
                    if ( crumb.startsWith( milk ) ) {
                        String stripped = crumb.substring( milk.length() );
                        String decoded = deserializer.decodeURIComponent(stripped);
                        return decoded;
                    }
                }
            }
        }
        
        return null;
    }

}
