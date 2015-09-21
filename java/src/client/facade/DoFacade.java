/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.IServerProxy;
import shared.locations.VertexLocation;
import shared.model.Model;

/**
 *
 */
public class DoFacade {
    
    
    IServerProxy proxy;
    Model model;
    
    public DoFacade(IServerProxy proxy, Model model){
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
    
    /**
     * Create a build road request using the model and use the proxy
     * to send this parameter to the server. 
     */
    public void buildRoad(){
       //Create the build roads parameter using the model and pass it into build road then proxy sends request to server
       //proxy.buildRoad();
    }
    
    /**
     * Create a build settlement request using the model and use the proxy
     * to send this parameter to the server. 
     * @param location where settlement is to be built
     */
    public void buildSettlement(VertexLocation location){
        
    }
    /**
    * Create a build city request using the model and use the proxy
    * to send this parameter to the server.
     * @param location where settlement is to be built
    */
    public void buildCity(VertexLocation location){
        
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
