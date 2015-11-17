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
import shared.communication.params.moves.RobPlayerRequest;
import shared.exceptions.GetPlayerException;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class robPlayer extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        if(isUserInGame(gameID, user)){
            RobPlayerRequest robPlayerRequest = (RobPlayerRequest)this.getDeserializer().toClass(RobPlayerRequest.class, json);
            Model currentModel = GameInfoContainer.getInstance().getGameModel(gameID);
            currentModel.robPlayer(robPlayerRequest);
            
            String recipientName = null;
            try {
                recipientName = currentModel.getPlayer( robPlayerRequest.getVictimIndex() ).getName();
            } catch (GetPlayerException ex) {
                Logger.getLogger(offerTrade.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.addHistoryMessage(gameID, "robbed" + (( recipientName == null ) ? "" : " " + recipientName), user);
            
            return this.getSerializer().toJson(currentModel);
        }else{
            return null;
        }
    }
    
}
