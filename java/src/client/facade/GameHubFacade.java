/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.Proxy;
import shared.model.Model;

/**
 *
 */
public class GameHubFacade {
    
    Proxy proxy;
    Model model;
    
    public GameHubFacade(Proxy proxy, Model model){
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
    
    public String listGames(){
        return new String("json");
    }
    
    public void createGame(){
        
    }
    
    public void join(){
        
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
