package shared.communication.responses;

import java.util.Objects;

/**
 * Wrapper object containing lobby information about an in-game player.  This object is returned as part of the IServerFacade.getGamesList() response
 *
 */
public class PlayerResponse {
    //String color - color of the player
    //String name - name of the player
    //int id - ID of the player
    
    private String color;
    private String name;
    private int id;
    
    public PlayerResponse(String color, String name, int id) {
		super();
		this.color = color;
		this.name = name;
		this.id = id;
	}

	public PlayerResponse(){
    	
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.color);
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + this.id;
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
        final PlayerResponse other = (PlayerResponse) obj;
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
