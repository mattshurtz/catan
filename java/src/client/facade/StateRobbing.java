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
    public boolean canPlaceRobber(HexLocation hexLocation) {
        return model.canPlaceRobber(hexLocation);
    }
    
    @Override
    public void robPlayer(int victimIndex) throws ServerException{
        int currentPlayerIndex = CatanFacade.getModel().getTurnTracker().getCurrentTurn();
        RobPlayerRequest request = new RobPlayerRequest(currentPlayerIndex,victimIndex, model.getMap().getRobber());
        request.setType("robPlayer");
        request.setPlayerIndex(currentPlayerIndex);
        
        proxy.robPlayer(request);
    }
}
