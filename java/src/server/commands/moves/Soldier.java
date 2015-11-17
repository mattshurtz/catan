/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.MoveRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class Soldier extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            MoveRequest request = (MoveRequest)this.getDeserializer().toClass(MoveRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.playSoldier(request);
            
            this.addHistoryMessage(gameID, "has got soul but (s)he played a Soldier", user);
            
            return this.getSerializer().toJson(currentModel); 
        }
        else {
            return null;
        }
    }
    
}
