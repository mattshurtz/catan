/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import shared.exceptions.GetPlayerException;
import shared.exceptions.InsufficentSupplies;
import shared.exceptions.InvalidLocation;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.map.Map;
import shared.model.map.VertexObject;

/**
 * bank (ResourceList): The Resource cards available to be distributed to 
 * the players.,
 * remainingDevCards (DevCardList): the dev cards available to be distributed
 * to the players,
 * chat (MessageList): All the chat messages., log (MessageList): All the log
 * messages., map (Map), players (array[Player]), tradeOffer (TradeOffer,
 * optional): The current trade offer, if there is one., turnTracker
 * (TurnTracker): This tracks who's turn it is and what action's being done.,
 * version (index): The version of the model. This is incremented whenever
 * anyone makes a move., winner (index): This is -1 when nobody's won yet. When
 * they have, it's their order index [0-3]
 */
public class Model {

    private ResourceList bank;
    private DevCardList deck;
    private MessageList chat;
    private MessageList log;
    private Map map;
    private ArrayList<Player> players;
    private TradeOffer tradeOffer;
    private TurnTracker turnTracker;
    private int version;
    private int winner;

    public Model() {
        bank = new ResourceList();
        chat = new MessageList();
        log = new MessageList();
        map = new Map();
        players = new ArrayList<Player>();
        TradeOffer tradeOffer;
        turnTracker = new TurnTracker();
        version = 0;
        winner = -1;
    }

    /**
     * @param playerIndex this is the index of the player
     * @return Player at the specified playerIndex
     * @throws GetPlayerException if the player list is empty, or if the index is invalid
     */
    public Player getPlayer(int playerIndex) throws GetPlayerException{
    	if (players == null || players.size() == 0) {
    		throw new GetPlayerException("There are currently no players in this model's player list");
    	}
    	if (playerIndex > 3 || playerIndex < 0) {
    		throw new GetPlayerException("Invalid playerIndex:  Must be 0-3");
    	}
        return players.get(playerIndex);
    }

    /**
     * Iterates through all of the settlements and cities in the map and gives
     * each player the resources they deserve for the given role.
     */
    public void receiveNewResources() {
    	
    }

    /**
     * Checks if a player has a road, the resources to build this road, if it is in a
     * valid location on the map, and if the player has a road left to play
     *
     * @param location this is the location of the Road you would like to build
     * playerIndex identifies the player who would like to build the road
     * @return true if no exception is thrown
     * InsufficentSupplies if the player did not have enough resources or pieces
     * @throws InvalidLocation if this is an invalid location for this player to
     * play a road
     */
    public boolean canBuildRoad(EdgeLocation location) throws InvalidLocation {
//        if(!map.canPlaceRoadAtLoc(location)) {
//            throw new InvalidLocation("");
//        }
        
        return true;
    }
    
    /**
     * 
     * @return true if no exception is thrown
     * @throws InsufficentSupplies if player doesn't have the resources or a road to place
     */
    public boolean canBuyRoad() throws InsufficentSupplies {
        int playerIndex = turnTracker.getCurrentTurn();
        if (!players.get(playerIndex).getResources().canBuyRoad()) {
            throw new InsufficentSupplies("Player does not have enough resources.");
        }
        if (!players.get(playerIndex).hasRoad()) {
            throw new InsufficentSupplies("Player does not have a road to build.");
        }
        return true;
    }

    /**
     * Removes a brick and wood from the player building the road(player.resourceList.buyRoad();) and creates a
     * road located at the given EdgeLocation (Map.buildRoad())
     *
     * @param location where the player is playing the road
     */
    public void buildRoad(EdgeLocation location) {

    }

    public boolean canBuySettlement()throws InsufficentSupplies{
        if(!players.get(turnTracker.getCurrentTurn()).getResources().canBuySettlement()){
            throw new InsufficentSupplies("Player does not have enough resources");
        }
        if(!players.get(turnTracker.getCurrentTurn()).hasSettlment()){
            throw new InsufficentSupplies("Player does not have a road to build");
        }
        return true;
    }
    
    /**
     * Checks if a player has a settlement to build, the resources to build this
     * settlement and if it is in a valid location on the map.
     *
     * @param location where the player would like to build the settlement
     * @return true if no exception is thrown
     * @throws InsufficentSupplies if the player did not have enough resources
     * @throws InvalidLocation if this is an invalid location for this player to
     * play a settlement
     */
    public boolean canBuildSettlement(VertexLocation location) throws InsufficentSupplies, InvalidLocation {

        //Get the normalized hexLocation
    	VertexLocation normVertLocation = location.getNormalizedLocation();
    	
    	ArrayList<VertexObject> allVObjects = map.getCitiesAndSettlements();
    	
    	//Check if the vertex in question already has a city or settlement
    	for (VertexObject vObject: allVObjects) {
    		if (vObject.getLocation().getNormalizedLocation().equals(normVertLocation)) {
    			return false;
    		}
    	}
    	
    	//If the hexDirection is northEast, check the current HexLocation's NorthWest and East vertices for settlements, and the north neighbor's east vertex
    		//check northwest of current hex
    		
    		
    		//check east of current hex
    		
    		//get northern neighboring hex
    		//check east of north neighbor hex
    	
    	//If it's northWest, check current HexLocation's northEast and West vertices for settlements, and north neighbor's west vertex

    	
    	//Get rid of this...
    	return true;
    }
    
     /**
     * Removes a brick, wood, sheep, and wheat from the player building the 
     * settlement(player.resourceList.buySettlement();) 
     * and creates a road located at the given EdgeLocation (Map.buildSettlment())
     *
     * @param location where the player is playing the road
     * @param playerIndex is used to identify the player playing the road
     */
    public void buildSettlement(EdgeLocation location, int playerIndex) {
        Player currentPlayer = players.get(turnTracker.getCurrentTurn());
    }
    
    public boolean canBuyCity() throws InsufficentSupplies{
        if(!players.get(turnTracker.getCurrentTurn()).getResources().canBuyCity()){
            throw new InsufficentSupplies("Player does not have enough resources");
        }
        if(!players.get(turnTracker.getCurrentTurn()).hasCity()){
            throw new InsufficentSupplies("Player does not have a road to build");
        }
        return true;        
    }

    /**
     * Checks if a player has a city, the resources to build this city and if it is in a
     * valid location on the map.
     *
     * @param location where the player would like to build the city
     * @param playerIndex identifies the player who would like to build this
     * city
     * @return true if no exception is thrown
     * @throws InsufficentSupplies if the player did not have enough resources
     * @throws InvalidLocation if this is an invalid location for this player to
     * play a city
     */
    public boolean canBuildCity(VertexLocation location, int playerIndex) throws InsufficentSupplies, InvalidLocation {
        return true;
    }

    /**
     * Removes three ore and two wheat from this player's ResourceList
     * Subtracts one city from players cities count
     * Adds settlement to players settlement count
     * (player.resourceList.buyCity();) 
     * Replace settlement located on the given VertexLocation with a city(Map.buildCity())
     * @param location where the player would like to place a city
     * @param playerIndex used to identify the player building this settlement
     */
    public void buildCity(VertexLocation location, int playerIndex) {

    }
    
    /**
     * @param rolledNumber - int not equal to 7
     */
    public void distributeResources(int rolledNumber)
    {
    	
    }

    public TurnTracker getTurnTracker() {
        return turnTracker;
    }

    public void setTurnTracker(TurnTracker tt) {
        this.turnTracker = tt;
    }

    public ResourceList getBank() {
        return bank;
    }

    public void setBank(ResourceList bank) {
        this.bank = bank;
    }

    public DevCardList getDeck() {
        return deck;
    }

    public void setDeck(DevCardList remainingDevCards) {
        this.deck = remainingDevCards;
    }

    public MessageList getChat() {
        return chat;
    }

    public void setChat(MessageList chat) {
        this.chat = chat;
    }

    public MessageList getLog() {
        return log;
    }

    public void setLog(MessageList log) {
        this.log = log;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public TradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public void setTradeOffer(TradeOffer tradeOffer) {
        this.tradeOffer = tradeOffer;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.version;
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
        final Model other = (Model) obj;
        if (!Objects.equals(this.bank, other.bank)) {
            return false;
        }
        if (!Objects.equals(this.deck, other.deck)) {
            return false;
        }
        if (!Objects.equals(this.chat, other.chat)) {
            return false;
        }
        if (!Objects.equals(this.log, other.log)) {
            return false;
        }
        if (!Objects.equals(this.map, other.map)) {
            return false;
        }
        if (!Objects.equals(this.players, other.players)) {
            return false;
        }
        if (!Objects.equals(this.tradeOffer, other.tradeOffer)) {
            return false;
        }
        if (!Objects.equals(this.turnTracker, other.turnTracker)) {
            return false;
        }
        if (this.version != other.version) {
            return false;
        }
        if (this.winner != other.winner) {
            return false;
        }
        return true;
    }
    
    

}
