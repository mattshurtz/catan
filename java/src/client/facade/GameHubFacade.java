/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.data.GameInfo;
import client.proxy.IServerProxy;
import shared.communication.params.JoinGameRequest;
import shared.model.Model;

/**
 *
 */
public class GameHubFacade {
    
    IServerProxy proxy;
    Model model;
    
    public GameHubFacade(IServerProxy proxy, Model model){
        this.proxy = proxy;
        this.model = model;
    }
    
    public void getModel(){
        
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
    
//    public String listGames(){
//    }
    
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
