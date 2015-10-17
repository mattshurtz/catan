package shared.model.map;

import java.util.Objects;

import client.facade.CatanFacade;
import shared.definitions.CatanColor;
import shared.exceptions.GetPlayerException;
import shared.locations.EdgeLocation;
import shared.model.*;

/**
*owner (index): The index (not id) of the player who owns this piece (0-3),
*location (EdgeLocation): The location of this road.
*/
public class Road {
    
    private int owner;
    private EdgeLocation location;

    public Road(int owner, EdgeLocation location) {
            this.owner = owner;
            this.location = location;
    }

    public int getOwner() {
            return owner;
    }

    public EdgeLocation getLocation() {
            return location;
    }
    
	/**
	 * @pre CatanFacade.getModel() is not null, and has a valid list of Players
	 * @return Color of this City/Settlement
	 */
	public CatanColor getColor() {
		Model currentModel = CatanFacade.getModel();
		if (currentModel == null)
			System.out.println("Catan Model is null but shouldn't be");
		
		try {
			Player player = currentModel.getPlayer(owner);
			return player.getColor();
		} catch (GetPlayerException e) {
			e.printStackTrace();
			return null;
		}
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
        final Road other = (Road) obj;
        if (this.owner != other.owner) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }
    
    
}
