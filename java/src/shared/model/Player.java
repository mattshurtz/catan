/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

/**
 * cities (number): How many cities this player has left to play, color
 * (string): The color of this player., discarded (boolean): Whether this player
 * has discarded or not already this discard phase., monuments (number): How
 * many monuments this player has played., name (string), newDevCards
 * (DevCardList): The dev cards the player bought this turn., oldDevCards
 * (DevCardList): The dev cards the player had when the turn started.,
 * playerIndex (index): What place in the array is this player? 0-3. It
 * determines their turn order. This is used often everywhere., playedDevCard
 * (boolean): Whether the player has played a dev card this turn., playerID
 * (integer): The unique playerID. This is used to pick the client player apart
 * from the others. This is only used here and in your cookie., resources
 * (ResourceList): The resource cards this player has., roads (number),
 * settlements (integer), soldiers (integer), victoryPoints (integer)
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
    public Player() {

    }

    /**
     * @return true if player can play a development card
     */
    public boolean canPlayDevCard() {
        return false;
    }

    /**
     *
     * @return the list of resources that belong to this player.
     */
    public ResourceList getResources() {
        return resources;
    }

    /**
     *
     * @return whether player has a road left to play.
     */
    public boolean hasRoad() {
        return false;
    }

    /**
     *
     * @return whether player has a settlement left to play.
     */
    public boolean hasSettlment() {
        return false;
    }

    /**
     *
     * @return whether player has a city left to play.
     */
    public boolean hasCity() {
        return false;
    }
    
    /**
     * decrements city count by one and increments settlement count by one
     */
    public void buildCity(){
        
    }
    /**
     * decrease road count by one
     */
    public void buildRoad(){
        
    }
    
    /**
     * decrease settlement count by one
     */
    public void buildSettlment(){
        
    }
    

}
