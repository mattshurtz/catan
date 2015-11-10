/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands.user;

import server.commands.Command;
import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.Credentials;

/**
 *
 * @author Scott
 */
public class login extends Command{

    @Override
    public String execute(String json, String gameID) {
        super.execute(json, gameID); //To change body of generated methods, choose Tools | Templates.
        
        Credentials creds = (Credentials) this.getDeserializer().toClass(Credentials.class,json);
        //Credentials creds = this.getDeserializer().toCredentials(json);
        
        
        GameInfoContainer container = GameInfoContainer.getInstance();
        
        boolean result = container.login(creds.getUsername(), creds.getPassword());
        
        if(result) {
        	return "LOGIN";
        } else {
        	return "FAILED LOGIN";
        }
    }
    
}
