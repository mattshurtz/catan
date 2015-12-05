/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.MaritimeTradeRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class maritimeTrade extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {

        if(isUserInGame(gameID, user)){
            MaritimeTradeRequest maritimeTradeRequest = (MaritimeTradeRequest)this.getDeserializer().toClass(MaritimeTradeRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            boolean success = currentModel.acceptMaritimeTrade(maritimeTradeRequest);
            if(success){
                this.addHistoryMessage(gameID, "did a maritime trade", user);
                currentModel.incrementVersion();
                Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, gameID, user,null);
            }
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
