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
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.Model;
import shared.model.ResourceList;

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
     * @param playerIndex of whom you offer the trade.
     * @param resourceList offer is a list or resources you offer.
     * @return whether the player has actually has the resources to be offered.
     */
    public boolean canOfferTrade(int playerIndex, ResourceList resourceList) {
        return false;
    }

    /**
     * @param playerIndex (integer): Who's accepting / rejecting this trade,
     * @return Whether you accept the trade or not, and actually have the
     * resources to do so.
     */
    public boolean canAcceptTrade(int playerIndex) {
        return false;
    }

    /**
     * @param inputResource resource you offer
     * @param ouputResouce resource you request
     * @param ratio of input to output resources.
     * @return true if you have a vertex object in the same location as this
     * port, and the resource cards offered are the same type as the port, and
     * the cards offered and requested have the correct ratio.
     */
    public boolean canMaritimeTrade(String inputResource, String ouputResouce, double ratio) {
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
     * @param roadLocation where player would like to place road
     * @return true if player has enough resources to buy a road, the edge
     * location in question is connected to a road or settlement/city belonging
     * to the player.
     */
    public boolean canBuildRoad(EdgeLocation roadLocation) {
        boolean returnValue = false;
        try {
            returnValue = model.canBuildRoad(roadLocation);
        } catch (InsufficentSupplies ex) {
            Logger.getLogger(CanDoFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidLocation ex) {
            Logger.getLogger(CanDoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnValue;
    }

    /**
     * @param vertex this is where the player would like build a
     * settlement
     * @return true if player has enough resources to buy a Settlement, has a
     * road connected to Hex vertex, and settlement is not within two hexEdges
     * of another Settlement.
     */
    public boolean canBuildSettlement(VertexLocation vertex) {
        return false;
    }

    /**
     * @param vertex this is where the player would like build a City
     * @return true if player has enough resources to buy a City, and owns a
     * settlement at this location
     */
    public boolean canBuildCity(VertexLocation vertex) {
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
