package server.persistence;

import server.gameinfocontainer.GameInfoContainer;
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
	
	public boolean wipe() {
		return false;
	}
	
	public boolean save(String command, String json, int gameId, int playerId) {
		return false;
	}	
	
	private boolean loadUsers() {
		return false;
	}
	
	private boolean loadGames() {
		return false;
	}
	
	private boolean saveCommand(String command, String json, int gameId, int playerId) {
		return false;
	}
	
	private boolean saveGame(int gameId) {
		return false;
	}	

}
