/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.BuildSettlementRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 */
public class buildSettlement extends Command{

    /**
     * Deserializes the request and Calls buildSettlement on the model. 
     * @param json 
     */
    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID,user)){
            BuildSettlementRequest buildSettlementRequest = (BuildSettlementRequest)this.getDeserializer().toClass(BuildSettlementRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            boolean success = currentModel.buildSettlement(buildSettlementRequest);
            
            if(success) {
                this.addHistoryMessage(gameID, "built a settlement", user);
                currentModel.incrementVersion();
                Persistence.getInstance().saveCommand("buildSettlement", json, gameID, user);
            }
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
