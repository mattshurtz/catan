/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.Objects;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

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

    int cities = 4;
    CatanColor color = null;
    boolean discarded = false;
    int monuments = 0;
    String name = "";
    DevCardList newDevCards = new DevCardList();
    DevCardList oldDevCards = new DevCardList();
    int playerIndex = 0;
    boolean playedDevCard = false;
    int playerID = -1; //userID
    ResourceList resources = new ResourceList();
    int roads = 15;
    int settlements = 5;
    int soldiers = 0;
    int victoryPoints = 0;
    
    static final int MAX_ROADS = 15;
    static final int MAX_SETTLEMENTS = 5;
    static final int MAX_CITIES = 4;
    
    public Player() {
        
    }

    public Player( CatanColor color, String name, int playerIndex, int playerId ) {
        this.color = color;
        this.name = name;
        this.playerIndex = playerIndex;
        this.playerID = playerId;
    }

    /**
     * @return true if player can play a development card
     */
    public boolean canPlayDevCard(DevCardType cardType) {
        if(playedDevCard){
            return false;
        }else if(cardType.equals(DevCardType.MONUMENT)){
            if(oldDevCards.canPlayDevCard(cardType)||newDevCards.canPlayDevCard(cardType)){
                return true;
            }
            return false;
        }
        else{
            //dont have a card of card Type in oldDevCards(playable)
            return oldDevCards.canPlayDevCard(cardType);
        }
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
        if(roads>0){
            return true;
        }
        return false;
    }

    /**
     *
     * @return whether player has a settlement left to play.
     */
    public boolean hasSettlment() {
        if(settlements>0){
            return true;
        }
        return false;
    }

    /**
     *
     * @return whether player has a city left to play.
     */
    public boolean hasCity() {
       if(cities>0){
           return true;
       }
        return false;
    }
    
    /**
     * decrements city count by one and increments settlement count by one
     */
    public void buildCity(){
        cities--;
        settlements++;
        resources.buyCity();
        
        incrementVictoryPoints();
    }
    
    /**
     * decrease road count by one
     */
    public void buildRoad(boolean isFree){
        roads--;
        if(!isFree)
        {
            resources.buyRoad();
        }
    }
    
    /**
     * decrease settlement count by one
     */
    public void buildSettlement(boolean isFree){
        settlements--;
        if(!isFree)
        {
            resources.buySettlement();
        }
        incrementVictoryPoints();
    }
    
    public void incrementSoldiers() {
        soldiers++;
    }
    
    public void incrementMonuments() {
        monuments++;
    }
    
    public void incrementVictoryPoints() {
        victoryPoints++;
    }
    
    public void decrementVictoryPoints() {
        victoryPoints--;
    }
    
    public int getPlayerIndex() {
        return playerIndex;
    }

    public int getCities() {
        return cities;
    }

    public void setCities(int cities) {
        this.cities = cities;
    }

    public CatanColor getColor() {
        return color;
    }

    public void setColor(CatanColor color) {
        this.color = color;
    }

    public boolean isDiscarded() {
        return discarded;
    }

    public void setDiscarded(boolean discarded) {
        this.discarded = discarded;
    }

    public int getMonuments() {
        return monuments;
    }

    public void setMonuments(int monuments) {
        this.monuments = monuments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DevCardList getNewDevCards() {
        return newDevCards;
    }

    public void setNewDevCards(DevCardList newDevCards) {
        this.newDevCards = newDevCards;
    }

    public DevCardList getOldDevCards() {
        return oldDevCards;
    }

    public void setOldDevCards(DevCardList oldDevCards) {
        this.oldDevCards = oldDevCards;
    }
    
    public int getTotalSoldiers(){
       return this.oldDevCards.soldier+this.newDevCards.soldier;
    }
    
    public int getTotalYearOfPlenty(){
        return this.oldDevCards.yearOfPlenty+this.newDevCards.yearOfPlenty;
    }

    public int getTotalMonuments(){
        return this.oldDevCards.monument+this.newDevCards.monument; 
    }
    
    public int getTotalRoadBuilding(){
        return this.oldDevCards.roadBuilding+this.newDevCards.roadBuilding;
    }
    
    public int getTotalMonopoly(){
        return this.oldDevCards.monopoly+this.newDevCards.monopoly;
    }
    
    public boolean isPlayedDevCard() {
        return playedDevCard;
    }

    public void setPlayedDevCard(boolean playedDevCard) {
        this.playedDevCard = playedDevCard;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getRoads() {
        return roads;
    }

    public void setRoads(int roads) {
        this.roads = roads;
    }

    public int getSettlements() {
        return settlements;
    }

    public void setSettlements(int settlements) {
        this.settlements = settlements;
    }

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void setResources(ResourceList resources) {
        this.resources = resources;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.cities;
        hash = 89 * hash + Objects.hashCode(this.color);
        hash = 89 * hash + (this.discarded ? 1 : 0);
        hash = 89 * hash + this.monuments;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.newDevCards);
        hash = 89 * hash + Objects.hashCode(this.oldDevCards);
        hash = 89 * hash + this.playerIndex;
        hash = 89 * hash + (this.playedDevCard ? 1 : 0);
        hash = 89 * hash + this.playerID;
        hash = 89 * hash + Objects.hashCode(this.resources);
        hash = 89 * hash + this.roads;
        hash = 89 * hash + this.settlements;
        hash = 89 * hash + this.soldiers;
        hash = 89 * hash + this.victoryPoints;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.cities != other.cities) {
            return false;
        }
        if (this.color != other.color) {
            return false;
        }
        if (this.discarded != other.discarded) {
            return false;
        }
        if (this.monuments != other.monuments) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.newDevCards, other.newDevCards)) {
            return false;
        }
        if (!Objects.equals(this.oldDevCards, other.oldDevCards)) {
            return false;
        }
        if (this.playerIndex != other.playerIndex) {
            return false;
        }
        if (this.playedDevCard != other.playedDevCard) {
            return false;
        }
        if (this.playerID != other.playerID) {
            return false;
        }
        if (!Objects.equals(this.resources, other.resources)) {
            return false;
        }
        if (this.roads != other.roads) {
            return false;
        }
        if (this.settlements != other.settlements) {
            return false;
        }
        if (this.soldiers != other.soldiers) {
            return false;
        }
        if (this.victoryPoints != other.victoryPoints) {
            return false;
        }
        return true;
    }    

    public void buyDevCard() {
        resources.buyDevCard();
    }

    public void giveDevCard(DevCardType purchased) {
        if(purchased != DevCardType.MONUMENT) {
            newDevCards.addDevCard(purchased);
        }
        else {
            oldDevCards.addDevCard(purchased);
        } 
    }

    public void finishTurn() {
        combineDevCardLists();
    }
    
    private void combineDevCardLists()
    {
        oldDevCards.setMonopoly(oldDevCards.getMonopoly() + newDevCards.getMonopoly());
        oldDevCards.setMonument(oldDevCards.getMonument() + newDevCards.getMonument());
        oldDevCards.setRoadBuilding(oldDevCards.getRoadBuilding() + newDevCards.getRoadBuilding());
        oldDevCards.setSoldier(oldDevCards.getSoldier() + newDevCards.getSoldier());
        oldDevCards.setYearOfPlenty(oldDevCards.getYearOfPlenty() + newDevCards.getYearOfPlenty());
        
        newDevCards = new DevCardList();
    }

    public void playMonument() {
        incrementVictoryPoints();
        if(oldDevCards.getMonument() > 0){
        	oldDevCards.removeMonument();
        } else {
        	newDevCards.removeMonument();
        }
        
    }

    public void giveResources(ResourceList resourceList) {
       resources.addResources(resourceList);
    }
}
