package server.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import server.gameinfocontainer.UserInfoBank;
import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;
import server.persistence.factory.AbstractFactory;
import shared.model.Model;

public class Persistence {

	private static Persistence instance;
	
	public static Persistence getInstance() {
		if (instance == null) {
			instance = new Persistence();
		}
		return instance;
	}
	
	private IGamesDAO gameDAO;
	private IUsersDAO userDAO;
	private PluginRegistry registry;
	
	private int delta;
	private String plugin;
	
	
	private HashMap<Integer,Integer> deltaCount;
	
	
	public Persistence() {
		deltaCount = new HashMap<Integer,Integer>();
		this.delta = 0;
		this.plugin = null;	
		registry = new PluginRegistry();
	}
	
	public void set(String plugin, int delta) {
		this.delta = delta;
		this.plugin = plugin;
		AbstractFactory factory = this.registry.CreateFactory(plugin);
		gameDAO = factory.getGamesDAO();
		userDAO = factory.getUsersDAO();
	}
	
	public boolean loadData() {
		if(loadUsers() && loadGames()) {
			return true;
		}
		return false;
	}
	
	public boolean saveCommand(String command, String json, int gameId, int playerId, String randomResult) {
		try {
			int version = GameInfoContainer.getInstance().getGameModel(gameId).getVersion();
			gameDAO.getConnectionUtility().startTransaction();
			gameDAO.addCommand();
			gameDAO.getConnectionUtility().endTransaction();
			deltaCount.put(gameId, deltaCount.get(gameId) + 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if (deltaCount.get(gameId) >= delta) {
			return saveGame(gameId);
		}
			
		return true;
	}
	
	public boolean addGame(int gameId) {
		try {
			Model game = GameInfoContainer.getInstance().getGameModel(gameId);
			gameDAO.getConnectionUtility().startTransaction();
			gameDAO.addGame(gameId, game);
			gameDAO.getConnectionUtility().endTransaction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			
		return true;
	}
	
	public boolean addUser() {
		
		try {
			userDAO.getConnectionUtility().startTransaction();
			userDAO.addUser();
			userDAO.getConnectionUtility().endTransaction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return false;
		
	}
	
	public boolean wipe() {
		try {
			gameDAO.getConnectionUtility().startTransaction();
			userDAO.getConnectionUtility().startTransaction();
			gameDAO.clearGames();
			userDAO.clearUsers();
			userDAO.getConnectionUtility().endTransaction();
			gameDAO.getConnectionUtility().endTransaction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}	
	
	private boolean loadUsers() {
		try {
			userDAO.getConnectionUtility().startTransaction();
			UserInfoBank users = userDAO.getUsers();
			userDAO.getConnectionUtility().endTransaction();
			GameInfoContainer.getInstance().setUser(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean loadGames() {
		try {
			gameDAO.getConnectionUtility().startTransaction();
			ModelBank games = gameDAO.getGames();
			gameDAO.getConnectionUtility().endTransaction();
			GameInfoContainer.getInstance().setGames(games);
			loadDeltaCount(games);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean loadCommands() {
		try {
			gameDAO.getConnectionUtility().startTransaction();
			ModelBank games = gameDAO.getCommands();
			gameDAO.getConnectionUtility().endTransaction();
			GameInfoContainer.getInstance().setGames(games);
			loadDeltaCount(games);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void loadDeltaCount(ModelBank games) {
		for(Entry<Integer, Model> game:games.getGames().entrySet()) {
			deltaCount.put(game.getKey(), 0);
		}
	}
	
	private boolean saveGame(int gameId) {
		
		try {
			Model game = GameInfoContainer.getInstance().getGameModel(gameId);
			gameDAO.getConnectionUtility().startTransaction();
			gameDAO.updateGame(gameId, game);
			gameDAO.getConnectionUtility().endTransaction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		deltaCount.put(gameId, 0);
		return true;
	}	

}
