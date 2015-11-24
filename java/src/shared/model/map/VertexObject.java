/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.util.ArrayList;
import java.util.Objects;

import client.facade.CatanFacade;
import shared.definitions.CatanColor;
import shared.exceptions.GetPlayerException;
import shared.locations.VertexLocation;
import shared.model.*;

/**
*owner (index): The index (not id) of the player who owns this piece (0-3),
*location (VertexLocation): The location of this piece.
*ArrayList of hexes are the three hexes that correspond to this vertex on the map this is used
*after roll when determining which resources a player should receive from the bank. 
*/
public abstract class VertexObject{
   private int owner;
   private VertexLocation location;  
   private ArrayList<Hex> hexes;

    public VertexObject(int owner, VertexLocation location) {
        super();
        this.owner = owner;
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.owner;
        hash = 97 * hash + Objects.hashCode(this.location);
        hash = 97 * hash + Objects.hashCode(this.hexes);
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
        final VertexObject other = (VertexObject) obj;
        if (this.owner != other.owner) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.hexes, other.hexes)) {
            return false;
        }
        return true;
    }

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	/**
	 * pre-condition: CatanFacade.getModel() is not null, and has a valid list of Players
	 * @return Color of this City/Settlement
	 */
	public CatanColor getColor() {
		Model currentModel = CatanFacade.getModel();
		if (currentModel == null){
			//System.out.println("Catan Model is null but shouldn't be");
		}
		try {
			Player player = currentModel.getPlayer(owner);
			return player.getColor();
		} catch (GetPlayerException e) {
			e.printStackTrace();
			return null;
		}
	}

	public VertexLocation getLocation() {
		return location;
	}

	public void setLocation(VertexLocation location) {
		this.location = location;
	}

	public ArrayList<Hex> getHexes() {
		return hexes;
	}

	public void setHexes(ArrayList<Hex> hexes) {
		this.hexes = hexes;
	}
    
}
