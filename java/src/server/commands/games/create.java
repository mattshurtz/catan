/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.games;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.CreateGameRequest;
import shared.exceptions.HTTPBadRequest;
import shared.exceptions.HTTPBadRequest;

/**
 *
 * @author Scott
 */
public class create extends Command{

    @Override
    public String execute(String json, String gameID) throws HTTPBadRequest {
        super.execute(json, gameID);
        
        CreateGameRequest request = (CreateGameRequest) this.getDeserializer().toClass(CreateGameRequest.class, json);
    
        int gid = GameInfoContainer.getInstance().createGame(request.getName(), request.isRandomNumbers(), request.isRandomPorts(), request.isRandomTiles());
        
        //Model game = GameInfoContainer.getInstance().getGame(gid);
        
        return null;
    }
    
}
