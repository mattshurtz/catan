/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands;

import server.gameinfocontainer.GameInfoContainer;
import shared.communication.params.Credentials;
import shared.communication.responses.GameResponse;
import shared.communication.responses.PlayerResponse;
import shared.exceptions.HTTPBadRequest;
import shared.json.Deserializer;
import shared.json.Serializer;
import shared.model.Model;

/**
 *
 */
public class Command {
    
    private Deserializer deserializer;
    private Serializer serializer;
    
    /**
     * Calls the function in the model to perform the given task in the 
     * classes that extend the Command class. Here they call the corresponding method
     * in the model to first check if the requested command is valid, and then perform
     * the command is it is valid. 
     * @param json 
     * @throws HTTPBadRequest 
     */
    public String execute(String json, String gameID, String user) throws HTTPBadRequest {
		return null;        
    }
    
    public Command()
    {
        deserializer = new Deserializer();
        serializer = new Serializer();
    }
    
    public boolean isUserInGame(int gameID, int userID){
        GameResponse game = GameInfoContainer.getInstance().getListOfGames().get(gameID);
        for(PlayerResponse player: game.getPlayers()){
            if(player.getId()== userID){
                return true;
            }
        }
        return false;
    }
    
    public Deserializer getDeserializer() {
        if(deserializer==null) {
        	deserializer = new Deserializer();
        }    	
    	return deserializer;
    }

    public void setDeserializer(Deserializer deserializer) {
        this.deserializer = deserializer;
    }

    public Serializer getSerializer() {
        if(serializer == null) {
            
        }
        return serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }
    
    public String buildUserCookie(Credentials creds, int result) {
    	return "catan.user=%7B%22name%22%3A%22" + creds.getUsername() + "%22%2C%22password%22%3A%22" + creds.getPassword() + "%22%2C%22playerID%22%3A" + result + "%7D;Path=/;";
    }
    public String buildGameCookie(int gameID) {
    	return "catan.game=" + gameID + "%7D;Path=/;";
    }
    
    
}
