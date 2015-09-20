/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.IServerProxy;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.exceptions.InsufficentSupplies;
import shared.exceptions.InvalidLocation;
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

    public CanDoFacade(IServerProxy proxy, Model model) {
        this.proxy = proxy;
        this.model = model;
    }

    /**
     * @param int playerIndex of whom you offer the trade.
     * @param ResourceList offer is a list or resources you offer.
     * @return whether the player has actually has the resources to be offered.
     */
    public boolean canOfferTrade() {
        return false;
    }

    /**
     * @param playerIndex (integer): Who's accepting / rejecting this trade,
     * @return Whether you accept the trade or not, and actually have the
     * resources to do so.
     */
    public boolean canAcceptTrade() {
        return false;
    }

    /**
     * @param String inputResource resource you offer
     * @param String ouputResouce resource you request
     * @param double ratio of input to output resources.
     * @return true if you have a vertex object in the same location as this
     * port, and the resource cards offered are the same type as the port, and
     * the cards offered and requested have the correct ratio.
     */
    public boolean canMaritimeTrade() {
        return false;
    }

    public void canBuyDevCard() {

    }

    public void canPlayYearOfPlenty() {

    }

    public void canPlayRoadBuilding() {

    }

    public void canPlaySoldier() {

    }

    public void canPlayMonopoly() {

    }

    /**
     * @param currentPlayerIndex player playing this road
     * @param EdgeLocation location where player would like to place road
     * @return true if player has enough resources to buy a road, the edge
     * location in question is connected to a road or settlement/city belonging
     * to the player.
     */
    public boolean canBuildRoad(int currentPlayerIndex, EdgeLocation roadLocation) {
        boolean returnValue = false;
        try {
            returnValue = model.canBuildRoad(roadLocation, currentPlayerIndex);
        } catch (InsufficentSupplies ex) {
            Logger.getLogger(CanDoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidLocation ex) {
            Logger.getLogger(CanDoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnValue;
    }

    /**
     * @param currentPlayerIndex player playing this road
     * @param VertexLocation this is where the player would like build a settlement
     * @return true if player has enough resources to buy a Settlement, has a
     * road connected to Hex vertex, and settlement is not within two hexEdges
     * of another Settlement.
     */
    public boolean canBuildSettlement(int currentPlayerIndex, VertexLocation vertex) {
        return false;
    }

    /**
     * @param VertexLocation this is where the player would like build a City
     * @return true if player has enough resources to buy a City, and owns a
     * settlement at this location
     */
    public boolean canBuildCity(int currentPlayerIndex, VertexLocation vertex) {
        return false;
    }

    public void canSendChat() {

    }

    public void canRollNumber() {

    }

    public void canRobPlayer() {

    }

    public void canFinishTurn() {

    }

}
