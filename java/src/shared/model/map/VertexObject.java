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
*location (VertexLocation): The location of this piece.
*ArrayList<Hex> hexes are the three hexes that correspond to this vertex on the map this is used
*after roll when determining which resources a player should receive from the bank. 
*/
public abstract class VertexObject{
   int owner;
   VertexLocation location;  
   ArrayList<Hex> hexes;
}
