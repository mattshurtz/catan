/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
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
    public String execute(String json, String gameID, String user) throws HTTPBadRequest {
            if(isUserInGame(Integer.parseInt(gameID),Integer.parseInt(user))){
            BuildSettlementRequest buildSettlementRequest = (BuildSettlementRequest)this.getDeserializer().toClass(BuildSettlementRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(Integer.getInteger(gameID));
            currentModel.buildSettlement(buildSettlementRequest);
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
