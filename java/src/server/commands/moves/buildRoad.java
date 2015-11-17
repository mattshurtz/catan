/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.exceptions.HTTPBadRequest;
import shared.communication.params.moves.BuildRoadRequest;
import shared.model.Model;

/**
 *
 */
public class buildRoad extends Command {
    
    /**
     * This needs to send a model response to the client?
     * @param json This is the Json request received from the client to build a road
     */
    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID,user)){
            BuildRoadRequest roadRequest = (BuildRoadRequest)this.getDeserializer().toClass(BuildRoadRequest.class, json);
            Model currentModel =GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.buildRoad(roadRequest);
            
            this.addHistoryMessage(gameID, "built a road", user);
            
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
        
    }
    
}
