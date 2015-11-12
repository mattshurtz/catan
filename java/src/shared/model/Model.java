/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import client.data.PlayerInfo;
import client.data.RobPlayerInfo;
import client.facade.CatanFacade;
import com.google.gson.annotations.SerializedName;
import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.communication.params.moves.BuildRoadRequest;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.definitions.TurnStatus;

import shared.exceptions.GetPlayerException;
import shared.exceptions.InsufficientSupplies;
import shared.json.Deserializer;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.map.CatanMap;
import shared.model.map.City;
import shared.model.map.Hex;
import shared.model.map.Road;
import shared.model.map.Settlement;
import shared.model.map.VertexObject;

/**
 * bank (ResourceList): The Resource cards available to be distributed to the
 * players., remainingDevCards (DevCardList): the dev cards available to be
 * distributed to the players, chat (MessageList): All the chat messages., log
 * (MessageList): All the log messages., catanMap (CatanMap), players
 * (array[Player]), tradeOffer (TradeOffer, optional): The current trade offer,
 * if there is one., turnTracker (TurnTracker): This tracks who's turn it is and
 * what action's being done., version (index): The version of the model. This is
 * incremented whenever anyone makes a move., winner (index): This is -1 when
 * nobody's won yet. When they have, it's their order index [0-3]
 */
public class Model {

    public static final EdgeLocation[] setValues = new EdgeLocation[]{
        new EdgeLocation(new HexLocation(-3, 1), EdgeDirection.NorthEast),
        new EdgeLocation(new HexLocation(-3, 2), EdgeDirection.NorthEast),
        new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast),
        new EdgeLocation(new HexLocation(-2, 3), EdgeDirection.North),
        new EdgeLocation(new HexLocation(-2, 3), EdgeDirection.NorthEast),
        new EdgeLocation(new HexLocation(-1, 3), EdgeDirection.North),
        new EdgeLocation(new HexLocation(-1, 3), EdgeDirection.NorthEast),
        new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North),
        new EdgeLocation(new HexLocation(1, 2), EdgeDirection.North),
        new EdgeLocation(new HexLocation(1, 2), EdgeDirection.NorthWest),
        new EdgeLocation(new HexLocation(2, 1), EdgeDirection.North),
        new EdgeLocation(new HexLocation(2, 1), EdgeDirection.NorthWest),
        new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest),
        new EdgeLocation(new HexLocation(3, -1), EdgeDirection.NorthWest),
        new EdgeLocation(new HexLocation(3, -2), EdgeDirection.NorthWest),};
    public static final HashSet<EdgeLocation> validWaterEdges = new HashSet<EdgeLocation>(Arrays.asList(setValues));
    public static final VertexLocation[] vertexValues = new VertexLocation[]{
        new VertexLocation(new HexLocation(-3, 1), VertexDirection.NorthEast),
        new VertexLocation(new HexLocation(-3, 2), VertexDirection.NorthEast),
        new VertexLocation(new HexLocation(-3, 3), VertexDirection.NorthEast),
        new VertexLocation(new HexLocation(-2, 3), VertexDirection.NorthWest),
        new VertexLocation(new HexLocation(-2, 3), VertexDirection.NorthEast),
        new VertexLocation(new HexLocation(-1, 3), VertexDirection.NorthEast),
        new VertexLocation(new HexLocation(-1, 3), VertexDirection.NorthWest),
        new VertexLocation(new HexLocation(0, 3), VertexDirection.NorthEast),
        new VertexLocation(new HexLocation(0, 3), VertexDirection.NorthWest),
        new VertexLocation(new HexLocation(1, 2), VertexDirection.NorthEast),
        new VertexLocation(new HexLocation(1, 2), VertexDirection.NorthWest),
        new VertexLocation(new HexLocation(2, 1), VertexDirection.NorthEast),
        new VertexLocation(new HexLocation(2, 1), VertexDirection.NorthWest),
        new VertexLocation(new HexLocation(3, 0), VertexDirection.NorthWest),
        new VertexLocation(new HexLocation(3, -1), VertexDirection.NorthWest),
        new VertexLocation(new HexLocation(3, -2), VertexDirection.NorthWest),};
    public static final HashSet<VertexLocation> validWaterVertex = new HashSet<VertexLocation>(Arrays.asList(vertexValues));

    private ResourceList bank;

    private DevCardList deck;
    private MessageList chat;
    private MessageList log;
    @SerializedName("map")
    private CatanMap catanMap;
    private ArrayList<Player> players;

    private TradeOffer tradeOffer;
    private TurnTracker turnTracker;

    private int version;
    private int winner;

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

    }

    public Model(String name, boolean randomNumbers, boolean randomPorts, boolean randomTiles) {
		// TODO Auto-generated constructor stub
	}

	/**
     * Gets information from trade offer and changes the resources of the
     * players accordingly.
     */

    public boolean doAcceptTrade(TradeOffer offer, int playerIndex){
    	ResourceList tradeOffer = offer.getOffer();
    	
        if (canAcceptTrade(tradeOffer, playerIndex))
        	return false;
        
        ResourceList recResources = players.get(offer.getReceiver()).getResources();
        ResourceList sendResources = players.get(offer.getSender()).getResources();
        
        int resource = tradeOffer.getBrick();
        sendResources.addResource(ResourceType.BRICK, resource);
        recResources.addResource(ResourceType.BRICK, resource * -1);
        
        resource = tradeOffer.getWood();
        sendResources.addResource(ResourceType.WOOD, resource);
        recResources.addResource(ResourceType.WOOD, resource * -1);
        
        resource = tradeOffer.getSheep();
        sendResources.addResource(ResourceType.SHEEP, resource);
        recResources.addResource(ResourceType.SHEEP, resource * -1);
        
        resource = tradeOffer.getOre();
        sendResources.addResource(ResourceType.ORE, resource);
        recResources.addResource(ResourceType.ORE, resource * -1);
        
        resource = tradeOffer.getWheat();
        sendResources.addResource(ResourceType.WHEAT, resource);
        recResources.addResource(ResourceType.WHEAT, resource * -1);
        
        return true;
    }
    
    public boolean doAcceptMaritimeTrade(ResourceType resource) {
    	if (canAcceptMaritimeTrade(resource))
    		return false;

        return true;
    }

    /**
     * Removes three ore and two wheat from this player's ResourceList.
     * Subtracts one city from players cities count. Adds settlement to players
     * settlement count (player.resourceList.buyCity();) Replace settlement
     * located on the given VertexLocation with a city(CatanMap.buildCity())
     *
     * @param location where the player would like to place a city
     * @param playerIndex used to identify the player building this settlement
     */
    public void buildCity(VertexLocation location, int playerIndex) {
        players.get(playerIndex).buildCity();
        
    }

    /**
     * Calls canBuyRoad and canBuildRoad first Removes a brick and wood from the
     * player building the road(player.resourceList.buyRoad();) and creates a
     * road located at the given EdgeLocation (CatanMap.buildRoad())
     *
     * @param buildRoadInfo where the player is playing the road
     */
    public void buildRoad(BuildRoadRequest buildRoadInfo) {
        players.get(buildRoadInfo.getPlayerIndex()).buildRoad();
        
    }

    /**
     * First calls canBuySettlement and canBuildSettlement then, if true Removes
     * a brick, wood, sheep, and wheat from the player building the
     * settlement(player.resourceList.buySettlement();) and creates a road
     * located at the given EdgeLocation (CatanMap.buildSettlment())
     *
     * @param location where the player is playing the road
     * @param playerIndex is used to identify the player playing the road
     */
    public void buildSettlement(EdgeLocation location, int playerIndex) {
        //NOTE: to be implemented
    }

    /**
     * calls canBuyDevCard, and Check if it is the players turn, then subtracts
     * the resources from the player.
     *
     * @param playerIndex
     */
    public void buyDevCard(int playerIndex) {

    }

    public boolean canAcceptMaritimeTrade(ResourceType resourceType) {
        return bank.hasResource(resourceType);
    }

    
    public boolean canAcceptTrade(ResourceList tradeOffer, int playerIndex) {
        return players.get(playerIndex).getResources().canAcceptTrade(tradeOffer);
    }

    /**
     * This function is going to be called only after canBuyCity is called and
     * returns true. This checks if the player owns a settlement at the location
     * specified.
     *
     * @param location where the player would like to build the city
     * @return true if the location specified already has a settlement owned by
     * this player, false if not
     */
    public boolean canBuildCity(VertexLocation location) {
        //get all the settlements on the catanMap
        ArrayList<Settlement> settlements = catanMap.getSettlements();
        int currentPlayer = turnTracker.getCurrentTurn();
        //Iterate through settlements to make sure the player owns a settlement at the target location
        for (Settlement settlement : settlements) {
            if (settlement.getOwner() == currentPlayer
                    && settlement.getLocation().getNormalizedLocation().equals(location)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a player has a road, the resources to build this road, if it is
     * in a valid location on the catanMap, and if the player has a road left to
     * play
     *
     * @param location this is the location of the Road you would like to build
     * playerIndex identifies the player who would like to build the road
     * @return true if no exception is thrown InsufficientSupplies if the player
     * did not have enough resources or pieces
     */
    public boolean canBuildRoad(EdgeLocation location) {
        if (isValidRoadLocation(location)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if a settlement can be built by the current player at the given
     * VertexLocation
     *
     * @param location where the player would like to build the settlement
     * @return true if given VertexLocation is valid, false if not
     */
    public boolean canBuildSettlement(VertexLocation location) {
        //If it's the first round, then no need to check for roads
        // also need check for if a settlement already exists at this location
        if (turnTracker.getStatus() == TurnStatus.FIRST_ROUND || turnTracker.getStatus() == TurnStatus.SECOND_ROUND) {
            if (isValidVertex(location.getNormalizedLocation())) {
                return true;
            } else {
                return false;
            }
        }
        if (!isValidVertex(location.getNormalizedLocation())) {
            return false;
        }
        //Check to make sure the target vertex is touching a road (if not FIRST_ROUND)
        return surroundingEdgeOfVertexHasRoad(location);
    }

    /**
     * Checks if you can by a city
     *
     * @return true if a city can be bought
     */
    public boolean canBuyCity() {
        //checks if the player has enough resources.
        if (!players.get(turnTracker.getCurrentTurn()).getResources().canBuyCity()) {
            return false;
        }
        //check if the player has remaining cities available to build
        if (!players.get(turnTracker.getCurrentTurn()).hasCity()) {
            return false;
        }
        return true;
    }

    /**
     * Check it a dev card can be bought
     *
     * @param playerIndex the id of the player
     * @return true if can buy
     */
    public boolean canBuyDevCard(int playerIndex) {
        Player current = players.get(playerIndex);
        if (turnTracker.getCurrentTurn() == playerIndex) {
            return current.getResources().canBuyDevCard();
        } else {
            return false;
        }
    }

    /**
     * Checks whether the player has supplies to build a road. (Resources and
     * road pieces)
     *
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
     * Checks if current player has enough resources for a settlement, and then
     * checks if they have any settlements remaining.
     *
     * @return True if they player has enough resources and settlements, false
     * if no
     */
    public boolean canBuySettlement() {
        if (!players.get(turnTracker.getCurrentTurn()).getResources().canBuySettlement()) {
            //Player doesn't have enough resources
            return false;
        }
        //checks if the player has remaining settlements available to build.
        if (!players.get(turnTracker.getCurrentTurn()).hasSettlment()) {
            //Player doesn't have enough settlements
            return false;
        }
        return true;
    }

    /**
     *
     * @param playerIndex
     * @return true if the player must discard cards because of a seven being
     * rolled
     */
    public boolean canDiscardCards(int playerIndex) {
        if (players.get(playerIndex).getResources().getTotalResources() > 7) {
            return true;
        }
        return false;
    }

    /**
     * Checks if this player has the current turn, so they can finish turn.
     *
     * @return true if finish turn is a valid command
     */
    public boolean canFinishTurn() {
        if (CatanFacade.getMyPlayerIndex() == getTurnTracker().getCurrentTurn()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a maritime trade can be offered
     *
     * @param resourceType the type to trade
     * @return true if trade can happen
     */
    public boolean canOfferMaritimeTrade(ResourceType resourceType) {
        int neededToTrade = catanMap.neededToOfferMaritimeTrade(getTurnTracker().getCurrentTurn(), resourceType);
        return canOfferResource(resourceType, neededToTrade);
    }

    /**
     * Can the resource be offered
     *
     * @param type the resource type
     * @param amount how many of the aboce resource
     * @return true if can trade
     */
    public boolean canOfferResource(ResourceType type, int amount) {
        return players.get(turnTracker.getCurrentTurn()).getResources().canOfferResource(type, amount);
    }

    /**
     * Can offer trade checks if they player has sufficient resources to make
     * the offer
     *
     * @param offer
     * @return whether they can make the offer or not.
     */
    public boolean canOfferTrade(ResourceList offer) {
        return false;
    }

    /**
     * Check if a robber can be placed at a location
     *
     * @param hexLocation the location of robber
     * @return true if the robber can be placed at the location
     */
    public boolean canPlaceRobber(HexLocation hexLocation) {
        if (catanMap.getRobber().equals(hexLocation)) {
            return false;
        }
        if (Math.abs(hexLocation.getX()) >= 3 || Math.abs(hexLocation.getY()) >= 3) {
            return false;
        } else if (Math.abs(hexLocation.getX() + hexLocation.getY()) == 3) {
            //water hex
            return false;
        }
        return true;
    }

    /**
     *
     * @param playerIndex
     * @return
     */
    public boolean canPlayDevCard(int playerIndex) {
        if (canPlayMonopoly(playerIndex) || canPlaySoldier(playerIndex) || canPlayRoadBuilding(playerIndex) || canPlayYearOfPlenty(playerIndex) || canPlayMonument(playerIndex)) {
            return true;
        }
        return false;
    }

    public boolean canPlayMonopoly(int playerIndex) {
        Player current = players.get(playerIndex);

        return current.canPlayDevCard(DevCardType.MONOPOLY);
    }

    public boolean canPlayMonument(int playerIndex) {
        Player current = players.get(playerIndex);

        return current.canPlayDevCard(DevCardType.MONUMENT);
    }

    public boolean canPlayRoadBuilding(int playerIndex) {
        Player current = players.get(playerIndex);

        return current.canPlayDevCard(DevCardType.ROAD_BUILD);
    }

    public boolean canPlaySoldier(int playerIndex) {
        Player current = players.get(playerIndex);

        return current.canPlayDevCard(DevCardType.SOLDIER);
    }

    public boolean canPlayYearOfPlenty(int playerIndex) {
        Player current = players.get(playerIndex);

        return current.canPlayDevCard(DevCardType.YEAR_OF_PLENTY);
    }

    /**
     * Checks if the player can be robbed at a given location i.e. if the given
     * player has either a settlement or city at given hexLoc
     */
    public boolean canRobPlayer(int playerIndex, HexLocation hexLoc) {
        return (playerIndex != CatanFacade.getMyPlayerIndex()) && catanMap.canRobPlayer(playerIndex, hexLoc);
    }

    /**
     * returns true if its the players turn and turn status is ROLLING
     */
    public boolean canRollNumber() {
        if (CatanFacade.getMyPlayerIndex() == getTurnTracker().getCurrentTurn()
                && getTurnTracker().getStatus() == TurnStatus.ROLLING) {
            return true;
        }

        return false;
    }

    /**
     * Call canDiscardCards then, if they can subtract the cards from this
     * players resource list using subtract resource in ResourceList Class.
     *
     * @param playerIndex
     * @param listToDiscard
     */
    public void discardCards(int playerIndex, ResourceList listToDiscard) {

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

    /**
     * Calls canFinishTurn and updates the turnTracker accordingly.
     */
    public void finishTurn() {

    }

    public ResourceList getBank() {
        return bank;
    }

    public MessageList getChat() {
        return chat;
    }

    public DevCardList getDeck() {
        return deck;
    }

    public MessageList getLog() {
        return log;
    }

    public CatanMap getMap() {
        return catanMap;
    }

    /**
     * @param playerIndex this is the index of the player
     * @return Player at the specified playerIndex
     * @throws GetPlayerException if the player list is empty, or if the index
     * is invalid
     */
    public Player getPlayer(int playerIndex) throws GetPlayerException {

        if (players == null || players.size() == 0) {
            throw new GetPlayerException("There are currently no players in this model's player list");
        }
        if (playerIndex > 3 || playerIndex < 0) {
            throw new GetPlayerException("Invalid playerIndex:  Must be 0-3");
        }
        return players.get(playerIndex);
    }

    public PlayerInfo[] getPlayerInfos() {
        List<PlayerInfo> ret = new ArrayList<>();
        List<Player> playas = getPlayers();
        Deserializer deserializer = new Deserializer();
        for (Player p : playas) {
            if (p != null && p.getName() != null) {
                ret.add(deserializer.toPlayerInfo(p));
            }
        }
        return ret.toArray(new PlayerInfo[0]);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets a RobPlayerInfo object given a player's index
     *
     * @param index Index of the player
     * @return RobPlayerInfo object representing the player at the given index
     */
    public RobPlayerInfo getRobPlayerInfo(int index) {
        RobPlayerInfo robInfo = new RobPlayerInfo();

        // Get playerInfo and make RobPlayerInfo object
        try {
            Deserializer deserializer = new Deserializer();
            Player player = getPlayer(index);
            PlayerInfo playerInfo = deserializer.toPlayerInfo(player);
            robInfo = new RobPlayerInfo(playerInfo);
            robInfo.setNumCards(player.getResources().getTotalResources());
        } catch (GetPlayerException e) {
            //Something's wrong with the given player index
            e.printStackTrace();
        }

        return robInfo;
    }

    public TradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public TurnTracker getTurnTracker() {
        return turnTracker;
    }

    public int getVersion() {
        return version;
    }

    public int getWinner() {
        return winner;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.version;
        return hash;
    }

    public boolean isBuiltThroughOpponent(ArrayList<VertexLocation> suspectVerticies) {
        int numOpponentsBuiltThrough = 0;
        for (VertexLocation loc : suspectVerticies) {
            for (VertexObject vObj : catanMap.getCitiesAndSettlements()) {
                if (vObj.getLocation().getNormalizedLocation().equals(loc)) {
                    if (vObj.getOwner() != CatanFacade.getMyPlayerIndex()) {
                        numOpponentsBuiltThrough++;
                    } else {
                        //found valid connected vertex with own building
                        return false;
                    }
                }
            }
        }

        if (numOpponentsBuiltThrough == suspectVerticies.size()) {
            //every suspect vertex is through an opponent
            return true;
        } else {
            //connected vertex with no building
            return false;
        }
    }

    public boolean isValidFirstRoad(EdgeLocation normEdge) {
        ArrayList<VertexObject> allVObjects = catanMap.getCitiesAndSettlements();
        int currentPlayer = turnTracker.getCurrentTurn();
        if (normEdge.getDir() == EdgeDirection.North) {
            for (VertexObject vertexObject : allVObjects) {

                if (vertexObject.getLocation().getNormalizedLocation().equals(new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthEast)) && vertexObject.getOwner() == currentPlayer && isValidPortEdge(normEdge) && !surroundingEdgeOfVertexHasRoad(vertexObject.getLocation().getNormalizedLocation())) {
                    return true;
                }
                if (vertexObject.getLocation().getNormalizedLocation().equals(new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthWest)) && vertexObject.getOwner() == currentPlayer && isValidPortEdge(normEdge) && !surroundingEdgeOfVertexHasRoad(vertexObject.getLocation().getNormalizedLocation())) {
                    return true;
                }
            }
        }

        if (normEdge.getDir() == EdgeDirection.NorthWest) {
            HexLocation southwestNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest);
            for (VertexObject vertexObject : allVObjects) {

                if (vertexObject.getLocation().getNormalizedLocation().equals(new VertexLocation(southwestNeighbor, VertexDirection.NorthEast)) && vertexObject.getOwner() == currentPlayer && isValidPortEdge(normEdge) && !surroundingEdgeOfVertexHasRoad(vertexObject.getLocation().getNormalizedLocation())) {
                    return true;
                }
                if (vertexObject.getLocation().getNormalizedLocation().equals(new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthWest)) && vertexObject.getOwner() == currentPlayer && isValidPortEdge(normEdge) && !surroundingEdgeOfVertexHasRoad(vertexObject.getLocation().getNormalizedLocation())) {
                    return true;
                }
            }
        }

        if (normEdge.getDir() == EdgeDirection.NorthEast) {
            HexLocation southeastNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast);
            for (VertexObject vertexObject : allVObjects) {

                if (vertexObject.getLocation().getNormalizedLocation().equals(new VertexLocation(southeastNeighbor, VertexDirection.NorthWest)) && vertexObject.getOwner() == currentPlayer && isValidPortEdge(normEdge) && !surroundingEdgeOfVertexHasRoad(vertexObject.getLocation().getNormalizedLocation())) {
                    return true;
                }
                if (vertexObject.getLocation().getNormalizedLocation().equals(new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthEast)) && vertexObject.getOwner() == currentPlayer && isValidPortEdge(normEdge) && !surroundingEdgeOfVertexHasRoad(vertexObject.getLocation().getNormalizedLocation())) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValidPortEdge(EdgeLocation normEdge) {
// Create a HashSet of invalid port eddge locations and check them
        // at the beginning of the can build edge function
        HexLocation hexLocation = normEdge.getHexLoc();
        //Water or Desert
        int x = hexLocation.getX();
        int y = hexLocation.getY();
        //Check for water coordinates
        if (Math.abs(x) >= 3 || Math.abs(y) >= 3 || Math.abs(x + y) >= 3) {
            if (validWaterEdges.contains(normEdge)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isValidPortVertex(VertexLocation location) {
        // Create a HashSet of invalid port eddge locations and check them
        // at the beginning of the can build edge function
        HexLocation hexLocation = location.getHexLoc();
        //Water or Desert
        int x = hexLocation.getX();
        int y = hexLocation.getY();
        //Check for water coordinates
        if (Math.abs(x) >= 3 || Math.abs(y) >= 3 || Math.abs(x + y) >= 3) {
            if (validWaterVertex.contains(location)) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isValidRoadLocation(EdgeLocation location) {
        EdgeLocation normEdge = location.getNormalizedLocation();
        HexLocation normHexLocation = normEdge.getHexLoc();
        int currentPlayer = turnTracker.getCurrentTurn();

        if (turnTracker.getStatus().equals(TurnStatus.FIRST_ROUND) || turnTracker.getStatus().equals(TurnStatus.SECOND_ROUND)) {
            return isValidFirstRoad(normEdge);
        }

        for (Road road : catanMap.getRoads()) {
            // check if road already exists on this edge
            if (road.getLocation().getNormalizedLocation().equals(normEdge)) {
                return false;
            }
        }

        //check if connected and determine connected verticies
        boolean connected = false;
        ArrayList<VertexLocation> connectedVerticies = new ArrayList<VertexLocation>();

        for (Road road : catanMap.getRoads()) {

            // check around North edge 
            if (normEdge.getDir() == EdgeDirection.North) {
//                VertexLocation firstSuspectVertex = new VertexLocation(normEdge.getHexLoc(),VertexDirection.NorthEast);
//                VertexLocation secondSuspectVertex = new VertexLocation(normEdge.getHexLoc(),VertexDirection.NorthWest);    

                //There exists a vertex an adjecent vertex object owned by the player
                //then this is a valid location. This is important for the setup phase
                HexLocation northeastNeighbor = normHexLocation.getNeighborLoc(EdgeDirection.NorthEast);
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        northeastNeighbor, EdgeDirection.NorthWest)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {

//                    if(isBuildingThroughOpponent(firstSuspectVertex)){
//                        return false;
//                    }
//                    else {
//                        return true;
//                    }
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthEast);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }

                HexLocation northwestNeighbor = normHexLocation.getNeighborLoc(EdgeDirection.NorthWest);
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        northwestNeighbor, EdgeDirection.NorthEast)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {

//                    if( isBuildingThroughOpponent(secondSuspectVertex)){
//                        return false;
//                    }
//                    else {
//                        return true;
//                    }           
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthWest);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        normEdge.getHexLoc(), EdgeDirection.NorthEast)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {
//                    if(isBuildingThroughOpponent(firstSuspectVertex) ){
//                        return false;
//                    }
//                    else {
//                        return true;
//                    }  
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthEast);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        normEdge.getHexLoc(), EdgeDirection.NorthWest)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {

//                    if(isBuildingThroughOpponent(secondSuspectVertex)){
//                        return false;
//                    }
//                    else {
//                        return true;
//                    }
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthWest);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
            }
            //Check arround the NorthWest edge
            if (normEdge.getDir() == EdgeDirection.NorthWest) {
                HexLocation northwestNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);
                HexLocation southwestNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest);

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        northwestNeighbor, EdgeDirection.NorthEast)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthWest);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        southwestNeighbor, EdgeDirection.NorthEast)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {

                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(southwestNeighbor, VertexDirection.NorthEast);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        southwestNeighbor, EdgeDirection.North)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(southwestNeighbor, VertexDirection.NorthEast);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        normEdge.getHexLoc(), EdgeDirection.North)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthWest);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
            }
            //Check arround the NorthEast edge
            if (normEdge.getDir() == EdgeDirection.NorthEast) {
                HexLocation northeastNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
                HexLocation southeastNeighbor = normEdge.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast);

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        northeastNeighbor, EdgeDirection.NorthWest)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthEast);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        southeastNeighbor, EdgeDirection.NorthWest)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(southeastNeighbor, VertexDirection.NorthWest);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        southeastNeighbor, EdgeDirection.North)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(southeastNeighbor, VertexDirection.NorthEast);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        normEdge.getHexLoc(), EdgeDirection.North)) && road.getOwner() == currentPlayer && isValidPortEdge(normEdge)) {
                    connected = true;
                    VertexLocation suspectVertex = new VertexLocation(normEdge.getHexLoc(), VertexDirection.NorthEast);
                    if (!connectedVerticies.contains(suspectVertex)) {
                        connectedVerticies.add(suspectVertex);
                    }
                }
            }
        }

        //check connected verticies for own or buildings
        if (connected && !isBuiltThroughOpponent(connectedVerticies)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidVertex(VertexLocation location) {
        VertexLocation normVertLocation = location.getNormalizedLocation();
        ArrayList<VertexObject> allVObjects = catanMap.getCitiesAndSettlements();

        for (VertexObject vObject : allVObjects) {
            //Assume that vObject.getLocation is returning the Normalized location
            //checks if a vertex object already exists at this location.
            VertexLocation normVObjectLocation = vObject.getLocation().getNormalizedLocation();
            if (normVObjectLocation.equals(normVertLocation)) {
                return false;
            }
            //If the hexDirection is northEast, check the current HexLocation's NorthWest and East vertices for settlements, and the north neighbor's east vertex
            // checks the surrounding vertices around the northeast vertex for vertex objects	
            if (normVertLocation.getDir() == VertexDirection.NorthEast) {
                HexLocation northEastNeighbor = normVertLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);

                if (normVObjectLocation.equals(new VertexLocation(
                        northEastNeighbor, VertexDirection.NorthWest))) {
                    return false;
                }
                //check southeast of current hex
                HexLocation southEastNeighbor = normVertLocation.getHexLoc().getNeighborLoc(EdgeDirection.SouthEast);

                if (normVObjectLocation.equals(new VertexLocation(
                        southEastNeighbor, VertexDirection.NorthWest))) {
                    return false;
                }

                if (normVObjectLocation.equals(new VertexLocation(
                        normVertLocation.getHexLoc(), VertexDirection.NorthWest))) {
                    return false;
                }
            }
            // checks the surrounding vertices around the northwest vertex for vertex objects	
            if (normVertLocation.getDir() == VertexDirection.NorthWest) {

                HexLocation northwestNeighbor = normVertLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);

                if (normVObjectLocation.equals(new VertexLocation(
                        northwestNeighbor, VertexDirection.NorthEast))) {
                    return false;
                }

                HexLocation southwestNeighbor = normVertLocation.getHexLoc().getNeighborLoc(EdgeDirection.SouthWest);

                if (normVObjectLocation.equals(new VertexLocation(
                        southwestNeighbor, VertexDirection.NorthEast))) {
                    return false;
                }

                if (normVObjectLocation.equals(new VertexLocation(
                        normVertLocation.getHexLoc(), VertexDirection.NorthEast))) {
                    return false;
                }
            }

        }

        if (!isValidPortVertex(normVertLocation)) {
            return false;
        }
        return true;
    }

    /**
     * Determines who has the largest army and awards that player largest army.
     */
    public void largestArmy() {

    }

    /**
     * Determines who should have the longest road and awards that player the
     * longest road.
     */
    public void longestRoad() {

    }

    /**
     * Initiates a trade between the trader and receiver, specified by the first
     * two arguments. The proposed trade is specified by the third argument.
     * Negative resources are the resources the trader is hoping to receive, and
     * positive resources are the resources the trader is willing to give.
     *
     * @param traderIndex
     * @param receiverIndex
     * @param offer
     */
    public void offerTrade(int traderIndex, int receiverIndex, ResourceList offer) {

    }

    /**
     * Takes all resources from all players matching the declared resource, and
     * gives them to player of the given playerIndex. Also removes the monopoly
     * card from that player's hand.
     *
     * @param playerIndex the player who played the monopoly card
     * @param resource the type of resource the player will steal from everyone
     */
    public void playMonopoly(int playerIndex, ResourceType resource) {
        try {
            for(Player player: players){
                if(!player.equals(players.get(playerIndex))){
                    int amountOfResource = player.getResources().getResource(resource);
                    players.get(playerIndex).resources.addResource(resource, amountOfResource);
                    player.getResources().subtractResource(resource, amountOfResource);
                }
            }
            players.get(playerIndex).oldDevCards.removeMonopoly();
        } catch (InsufficientSupplies ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds one victory points to the player of given playerIndex and removes
     * the "monument" card from their hand
     *
     * @param playerIndex the player playing the monument card.
     */
    public void playMonument(int playerIndex) {
        try {
            players.get(playerIndex).incrementVictoryPoints();
            players.get(playerIndex).oldDevCards.removeMonument();
        } catch (InsufficientSupplies ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Removes the Road Building card from the given player's hand (Actual
     * placing of roads is handled on the client side by the MapController)
     *
     * @param playerIndex
     */
    public void playRoadBuilding(int playerIndex) {
        try {
            players.get(playerIndex).oldDevCards.removeRoadBuilding();
        } catch (InsufficientSupplies ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Removes the Soldier card from the given player's hand, and increases the
     * soldier count by one for that player. (Actual placing of the robber is
     * handled on the client side by the MapController).
     *
     * @param playerIndex
     */
    public void playSoldier(int playerIndex) {
        try {
            players.get(playerIndex).oldDevCards.removeSoldier();
            players.get(playerIndex).incrementSoldiers();
        } catch (InsufficientSupplies ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds two resources to the given player's bank, and removes the Year of
     * Plenty card from that player's hand. The types of resources given are
     * specified by the second and third arguments.
     *
     * @param playerIndex
     * @param firstResource
     * @param secondResource
     */
    public void playYearOfPlenty(int playerIndex,
            ResourceType firstResource, ResourceType secondResource) {
        try {
            players.get(playerIndex).oldDevCards.removeYearOfPlenty();
            players.get(playerIndex).resources.addResource(firstResource, 1);
            players.get(playerIndex).resources.addResource(secondResource, 1);
        } catch (InsufficientSupplies ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Removes resources from the victim's bank and adds them to the robber's
     * bank.
     *
     * @param robberIndex
     * @param victimIndex
     */
    public void robPlayer(int robberIndex, int victimIndex) {
        ResourceType robbed = players.get(victimIndex).resources.robResource();
        players.get(robberIndex).resources.addResource(robbed, 1);
    }

    /**
     * Rolls a number and changes the turn status from ROLLING to PLAYING Add
     * resources accordingly.
     */
    public boolean rollNumber(int rolledNumber) {

        if (rolledNumber < 1 && rolledNumber > 13 && canRollNumber()) {
            distributeResources(rolledNumber);
            return true;
        }
        return false;
    }

    /**
     * @param rolledNumber - int not equal to 7
     */
    public void distributeResources(int rolledNumber) {
        for (Hex hex : catanMap.getHexes()) {
            if (hex.getNumber() == rolledNumber) {
                for (Player player : players) {
                    for (VertexObject building : catanMap.getCitiesAndSettlements()) {
                        ArrayList<VertexLocation> validOwnerLocations = catanMap.getValidNormalizedVertexObjectLocations(hex.getLocation());
                        if (validOwnerLocations.contains(building.getLocation().getNormalizedLocation()) && building.getOwner() == player.getPlayerIndex()) {
                            if (building instanceof City) {
                                player.getResources().addResource(hex.getResourceType(), 2);
                            } else {
                                player.getResources().addResource(hex.getResourceType(), 1);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Posts a chat message to the chat list.
     *
     * @param chat the MessageLine object containing the message as well as the
     * name of the player that sent it.
     */
    public void doSendChat(MessageLine chat) {
        this.chat.addLine(chat);
    }

    public void setBank(ResourceList bank) {
        this.bank = bank;
    }

    /**
     * This get's called by the sendChat Command and adds a message to the chat
     *
     * @param chat
     */
    public void setChat(MessageList chat) {
        this.chat = chat;
    }

    public void setDeck(DevCardList remainingDevCards) {
        this.deck = remainingDevCards;
    }

    public void setLog(MessageList log) {
        this.log = log;
    }

    public void setMap(CatanMap catanMap) {
        this.catanMap = catanMap;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setTradeOffer(TradeOffer tradeOffer) {
        this.tradeOffer = tradeOffer;
    }

    public void setTurnTracker(TurnTracker tt) {
        this.turnTracker = tt;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public boolean surroundingEdgeOfVertexHasRoad(VertexLocation location) {

        VertexLocation normLocation = location.getNormalizedLocation();
        int currentPlayer = turnTracker.getCurrentTurn();
        for (Road road : catanMap.getRoads()) {
            //Assume that vObject.getLocation is returning the Normalized location
            //If the hexDirection is northEast, check the current HexLocation's NorthWest and East vertices for settlements, and the north neighbor's east vertex
            // checks the surrounding vertices around the northeast vertex for vertex objects	
            if (normLocation.getDir() == VertexDirection.NorthEast) {
                HexLocation northeastNeighbor = normLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthEast);
                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        northeastNeighbor, EdgeDirection.NorthWest)) && road.getOwner() == currentPlayer) {
                    return true;
                }
                //check southeast of current hex

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        normLocation.getHexLoc(), EdgeDirection.North)) && road.getOwner() == currentPlayer) {
                    return true;
                }

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        normLocation.getHexLoc(), EdgeDirection.NorthEast)) && road.getOwner() == currentPlayer) {
                    return true;
                }
            }
            // checks the surrounding vertices around the northwest vertex for vertex objects	
            if (normLocation.getDir() == VertexDirection.NorthWest) {

                HexLocation northwestNeighbor = normLocation.getHexLoc().getNeighborLoc(EdgeDirection.NorthWest);

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        northwestNeighbor, EdgeDirection.NorthEast)) && road.getOwner() == currentPlayer) {
                    return true;
                }

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        normLocation.getHexLoc(), EdgeDirection.North)) && road.getOwner() == currentPlayer) {
                    return true;
                }

                if (road.getLocation().getNormalizedLocation().equals(new EdgeLocation(
                        normLocation.getHexLoc(), EdgeDirection.NorthWest)) && road.getOwner() == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

}
