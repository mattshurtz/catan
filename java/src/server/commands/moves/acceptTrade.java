/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.AcceptTradeRequest;
import shared.communication.params.moves.OfferTradeRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class acceptTrade extends Command{

    /**
     * Calls can acceptTrade in the model. 
     * @param json 
     * @throws HTTPBadRequest 
     */
    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            AcceptTradeRequest acceptTradeRequest = (AcceptTradeRequest)this.getDeserializer().toClass(AcceptTradeRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.acceptTrade(acceptTradeRequest);
            
            String msg;
            if ( acceptTradeRequest.isWillAccept() ) {
                msg = "accepted the trade";
            } else {
                msg = "didn't accept the trade";
            }
            this.addHistoryMessage(gameID, msg, user);
            Persistence.getInstance().saveCommand("acceptTrade", json, gameID, user);
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
