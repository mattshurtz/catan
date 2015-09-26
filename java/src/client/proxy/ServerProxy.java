package client.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import shared.communication.params.*;
import shared.communication.responses.*;
import shared.exceptions.ServerException;
import shared.json.Serializer;
import shared.model.Model;

public class ServerProxy implements IServerProxy {
    
    private String host = "localhost";
    private int port = 8081;
    
    private Serializer serializer = new Serializer();
    
    List<String> cookies = new ArrayList<>();
    
    public ServerProxy(){
       
    }
    
    /**
     * For development purposes only.
     * @param args 
     */
    public static void main(String[] args) {
        ServerProxy sp = new ServerProxy();
        Credentials c = new Credentials();
        c.setUsername("Sam");
        c.setPassword("sam");
        System.out.println(sp.doPost("user/login", c));
        String gameListJson = sp.doGet("games/list");
        System.out.println(gameListJson);
        JoinGameRequest jgr = new JoinGameRequest();
        jgr.setColor("yellow");
        jgr.setGameID(0);
        System.out.println(sp.doPost("games/join", jgr));
        System.out.println(sp.doGet("game/model"));
    }
    
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
    
    private String doGet( String url_str ) {
        return doPost( url_str, null );
    }
    
    /**
     * Returns just response body of request (handles all cookies and headers on its own)
     * @param url_str
     * @param postParams
     * @return 
     */
    private String doPost( String url_str, Object postParams ) {
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
            
            // For testing
            for (Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
                System.out.println(header.getKey() + "=" + header.getValue());
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
                in = conn.getErrorStream();
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
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    
    @Override
    public Model getGameModel(int version) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] listAi() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String postCommands(PostCommandsRequest postCommandsRequest) throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCommands() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAi( AddAiRequest add ) throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buildRoad() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void offerTrade() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void acceptTrade() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void maritimeTrade() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buyDevCard() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playYearOfPlenty() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playRoadBuilding() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playSoldier() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playMonopoly() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buildSettlement() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buildCity() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendChat() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rollNumber() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void robPlayer() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finishTurn() throws ServerException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(Credentials userCredentials) throws ServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean register(Credentials userCredentials) throws ServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<GameResponse> getGamesList() throws ServerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CreateGameResponse createGame(CreateGameRequest gameRequests) throws ServerException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean joinGame(JoinGameRequest joinRequest) throws ServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean saveGame(SaveGameRequest saveRequest) throws ServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean loadGame(LoadGameRequest loadRequest) throws ServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Model resetGame() throws ServerException {
        // TODO Auto-generated method stub
        return null;
    }

}
