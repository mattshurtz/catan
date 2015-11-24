/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.game;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.exceptions.HTTPBadRequest;
import shared.model.Model;

/**
 *
 * @author Scott
 */
public class model extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        Model mod = GameInfoContainer.getInstance().getGameModel( gameID );
        
        
        if(mod!=null)
        	return getSerializer().toJson(mod);
        return null;
    }
    
}
