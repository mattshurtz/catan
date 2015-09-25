package shared.communication.responses;

import java.util.List;
import java.util.Objects;

/**
 * Wrapper object for the IServerProxy.createNewGame response.
 * @author Alex
 * String title - title of the new game
 * int id - ID of the new game
 * List EmptyPlayerFromJSON players - list of empty player objects
 */
public class CreateGameResponse {
	//String title - title of the new game
	//int id - ID of the new game
	//List<EmptyPlayerFromJSON> players - list of empty player objects
    private String title;
    private int id;
    private List<EmptyPlayerResponse> players;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<EmptyPlayerResponse> getPlayers() {
        return players;
    }

    public void setPlayers(List<EmptyPlayerResponse> players) {
        this.players = players;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CreateGameResponse other = (CreateGameResponse) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        // Commented out for jUnit testing -- no way to know the id # in advance
//        if (this.id != other.id) {
//            return false;
//        }
        if (!Objects.equals(this.players, other.players)) {
            return false;
        }
        return true;
    }
    
    
    
}
