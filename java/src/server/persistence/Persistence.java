package server.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import static jdk.nashorn.internal.runtime.Version.version;

import server.facade.ResponderFacade;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import server.gameinfocontainer.UserInfoBank;
import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;
import server.persistence.factory.AbstractFactory;
import shared.communication.params.CommandParam;
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
	
	private boolean saving;
	
	
	private HashMap<Integer,Integer> deltaCount;
	
	
	public Persistence() {
		deltaCount = new HashMap<Integer,Integer>();
		this.delta = 0;
		this.plugin = null;	
		registry = new PluginRegistry();
		this.saving = false;
	}
	
	public void set(String plugin, int delta) {
		this.delta = delta;
		this.plugin = plugin;
		AbstractFactory factory = this.registry.CreateFactory(plugin);
		gameDAO = factory.getGamesDAO();
		userDAO = factory.getUsersDAO();
	}
	
	public boolean loadData() {
		this.saving = false;
		if(loadUsers() && loadGames() && loadCommands()) {
			this.saving = true;
			return true;
		}
		this.saving = true;
		return false;
	}
	
	public boolean saveCommand(String command, String json, int gameId, int playerId, String randomResult) {
		if (!saving)
			return false;
		
		try {
			int version = GameInfoContainer.getInstance().getGameModel(gameId).getVersion();
			gameDAO.getConnectionUtility().startTransaction();
			gameDAO.addCommand(command, json, playerId, gameId, version, randomResult);
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
		if (!saving)
			return false;
		
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
		if (!saving)
			return false;
		
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
			loadCommands(games);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean loadCommands(ModelBank games) {
		ArrayList<CommandParam> commands;
		try {
			gameDAO.getConnectionUtility().startTransaction();
			commands = gameDAO.getCommands(game_id, version);
			gameDAO.getConnectionUtility().endTransaction();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		ResponderFacade serverFacade = new ResponderFacade();
		
		for(CommandParam cmd:commands) {
			serverFacade.doFunction(cmd.getCommand(), cmd.getJson(), cmd.getGameId(), cmd.getPlayerId(), cmd.getRandom());
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
