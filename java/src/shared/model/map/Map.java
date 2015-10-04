/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.util.ArrayList;
import java.util.Objects;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;

import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
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
    public boolean canPlaceRoadAtLoc(int playerIndex, EdgeLocation location, boolean restricted ) {
    	boolean valid = false;
    	
    	
        return valid;
    }
    
    private boolean edgeOccupied(EdgeLocation location) {
    	boolean occupied = false;
    	
//    	foreach(Road road : roads) {
//    		if(road.getLocation() == location) {
//    			occupied = true;
//    			break;
//    		}
//    	}
//    	
    	return occupied;
    }
    
    private boolean edgeAdjacent(int playerIndex) {
//    	bool adjacent = false;
    	
//    	foreachlhlkhj;;;;
//    	'';l
        return false;
    }
    
    
    /**
     * add road to roads list
     * @param road to be placed at this locations
     */
    public void placeRoadAtLocation(Road road) {
    	roads.add(road);
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
     * @param city this is the city to be placed
     */
    public void placeCityAtLocation(City city, VertexLocation location){
        
    }
    
    public int neededToOfferMaritimeTrade(int playerIndex, ResourceType resourceType) {
        //create list of all vertex objects
        ArrayList<VertexObject> allBuildings = new ArrayList(settlements);
        allBuildings.addAll(cities);
        
        //check for player vertex objects on port edges
        ArrayList<VertexLocation> verticiesOnResourcePorts = getResourcePortVerticies();
        ArrayList<VertexLocation> verticiesOnGeneralPorts = getGeneralPortVerticies();
        
        ArrayList<VertexObject> resourcePortBuildings = new ArrayList<VertexObject>();
        ArrayList<VertexObject> generalPortBuildings = new ArrayList<VertexObject>();
        
        for(VertexObject building : allBuildings) {
            if(building.getOwner() == playerIndex) {
                if(verticiesOnResourcePorts.contains(building.getLocation().getNormalizedLocation())) {
                    resourcePortBuildings.add(building);
                }
                else if(verticiesOnGeneralPorts.contains(building.getLocation().getNormalizedLocation()))
                {
                    generalPortBuildings.add(building);
                }
            }
        }
        
        if(!resourcePortBuildings.isEmpty()) {
            return 2;
        }
        else if(!resourcePortBuildings.isEmpty()) {
            return 3;
        }
        else {
            return 4;
        }
    }
    
    private ArrayList<VertexLocation> getResourcePortVerticies() {
        ArrayList<VertexLocation> list = new ArrayList<VertexLocation>();
        
        for(Port port: ports) {
            
            EdgeLocation portEdge = port.getEdgeLocation();
            EdgeDirection portEdgeDirection = portEdge.getDir();
            HexLocation portLocation = portEdge.getHexLoc();
            
            if(port.getRatio() == 2) {
                switch(portEdgeDirection) {
                    case North:
                    {
                        list.add(new VertexLocation(portLocation, VertexDirection.NorthWest));
                        list.add(new VertexLocation(portLocation, VertexDirection.NorthEast));
                        break;
                    }
                    case NorthEast:
                    {
                        list.add(new VertexLocation(portLocation, VertexDirection.NorthEast));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest));
                        break;
                    }
                    case SouthEast:
                    {
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthEast));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest));
                        break;
                    }
                    case South:
                    {
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthEast));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthWest));
                        break;
                    }
                    case SouthWest:
                    {
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthWest));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.NorthEast));
                        break;
                    }
                    case NorthWest:
                    {
                        list.add(new VertexLocation(portLocation, VertexDirection.NorthWest));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.NorthEast));
                        break;
                    }
                }
            }
        }
        
        return list;
    }
    
        private ArrayList<VertexLocation> getGeneralPortVerticies() {
        ArrayList<VertexLocation> list = new ArrayList<VertexLocation>();
        
        for(Port port: ports) {
            
            EdgeLocation portEdge = port.getEdgeLocation();
            EdgeDirection portEdgeDirection = portEdge.getDir();
            HexLocation portLocation = portEdge.getHexLoc();
            
            if(port.getRatio() == 3) {
                switch(portEdgeDirection) {
                    case North:
                    {
                        list.add(new VertexLocation(portLocation, VertexDirection.NorthWest));
                        list.add(new VertexLocation(portLocation, VertexDirection.NorthEast));
                        break;
                    }
                    case NorthEast:
                    {
                        list.add(new VertexLocation(portLocation, VertexDirection.NorthEast));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest));
                        break;
                    }
                    case SouthEast:
                    {
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthEast));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.SouthEast), VertexDirection.NorthWest));
                        break;
                    }
                    case South:
                    {
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthEast));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthWest));
                        break;
                    }
                    case SouthWest:
                    {
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.South), VertexDirection.NorthWest));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.NorthEast));
                        break;
                    }
                    case NorthWest:
                    {
                        list.add(new VertexLocation(portLocation, VertexDirection.NorthWest));
                        list.add(new VertexLocation(portLocation.getNeighborLoc(EdgeDirection.SouthWest), VertexDirection.NorthEast));
                        break;
                    }
                }
            }
        }
        
        return list;
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
     * @param settlement to be place at locations
     */
    public void placeSettlmentAtLoc(Settlement settlement, VertexLocation location){
        
    }
    
    public boolean canRobPlayer(int playerIndex) {
        //get rob-able locations
        ArrayList<VertexLocation> validRobbingLocations = getValidNormalizedRobbingLocations();
        
        //create list of all vertex objects
        ArrayList<VertexObject> allBuildings = new ArrayList(settlements);
        allBuildings.addAll(cities);
        
        for(VertexObject building : allBuildings) {
            if(validRobbingLocations.contains(building.getLocation().getNormalizedLocation()) && building.getOwner() == playerIndex) {
                return true;
            }
        }

        return false;
    }
            
    private ArrayList<VertexLocation> getValidNormalizedRobbingLocations() {
        ArrayList<VertexLocation> list = new ArrayList<VertexLocation>();
        
        list.add(new VertexLocation(robber, VertexDirection.NorthEast));
        list.add(new VertexLocation(robber, VertexDirection.NorthWest));
        list.add(new VertexLocation(robber, VertexDirection.West).getNormalizedLocation());
        list.add(new VertexLocation(robber, VertexDirection.SouthWest).getNormalizedLocation());
        list.add(new VertexLocation(robber, VertexDirection.SouthEast).getNormalizedLocation());
        list.add(new VertexLocation(robber, VertexDirection.East).getNormalizedLocation());
        
        return list;

    }
    
    
    /**
     * 
     * @param rolledNumber - dice value if not == 7
     */
    public void distributeResources(int rolledNumber)
    {
    	
    }

    
    public ArrayList<Hex> getHexes() {
        return hexes;
    }

    public void setHexes(ArrayList<Hex> hexes) {
        this.hexes = hexes;
    }

    public ArrayList<Port> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<Port> ports) {
        this.ports = ports;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public ArrayList<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(ArrayList<Settlement> settlements) {
        this.settlements = settlements;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
    
    public ArrayList<VertexObject> getCitiesAndSettlements() {
    	ArrayList<VertexObject> vObjects = new ArrayList<VertexObject>();
    	for (Settlement settlement: settlements) {
    		vObjects.add(settlement);
    	}
    	for (City city: cities) {
    		vObjects.add(city);
    	}
    	return vObjects;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public HexLocation getRobber() {
        return robber;
    }

    public void setRobber(HexLocation robber) {
        this.robber = robber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.hexes);
        hash = 67 * hash + Objects.hashCode(this.ports);
        hash = 67 * hash + Objects.hashCode(this.roads);
        hash = 67 * hash + Objects.hashCode(this.settlements);
        hash = 67 * hash + Objects.hashCode(this.cities);
        hash = 67 * hash + this.radius;
        hash = 67 * hash + Objects.hashCode(this.robber);
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
        final Map other = (Map) obj;
        if (!Objects.equals(this.hexes, other.hexes)) {
            return false;
        }
        if (!Objects.equals(this.ports, other.ports)) {
            return false;
        }
        if (!Objects.equals(this.roads, other.roads)) {
            return false;
        }
        if (!Objects.equals(this.settlements, other.settlements)) {
            return false;
        }
        if (!Objects.equals(this.cities, other.cities)) {
            return false;
        }
        if (this.radius != other.radius) {
            return false;
        }
        if (!Objects.equals(this.robber, other.robber)) {
            return false;
        }
        return true;
    }
    
    
      
}
