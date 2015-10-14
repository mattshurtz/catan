/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.IServerProxy;
import shared.communication.params.moves.RobPlayerRequest;
import shared.exceptions.ServerException;
import shared.locations.HexLocation;
import shared.model.Model;

/**
 *
 * @author JanPaul
 */
public class StateRobbing extends StateBase {

    public StateRobbing(IServerProxy proxy, Model model) {
        super(proxy, model);
    }

    @Override
    public boolean canRobPlayer(int playerIndex) {
        return model.canRobPlayer(playerIndex);
    }
    
    @Override
    public void robPlayer(int victimIndex, HexLocation location) throws ServerException{
        int currentPlayerIndex = CatanFacade.getModel().getTurnTracker().getCurrentTurn();
        RobPlayerRequest request = new RobPlayerRequest(currentPlayerIndex,victimIndex, location);
        request.setType("robPlayer");
        request.setPlayerIndex(currentPlayerIndex);
        
        proxy.robPlayer(request);
    }
}