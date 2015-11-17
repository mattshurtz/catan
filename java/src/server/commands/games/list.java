/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.games;

import java.util.List;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.responses.GameResponse;
import shared.exceptions.HTTPBadRequest;
import shared.exceptions.HTTPBadRequest;

/**
 *
 * @author Scott
 */
public class list extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        List<GameResponse> list = GameInfoContainer.getInstance().getListOfGames();
        
        return this.getSerializer().toJson(list);
    }
    
}
