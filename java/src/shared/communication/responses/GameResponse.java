package shared.communication.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper object for information about a current game.  Includes the title of the game, the id, and a list of Players currently in the game.
 * @author Alex
 *
 */
public class GameResponse {
	//String title - title of the game
	//int id - ID of the game
	//List<PlayerFromJSON> players - list of players currently in this game
    private String title;
    private int id;
    List<PlayerResponse> players;
    
    public GameResponse() {
    	
    }

    public GameResponse(String title, int id, List<PlayerResponse> players) {
		super();
		this.title = title;
		this.id = id;
		this.players = players;
	}

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

    public List<PlayerResponse> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerResponse> players) {
        this.players = players;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.title);
        hash = 19 * hash + this.id;
        hash = 19 * hash + Objects.hashCode(this.players);
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
        final GameResponse other = (GameResponse) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.players, other.players)) {
            return false;
        }
        return true;
    }
    
    public static GameResponse getDefaultSampleGameResponse() {
        GameResponse gr = new GameResponse();
        gr.setId(0);
        gr.setTitle("Default Game");
        PlayerResponse sam = new PlayerResponse();
        sam.setColor("orange");
        sam.setName("Sam");
        sam.setId(0);
        PlayerResponse brooke = new PlayerResponse();
        brooke.setColor("blue");
        brooke.setName("Brooke");
        brooke.setId(1);
        PlayerResponse pete = new PlayerResponse();
        pete.setColor("red");
        pete.setName("Pete");
        pete.setId(10);
        PlayerResponse mark = new PlayerResponse();
        mark.setColor("green");
        mark.setName("Mark");
        mark.setId(11);
        List<PlayerResponse> playas = new ArrayList<>();
        playas.add( sam );
        playas.add( brooke );
        playas.add( pete );
        playas.add( mark );
        gr.setPlayers(playas);
        
        return gr;
    }
}
