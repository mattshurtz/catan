/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.MoveRequest;
import shared.definitions.DevCardType;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 */
public class buyDevCard extends Command{

    /**
     * Deserializes the json then calls buyDevCard in the model. 
     * @param json 
     */
    @Override
    public String execute(String json, int gameID, int user, String random) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            MoveRequest moveRequest = (MoveRequest)this.getDeserializer().toClass(MoveRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            DevCardType card;
            if(random == null){
                card = currentModel.buyDevCard(moveRequest,null);
            }else{
                card = currentModel.buyDevCard(moveRequest,DevCardType.valueOf(random));
            }
            this.addHistoryMessage(gameID, "bought a development card", user);
            
            if (card==null) {
            	 Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, gameID, user,null);
                 
            } else {
            	 Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, gameID, user,card.toString());
                 
            }
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }    
    }
    
    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
    	return this.execute(json, gameID, user, null);
    }
    
}
