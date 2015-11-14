/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.games;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.CreateGameRequest;
import shared.communication.responses.CreateGameResponse;
import shared.exceptions.HTTPBadRequest;
import shared.exceptions.HTTPBadRequest;

/**
 *
 * @author Scott
 */
public class create extends Command{

    @Override
    public String execute(String json, String gameID, String user) throws HTTPBadRequest {
        CreateGameRequest request = (CreateGameRequest) this.getDeserializer().toClass(CreateGameRequest.class, json);
    
        int gid = GameInfoContainer.getInstance().createGame(request.getName(), request.isRandomNumbers(), request.isRandomPorts(), request.isRandomTiles());
        
        CreateGameResponse response = new CreateGameResponse( request.getName(), gid );
        
        return getSerializer().toJson(response);
    }
    
}
