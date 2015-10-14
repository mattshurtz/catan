/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.facade;

import client.proxy.IServerProxy;
import java.util.Random;
import shared.communication.params.moves.RollNumberRequest;
import shared.exceptions.ServerException;
import shared.model.Model;

/**
 *
 * @author JanPaul
 */
public class StateRolling extends StateBase {

    public StateRolling(IServerProxy proxy, Model model) {
        super(proxy, model);
    }
    
    @Override
    public int rollNumber() throws ServerException{
        Random rand = new Random();
        
        int firstDice = rand.nextInt(6) + 1;
        int secondDice = rand.nextInt(6) + 1;
        
        int total = firstDice + secondDice;
        
        RollNumberRequest request = new RollNumberRequest(total);
        request.setType("rollNumber");
        request.setPlayerIndex(CatanFacade.getModel().getTurnTracker().getCurrentTurn());
        
        proxy.rollNumber(request);
        return total;
    }
    
}
