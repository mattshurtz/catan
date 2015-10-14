package client.facade;

import client.proxy.IServerProxy;
import shared.communication.params.moves.AcceptTradeRequest;
import shared.definitions.ResourceType;
import shared.exceptions.InsufficientSupplies;
import shared.exceptions.InvalidLocation;
import shared.exceptions.ServerException;
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
    public boolean canAcceptTrade(ResourceList tradeOffer) {
        // TODO check if we have sufficient resources to accept this trade
        return false;
    }
    
    @Override
    public void acceptTrade( boolean willAccept ) throws ServerException{
        
        AcceptTradeRequest request = new AcceptTradeRequest(willAccept);
        request.setType("acceptTrade");
        request.setPlayerIndex(CatanFacade.getMyPlayerIndex());
 
        proxy.acceptTrade(request);
    }
}
