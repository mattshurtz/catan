package server.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import server.facade.ResponderFacade;
import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import server.gameinfocontainer.UserInfoBank;
import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;
import server.persistence.factory.AbstractFactory;
import shared.communication.params.CommandParam;
import shared.model.Model;
import shared.model.User;

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
		gameDAO = factory.getGameDAO();
		userDAO = factory.getUserDAO();
	}
	
	public boolean loadData() {
		this.saving = false;
		addDefaultGamesUsers();
		if(loadUsers() && loadGames()) {
			this.saving = true;			
			System.out.println("Persistence ON");
			return true;
		}
		this.saving = false;
		System.out.println("Persistence OFF");
		return false;
	}
	
	private void addDefaultGamesUsers() {
		this.saving = true;
		GameInfoContainer.getInstance();
		addGame(0);
		addGame(1);
		addUser(0);
		addUser(1);
		addUser(2);
		addUser(3);
		addUser(4);
		this.saving = false;
	}
	
	public boolean saveCommand(String command, String json, int gameId, int playerId, String randomResult) {
		if (!saving)
			return false;
		
		command = command.replace("server.commands.","");
		
		System.out.println("Saving Command: " + command+" delta: "+deltaCount.get(gameId));
		
		try {
			int version = GameInfoContainer.getInstance().getGameModel(gameId).getVersion();
			gameDAO.getConnectionUtility().startTransaction();
			gameDAO.addCommand(command, json, playerId, gameId, version, randomResult);
			gameDAO.getConnectionUtility().endTransaction();
			deltaCount.put(gameId, deltaCount.get(gameId) + 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			gameDAO.getConnectionUtility().rollBack();
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
		
		System.out.println("Adding Game: " + gameId);
		
		try {
			Model game = GameInfoContainer.getInstance().getGameModel(gameId);
			gameDAO.getConnectionUtility().startTransaction();
			gameDAO.addGame(gameId, game);
			gameDAO.getConnectionUtility().endTransaction();
			deltaCount.put(gameId, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			gameDAO.getConnectionUtility().rollBack();
			e.printStackTrace();
			return false;
		}
			
		return true;
	}
	
	public boolean addUser(int userID) {
		if (!saving)
			return false;
		
		User user = GameInfoContainer.getInstance().getUsers().getUsers().get(userID);
		
		
		System.out.println("Adding User: " + user.getUsername() );
		
		try {
			userDAO.getConnectionUtility().startTransaction();
			userDAO.addUser(userID, user.getUsername(), user.getPassword());
			userDAO.getConnectionUtility().endTransaction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			userDAO.getConnectionUtility().rollBack();
			e.printStackTrace();
			return false;
		}
		
		return false;
		
	}
	
	public boolean wipe() {
		System.out.println("Wiping Database");
		
		try {
			gameDAO.getConnectionUtility().startTransaction();
			userDAO.getConnectionUtility().startTransaction();
			gameDAO.clearGames();
			userDAO.clearUsers();
			userDAO.getConnectionUtility().endTransaction();
			gameDAO.getConnectionUtility().endTransaction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//userDAO.getConnectionUtility().rollBack();
			//gameDAO.getConnectionUtility().rollBack();
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	
	private boolean loadUsers() {
		
		System.out.println("Loading Users");
		try {
			userDAO.getConnectionUtility().startTransaction();
			UserInfoBank users = userDAO.getUsers();
			userDAO.getConnectionUtility().endTransaction();
			GameInfoContainer.getInstance().setUser(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			userDAO.getConnectionUtility().rollBack();
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean loadGames() {
		System.out.println("Loading Games");
		
		try {
			gameDAO.getConnectionUtility().startTransaction();
			ModelBank games = gameDAO.getGames();
			gameDAO.getConnectionUtility().endTransaction();
			GameInfoContainer.getInstance().setGames(games);
			loadDeltaCount(games);
			loadCommands(games);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			gameDAO.getConnectionUtility().rollBack();
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean loadCommands(ModelBank games) {
		System.out.println("Loading Commands");
		
		ResponderFacade serverFacade = new ResponderFacade();
		
		for(Entry<Integer, Model> game: games.getGames().entrySet()) {
			ArrayList<CommandParam> commands;			
			try {
				gameDAO.getConnectionUtility().startTransaction();
				commands = gameDAO.getCommands(game.getKey(), game.getValue().getVersion());
				gameDAO.getConnectionUtility().endTransaction();
				for(CommandParam cmd:commands) {
					serverFacade.doFunction(cmd.getCommand(), cmd.getJson(), cmd.getGameId(), cmd.getPlayerId(), cmd.getRandom());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				gameDAO.getConnectionUtility().rollBack();
				e.printStackTrace();
				return false;
			}	
		}	
		return true;
	}
	
	private void loadDeltaCount(ModelBank games) {
		for(Entry<Integer, Model> game:games.getGames().entrySet()) {
			deltaCount.put(game.getKey(), 0);
		}
	}
	
	private boolean saveGame(int gameId) {
		
		System.out.println("Updating Game: " + gameId);
		
		try {
			Model game = GameInfoContainer.getInstance().getGameModel(gameId);
			gameDAO.getConnectionUtility().startTransaction();
			gameDAO.updateGame(gameId, game);
			gameDAO.getConnectionUtility().endTransaction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			gameDAO.getConnectionUtility().rollBack();
			e.printStackTrace();
			return false;
		}
		deltaCount.put(gameId, 0);
		return true;
	}	

}
