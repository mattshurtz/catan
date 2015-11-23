package shared.model.map;

import java.util.ArrayList;
import shared.locations.HexLocation;

import shared.locations.VertexLocation;


/**
*owner (index): The index (not id) of the player who owns this piece (0-3),
*location (VertexLocation): The location of this settlement.
*/
public class City extends VertexObject{

    public transient final int VICTORY_POINTS = 2;

    public City(int owner, VertexLocation location) {
        super(owner, location);
    }
   
    /**
     * @param rolledNumber - int not equal to 7
     * @param robber - location of robber - disbales resource distribution
     */
    public void distributeResources(int rolledNumber, HexLocation robber)
    {
    	
    }
}
