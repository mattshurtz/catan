package client.facade;

import client.proxy.IServerProxy;
import shared.definitions.ResourceType;
import shared.exceptions.InsufficientSupplies;
import shared.exceptions.InvalidLocation;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.model.Model;
import shared.model.ResourceList;

/**
 * A "can do" facade for when it's not my turn -- basically just automatically
 * returns false for everything other than chatting and responding to trades.
 */
public class StateNotMyTurn extends StateBase {

    public StateNotMyTurn(IServerProxy proxy, Model model) {
       super( proxy, model );
    }
    
    @Override
    public boolean canBuyRoad() throws InsufficientSupplies {
        return false;
    }

    @Override
    public boolean canBuildRoad(EdgeLocation roadLocation) throws InvalidLocation {
        return false;
    }

    @Override
    public boolean canFinishTurn() {
        return false;
    }

    @Override
    public boolean canRobPlayer(int playerIndex) {
        return false;
    }

    @Override
    public boolean canRollNumber() {
        return false;
    }

    @Override
    public boolean canSendChat() {
        return true;
    }

    @Override
    public boolean canBuyCity() {
        return false;
    }

    @Override
    public boolean canBuildCity(VertexLocation vertex) {
        return false;
    }

    @Override
    public boolean canBuySettlement() {
        return false;
    }

    @Override
    public boolean canBuildSettlement(VertexLocation vertex) {
        return false;
    }

    @Override
    public boolean canPlayMonopoly() {
        return false;
    }

    @Override
    public boolean canPlaySoldier() {
        return false;
    }

    @Override
    public boolean canPlayRoadBuilding() {
        return false;
    }

    @Override
    public boolean canPlayYearOfPlenty() {
        return false;
    }
    
    @Override
    public boolean canPlayMonument() {
        return false;
    }

    @Override
    public boolean canBuyDevCard() {
        return false;
    }

    @Override
    public boolean canAcceptTrade(ResourceList tradeOffer) {
        return false;
    }

    @Override
    public boolean canOfferMaritimeTrade(ResourceType resourceType) {
        return false;
    }

    @Override
    public boolean canAcceptMaritimeTrade(ResourceType resourceType) {
        return false;
    }

    @Override
    public boolean canOfferTrade(int playerIndex, ResourceList resourceList) {
        return false;
    }
}
