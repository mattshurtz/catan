/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import java.util.logging.Level;
import java.util.logging.Logger;
import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.OfferTradeRequest;
import shared.exceptions.GetPlayerException;
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
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID,user)){
            OfferTradeRequest offerTradeRequest = (OfferTradeRequest)this.getDeserializer().toClass(OfferTradeRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.offerTrade(offerTradeRequest);
            
            String recipientName = null;
            try {
                recipientName = currentModel.getPlayer( offerTradeRequest.getReceiver() ).getName();
            } catch (GetPlayerException ex) {
                Logger.getLogger(offerTrade.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.addHistoryMessage(gameID, "offered trade" + (( recipientName == null ) ? "" : " to " + recipientName), user);
            Persistence.getInstance().saveCommand("offerTrade", json, gameID, user);
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
