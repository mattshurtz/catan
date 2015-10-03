package client.facade;

import client.data.GameInfo;
import client.proxy.IServerProxy;
import java.util.List;
import shared.communication.responses.GameResponse;

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
    
    public void login(){
        
        
    }
    
    public void register(){
        
    }
    
    public void changeLogLevel(){
         
    }  
    
}
