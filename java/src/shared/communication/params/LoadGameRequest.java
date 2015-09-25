package shared.communication.params;

/**
 * Wrapper object containing the name of the save file to load. To be parsed to
 * JSON and used in the IServerProxy.loadGame request.
 *
 *
 * LoadGameRequest { name (string): The name of the saved
 * game file that you want to load. (The game's ID is restored as well.) }
 */
public class LoadGameRequest {
	//String name - name of the game file to load
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
