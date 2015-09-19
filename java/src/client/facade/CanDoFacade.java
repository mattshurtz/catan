/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.IServerProxy;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import shared.model.Model;

/**
 *
 */
public class CanDoFacade {
    
    IServerProxy proxy;
    Model model;
    
    public CanDoFacade(IServerProxy proxy, Model model){
        this.proxy = proxy;
        this.model = model;
    }
    
    
    /**
     * @param int playerIndex
     * @param ResourceList offer
     * @return whether the player has actually has the resources to be offered. 
     */
    public boolean canOfferTrade(){
        return false;
    }
    
    /**
     * type (acceptTrade),
     * playerIndex (integer): Who's accepting / rejecting this trade,
     * willAccept (boolean): Whether you accept the trade or not
     */
    public boolean canAcceptTrade() {
    	return false;
    }
    
    public void canMaritimeTrade(){
        
    }
        
    public void canBuyDevCard(){
        
    }
    
    public void canPlayYearOfPlenty(){
        
    }
    
    public void canPlayRoadBuilding(){
        
    }
    
    public void canPlaySoldier(){
        
    }
    
    public void canPlayMonopoly(){
        
    }
    
    /** 
    * @param currentPlayerIndex
    * @param HexLocation
    * @param EdgeLocation
    * @return true if player has enough resources to buy a road, the edge location in question is connected to
    * a road or settlement/city belonging to the player. 
    */
    public boolean canBuildRoad(int currentPlayerIndex, EdgeLocation roadLocation){
        return model.getPlayer(currentPlayerIndex).canBuildRoad(roadLocation);
    }
    
     /** 
     * @param VertexLocation this is where the player would like build a settlement
     * @return true if player has enough resources to buy a Settlement, has a road connected to Hex vertex,
     * and settlement is not within two hexEdges of another Settlement.  
     */
    public boolean canBuildSettlement(int currentPlayerIndex, VertexLocation vertex){
        return false;
    }
    
    /** 
     * @param VertexLocation this is where the player would like build a settlement
     * @return true if player has enough resources to buy a City, and owns a settlement at this location
     */
    public boolean canBuildCity(int currentPlayerIndex, VertexLocation vertex) {
    	return false;
    }
    
    public void canSendChat(){
        
    }
    
    public void canRollNumber(){
        
    }
    
    public void canRobPlayer(){
        
    }
    
    public void canFinishTurn(){
        
    }
    
    
    
    
}
