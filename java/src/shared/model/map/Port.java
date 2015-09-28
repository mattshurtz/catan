/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.util.Objects;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;

/**
*resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What type
*resource this port trades for. If it's omitted, then it's for any resource.,
*location (HexLocation): Which hex this port is on. This shows the (ocean/non-existent) hex to
*draw the port on.,
*direction (string) = ['NW' or 'N' or 'NE' or 'E' or 'SE' or 'SW']: Which edge this port is on.,
*ratio (integer): The ratio for trade in (ie, if this is 2, then it's a 2:1 port.
*/
public class Port {
   ResourceType type;
   String resource;
   HexLocation location;
   EdgeDirection direction;
   int ratio;
   
    public Port(int ratio, String resource, ResourceType type, EdgeDirection direction, HexLocation location) {
            this.resource = resource;
            this.type = type;
            this.location = location;
            this.direction = direction;
            this.ratio = ratio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.resource);
        hash = 37 * hash + Objects.hashCode(this.location);
        hash = 37 * hash + Objects.hashCode(this.direction);
        hash = 37 * hash + this.ratio;
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
        final Port other = (Port) obj;
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.resource, other.resource)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (this.direction != other.direction) {
            return false;
        }
        if (this.ratio != other.ratio) {
            return false;
        }
        return true;
    }
   
   
}
