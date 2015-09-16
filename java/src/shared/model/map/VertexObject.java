/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import shared.locations.EdgeLocation;

/**
*owner (index): The index (not id) of the player who owns thie piece (0-3),
*location (EdgeLocation): The location of this road.
*/
public class VertexObject {
    int owner;
    EdgeLocation location;
}
