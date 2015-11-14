/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.OfferTradeRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 */
public class offerTrade extends Command{

    /**
     * Calls can Offer Trade in the model then offers the trade by changing the setTradeOffer
     * in the model. 
     * @param json 
     */
    @Override
    public String execute(String json, String gameID, String user) throws HTTPBadRequest {
        if(isUserInGame(Integer.getInteger(gameID),Integer.getInteger(user))){
            OfferTradeRequest offerTradeRequest = (OfferTradeRequest)this.getDeserializer().toClass(OfferTradeRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(Integer.getInteger(gameID));
            currentModel.offerTrade(offerTradeRequest);
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
