package shared.communication.params;

/**
 * Wrapper object containing information necessary to save the game.
 *
 * SaveGameRequest { id (integer): The ID of the game to save, name (string):
 * The file name you want to save it under }
 */
public class SaveGameRequest {
    //int id - ID of the game the player wants to save
    //String name - name of the file the player wants to save the game state to
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
