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
 * @author karahartley
 */
public class DoFacade {
    
    
    Proxy proxy;
    Model model;
    
    public DoFacade(Proxy proxy, Model model){
        this.proxy = proxy;
        this.model = model;
    }
    
    public void offerTrade(){
        
    }
    
    /**
     * type (acceptTrade),
     * playerIndex (integer): Who's accepting / rejecting this trade,
     * willAccept (boolean): Whether you accept the trade or not
     */
    public void acceptTrade(){
        
    }
    
    public void maritimeTrade(){
        
    }
    
        
    public void buyDevCard(){
        
    }
    
    public void playYearOfPlenty(){
        
    }
    
    public void playRoadBuilding(){
        
    }
    
    public void playSoldier(){
        
    }
    
    public void playMonopoly(){
        
    }
    
    public void buildRoad(){
       //Create the build roads parameter using the model and pass it into build road then proxy sends request to server
       //proxy.buildRoad();
    }
    
    public void buildSettlement(){
        
    }
    
    public void buildCity(){
        
    }
    
    public void sendChat(){
        
    }
    
    public void rollNumber(){
        
    }
    
    public void robPlayer(){
        
    }
    
    public void finishTurn(){
        
    }
    
    
}
