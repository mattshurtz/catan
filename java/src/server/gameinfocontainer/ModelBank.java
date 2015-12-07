/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.gameinfocontainer;
import java.io.Serializable;
import shared.communication.params.moves.BuildRoadRequest;
import shared.communication.params.moves.BuildSettlementRequest;
import shared.communication.responses.GameResponse;
import shared.communication.responses.PlayerResponse;
import shared.definitions.CatanColor;
import shared.definitions.TurnStatus;
import shared.exceptions.GetPlayerException;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.model.Model;
import shared.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 *
 * @author Scott
 */
public class ModelBank implements Serializable {
    
    // integer: game id. string: game name. model: game state
    private Map<Integer, Pair<String, Model>> games;
    
    public Map<Integer, Model> getGames() {
        // go through and make sure each model is assigned the right name
        Map<Integer, Model> modelMap = new HashMap<>();
        for ( Integer gameId : games.keySet() ) {
            Pair<String, Model> p = games.get( gameId );
            Model m = p.getValue();
            m.setName( p.getKey() );
            modelMap.put( gameId, m );
        }
		return modelMap;
	}

	private int nextGameId = 0;
    
    
    /**
     * Constructor for ModelBank that holds all the games in the server
     */
    public ModelBank( boolean addDefaultGame ) {
    	games = new HashMap<>();
        if ( addDefaultGame )
            addDefaultModel();
    }
    
    public void addGame( int id, Model theModel ) {
        games.put(id, new Pair<>(theModel.getName(), theModel));
        if ( nextGameId <= id )
            nextGameId = ++id;
    }
    
    public int addDefaultModel() {
    	Model defaultModel = new Model("Default Setup", false, false, false);
      	defaultModel.addPlayer(CatanColor.BLUE.toString(), 0, "Matt");
    	defaultModel.addPlayer(CatanColor.GREEN.toString(), 1, "Scott");
    	defaultModel.addPlayer(CatanColor.ORANGE.toString(), 2, "Jan");
    	defaultModel.addPlayer(CatanColor.RED.toString(), 3, "Garrett");
    	addGame(defaultModel);
    	
    	Model readyModel = new Model("Default Post Setup", false, false, false);
    	readyModel.addPlayer(CatanColor.BLUE.toString(), 0, "Matt");
    	readyModel.addPlayer(CatanColor.GREEN.toString(), 1, "Scott");
    	readyModel.addPlayer(CatanColor.ORANGE.toString(), 2, "Jan");
    	readyModel.addPlayer(CatanColor.RED.toString(), 3, "Garrett");
    	
    	
    	//PLAYER 1
    	
    	BuildSettlementRequest s1 = new BuildSettlementRequest(new VertexLocation(new HexLocation(-2,0), VertexDirection.West), true);
    	s1.setPlayerIndex(0);
    	readyModel.buildSettlement(s1);
    	
    	BuildSettlementRequest s2 = new BuildSettlementRequest(new VertexLocation(new HexLocation(0,2), VertexDirection.West), true);
    	s2.setPlayerIndex(0);
    	readyModel.buildSettlement(s2);
    	
    	BuildRoadRequest r1 = new BuildRoadRequest(new EdgeLocation(new HexLocation(-2,0), EdgeDirection.SouthWest), true);
    	r1.setPlayerIndex(0);
    	readyModel.buildRoad(r1);

    	BuildRoadRequest r2 = new BuildRoadRequest(new EdgeLocation(new HexLocation(0,2), EdgeDirection.SouthWest), true);
    	r2.setPlayerIndex(0);
    	readyModel.buildRoad(r2);
    	
    	
    	//PLAYER 2
    	readyModel.getTurnTracker().setCurrentTurn(1);
    	
    	BuildSettlementRequest s3 = new BuildSettlementRequest(new VertexLocation(new HexLocation(0,-1), VertexDirection.West), true);
    	s3.setPlayerIndex(1);
    	readyModel.buildSettlement(s3);
    	
    	BuildSettlementRequest s4 = new BuildSettlementRequest(new VertexLocation(new HexLocation(0,1), VertexDirection.West), true);
    	s4.setPlayerIndex(1);
    	readyModel.buildSettlement(s4);
    	
    	BuildRoadRequest r3 = new BuildRoadRequest(new EdgeLocation(new HexLocation(0,-1), EdgeDirection.SouthWest), true);
    	r3.setPlayerIndex(1);
    	readyModel.buildRoad(r3);

    	BuildRoadRequest r4 = new BuildRoadRequest(new EdgeLocation(new HexLocation(0,1), EdgeDirection.SouthWest), true);
    	r4.setPlayerIndex(1);
    	readyModel.buildRoad(r4);
    	
    	
    	//PLAYER 3
    	readyModel.getTurnTracker().setCurrentTurn(2);
    	
    	BuildSettlementRequest s5 = new BuildSettlementRequest(new VertexLocation(new HexLocation(2,-2), VertexDirection.West), true);
    	s5.setPlayerIndex(2);
    	readyModel.buildSettlement(s5);
    	
    	BuildSettlementRequest s6 = new BuildSettlementRequest(new VertexLocation(new HexLocation(-2,2), VertexDirection.West), true);
    	s6.setPlayerIndex(2);
    	readyModel.buildSettlement(s6);
    	
    	BuildRoadRequest r5 = new BuildRoadRequest(new EdgeLocation(new HexLocation(2,-2), EdgeDirection.SouthWest), true);
    	r5.setPlayerIndex(2);
    	readyModel.buildRoad(r5);

    	BuildRoadRequest r6 = new BuildRoadRequest(new EdgeLocation(new HexLocation(-2,2), EdgeDirection.SouthWest), true);
    	r6.setPlayerIndex(2);
    	readyModel.buildRoad(r6);
    	
    	
    	//PLAYER 4
    	readyModel.getTurnTracker().setCurrentTurn(3);
    	
    	BuildSettlementRequest s7 = new BuildSettlementRequest(new VertexLocation(new HexLocation(0,0), VertexDirection.West), true);
    	s7.setPlayerIndex(3);
    	readyModel.buildSettlement(s7);
    	
    	BuildSettlementRequest s8 = new BuildSettlementRequest(new VertexLocation(new HexLocation(1,-1), VertexDirection.West), true);
    	s8.setPlayerIndex(3);
    	readyModel.buildSettlement(s8);
    	
    	BuildRoadRequest r7 = new BuildRoadRequest(new EdgeLocation(new HexLocation(0,0), EdgeDirection.SouthWest), true);
    	r7.setPlayerIndex(3);
    	readyModel.buildRoad(r7);

    	BuildRoadRequest r8 = new BuildRoadRequest(new EdgeLocation(new HexLocation(1,-1), EdgeDirection.SouthWest), true);
    	r8.setPlayerIndex(3);
    	readyModel.buildRoad(r8);
        
//For TA passoff use ONLY
//        try {
//            readyModel.getPlayer(0).getOldDevCards().AddRoadBuilding();
//            readyModel.getDeck().removeRoadBuilding();
//            readyModel.getBank().subtractResource(ResourceType.BRICK, 8);
//            readyModel.getBank().subtractResource(ResourceType.WOOD, 8);
//            readyModel.getPlayer(0).getResources().addResource(ResourceType.WOOD, 4);
//            readyModel.getPlayer(0).getResources().addResource(ResourceType.BRICK, 4);
//            readyModel.getPlayer(1).getResources().addResource(ResourceType.WOOD, 4);
//            readyModel.getPlayer(1).getResources().addResource(ResourceType.BRICK, 4);            
//        } catch (GetPlayerException ex) {
//            Logger.getLogger(ModelBank.class.getName()).log(Level.SEVERE, null, ex);
//        }
    	
    	//Give out some cards
    	for (int i=2;i<13;i++) {
    		if (i==7)
    			continue;
    		readyModel.distributeResources(i);
    	}
        
    	//SETUP GAME
    	readyModel.getTurnTracker().setCurrentTurn(0);
    	readyModel.getTurnTracker().setStatus(TurnStatus.ROLLING);
    	
    	return addGame(readyModel);
    }
    
    /**
     * Add a game to the games list 
     * @param game is a share.model.Model
     * @return the game's id
     */
    public int addGame(Model game) {
        this.games.put( nextGameId, new Pair<>( game.getName(), game ) );
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
    	try {
    		Pair<String, Model> p = this.games.get( gameId );
            Model m = p.getValue();
            m.setName( p.getKey() );
            return m;
    	} catch (Exception e) {
    		return null;
    	}
    	
    }

	public List<GameResponse> toGameResponseList() {
		List<GameResponse> g = new ArrayList<GameResponse>();
		
		for( Integer i : games.keySet() ) {
			Model current = games.get(i).getValue();
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

    public void setGame(int id, Model game) {
        this.games.put(id, new Pair<>( game.getName(), game ) );
    }

}
