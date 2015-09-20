/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import com.google.gson.Gson;
import java.util.ArrayList;
import shared.exceptions.InsufficentSupplies;
import shared.exceptions.InvalidLocation;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.map.Map;

/**
 * bank (ResourceList): The cards available to be distributed to the players.,
 * chat (MessageList): All the chat messages., log (MessageList): All the log
 * messages., map (Map), players (array[Player]), tradeOffer (TradeOffer,
 * optional): The current trade offer, if there is one., turnTracker
 * (TurnTracker): This tracks who's turn it is and what action's being done.,
 * version (index): The version of the model. This is incremented whenever
 * anyone makes a move., winner (index): This is -1 when nobody's won yet. When
 * they have, it's their order index [0-3]
 */
public class Model {

    ResourceList bank;
    MessageList chat;
    MessageList log;
    Map map;
    ArrayList<Player> players;
    TradeOffer tradeOffer;
    TurnTracker turnTracker;
    int version;
    int winner;

    public Model() {
        bank = new ResourceList();
        chat = new MessageList();
        log = new MessageList();
        map = new Map();
        players = new ArrayList<Player>();
        tradeOffer = new TradeOffer();
        turnTracker = new TurnTracker();
        version = 0;
        winner = -1;
    }

    /**
     * @param json this will be the Json representation of the model returned
     * from the server.
     * @return a new Model class representation of the current model on the
     * server.
     */
    public static Model parseModel(String json) {
        return new Gson().fromJson(json, Model.class);
    }

    /**
     * @param playerIndex this is the index of the player
     * @return Player at the specified playerIndex
     */
    public Player getPlayer(int playerIndex) {
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
     * @param playerIndex identifies the player who would like to build the road
     * @return true if no exception is thrown
     * @throws InsufficentSupplies if the player did not have enough resources or pieces
     * @throws InvalidLocation if this is an invalid location for this player to
     * play a road
     */
    public boolean canBuildRoad(EdgeLocation location, int playerIndex) throws InsufficentSupplies, InvalidLocation {
        if (!players.get(playerIndex).hasRoad()) {
            throw new InsufficentSupplies("");
        }
        if (!map.canPlaceRoadAtLoc(location)) {
            throw new InvalidLocation("");
        }
        if (!players.get(playerIndex).getResources().canBuyRoad()) {
            throw new InsufficentSupplies("");
        }
        return true;
    }

    /**
     * Removes a brick and wood from the player building the road(player.resourceList.buyRoad();) and creates a
     * road located at the given EdgeLocation (Map.buildRoad())
     *
     * @param location where the player is playing the road
     * @param playerIndex is used to identify the player playing the road
     */
    public void buildRoad(EdgeLocation location, int playerIndex) {

    }

    /**
     * Checks if a player has a settlement to build, the resources to build this
     * settlement and if it is in a valid location on the map.
     *
     * @param location where the player would like to build the settlement
     * @param playerIndex identifies the player who would like to build this
     * settlement
     * @return true if no exception is thrown
     * @throws InsufficentSupplies if the player did not have enough resources
     * @throws InvalidLocation if this is an invalid location for this player to
     * play a settlement
     */
    public boolean canBuildSettlement(VertexLocation location, int playerIndex) throws InsufficentSupplies, InvalidLocation {
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

}
