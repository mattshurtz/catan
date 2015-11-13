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
 */
public class offerTrade extends Command{

    /**
     * Calls can Offer Trade in the model then offers the trade by changing the setTradeOffer
     * in the model. 
     * @param json 
     */
    @Override
    public String execute(String json, String gameID, String user) throws HTTPBadRequest {
        return super.execute(json, gameID, user);
    }
    
}
