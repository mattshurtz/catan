/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import java.util.HashMap;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
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
            
            HashMap<String,String> list = (HashMap<String,String>) this.getDeserializer().toClass(HashMap.class,json);
            
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.playMonopoly(request);
            
            this.addHistoryMessage(gameID, "played a Monopoly", user);
            Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, gameID, user,null);
            
            return this.getSerializer().toJson(currentModel);
        }
        else {
            return null;
        }
    }
    
}
