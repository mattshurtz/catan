/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.util.ArrayList;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * hexes (array[Hex]): A list of all the hexes on the grid - it's only land
 * tiles, ports (array[Port]), roads (array[Road]), settlements
 * (array[VertexObject]), cities (array[VertexObject]), radius (integer): The
 * radius of the map (it includes the center hex, and the ocean hexes; pass this
 * into the hexgrid constructor), robber (HexLocation): The current location of
 * the robber
 */
/**
 * @author Shurt
 *
 */
public class Map {

    

	ArrayList<Hex> hexes;
    ArrayList<Port> ports;
    ArrayList<Road> roads;
    ArrayList<Settlement> settlements;
    ArrayList<City> cities;
    int radius;
    HexLocation robber;
    
    public Map() {
		// TODO Auto-generated constructor stub
	}
    
    

    public Map(ArrayList<Hex> hexes, ArrayList<Port> ports, ArrayList<Road> roads, ArrayList<Settlement> settlements,
			ArrayList<City> cities, int radius, HexLocation robber) {
		super();
		this.hexes = hexes;
		this.ports = ports;
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.radius = radius;
		this.robber = robber;
	}

	/**
     * @param location specifies a HexLocation to see if a road can be placed
     * there
     * @return true if the place can accept a road
     */
    public boolean canPlaceRoadAtLoc(EdgeLocation location) {
        return false;
    }
    
    /**
     * add road to roads list
     */
    public void placeRoadAtLocation(Road road) {

    }

    /**
     * @param location specifies a HexLocation to see if a city can be placed
     * there
     * @return true if the place can accept a city
     */
    public boolean canPlaceCityAtLoc(VertexLocation location) {
        return false;
    }
    
    /**
     * add city to city List
     * @param location where the city is to be placed
     */
    public void placeCityAtLocation(City city){
        
    }
    

    /**
     * @param location specifies a HexLocation to see if a settlement can be
     * placed there
     * @return true if the place can accept a settlement
     */
    public boolean canPlaceSettlementAtLoc(VertexLocation location) {
        return false;
    }
    
    /**
     * add settlement to settlements list
     * @param location where settlement is to be placed
     */
    public void placeSettlmentAtLoc(Settlement settlement){
        
    }
}
