/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.moves;

import server.commands.Command;
import shared.exceptions.HTTPBadRequest;

/**
 *
 * @author Scott
 */
public class sendChat extends Command{

    @Override
    public String execute(String json, String gameID, String user) throws HTTPBadRequest {
        return super.execute(json, gameID, user);
    }
    
}
