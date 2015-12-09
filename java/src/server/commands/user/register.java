/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.user;

import server.commands.Command;
import server.data.PlayerInfoCookie;
import shared.exceptions.HTTPBadRequest;
import server.gameinfocontainer.GameInfoContainer;
import server.persistence.Persistence;
import shared.communication.params.Credentials;
import shared.json.Serializer;

/**
 *
 * @author Scott
 */
public class register extends Command{
    
    Serializer serializer = new Serializer();

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        
        Credentials creds = (Credentials) this.getDeserializer().toClass(Credentials.class,json);
        GameInfoContainer container = GameInfoContainer.getInstance();
        int result = container.register(creds.getUsername(), creds.getPassword());
        
        if(result > -1) {
        	Persistence.getInstance().addUser(result, creds.getUsername(), creds.getPassword());
        	return buildUserCookie( creds, result );
        } else {
        	throw new HTTPBadRequest("Could not add user");
        }
    }
    
}
