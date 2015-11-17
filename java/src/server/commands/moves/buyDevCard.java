/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.MaritimeTradeRequest;
import shared.communication.params.moves.MoveRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 */
public class buyDevCard extends Command{

    /**
     * Deserializes the json then calls buyDevCard in the model. 
     * @param json 
     */
    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            MoveRequest moveRequest = (MoveRequest)this.getDeserializer().toClass(MoveRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.buyDevCard(moveRequest);
            
            this.addHistoryMessage(gameID, "bought a development card", user);
            
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }    }
    
}
