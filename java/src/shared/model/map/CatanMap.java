/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import shared.definitions.HexType;
import shared.definitions.PortType;
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
public class CatanMap implements Serializable{

    List<Hex> hexes;
    List<Port> ports;
    List<Road> roads;
    List<Settlement> settlements;
    List<City> cities;
    int radius;
    HexLocation robber;
    
    transient Random rand = new Random();
    
    // Default values, to be used when random tiles/numbers/ports is FALSE
    public static final Map<HexLocation, HexType> defaultTiles;
    public static final Map<HexLocation, Integer> defaultNumbers;
    public static final Map<HexLocation, PortType> defaultPorts;
    // set them up
    static {
        // default tiles
        defaultTiles = new HashMap<>();
        defaultTiles.put(new HexLocation(-2,0), HexType.ORE);
        defaultTiles.put(new HexLocation(-2,1), HexType.WHEAT);
        defaultTiles.put(new HexLocation(-2,2), HexType.WOOD);
        defaultTiles.put(new HexLocation(-1,-1), HexType.BRICK);
        defaultTiles.put(new HexLocation(-1,0), HexType.SHEEP);
        defaultTiles.put(new HexLocation(-1,1), HexType.SHEEP);
        defaultTiles.put(new HexLocation(-1,2), HexType.ORE);
        defaultTiles.put(new HexLocation(0,-2), HexType.DESERT);
        defaultTiles.put(new HexLocation(0,-1), HexType.WOOD);
        defaultTiles.put(new HexLocation(0,0), HexType.WHEAT);
        defaultTiles.put(new HexLocation(0,1), HexType.WOOD);
        defaultTiles.put(new HexLocation(0,2), HexType.WHEAT);
        defaultTiles.put(new HexLocation(1,-2), HexType.BRICK);
        defaultTiles.put(new HexLocation(1,-1), HexType.ORE);
        defaultTiles.put(new HexLocation(1,0), HexType.BRICK);
        defaultTiles.put(new HexLocation(1,1), HexType.SHEEP);
        defaultTiles.put(new HexLocation(2,-2), HexType.WOOD);
        defaultTiles.put(new HexLocation(2,-1), HexType.SHEEP);
        defaultTiles.put(new HexLocation(2,0), HexType.WHEAT);
        
        // default numbers
        defaultNumbers = new HashMap<>();
        defaultNumbers.put(new HexLocation(-2,0), 5);
        defaultNumbers.put(new HexLocation(-2,1), 2);
        defaultNumbers.put(new HexLocation(-2,2), 6);
        defaultNumbers.put(new HexLocation(-1,-1), 8);
        defaultNumbers.put(new HexLocation(-1,0), 10);
        defaultNumbers.put(new HexLocation(-1,1), 9);
        defaultNumbers.put(new HexLocation(-1,2), 3);
        defaultNumbers.put(new HexLocation(0,-2), 0); //Desert Default Location
        defaultNumbers.put(new HexLocation(0,-1), 3);
        defaultNumbers.put(new HexLocation(0,0), 11);
        defaultNumbers.put(new HexLocation(0,1), 4);
        defaultNumbers.put(new HexLocation(0,2), 8);
        defaultNumbers.put(new HexLocation(1,-2), 4);
        defaultNumbers.put(new HexLocation(1,-1), 9);
        defaultNumbers.put(new HexLocation(1,0), 5);
        defaultNumbers.put(new HexLocation(1,1), 10);
        defaultNumbers.put(new HexLocation(2,-2), 11);
        defaultNumbers.put(new HexLocation(2,-1), 12);
        defaultNumbers.put(new HexLocation(2,0), 6);
        
        // default ports
        defaultPorts = new HashMap<>();
        defaultPorts.put(new HexLocation(1,-3), PortType.ORE);
        defaultPorts.put(new HexLocation(3,-3), PortType.THREE);
        defaultPorts.put(new HexLocation(3,-1), PortType.SHEEP);
        defaultPorts.put(new HexLocation(2,1), PortType.THREE);
        defaultPorts.put(new HexLocation(0,3), PortType.THREE);
        defaultPorts.put(new HexLocation(-2,3), PortType.BRICK);
        defaultPorts.put(new HexLocation(-3,2), PortType.WOOD);
        defaultPorts.put(new HexLocation(-3,0), PortType.THREE);
        defaultPorts.put(new HexLocation(-1,-2), PortType.WHEAT);
    }
    
    // Lists of locations to use for placing hexes, ports
    // (Regardless of whether they're random or not)
    public static final List<HexLocation> tileLocations, portLocations;
    public static final List<HexType> tileTypes;
    public static final List<PortType> portTypes;
    public static final List<Integer> numbers;
    public static final Map<HexLocation, EdgeDirection> portDirections;
    static {
        tileLocations = new ArrayList<>();
        tileLocations.add(new HexLocation(-2,0));
        tileLocations.add(new HexLocation(-2,1));
        tileLocations.add(new HexLocation(-2,2));
        tileLocations.add(new HexLocation(-1,-1));
        tileLocations.add(new HexLocation(-1,0));
        tileLocations.add(new HexLocation(-1,1));
        tileLocations.add(new HexLocation(-1,2));
        tileLocations.add(new HexLocation(0,-2));
        tileLocations.add(new HexLocation(0,-1));
        tileLocations.add(new HexLocation(0,0));
        tileLocations.add(new HexLocation(0,1));
        tileLocations.add(new HexLocation(0,2));
        tileLocations.add(new HexLocation(1,-2));
        tileLocations.add(new HexLocation(1,-1));
        tileLocations.add(new HexLocation(1,0));
        tileLocations.add(new HexLocation(1,1));
        tileLocations.add(new HexLocation(2,-2));
        tileLocations.add(new HexLocation(2,-1));
        tileLocations.add(new HexLocation(2,0));
        
        tileTypes = new ArrayList<>();
        tileTypes.add( HexType.BRICK );
        tileTypes.add( HexType.BRICK );
        tileTypes.add( HexType.BRICK );
        tileTypes.add( HexType.DESERT );
        tileTypes.add( HexType.SHEEP );
        tileTypes.add( HexType.SHEEP );
        tileTypes.add( HexType.SHEEP );
        tileTypes.add( HexType.SHEEP );
        tileTypes.add( HexType.ORE );
        tileTypes.add( HexType.ORE );
        tileTypes.add( HexType.ORE );
        tileTypes.add( HexType.WHEAT );
        tileTypes.add( HexType.WHEAT );
        tileTypes.add( HexType.WHEAT );
        tileTypes.add( HexType.WHEAT );
        tileTypes.add( HexType.WOOD );
        tileTypes.add( HexType.WOOD );
        tileTypes.add( HexType.WOOD );
        tileTypes.add( HexType.WOOD );
        
        portLocations = new ArrayList<>();
        portLocations.add(new HexLocation(1,-3));
        portLocations.add(new HexLocation(3,-3));
        portLocations.add(new HexLocation(3,-1));
        portLocations.add(new HexLocation(2,1));
        portLocations.add(new HexLocation(0,3));
        portLocations.add(new HexLocation(-2,3));
        portLocations.add(new HexLocation(-3,2));
        portLocations.add(new HexLocation(-3,0));
        portLocations.add(new HexLocation(-1,-2));
        
        portDirections = new HashMap<>();
        portDirections.put(new HexLocation(1,-3), EdgeDirection.South);
        portDirections.put(new HexLocation(3,-3), EdgeDirection.SouthWest);
        portDirections.put(new HexLocation(3,-1), EdgeDirection.NorthWest);
        portDirections.put(new HexLocation(2,1), EdgeDirection.NorthWest);
        portDirections.put(new HexLocation(0,3), EdgeDirection.North);
        portDirections.put(new HexLocation(-2,3), EdgeDirection.NorthEast);
        portDirections.put(new HexLocation(-3,2), EdgeDirection.NorthEast);
        portDirections.put(new HexLocation(-3,0), EdgeDirection.SouthEast);
        portDirections.put(new HexLocation(-1,-2), EdgeDirection.South);
        
        portTypes = new ArrayList<>();
        portTypes.add( PortType.THREE );
        portTypes.add( PortType.THREE );
        portTypes.add( PortType.SHEEP );
        portTypes.add( PortType.THREE );
        portTypes.add( PortType.THREE );
        portTypes.add( PortType.ORE );
        portTypes.add( PortType.BRICK );
        portTypes.add( PortType.WOOD );
        portTypes.add( PortType.WHEAT );
        
        numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(3);
        numbers.add(3);
        numbers.add(4);
        numbers.add(4);
        numbers.add(5);
        numbers.add(5);
        numbers.add(6);
        numbers.add(6);
        numbers.add(8);
        numbers.add(8);
        numbers.add(9);
        numbers.add(9);
        numbers.add(10);
        numbers.add(10);
        numbers.add(11);
        numbers.add(11);
        numbers.add(12);
    }
    
    public CatanMap() {
        hexes = new ArrayList<>();
        ports = new ArrayList<>();
        roads = new ArrayList<>();
        settlements = new ArrayList<>();
        cities = new ArrayList<>();
        radius = 4;
	}
    
    /**
     * Creates a new CatanMap with either the default setup or random stuff as specified.
     * @param randomNumbers
     * @param randomPorts
     * @param randomTiles 
     */
    public CatanMap( boolean randomNumbers, boolean randomPorts, boolean randomTiles ) {
        this();
        
        initTiles( randomTiles );
        initNumbers( randomNumbers );
        initPorts( randomPorts );
        
        
    }
    
    private void initTiles( boolean randomTiles ) {
        
        // Initialize all the hexes with the number 0 & no ports yet
        this.hexes = new ArrayList<>();
        List<HexType> hextypes = new ArrayList<HexType>( tileTypes );
        for ( HexLocation hexLoc : tileLocations ) {
            HexType addType;
            if ( randomTiles ) {
                addType = (HexType) removeRandomEntryFrom( hextypes );
            } else {
                addType = defaultTiles.get( hexLoc );
            }
            // Sets the resource type as NULL for desert, which is fine, it's handled
            // inside Hex.getHexType()
            this.hexes.add( new Hex(hexLoc, addType.getResourceType(), 0));
            
            if(addType == HexType.DESERT)
            {
                this.robber = hexLoc;
            }
        }
    }
       
    private void initPorts( boolean randomPorts ) {
        
        // Initialize all the hexes with the number 0 & no ports yet
        this.ports = new ArrayList<>();
        List<PortType> porttypes = new ArrayList<>( portTypes );
        for ( HexLocation hexLoc : portLocations ) {
            PortType addType;
            if ( randomPorts ) {
                addType = (PortType) removeRandomEntryFrom( porttypes );
            } else {
                addType = defaultPorts.get( hexLoc );
            }
            this.ports.add( new Port( addType, portDirections.get( hexLoc ), hexLoc ));
        }
    }
    
    private void initNumbers( boolean randomNumbers ) {
        // make a temp copy of all the numbers
        List<Integer> tempNums = new ArrayList<>( numbers );
        
        Hex desert= null;
        
        for ( Hex h : this.hexes ) {
        	if ( h.getHexType() == HexType.DESERT ) {
        		desert = h;
        		break;
        	}
        }
        
        for ( Hex h : this.hexes ) {
            int theNum = 0;
            
            // Desert doesn't have a number
            if ( h.getHexType() == HexType.DESERT )
                continue; // skip it
            
            if ( randomNumbers ) {
                theNum = (Integer) removeRandomEntryFrom( tempNums );
            } else {
            	////System.out.println(h.getLocation().getX() + "," + h.getLocation().getY());
            	
            	if (h.getLocation() == new HexLocation(0,-2)) { //if in default desert location
            		theNum = defaultNumbers.get(desert.getLocation());
            	} else {
            		theNum = defaultNumbers.get( h.getLocation() );
            	}
            	
                
            }
            h.setNumber( theNum );
        }
    }
    
    public Hex getHexAt( HexLocation hexLoc ) {
        // It's inefficient, but hey it works for now ... loop through all the hexes & return
        // the one that matches the hexlocation given
        for ( Hex h : this.hexes ) {
            if ( h.getLocation().equals( hexLoc ) )
                return h;
        }
        return null;
    }
    
    public Port getPortAt( HexLocation hexLoc ) {
        // It's inefficient, but hey it works for now ... loop through all the hexes & return
        // the one that matches the hexlocation given
        for ( Port p : this.ports ) {
            if ( p.getEdgeLocation().getHexLoc().equals( hexLoc ) )
                return p;
        }
        return null;
    }
    
    private Object removeRandomEntryFrom( List l ) {
        int randomNum = rand.nextInt(l.size());
        Object ret = l.get( randomNum );
        l.remove( randomNum );
        return ret;
    }

    public CatanMap(List<Hex> hexes, List<Port> ports, List<Road> roads, List<Settlement> settlements,
			List<City> cities, int radius, HexLocation robber) {
		this();
		this.hexes = hexes;
		this.ports = ports;
		this.roads = roads;
		this.settlements = settlements;
		this.cities = cities;
		this.radius = radius;
		this.robber = robber;
	}
    
    /**
     * add road to roads list
     * @param road to be placed at this locations
     */
    public void placeRoadAtLocation(Road road) {
    	roads.add(road);
    }
 
    public int neededToOfferMaritimeTrade(int playerIndex, ResourceType resourceType) {
        //create list of all vertex objects
        ArrayList<VertexObject> allBuildings = new ArrayList(settlements);
        allBuildings.addAll(cities);
        
        //check for player vertex objects on port edges
        ArrayList<VertexLocation> verticiesOnResourcePort = getResourcePortVerticies(resourceType);
        ArrayList<VertexLocation> verticiesOnGeneralPorts = getGeneralPortVerticies();
        
        ArrayList<VertexObject> resourcePortBuildings = new ArrayList<VertexObject>();
        ArrayList<VertexObject> generalPortBuildings = new ArrayList<VertexObject>();
        
        for(VertexObject building : allBuildings) {
            if(building.getOwner() == playerIndex) {
                if(verticiesOnResourcePort.contains(building.getLocation().getNormalizedLocation())) {
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
        else if(!generalPortBuildings.isEmpty()) {
            return 3;
        }
        else {
            return 4;
        }
    }
    
    private ArrayList<VertexLocation> getResourcePortVerticies(ResourceType resourceType) {
        ArrayList<VertexLocation> list = new ArrayList<VertexLocation>();
        
        for(Port port: ports) {
            
            EdgeLocation portEdge = port.getEdgeLocation();
            EdgeDirection portEdgeDirection = portEdge.getDir();
            HexLocation portLocation = portEdge.getHexLoc();
            
            if(port.getRatio() == 2 && port.getPortType().toString().equals(resourceType.toString())) {
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
    
    public boolean canRobPlayer(int playerIndex, HexLocation hexLoc) {
        //get rob-able locations
        ArrayList<VertexLocation> validRobbingLocations = getValidNormalizedVertexObjectLocations(hexLoc);
        
        //create list of all vertex objects
        ArrayList<VertexObject> allBuildings = new ArrayList<VertexObject>(settlements);
        allBuildings.addAll(cities);
        
        for(VertexObject building : allBuildings) {
            if(validRobbingLocations.contains(building.getLocation().getNormalizedLocation()) && building.getOwner() == playerIndex) {
                return true;
            }
        }

        return false;
    }
            
    public ArrayList<VertexLocation> getValidNormalizedVertexObjectLocations(HexLocation hexLoc) {
        ArrayList<VertexLocation> list = new ArrayList<VertexLocation>();
        
        list.add(new VertexLocation(hexLoc, VertexDirection.NorthEast));
        list.add(new VertexLocation(hexLoc, VertexDirection.NorthWest));
        list.add(new VertexLocation(hexLoc, VertexDirection.West).getNormalizedLocation());
        list.add(new VertexLocation(hexLoc, VertexDirection.SouthWest).getNormalizedLocation());
        list.add(new VertexLocation(hexLoc, VertexDirection.SouthEast).getNormalizedLocation());
        list.add(new VertexLocation(hexLoc, VertexDirection.East).getNormalizedLocation());
        
        return list;

    }
    
    
    /**
     * 
     * @param rolledNumber - dice value if not == 7
     */
    public void distributeResources(int rolledNumber)
    {
    	
    }

    
    public List<Hex> getHexes() {
        return hexes;
    }

    public void setHexes(ArrayList<Hex> hexes) {
        this.hexes = hexes;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<Port> ports) {
        this.ports = ports;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public void setSettlements(ArrayList<Settlement> settlements) {
        this.settlements = settlements;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
    
    public List<VertexObject> getCitiesAndSettlements() {
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
        final CatanMap other = (CatanMap) obj;
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

    public void addCity(City city) {
        settlements.remove( indexOfSettlementAt( city.getLocation() ) );
        cities.add(city);
    }

    public void addRoad(Road road) {
        roads.add(road);
    }

    public void addSettlement(Settlement settlement) {
        settlements.add(settlement);
    }

    public void makeCityAt(VertexLocation vertexLocation, int ownder ) {
        // Remove settlement at that location & add city there
        
        cities.add( new City(ownder, vertexLocation));
    }
    
    public int indexOfSettlementAt( VertexLocation loc ) {
        VertexLocation normal = loc.getNormalizedLocation();
        for ( int i = 0; i < settlements.size(); i++ ) {
            if ( settlements.get(i).getLocation().getNormalizedLocation().equals( normal ) ) {
                return i;
            }
        }
        return -1;
    }
    
}
