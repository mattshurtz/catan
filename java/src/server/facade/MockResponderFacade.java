/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import shared.communication.responses.CreateGameResponse;
import shared.communication.responses.EmptyPlayerResponse;
import shared.communication.responses.GameResponse;
import shared.exceptions.HTTPBadRequest;
import shared.exceptions.ServerException;
import shared.json.Deserializer;
import shared.json.Serializer;
import shared.model.Model;
/**
 *
 */
public class MockResponderFacade implements IServerFacade {

     /**
     * Create Hard-coded Responses to be able to test our functions. 
     * This command function will perform the logic for the operation. 
     * @throws HTTPBadRequest 
     */
    @Override
    public String doFunction(String command, String content, int gameId, int user) throws HTTPBadRequest {
    	String returnValue = null;
    	
    	switch (command) {
    	case "games.create":            
            CreateGameResponse response = new CreateGameResponse();
            response.setTitle( "TITLE" );
            response.setId(0);
            List<EmptyPlayerResponse> epr = new ArrayList<>();
            for (int i = 0; i < 4; i++ )
                epr.add( new EmptyPlayerResponse() );
            response.setPlayers(epr);
            returnValue = new Serializer().toJson(response);
    		break;
    	case "games.join":
    		returnValue = "catan.game=" + 0 + "%7D;Path=/;";
    		break;
    	case "games.list":
    		List<GameResponse> list = new ArrayList<>();
            list.add( GameResponse.getDefaultSampleGameResponse() );
            returnValue = new Serializer().toJson(list);
    		break;
    	case "game.model":
    	case "moves.acceptTrade":
    	case "moves.buildCity":
    	case "moves.buildRoad":
    	case "moves.buildSettlement":
    	case "moves.buyDevCard":
    	case "moves.discardCards":
    	case "moves.finishTurn":
    	case "moves.maritimeTrade":
    	case "moves.Monopoly":
    	case "moves.Monument":
    	case "moves.offerTrade":
    	case "moves.Road_Building":
    	case "moves.robPlayer":
    	case "moves.rollNumber":
    	case "moves.sendChat":
    	case "moves.Soldier":
    	case "moves.Year_of_Plenty":
    		Model model = null;    	
        	try {
                model = new Deserializer().getTestModel();
            } catch ( IOException e ) {
            	try {
    				throw new ServerException("Invalid Input");
    			} catch (ServerException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
            }
        	returnValue = new Serializer().toJson(model);
    		break;
    	case "user.login":
    	case "user.register":
    		returnValue = "catan.user=%7B%22name%22%3A%22" + "Matt" + "%22%2C%22password%22%3A%22" + "123456" + "%22%2C%22playerID%22%3A" + "0" + "%7D;Path=/;";
    		break;
    	default:
    		throw new HTTPBadRequest("Command not recognized");
    	}
    	//System.out.println("Mock Facade Returned - " + returnValue);
    	return returnValue;
    }    
}
