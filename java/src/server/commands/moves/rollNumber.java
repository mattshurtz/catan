/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.RollNumberRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class rollNumber extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            RollNumberRequest rollNumberRequest = (RollNumberRequest)this.getDeserializer().toClass(RollNumberRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.rollNumber(rollNumberRequest);
            
            int theNum = rollNumberRequest.getNumber();
            this.addHistoryMessage(gameID, "rolled " + getArticle( theNum ) + " " + theNum, user);
            
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
    private static String getArticle( int number ) {
        switch ( number ) {
            case 8:
            case 11:
                return "an";
            
            default:
                return "a";
        }
    }
    
}
