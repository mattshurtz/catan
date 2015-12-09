/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import java.util.logging.Level;
import java.util.logging.Logger;
import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.moves.RobPlayerRequest;
import shared.definitions.ResourceType;
import shared.exceptions.GetPlayerException;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class robPlayer extends Command{

    @Override
    public String execute(String json, int gameID, int user, String random) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            RobPlayerRequest robPlayerRequest = (RobPlayerRequest)this.getDeserializer().toClass(RobPlayerRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            ResourceType success;
			try {
				//returns resource if got one
				//returns null if did not get one
				//throws exception if unable to rob player
                if(random==null){
                    success = currentModel.robPlayer(robPlayerRequest,null);
                }else{
                    success = currentModel.robPlayer(robPlayerRequest,ResourceType.valueOf(random));
                }
				
                currentModel.incrementVersion();
                
                if(success == null){
                    Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, gameID, user,null);
                }else{
                    Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, gameID, user,success.toString());
                }
                String recipientName = null;
		        
                if(robPlayerRequest.getVictimIndex() >=0 && robPlayerRequest.getVictimIndex() < currentModel.getPlayers().size()
		            			&& currentModel.getPlayers().get(robPlayerRequest.getVictimIndex()).getResources().getTotalResources() > 0) {
	            	try {
		                recipientName = currentModel.getPlayer( robPlayerRequest.getVictimIndex() ).getName();
		            } catch (GetPlayerException ex) {
		                Logger.getLogger(offerTrade.class.getName()).log(Level.SEVERE, null, ex);
		            }
		            this.addHistoryMessage(gameID, "robbed" + (( recipientName == null ) ? "" : " " + recipientName), user);
	            }
                
			} catch (Exception e) {
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
