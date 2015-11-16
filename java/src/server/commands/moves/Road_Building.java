/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.PlayRoadBuildingRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class Road_Building extends Command{

    @Override
    public String execute(String json, String gameID, String user) throws HTTPBadRequest {
        if(isUserInGame(Integer.parseInt(gameID), Integer.parseInt(user))) {
            PlayRoadBuildingRequest request = (PlayRoadBuildingRequest) this.getDeserializer()
                                                    .toClass(PlayRoadBuildingRequest.class, json);
           
            Model currentModel = GameInfoContainer.getInstance().getGameModel(Integer.parseInt(gameID));
            currentModel.playRoadBuilding(request);
            return this.getSerializer().toJson(currentModel);    
        }
        else {
            return null;
        }
    }
}
