package shared.communication.params;

/**
 * No version ID -- used for writing commands
 * 
 * @author JanPaul
 */
public class CommandParam {
	private String command;
	private String json;
	private int playerId;
	private int gameId;
	private String random;
	
	public CommandParam(String command, String json, int playerId, int gameId, String random) {
		super();
		this.command = command;
		this.json = json;
		this.playerId = playerId;
		this.gameId = gameId;
		this.random = random;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}


}
