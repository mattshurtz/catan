package client.facade;

import client.proxy.IServerProxy;
import shared.communication.params.moves.AcceptTradeRequest;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
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
    	try {
			ResourceList ph = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getResources();
			
			if( ph.getBrick() + tradeOffer.getBrick() < 0 ||
				ph.getOre() + tradeOffer.getOre() < 0 ||
				ph.getSheep() + tradeOffer.getSheep() < 0 ||
				ph.getWheat() + tradeOffer.getWheat() < 0 ||
				ph.getWood() + tradeOffer.getWood() < 0) {
				return false;
			}
			return true;
    	} catch (GetPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    	
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
