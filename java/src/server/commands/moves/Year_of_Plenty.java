/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.PlayYearOfPlentyRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class Year_of_Plenty extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            PlayYearOfPlentyRequest request = (PlayYearOfPlentyRequest)this.getDeserializer().toClass(PlayYearOfPlentyRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.playYearOfPlenty(request);
            
            this.addHistoryMessage(gameID, "played Year of Plenty", user);
            Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, gameID, user,null);
            
            return this.getSerializer().toJson(currentModel); 
        }
        else {
            return null;
        }
    }
    
}
