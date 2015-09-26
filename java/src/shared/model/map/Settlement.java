/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.util.ArrayList;
import shared.locations.HexLocation;

import shared.locations.VertexLocation;

/**
*owner (index): The index (not id) of the player who owns this piece (0-3),
*location (VertexLocation): The location of this settlement.
*/
public class Settlement extends VertexObject{

    public final int VICTORY_POINTS = 1;

    public Settlement(int owner, VertexLocation location, ArrayList<Hex> hexes) {
        super(owner, location, hexes);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param rolledNumber - int not equal to 7
     * @param robber - location of robber - disbales resource distribution
     */
    public void distributeResources(int rolledNumber, HexLocation robber)
    {
    	
    }
    
    
 
}
