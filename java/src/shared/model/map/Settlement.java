/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.io.Serializable;

import shared.locations.VertexLocation;

/**
*owner (index): The index (not id) of the player who owns this piece (0-3),
*location (VertexLocation): The location of this settlement.
*/
public class Settlement extends VertexObject implements Serializable{


    public transient final int VICTORY_POINTS = 1;

    public Settlement(int owner, VertexLocation location) {
        super(owner, location);
    }

}
