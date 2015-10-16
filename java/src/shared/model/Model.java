/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import client.facade.CatanFacade;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.definitions.TurnStatus;

import shared.exceptions.GetPlayerException;
import shared.exceptions.InvalidLocation;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.map.Hex;
import shared.model.map.CatanMap;
import shared.model.map.Port;
import shared.model.map.Road;
import shared.model.map.Settlement;
import shared.model.map.VertexObject;

/**
 * bank (ResourceList): The Resource cards available to be distributed to the
 * players., remainingDevCards (DevCardList): the dev cards available to be
 * distributed to the players, chat (MessageList): All the chat messages., log
 * (MessageList): All the log messages., catanMap (CatanMap), players (array[Player]),
 * tradeOffer (TradeOffer, optional): The current trade offer, if there is one.,
 * turnTracker (TurnTracker): This tracks who's turn it is and what action's
 * being done., version (index): The version of the model. This is incremented
 * whenever anyone makes a move., winner (index): This is -1 when nobody's won
 * yet. When they have, it's their order index [0-3]
 */
public class Model {
    private ResourceList bank;
    private DevCardList deck;
    private MessageList chat;
    private MessageList log;
    private CatanMap catanMap;
    private ArrayList<Player> players;
    private TradeOffer tradeOffer;
    private TurnTracker turnTracker;
    private int version;
    private int winner;
    private HashSet<EdgeLocation> invalidWaterEdges;

    public Model() {
        bank = new ResourceList();
        chat = new MessageList();
        log = new MessageList();
        catanMap = new CatanMap();
        players = new ArrayList<Player>();
        TradeOffer tradeOffer;
        turnTracker = new TurnTracker();
        version = 0;
        winner = -1;
        invalidWaterEdges = new HashSet<EdgeLocation>();
    }
    
    public void setInvalidWaterEdges(){
        HashSet<EdgeLocation> invalidWaterEdges = new HashSet<EdgeLocation>();
        
        for(Port port: catanMap.getPorts()){
            
        }
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
     * Iterates through all of the settlements and cities in the catanMap and gives
     * each player the resources they deserve for the given role.
     */
    public void receiveNewResources() {
    	//Do not implement for Phase 1
    }

    /**
     * Checks if a player has a road, the resources to build this road, if it is in a
     * valid location on the catanMap, and if the player has a road left to play
     *
     * @param location this is the location of the Road you would like to build
     * playerIndex identifies the player who would like to build the road
     * @return true if no exception is thrown
     * InsufficientSupplies if the player did not have enough resources or pieces
     * @throws InvalidLocation if this is an invalid location for this player to
     * play a road
     */
    public boolean canBuildRoad(EdgeLocation location) throws InvalidLocation {
    // check if it is port edge.       
      
        if(isValidRoadLocation(location)){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Checks whether the player has supplies to build a road. (Resources and road pieces)
     * @return true if player has enough supplies, false if not
     */
    public boolean canBuyRoad() {
        int playerIndex = CatanFacade.getMyPlayerIndex();
        if (!players.get(playerIndex).getResources().canBuyRoad()) {
            return false;
        } else if (!players.get(playerIndex).hasRoad()) {
            return false;
        }
        
        return true;
    }

    /**
     * Checks if the player can be robbed
     */
    public boolean canRobPlayer(int playerIndex) {
        return catanMap.canRobPlayer(playerIndex);
    }
    
    public boolean canPlaceRobber(HexLocation hexLocation){
            if(catanMap.getRobber().equals(hexLocation)){
                return false;
            }
            if(hexLocation.getX()>3 || hexLocation.getY()>3){
                return false;
            }
            return true;
    }
    
    /**
     * Removes a brick and wood from the player building the road(player.resourceList.buyRoad();) and creates a
     * road located at the given EdgeLocation (CatanMap.buildRoad())
     *
     * @param location where the player is playing the road
     */
    public void buildRoad(EdgeLocation location) {

    }

    /**
     * Checks if current player has enough resources for a settlement, and then checks if they have 
     * any settlements remaining.
     * @return True if they player has enough resources and settlements, false if no
     */
    public boolean canBuySettlement() {
        if(!players.get(turnTracker.getCurrentTurn()).getResources().canBuySettlement()){
            //Player doesn't have enough resources
        	return false;
        }
        //checks if the player has remaining settlements available to build.
        if(!players.get(turnTracker.getCurrentTurn()).hasSettlment()){
            //Player doesn't have enough settlements
        	return false;
        }
        return true;
    }
    
    /**
     * Checks if a settlement can be built by the current player at the given VertexLocation
     *
     * @param location where the player would like to build the settlement
     * @return true if given VertexLocation is valid, false if not
     */
    public boolean canBuildSettlement(VertexLocation location) {
        
		if (!isValidVertex(location)){
            return false;
        }
		
		//If it's the first round, then no need to check for roads
		if (turnTracker.getStatus() == TurnStatus.FIRST_ROUND){
			return true;
		}
		
		//Check to make sure the target vertex is touching a road (if not FIRST_ROUND)
        return surroundingEdgeOfVertexHasRoad(location);
    }
    
     public boolean isValidRoadLocation(EdgeLocation location) {
        EdgeLocation normEdge = location.getNormalizedLocation();
        HexLocation normHexLocation = normEdge.getHexLoc();
        int currentPlayer = turnTracker.getCurrentTurn();
        
        if(turnTracker.getStatus().equals(TurnStatus.FIRST_ROUND) || turnTracker.getStatus().equals(TurnStatus.SECOUND_ROUND)){
            return isValidFirstRoad(normEdge);
        }

        for (Road road: catanMap.getRoads()) {
           // check if road already exists on this edge
            if(road.getLocation().equals(location)){
                return false;
            }
           // check around North edge 
            if(normEdge.getDir()==EdgeDirection.North){

                //There exists a vertex an adjecent vertex object owned by the player
                //then this is a valid location. This is important for the setup phase
                
                HexLocation northeastNeighbor = normHexLocation.getNeighborLoc(EdgeDirection.NorthEast);
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                            northeastNeighbor, EdgeDirection.NorthWest))&&road.getOwner()==currentPlayer){
                    return true;
                }
                
                HexLocation northwestNeighbor = normHexLocation.getNeighborLoc(EdgeDirection.NorthWest);
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                            northwestNeighbor, EdgeDirection.SouthEast))&&road.getOwner()==currentPlayer){
                    return true;
                }
                
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                normEdge.getHexLoc(), EdgeDirection.NorthEast))&&road.getOwner()==currentPlayer){
                    return true;
                }
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                normEdge.getHexLoc(), EdgeDirection.NorthWest))&&road.getOwner()==currentPlayer){
                    return true;
                }
            }
            //Check arround the NorthWest edge
            if(normEdge.getDir()==EdgeDirection.NorthWest){
                HexLocation northwestNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);
                HexLocation southwestNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest);


                
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                            northwestNeighbor, EdgeDirection.NorthEast))&&road.getOwner()==currentPlayer){
                    return true;
                }
   
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                            southwestNeighbor, EdgeDirection.NorthEast))&&road.getOwner()==currentPlayer){

                    return true;
                }
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                            southwestNeighbor, EdgeDirection.North))&&road.getOwner()==currentPlayer){
                    return true;
                }
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                normEdge.getHexLoc(), EdgeDirection.North))&&road.getOwner()==currentPlayer){
                    return true;
                }
            }
            //Check arround the NorthEast edge
            if(normEdge.getDir()==EdgeDirection.NorthEast){
                HexLocation northeastNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
                HexLocation southeastNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast);

                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                            northeastNeighbor, EdgeDirection.NorthWest))&&road.getOwner()==currentPlayer){
                    return true;
                }
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                            southeastNeighbor, EdgeDirection.NorthWest))&&road.getOwner()==currentPlayer){
                    return true;
                }
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                            southeastNeighbor, EdgeDirection.North))&&road.getOwner()==currentPlayer){
                    return true;
                }
                if(road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                normEdge.getHexLoc(), EdgeDirection.North))&&road.getOwner()==currentPlayer){
                    return true;
                }
            }
        }
        return false;
    }
     
    public boolean isValidFirstRoad(EdgeLocation normEdge){
        ArrayList<VertexObject> allVObjects = catanMap.getCitiesAndSettlements();
        int currentPlayer = turnTracker.getCurrentTurn();
        if(normEdge.getDir()==EdgeDirection.North){
        for(VertexObject vertexObject: allVObjects){
                    if(vertexObject.getLocation().getNormalizedLocation().equals
        (new VertexLocation(normEdge.getHexLoc(),VertexDirection.NorthEast))&& vertexObject.getOwner()==currentPlayer){
                        return true;
                    }
                    if(vertexObject.getLocation().getNormalizedLocation().equals
        (new VertexLocation(normEdge.getHexLoc(),VertexDirection.NorthWest))&& vertexObject.getOwner()==currentPlayer){
                        return true;
                    }
                 }
        }
        
        if(normEdge.getDir()==EdgeDirection.NorthWest){
            HexLocation southwestNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest);
                for(VertexObject vertexObject: allVObjects){
                    if(vertexObject.getLocation().getNormalizedLocation().equals
        (new VertexLocation(southwestNeighbor,VertexDirection.NorthEast))&& vertexObject.getOwner()==currentPlayer){
                        return true;
                    }
                    if(vertexObject.getLocation().getNormalizedLocation().equals
        (new VertexLocation(normEdge.getHexLoc(),VertexDirection.NorthWest))&& vertexObject.getOwner()==currentPlayer){
                        return true;
                    }
                 }
        }
        if(normEdge.getDir()==EdgeDirection.NorthEast){
            HexLocation southeastNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast);
                for(VertexObject vertexObject: allVObjects){
                    if(vertexObject.getLocation().getNormalizedLocation().equals
        (new VertexLocation(southeastNeighbor,VertexDirection.NorthWest))&& vertexObject.getOwner()==currentPlayer){
                        return true;
                    }
                    if(vertexObject.getLocation().getNormalizedLocation().equals
        (new VertexLocation(normEdge.getHexLoc(),VertexDirection.NorthWest))&& vertexObject.getOwner()==currentPlayer){
                        return true;
                    }
                 } 
        }
        
        return false;
    }
    
    public boolean isPortEdge(){
        // Create a HashSet of invalid port eddge locations and check them
        // at the beginning of the can build edge function
        
        return false;
    }
    
    public boolean surroundingEdgeOfVertexHasRoad(VertexLocation location){
        VertexLocation normLocation = location.getNormalizedLocation();
        int currentPlayer = turnTracker.getCurrentTurn();
        for (Road road: catanMap.getRoads()) {
        //Assume that vObject.getLocation is returning the Normalized location
        //If the hexDirection is northEast, check the current HexLocation's NorthWest and East vertices for settlements, and the north neighbor's east vertex
    	// checks the surrounding vertices around the northeast vertex for vertex objects	
    		if (normLocation.getDir()==VertexDirection.NorthEast) {
                    HexLocation northeastNeighbor = normLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
                    if(road.getLocation().equals(new EdgeLocation(
                            northeastNeighbor, EdgeDirection.NorthWest))&&road.getOwner()==currentPlayer){
                            return true;
                    }
                    //check southeast of current hex
                    
                    if(road.getLocation().equals(new EdgeLocation(
                            normLocation.getHexLoc(), EdgeDirection.North))&&road.getOwner()==currentPlayer){
                            return true;
                    }
                    
                    if(road.getLocation().equals(new EdgeLocation(
                            normLocation.getHexLoc(), EdgeDirection.NorthEast))&&road.getOwner()==currentPlayer){
                            return true;
                    }
    		}
    	// checks the surrounding vertices around the northwest vertex for vertex objects	
                if(normLocation.getDir()==VertexDirection.NorthWest){
                    
                    HexLocation northwestNeighbor = normLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);
                    
                    if(road.getLocation().equals(new EdgeLocation(
                            northwestNeighbor, EdgeDirection.NorthEast))&&road.getOwner()==currentPlayer){
                        return true;
                    }
                    
                    if(road.getLocation().equals(new EdgeLocation(
                            normLocation.getHexLoc(), EdgeDirection.North))&&road.getOwner()==currentPlayer){
                        return true;
                    }
                    
                    if(road.getLocation().equals(new EdgeLocation(
                            normLocation.getHexLoc(), EdgeDirection.NorthWest))&&road.getOwner()==currentPlayer){
                        return true;
                    }
                }
    	}
        return false;
    }
    
    public boolean isValidVertex(VertexLocation location){
        VertexLocation normVertLocation = location.getNormalizedLocation();
    	ArrayList<VertexObject> allVObjects = catanMap.getCitiesAndSettlements();
        
        for (VertexObject vObject: allVObjects) {
            //Assume that vObject.getLocation is returning the Normalized location
            //checks if a vertex object already exists at this location.
            if (vObject.getLocation().equals(normVertLocation)) {
    			return false;
    		}
        //If the hexDirection is northEast, check the current HexLocation's NorthWest and East vertices for settlements, and the north neighbor's east vertex
    	// checks the surrounding vertices around the northeast vertex for vertex objects	
    		if (normVertLocation.getDir()==VertexDirection.NorthEast) {
                    HexLocation northEastNeighbor = normVertLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
                  
                    if(vObject.getLocation().equals(new VertexLocation(
                            northEastNeighbor, VertexDirection.NorthWest))){
                            return false;
                    }
                    //check southeast of current hex
                    HexLocation southEastNeighbor = normVertLocation.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast);
                    
                    if(vObject.getLocation().equals(new VertexLocation(
                            southEastNeighbor, VertexDirection.NorthWest))){
                            return false;
                    }
                    
                    if(vObject.getLocation().equals(new VertexLocation(
                            normVertLocation.getHexLoc(), VertexDirection.NorthWest))){
                            return false;
                    }
    		}
                // checks the surrounding vertices around the northwest vertex for vertex objects	
                if(normVertLocation.getDir()==VertexDirection.NorthWest){
                    
                    HexLocation northwestNeighbor = normVertLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);
                    
                    if(vObject.getLocation().equals(new VertexLocation(
                            northwestNeighbor, VertexDirection.NorthEast))){
                        return false;
                    }
                    
                    HexLocation southwestNeighbor = normVertLocation.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest);

                    if(vObject.getLocation().equals(new VertexLocation(
                            southwestNeighbor, VertexDirection.NorthEast))){
                        return false;
                    }
                    
                    if(vObject.getLocation().equals(new VertexLocation(
                            normVertLocation.getHexLoc(), VertexDirection.NorthEast))){
                        return false;
                    }
                }
    	}
        return true;
    }
    
     /**
     * Removes a brick, wood, sheep, and wheat from the player building the 
     * settlement(player.resourceList.buySettlement();) 
     * and creates a road located at the given EdgeLocation (CatanMap.buildSettlment())
     *
     * @param location where the player is playing the road
     * @param playerIndex is used to identify the player playing the road
     */
    public void buildSettlement(EdgeLocation location, int playerIndex) {
        //NOTE: to be implemented
    }
    
    
    public boolean canBuyCity() {
        //checks if the player has enough resources.
        if(!players.get(turnTracker.getCurrentTurn()).getResources().canBuyCity()){
            return false;
        }
        //check if the player has remaining cities available to build
        if(!players.get(turnTracker.getCurrentTurn()).hasCity()){
            return false;
        }
        return true;        
    }

    /**
     * This function is going to be called only after canBuyCity is called and returns true.
     * This checks if the player owns a settlement at the location specified.
     *
     * @param location where the player would like to build the city
     * @param playerIndex identifies the player who would like to build this
     * city
     * @return true if the location specified already has a settlement owned by this player, false if not
     */
    public boolean canBuildCity(VertexLocation location) {
    	//get all the settlements on the catanMap
    	ArrayList<Settlement> settlements = catanMap.getSettlements();
    	int currentPlayer = turnTracker.getCurrentTurn();
    	//Iterate through settlements to make sure the player owns a settlement at the target location
    	for (Settlement settlement: settlements) {
    		if (settlement.getOwner() == currentPlayer && 
                        settlement.getLocation().getNormalizedLocation().equals(location)) { 
    			return true;
    		}
    	}
    	
    	return false;
    }

    public boolean canBuyDevCard(int playerIndex) {
        Player current = players.get(playerIndex);
        if(turnTracker.getCurrentTurn()==playerIndex){
            return current.getResources().canBuyDevCard();
        }else{
            return false;
        }
    }
    
    public boolean canPlayMonopoly(int playerIndex) {        
        Player current = players.get(playerIndex);
        
        return current.canPlayDevCard(DevCardType.MONOPOLY);
    }
    
    public boolean canPlaySoldier(int playerIndex) {        
        Player current = players.get(playerIndex);
        
        return current.canPlayDevCard(DevCardType.SOLDIER);
    }
    
    public boolean canPlayRoadBuilding(int playerIndex) {        
        Player current = players.get(playerIndex);
        
        return current.canPlayDevCard(DevCardType.ROAD_BUILD);
    }
    
    public boolean canPlayYearOfPlenty(int playerIndex) {        
        Player current = players.get(playerIndex);
        
        return current.canPlayDevCard(DevCardType.YEAR_OF_PLENTY);
    }
    
    public boolean canPlayMonument(int playerIndex) {        
        Player current = players.get(playerIndex);
        
        return current.canPlayDevCard(DevCardType.MONUMENT);
    }
    
    /**
     * returns true if its the players turn and turn status is ROLLING 
     */
    public boolean canRollNumber() {
        if(CatanFacade.getMyPlayerIndex() == getTurnTracker().getCurrentTurn()
                && getTurnTracker().getStatus() == TurnStatus.ROLLING)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * 
     * @return true if finish turn is a valid command
     */
    public boolean canFinishTurn()
    {
        if(CatanFacade.getMyPlayerIndex() == getTurnTracker().getCurrentTurn())
        {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @param playerIndex
     * @return true if the player must discard cards because of a seven being rolled
     */
    public boolean canDiscardCards(int playerIndex) {
        if(players.get(playerIndex).getResources().getTotalResources() > 7) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Removes three ore and two wheat from this player's ResourceList
     * Subtracts one city from players cities count
     * Adds settlement to players settlement count
     * (player.resourceList.buyCity();) 
     * Replace settlement located on the given VertexLocation with a city(CatanMap.buildCity())
     * @param location where the player would like to place a city
     * @param playerIndex used to identify the player building this settlement
     */
    public void buildCity(VertexLocation location, int playerIndex) {
    	//Don't implement for phase one
    }
    
    /**
     * @param rolledNumber - int not equal to 7
     */
    public void distributeResources(int rolledNumber)
    {
    	//Don't implement in phase 1
    }
    
    public boolean canOfferResource(ResourceType type, int amount) {
       return players.get(turnTracker.getCurrentTurn()).getResources().canOfferResource(type, amount);
    }
    
    public boolean canOfferMaritimeTrade(ResourceType resourceType) {
       int neededToTrade = catanMap.neededToOfferMaritimeTrade(getTurnTracker().getCurrentTurn(), resourceType);
       return canOfferResource(resourceType, neededToTrade);
    }
    
    public boolean canAcceptMaritimeTrade(ResourceType resourceType) {
        return bank.hasResource(resourceType);
    }
    
    public boolean canAcceptTrade(ResourceList tradeOffer){
        return players.get(CatanFacade.getMyPlayerIndex()).getResources().canAcceptTrade(tradeOffer);
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

    public CatanMap getMap() {
        return catanMap;
    }

    public void setMap(CatanMap catanMap) {
        this.catanMap = catanMap;
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
        if (!Objects.equals(this.catanMap, other.catanMap)) {
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
    
    
