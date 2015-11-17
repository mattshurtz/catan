/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.PlayMonopolyRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class Monopoly extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)) {
            PlayMonopolyRequest request = (PlayMonopolyRequest)this.getDeserializer()
                                                .toClass(PlayMonopolyRequest.class, json);
            
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.playMonopoly(request);
            
            this.addHistoryMessage(gameID, "played a Monopoly", user);
            
            return this.getSerializer().toJson(currentModel);
        }
        else {
            return null;
        }
    }
    
}
