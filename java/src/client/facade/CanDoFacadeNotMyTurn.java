package client.facade;

import client.proxy.IServerProxy;
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
public class CanDoFacadeNotMyTurn extends CanDoFacade {

    public CanDoFacadeNotMyTurn(IServerProxy proxy, Model model) {
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
    public boolean canBuyDevCard() {
        return false;
    }

    @Override
    public boolean canMaritimeTrade(String inputResource, String ouputResouce, double ratio) {
        return false;
    }

    @Override
    public boolean canAcceptTrade(int playerIndex) {
        return true;
    }

    @Override
    public boolean canOfferTrade(int playerIndex, ResourceList resourceList) {
        return false;
    }
}
