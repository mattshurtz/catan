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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Scott
 */
public class ModelBank {
    
    private Map<Integer, Model> games;
    private int nextGameId = 0;
    
    /**
     * Constructor for ModelBank that holds all the games in the server
     */
    public ModelBank() {
    	games = new HashMap<>();
    }
    
    /**
     * Add a game to the games list 
     * @param game is a share.model.Model
     * @return the game's id
     */
    public int addGame(Model game) {
        this.games.put( nextGameId, game );
        int ret = nextGameId;
        nextGameId++;
        return ret;
    }
    
    /**
     * Removes a game from the games list
     * @param gameId is the id of the game to be removed
     */
    public void removeGame(int gameId) {
    	this.games.remove( gameId );
    }
    
    /**
     * Get the game model
     * @param gameId of the game to retrieve
     * @return the game Model
     */
    public Model getGame(int gameId) {
    	return this.games.get( gameId );
    }

	public List<GameResponse> toGameResponseList() {
		List<GameResponse> g = new ArrayList<GameResponse>();
		
		for( Integer i : games.keySet() ) {
			Model current = games.get(i);
			ArrayList<PlayerResponse> p = new ArrayList<PlayerResponse>();
			for(int j=0;j<current.getPlayers().size();j++) {
				Player player;
				try {
					player = current.getPlayer(j);
					p.add(new PlayerResponse(player.getColor().toString(),player.getName(),player.getPlayerID()));
				} catch (GetPlayerException e) {
					e.printStackTrace();
				}				
			}
			g.add(new GameResponse(current.getName(),i,p));
		}
		
		return g;
	}
}
