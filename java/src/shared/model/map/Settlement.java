/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.util.ArrayList;

import shared.locations.VertexLocation;

/**
*owner (index): The index (not id) of the player who owns this piece (0-3),
*location (VertexLocation): The location of this settlement.
*/
public class Settlement extends VertexObject{

	public Settlement(int owner, VertexLocation location, ArrayList<Hex> hexes) {
		super(owner, location, hexes);
		// TODO Auto-generated constructor stub
	}
 
}