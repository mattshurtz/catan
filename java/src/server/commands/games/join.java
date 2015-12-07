/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.games;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.JoinGameRequest;
import shared.exceptions.HTTPBadRequest;

/**
 *
 * @author Scott
 */
public class join extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
    	
    	JoinGameRequest request = (JoinGameRequest) this.getDeserializer().toClass(JoinGameRequest.class, json);
    	
    	if (GameInfoContainer.getInstance().joinGame(user, request.getColor(), request.getGameID())) {
    		GameInfoContainer.getInstance().getGameModel(request.getGameID()).incrementVersion();
    		Persistence.getInstance().saveCommand(this.getClassName(this.getClass()), json, request.getGameID(), user,null);
    		return this.buildGameCookie(request.getGameID());
    	} else {
    		throw new HTTPBadRequest("Could not add player to game");
    	}
    	
    	
    }
    
}
