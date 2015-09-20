/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import shared.definitions.ResourceType;
import shared.locations.VertexLocation;

/**
 */
public class ResourceList {

    int brick;
    int ore;
    int sheep;
    int wheat;
    int wood;

    /**
     * @param ResourceList accept is the list of resource necessary to accept
     * the trade.
     * @return true if player has enough resources to accept the trade
     */
    public boolean canAcceptTrade(ResourceList accept) {
        return false;
    }

    /**
     * @param ResourceList offer is the resources to be offered in trade.
     * @return true if the player has the resources to be offered;
     */
    public boolean canOfferTade(ResourceList offer) {
        return false;
    }

    /**
     * @return true if player has enough resources to buy a road
     */
    public boolean canBuyRoad() {
        if (wood > 0 && brick < 0) {
            return true;
        }
        return false;
    }

    /**
     * subtracts one brick and one wood from the resourceList;
     */
    public void buyRoad() {

    }

    /**
     * @return true if player has enough resources to buy a Settlement
     */
    public boolean canBuySettlement() {
        return false;
    }

    /**
     * subtracts one brick, wood, sheep, and wheat from the resourceList;
     */
    public void buySettlement() {

    }

    /**
     * @return true if player has enough resources to buy a City
     */
    public boolean canBuyCity() {
        return false;
    }

    /**
     * subtracts three ore and two wheat from ResourceList
     */
    public void buyCity() {

    }

    /**
     * Use to add a resource to the resource list(trade, draw)
     *
     * @param resourceType this is the type of resource to be incremented.
     */
    public void addResource(ResourceType resourceType) {
        if (resourceType == ResourceType.BRICK) {
            brick++;
        }
    }

    /**
     * Use to subtract a resource from the resource list.
     *
     * @param resourceType this is the type of resource to be decremented.
     */
    public void subtractResource(ResourceType resourceType) {

    }
}
