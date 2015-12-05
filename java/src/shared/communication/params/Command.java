package shared.communication.params;

public class Command {
	private String command;
	private String json;
	private int playerId;
	private int gameId;
	private String random;
	
	public Command(String command, String json, int playerId, int gameId, String random) {
		super();
		this.command = command;
		this.json = json;
		this.playerId = playerId;
		this.gameId = gameId;
		this.random = random;
	}
	
	
}
