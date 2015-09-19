/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
*cities (number): How many cities this player has left to play,
*color (string): The color of this player.,
*discarded (boolean): Whether this player has discarded or not already this discard phase.,
*monuments (number): How many monuments this player has played.,
*name (string),
*newDevCards (DevCardList): The dev cards the player bought this turn.,
*oldDevCards (DevCardList): The dev cards the player had when the turn started.,
*playerIndex (index): What place in the array is this player? 0-3. It determines their turn order.
*This is used often everywhere.,
*playedDevCard (boolean): Whether the player has played a dev card this turn.,
*playerID (integer): The unique playerID. This is used to pick the client player apart from the
*others. This is only used here and in your cookie.,
*resources (ResourceList): The resource cards this player has.,
*roads (number),
*settlements (integer),
*soldiers (integer),
*victoryPoints (integer)
*/
/**
 * @author Shurt
 *
 */
public class Player {
    
        int cities;
        String color;
        boolean discarded;
        int monuments;
        String name;
        DevCardList newDevCards;
        DevCardList oldDevCards;
        int playerIndex;
        boolean playedDevCard;
        int playerID;
        ResourceList resources;
        int roads;
        int settlements;
        int soldiers;
        int victoryPoints;
        
    
    /**
     * 
     */
    public Player(){
        
    }

    /** 
     * @param EdgeLocation where the player would like to place the road. 
     * @return true if player has enough resources to buy a road, has a settlement or road connected
     * to Hex edge, and there is not a road already there. 
     */
    public boolean canBuildRoad(EdgeLocation roadLocation) {
    	return false;
    }
    
    /** 
     * @param VertexLocation where the player would like to place the settlement.
     * @return true if player has enough resources to buy a Settlement, has a road connected to Hex vertex,
     * and settlement is not within two hexEdges of another Settlement. 
     */
    public boolean canBuildSettlement(VertexLocation settlementLocation) {
    	return false;
    }
    
    /** 
     * @param VertexLocation where the player would like to place the city.
     * @return true if player has enough resources to buy a City, and has a settlement on the hex vertex
     */
    public boolean canBuildCity(VertexLocation cityLocation) {
    	return false;
    }
    
    /** 
     * @param ResourceList accept is the list of resource necessary to accept the trade. 
     * @return true if player has enough resources to accept the trade
     */
    public boolean canAcceptTrade( ResourceList accept) {
    	return false;
    }
    
    /** 
     * @param ResourceList offer is the resources to be offered in trade. 
     * @return true if the player has the resources to be offered;
     */
    public boolean canOfferTade(ResourceList offer){
        return false;
    }
    
    /** 
     * @return true if player can play a development card
     */
    public boolean canPlayDevCard() {
    	return false;
    }
    
    
    
    
}
