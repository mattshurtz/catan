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
 * An abstract class defining all the CanDo methods, extended by the 
 * CanDo - MyTurn - facade (which actually checks whether things are doable)
 * and the CanDo - NotMyturn - facade (which just returns false because you
 * can't do anything when it's not your turn).
 */
public abstract class CanDoFacade {

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

    public boolean canBuyDevCard() {
        return false;
    }

    public boolean canPlayYearOfPlenty() {
        return false;
    }

    public boolean canPlayRoadBuilding() {
        return false;
    }

    public boolean canPlaySoldier() {
        return false;
    }

    public boolean canPlayMonopoly() {
        return false;
    }

    /**
     * @param roadLocation where player would like to place road
     * @return true if player has enough resources to buy a road, the edge
     * location in question is connected to a road or settlement/city belonging
     * to the player.
     */
    public boolean canBuildRoad(EdgeLocation roadLocation) throws InvalidLocation {
        try {
            return model.canBuildRoad(roadLocation);
        } catch (InvalidLocation ex) {
            Logger.getLogger(CanDoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * 
     * @return true if insufficentSupplies is not thrown. 
     */
    public boolean canBuyRoad() throws InsufficentSupplies {
        try {
            return model.canBuyRoad();
        } catch (InsufficentSupplies ex) {
            Logger.getLogger(CanDoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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

    public boolean canSendChat() {
        return false;
    }

    public boolean canRollNumber() {
        return false;
    }

    public boolean canRobPlayer() {
        return false;
    }

    public boolean canFinishTurn() {
        return false;
    }

}
