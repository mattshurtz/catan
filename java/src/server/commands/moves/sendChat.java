/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.moves.SendChatRequest;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class sendChat extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID,user)){
            SendChatRequest sendChatRequest = (SendChatRequest)this.getDeserializer().toClass(SendChatRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            boolean success = currentModel.sendChat(sendChatRequest);
            
            if (success) {
            	currentModel.incrementVersion();
            }
            
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
