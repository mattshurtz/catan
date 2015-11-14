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
import shared.communication.params.Credentials;
import shared.json.Serializer;

/**
 *
 * @author Scott
 */
public class register extends Command{
    
    Serializer serializer = new Serializer();

    @Override
    public String execute(String json, String gameID, String user) throws HTTPBadRequest {
    	super.execute(json, gameID, user); //To change body of generated methods, choose Tools | Templates.
        
        Credentials creds = (Credentials) this.getDeserializer().toClass(Credentials.class,json);
        //Credentials creds = this.getDeserializer().toCredentials(json);
        
        
        GameInfoContainer container = GameInfoContainer.getInstance();
        
        int result = container.register(creds.getUsername(), creds.getPassword());
        
        if(result > -1) {
        	return buildUserCookie( creds, result );
        } else {
        	throw new HTTPBadRequest("Could not add user");
        }
    }
    
}
