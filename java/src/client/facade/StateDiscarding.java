/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.IServerProxy;
import shared.communication.params.moves.DiscardCardsRequest;
import shared.exceptions.ServerException;
import shared.model.Model;
import shared.model.ResourceList;

/**
 *
 * @author JanPaul
 */
public class StateDiscarding extends StateBase {

    public StateDiscarding(IServerProxy proxy, Model model) {
        super(proxy, model);
    }
    
    @Override
    public boolean canDiscardCards(int playerIndex, ResourceList discardedCards ) {
        return model.canDiscardCards(playerIndex);
    }
    
    @Override
    public void discardCards( ResourceList discardedCards ) throws ServerException {
        DiscardCardsRequest request = new DiscardCardsRequest(CatanFacade.getMyPlayerIndex(), discardedCards);
        request.setType("discardCards");
        
        proxy.discardCards(request);
    }
}
