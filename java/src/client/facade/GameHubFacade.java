package client.facade;

import client.data.GameInfo;
import client.proxy.IServerProxy;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.communication.params.Credentials;
import shared.communication.responses.GameResponse;
import shared.exceptions.ServerException;

/**
 * A facade dealing with all important game hub operations, such as getting the model,
 * loading and saving the game, logging in, registering, and changing the server's log
 * level.
 */
public class GameHubFacade {
    
    IServerProxy proxy;
    
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
    
    public List<GameResponse> listGames(){
        return null;
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
