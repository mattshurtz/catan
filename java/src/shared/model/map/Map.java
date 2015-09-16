/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.util.ArrayList;
import shared.locations.HexLocation;

/**
*hexes (array[Hex]): A list of all the hexes on the grid - it's only land tiles,
*ports (array[Port]),
*roads (array[Road]),
*settlements (array[VertexObject]),
*cities (array[VertexObject]),
*radius (integer): The radius of the map (it includes the center hex, and the ocean hexes; pass
*this into the hexgrid constructor),
*robber (HexLocation): The current location of the robber
*/
public class Map {
    ArrayList<Hex> hexes;
    ArrayList<Port> ports;
    ArrayList<Road> roads;
    ArrayList<VertexObject> settlements;
    ArrayList<VertexObject> cities;
    int radius;
    HexLocation robber;
}
