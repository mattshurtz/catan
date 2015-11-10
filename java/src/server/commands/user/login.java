/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.user;

import server.commands.Command;
import shared.exceptions.HTTPBadRequest;
import shared.exceptions.*;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.Credentials;

/**
 *
 * @author Scott
 */
public class login extends Command{

    @Override
    public String execute(String json, String gameID) throws HTTPBadRequest {
        super.execute(json, gameID); //To change body of generated methods, choose Tools | Templates.
        
        Credentials creds = (Credentials) this.getDeserializer().toClass(Credentials.class,json);
        //Credentials creds = this.getDeserializer().toCredentials(json);
        
        
        GameInfoContainer container = GameInfoContainer.getInstance();
        
        int result = container.login(creds.getUsername(), creds.getPassword());
        
        if(result > -1) {
        	String cookie = "catan.user=%7B%22name%22%3A%22" + creds.getUsername() + "%22%2C%22password%22%3A%22" + creds.getPassword() + "%22%2C%22playerID%22%3A" + result + "%7D;Path=/;";
        	return cookie;
        } else {
        	throw new HTTPBadRequest("Login failed - bad password or username");
        }
    }
    
}
