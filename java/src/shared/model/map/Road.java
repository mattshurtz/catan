/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.util.Objects;
import shared.locations.EdgeLocation;

/**
*owner (index): The index (not id) of the player who owns this piece (0-3),
*location (EdgeLocation): The location of this road.
*/
public class Road {
    
    int owner;
    EdgeLocation location;

    public Road(int owner, EdgeLocation location) {
            this.owner = owner;
            this.location = location;
    }

    int getOwner() {
            return owner;
    }

    EdgeLocation getLocation() {
            return location;
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
