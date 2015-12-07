package shared.communication.params;

/**
 * What we write to the file. Includes version number, unlike CommandParam
 * 
 * @author JanPaul
 */
public class SerializableCommand {
    private String command;
	private String json;
	private int playerId;
	private int gameId;
	private String random;
    private int version;

    public SerializableCommand(String command, String json, int player_id, int game_id, int version, String randomValue) {
        this.command = command;
        this.json = json;
        this.playerId = player_id;
        this.gameId = game_id;
        this.version = version;
        this.random = randomValue;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public CommandParam toCommandParam() {
        return new CommandParam( command, json, playerId, gameId, random );
    }
    
}
