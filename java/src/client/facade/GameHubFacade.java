package client.facade;

import client.data.GameInfo;
import client.data.PlayerInfo;
import client.proxy.IServerProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.communication.params.Credentials;
import shared.communication.params.JoinGameRequest;
import shared.communication.responses.GameResponse;
import shared.communication.responses.PlayerResponse;
import shared.exceptions.ServerException;
import shared.json.Deserializer;

/**
 * A facade dealing with all important game hub operations, such as getting the model,
 * loading and saving the game, logging in, registering, and changing the server's log
 * level.
 */
public class GameHubFacade {
    
    private Deserializer deserializer = new Deserializer();
    private IServerProxy proxy;
    
    public GameHubFacade(IServerProxy proxy){
        this.proxy = proxy;
    }
    
    public void resetCommands(){
        
    }
    
    public void executeCommands(){
        
    }
    
    public void getCommands(){
        
    }
    
    public void addAI(){
        
    }
    
    /** 
     * List different possible AI's that can be added to games.
     */
    public String[] listAI(){
        try {
            return proxy.listAi();
        } catch (ServerException ex) {
            Logger.getLogger(GameHubFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public GameInfo[] listGames(){
        List<GameResponse> gamesList = null;
        try {
            gamesList = CatanFacade.getProxy().getGamesList();
        } catch (ServerException ex) {
            Logger.getLogger(GameHubFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        List<GameInfo> ret = new ArrayList<>();
        for ( int i = 0; i < gamesList.size(); i++ ) {
            GameResponse gr = gamesList.get(i);
            GameInfo gi = new GameInfo();
            gi.setId( gr.getId() );
            gi.setTitle( gr.getTitle() );
            List<PlayerResponse> players = gr.getPlayers();
            for ( PlayerResponse pr : players ) {
                PlayerInfo pi = deserializer.toPlayerInfo( pr );
                if ( pi != null )
                    gi.addPlayer( pi );
            }
            ret.add(gi);
        }
        return ret.toArray(new GameInfo[0]);
    }
    
    public void createGame(){
        
    }
    
    public boolean join(GameInfo gameInfo, String color){
        JoinGameRequest jgr = new JoinGameRequest();
        jgr.setGameID( gameInfo.getId() );
        jgr.setColor(color);
        try {
            return proxy.joinGame( jgr );
        } catch (ServerException ex) {
            Logger.getLogger(GameHubFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void save(){
        
    }
    
    public void load(){
        
    }
    
    public boolean login( String username, String password ){
        Credentials cred = new Credentials();
        cred.setUsername( username );
        cred.setPassword( password );
        try {
            return this.proxy.login( cred );
        } catch (ServerException ex) {
            Logger.getLogger(GameHubFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void register(){
        
    }
    
    public void changeLogLevel(){
         
    }  
    
}
