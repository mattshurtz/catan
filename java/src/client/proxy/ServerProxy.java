package client.proxy;

import client.facade.CatanFacade;
import client.paller.ServerPaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import shared.communication.params.*;
import shared.communication.params.moves.AcceptTradeRequest;
import shared.communication.params.moves.BuildCityRequest;
import shared.communication.params.moves.BuildRoadRequest;
import shared.communication.params.moves.BuildSettlementRequest;
import shared.communication.params.moves.DiscardCardsRequest;
import shared.communication.params.moves.MaritimeTradeRequest;
import shared.communication.params.moves.MoveRequest;
import shared.communication.params.moves.OfferTradeRequest;
import shared.communication.params.moves.PlayMonopolyRequest;
import shared.communication.params.moves.PlayRoadBuildingRequest;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.communication.params.moves.RobPlayerRequest;
import shared.communication.params.moves.RollNumberRequest;
import shared.communication.params.moves.SendChatRequest;
import shared.communication.responses.*;
import shared.exceptions.ServerException;
import shared.json.Deserializer;
import shared.json.Serializer;
import shared.model.Model;

public class ServerProxy implements IServerProxy {

    private String host = "localhost";
    private int port = 8081;

    private Serializer serializer = new Serializer();
    private Deserializer deserializer = new Deserializer();

    List<String> cookies = new ArrayList<>();

    public ServerProxy(){

    }

    public ServerProxy( String host, int port ) {
        this();
        this.setHost( host );
        this.setPort( port );
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * For development purposes only.
     * @param args
     * @throws ServerException
     */
//    public static void main(String[] args) throws ServerException {
//        ServerProxy sp = new ServerProxy();
//        Credentials c = new Credentials();
//        c.setUsername("Sam");
//        c.setPassword("sam");
//        System.out.println(sp.doPost("user/login", c));
//
//        String response = sp.doGet( "games/list" );
//        System.out.println(response);
//        List<GameResponse> games = sp.deserializer.toGamesList(response);
////        String gameListJson = sp.doGet("games/list");
////        System.out.println(gameListJson);
////        JoinGameRequest jgr = new JoinGameRequest();
////        jgr.setColor("yellow");
////        jgr.setGameID(0);
////        System.out.println(sp.doPost("games/join", jgr));
////        System.out.println(sp.doGet("game/model"));
//    }

    private String baseUrl() {
        return "http://" + host + ":" + port + "/";
    }

    private void outputPostObject( HttpURLConnection conn, Object params ) throws IOException {
        try ( OutputStream os = conn.getOutputStream() )
        {
            PrintWriter pw = new PrintWriter( os );
            String json = serializer.toJson( params );
            pw.write( json );
            pw.close();
        }
    }

    private String doGet( String url_str ) throws ServerException {
        return doPost( url_str, null );
    }

    /**
     * Returns just response body of request (handles all cookies and headers on its own)
     * @param url_str
     * @param postParams
     * @return
     * @throws ServerException
     */
    private String doPost( String url_str, Object postParams ) throws ServerException {
        try {
            URL url = new URL( baseUrl() + url_str );
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            for (String cookie : cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
            }
            conn.setDoInput( true );

            if ( postParams != null ) {
                conn.setDoOutput( true );
                outputPostObject( conn, postParams );
            }

            // Grab the cookie headers & save 'em
            for (Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
                // For testing
//                System.out.println(header.getKey() + "=" + header.getValue());
                if ( header.getKey() == null || header.getValue() == null )
                    continue;
                if ( header.getKey().equalsIgnoreCase("set-cookie") ) {
                    cookies.addAll( header.getValue() );
                }
            }

            int responseCode = conn.getResponseCode();
            String ret = "";
            InputStream in;
            if ( responseCode != 200 ) {
                ret += "Server returned non-OK response code: " + responseCode + " (" + conn.getResponseMessage() + ")\n";
                throw new ServerException(ret);
                //getErrorStream is null

            } else {
                in = conn.getInputStream();
            }
            return convertStreamToString( in );
        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads all contents of an InputStream and returns it as a string.
     *
     * From http://stackoverflow.com/a/5445161/530728 .
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(java.io.InputStream is) {
    	if(is==null){
    		return null;
    	}

        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    private boolean toBoolean( String response ) throws Exception {
        if ( response.equalsIgnoreCase("Success") )
            return true;
        else if ( response.equalsIgnoreCase("Failure") )
            return false;
        else
            throw new Exception( "Invalid success/failure response: " + response );
    }

    @Override
    public Model getGameModel(int version) throws ServerException {
        String response = doPost("game/model",version);
        return deserializer.toJavaModel(response);
    }

    @Override
    public String[] listAi() throws ServerException {
    	String response = doGet("game/listAI");
    	return deserializer.toStringArray( response );
    }

    @Override
    public Model postCommands(PostCommandsRequest postCommandsRequest) throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCommands() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAi( AddAiRequest add ) throws ServerException {
        String response = doPost( "game/addAI", add );
        try {
            return toBoolean( response );
        } catch (Exception ex) {
            Logger.getLogger(ServerProxy.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Model buildRoad( BuildRoadRequest req ) throws ServerException {
    /*    try {
            String JSON = doPost( "moves/buildRoad", req );
        } catch ( Exception e ) {
            throw new ServerException( e );
        }
    */
        String JSON = doPost("moves/buildRoad", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model discardCards(DiscardCardsRequest req) throws ServerException {
        String JSON = doPost("moves/discardCards", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model offerTrade(OfferTradeRequest req) throws ServerException {
        String JSON = doPost("moves/offerTrade", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model acceptTrade(AcceptTradeRequest req) throws ServerException {
        String JSON = doPost("moves/acceptTrade", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model maritimeTrade(MaritimeTradeRequest req) throws ServerException {
        String JSON = doPost("moves/maritimeTrade", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model buyDevCard(MoveRequest req) throws ServerException {
        String JSON = doPost("moves/buyDevCard", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model playYearOfPlenty(PlayYearOfPlentyRequest req) throws ServerException {
        String JSON = doPost("moves/Year_of_Plenty", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model playRoadBuilding(PlayRoadBuildingRequest req) throws ServerException {
        String JSON = doPost("moves/Road_Building", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model playSoldier(RobPlayerRequest req) throws ServerException {
        String JSON = doPost("moves/Soldier", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model playMonopoly(PlayMonopolyRequest req) throws ServerException {
        String JSON = doPost("moves/Monopoly", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model playMonument(MoveRequest req) throws ServerException {
        String JSON = doPost("moves/Monument", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model buildSettlement(BuildSettlementRequest req) throws ServerException {
        String JSON = doPost("moves/buildSettlement", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model buildCity(BuildCityRequest req) throws ServerException {
        String JSON = doPost("moves/buildCity", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model sendChat(SendChatRequest req) throws ServerException {
        String JSON = doPost("moves/sendChat", req);

        return deserializer.toJavaModel(JSON);

        //SEE NOTE BELOW
    }

    @Override
    public Model rollNumber(RollNumberRequest req) throws ServerException {
        String JSON = doPost("moves/rollNumber", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model robPlayer(RobPlayerRequest req) throws ServerException {
        String JSON = doPost("moves/robPlayer", req);

        return deserializer.toJavaModel(JSON);
    }

    @Override
    public Model finishTurn(MoveRequest req) throws ServerException {
        String JSON = doPost("moves/finishTurn", req);
        return deserializer.toJavaModel(JSON);
    }

    @Override
    public boolean login(Credentials userCredentials) throws ServerException {
        try {
            String response = doPost( "user/login", userCredentials );
            boolean success = toBoolean( response );

            if ( success ) {
                CatanFacade.getMyPlayerInfo().setName( userCredentials.getUsername() );
            }

            return success;
        } catch ( Exception e ) {
        	return false;
        }
    }

    @Override
    public boolean register(Credentials userCredentials) throws ServerException {
        try {
            String response = doPost( "user/register", userCredentials );
            return toBoolean( response );
        } catch ( Exception e ) {
            throw new ServerException( e.getMessage() );
        }
    }

    @Override
    public List<GameResponse> getGamesList() throws ServerException {
        String response = doGet( "games/list" );
        return deserializer.toGamesList( response );
    }

    @Override
    public CreateGameResponse createGame(CreateGameRequest gameRequests) throws ServerException {
        String response = doPost("games/create",gameRequests);
        return deserializer.toGameResponse(response);
        //return null;
    }

    @Override
    public boolean joinGame(JoinGameRequest joinRequest) throws ServerException {
        try {
        	String response = doPost("games/join",joinRequest);
			boolean success = toBoolean( response );
            if ( ! success )
                return false;
            // else, start the Paller so we can get updated when other people join the game
            // & do other stuff
            CatanFacade.startServerPalling();
            return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }

    @Override
    public boolean saveGame(SaveGameRequest saveRequest) throws ServerException {
        try {
        	String response = doPost("games/save",saveRequest);
			return toBoolean(response);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
    }

    @Override
    public boolean loadGame(LoadGameRequest loadRequest) throws ServerException {
    	try {
        	String response = doPost("games/load",loadRequest);
			return toBoolean(response);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
    }

    @Override
    public Model resetGame() throws ServerException {
    	String JSON = doPost("game/reset", null);
        return deserializer.toJavaModel(JSON);
    }

}
