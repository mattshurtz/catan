/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.games;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.CreateGameRequest;
import shared.communication.responses.CreateGameResponse;
import shared.exceptions.HTTPBadRequest;

/**
 *
 * @author Scott
 */
public class create extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        CreateGameRequest request = (CreateGameRequest) this.getDeserializer().toClass(CreateGameRequest.class, json);
    
        if(request.getName() == null || request.getName().equals("")) {
        	throw new HTTPBadRequest("The game must have a name");
        }
        
        int gid = GameInfoContainer.getInstance().createGame(request.getName(), request.isRandomNumbers(), request.isRandomPorts(), request.isRandomTiles());
        
        CreateGameResponse response = new CreateGameResponse( request.getName(), gid );
        
        Persistence.getInstance().addGame(gid);
        
        return getSerializer().toJson(response);
    }
    
}
