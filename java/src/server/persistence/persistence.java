package server.persistence;

import server.gameinfocontainer.GameInfoContainer;
import server.gameinfocontainer.ModelBank;
import server.gameinfocontainer.UserInfoBank;
import server.persistence.DAO.IGamesDAO;
import server.persistence.DAO.IUsersDAO;
import server.persistence.factory.IFactory;

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
	private IFactory factory;
	private pluginRegistry registry;
	
	private int delta;
	private String plugin;
	
	public Persistence() {
		
	}
	
	public void set(String plugin, int delta) {
		this.delta = delta;
		this.plugin = plugin;
		
	}
	
	public boolean loadData() {
		if(loadUsers() && loadGames()) {
			return true;
		}
		return false;
	}
	
	public boolean saveCommand(String command, String json, int gameId, int playerId) {
		return false;
	}
	
	public boolean newGame(int gameId) {
		return false;
	}
	
	public boolean wipe() {
		return false;
	}
	
		
	
	private boolean loadUsers() {
		try {
			UserInfoBank users = factory.getUserDAO().getUsers();
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
			ModelBank games = factory.getGameDAO().getGames();
			GameInfoContainer.getInstance().setGames(games);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean saveGame(int gameId) {
		return false;
	}	

}
