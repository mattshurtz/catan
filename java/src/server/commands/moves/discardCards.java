/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.DiscardCardsRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class discardCards extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            DiscardCardsRequest discardCardsRequest = (DiscardCardsRequest)this.getDeserializer().toClass(DiscardCardsRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            
            boolean success = currentModel.discardCards(discardCardsRequest);
            if(success){
                currentModel.incrementVersion();
                Persistence.getInstance().saveCommand("discardCards", json, gameID, user);
            }
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
