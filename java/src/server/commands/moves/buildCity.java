/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.BuildCityRequest;
import shared.communication.params.moves.DiscardCardsRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 */
public class buildCity extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID,user)){
            BuildCityRequest buildCityRequest = (BuildCityRequest)this.getDeserializer().toClass(BuildCityRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            boolean success = currentModel.buildCity( buildCityRequest );
            
            if(success){
                currentModel.incrementVersion();
                this.addHistoryMessage(gameID, "built a city", user);
                Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, gameID, user,null);
            }
            
            return this.getSerializer().toJson(currentModel);
        } else {
            return null;
        }
    }
    
}
