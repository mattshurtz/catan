/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.user;

import server.commands.Command;
import shared.exceptions.HTTPBadRequest;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.Credentials;

/**
 *
 * @author Scott
 */
public class login extends Command{

    @Override
    public String execute(String json, int gameID, int user) throws HTTPBadRequest {
        
        Credentials creds = (Credentials) this.getDeserializer().toClass(Credentials.class,json);
        GameInfoContainer container = GameInfoContainer.getInstance();
        int result = container.login(creds.getUsername(), creds.getPassword());
        
        if(result > -1) {
        	String cookie = this.buildUserCookie(creds, result);
        	return cookie;
        } else {
        	throw new HTTPBadRequest("Login failed - bad password or username");
        }
    }
    
}
