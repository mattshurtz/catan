/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.games;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.JoinGameRequest;
import shared.exceptions.HTTPBadRequest;
import shared.exceptions.HTTPBadRequest;

/**
 *
 * @author Scott
 */
public class join extends Command{

    @Override
    public String execute(String json, String gameID) throws HTTPBadRequest {
    	
    	JoinGameRequest request = (JoinGameRequest) this.getDeserializer().toClass(JoinGameRequest.class, json);
    	
    	int playerId = 0;
    	
    	if (GameInfoContainer.getInstance().joinGame(playerId, request.getColor(), request.getGameID())) {
    		return "catan.game=" + gameID + "%7D;Path=/;";
    	} else {
    		throw new HTTPBadRequest("Could not add player to game");
    	}
    	
    	
    }
    
}
