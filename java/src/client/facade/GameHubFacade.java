package client.facade;

import client.data.GameInfo;
import client.proxy.IServerProxy;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.communication.params.Credentials;
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
    
    public void listAI(){
        
    }
    
    public GameInfo[] listGames(){
        List<GameResponse> gamesList = null;
        try {
            gamesList = CatanFacade.getProxy().getGamesList();
        } catch (ServerException ex) {
            Logger.getLogger(GameHubFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        GameInfo[] ret = new GameInfo[ gamesList.size() ];
        for ( int i = 0; i < gamesList.size(); i++ ) {
            GameResponse gr = gamesList.get(i);
            GameInfo gi = new GameInfo();
            gi.setId( gr.getId() );
            gi.setTitle( gr.getTitle() );
            List<PlayerResponse> players = gr.getPlayers();
            for ( PlayerResponse pr : players ) {
                gi.addPlayer( deserializer.toPlayerInfo( pr ) );
            }
            ret[i] = gi;
        }
        return ret;
    }
    
    public void createGame(){
        
    }
    
    public void join(GameInfo gameInfo, String color){

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
