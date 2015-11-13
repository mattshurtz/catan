/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.gameinfocontainer;
import shared.communication.responses.GameResponse;
import shared.communication.responses.PlayerResponse;
import shared.exceptions.GetPlayerException;
import shared.model.Model;
import shared.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Scott
 */
public class ModelBank {
    private ArrayList<Model> games;
    
    /**
     * Constructor for ModelBank that holds all the games in the server
     */
    public ModelBank() {
    	games = new ArrayList<Model>();
    }
    
    /**
     * Sets the games. Mainly used to replace all games if needed.
     * @param games is an ArrayList of Model
     */
    public void setGames(ArrayList<Model> games) {
    	
    }
    
    /**
     * Add a game to the games array 
     * @param game is a share.model.Model
     * @return the game's id
     */
    public int addGame(Model game) {
    	return -1;
    }
    
    /**
     * Removes a game from the games array
     * @param gameId is the id of the game to be removed
     */
    public void removeGame(int gameId) {
    	
    }
    
    /**
     * Get the game model
     * @param gameId of the game to retrieve
     * @return the game Model
     */
    public Model getGame(int gameId) {
    	return null;
    }

	public List<GameResponse> toGameResponseList() {
		List<GameResponse> g = new ArrayList<GameResponse>();
		
		for(int i=0;i<games.size();i++) {
			Model current = games.get(i);
			ArrayList<PlayerResponse> p = new ArrayList<PlayerResponse>();
			for(int j=0;j<current.getPlayers().size();j++) {
				Player player;
				try {
					player = current.getPlayer(j);
					p.add(new PlayerResponse(player.getColor().toString(),player.getName(),player.getPlayerID()));
				} catch (GetPlayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
			g.add(new GameResponse("TITLE",i,p));
		}
		
		return g;
	}
}
