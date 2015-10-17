package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import shared.model.Model;
import shared.model.map.*;
import client.base.*;
import client.data.*;
import client.facade.CatanFacade;

import java.util.logging.Level;
import java.util.logging.Logger;

import shared.exceptions.InvalidLocation;
import shared.exceptions.ServerException;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private IRobView robView;
	
	public MapController(IMapView view, IRobView robView) {
		super(view);
        CatanFacade.addObserver( this );
		
		setRobView(robView);
		
		initFromModel();
	}
	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	/**
	 * all of this is hard coded info, you need to use these same 
	 * functions and format to generate the map from your model
	 */
	
	protected void initFromModel() {
        System.out.println("Initing from model inside MapController...");
		
		//Get Model's map from CatanFacade
		Model model  = CatanFacade.getModel();
        if ( model == null )
            return;
        
		CatanMap map = model.getMap();
		
		//Add all the hexes
		ArrayList<Hex> hexList = map.getHexes();
		for (Hex hex : hexList) {
			
			//Set the hex type
			HexType hexType = hex.getHexType();
			HexLocation hexLoc = hex.getLocation();
			getView().addHex(hexLoc, hexType);
			
			//Add the number to this hex
			getView().addNumber(hexLoc, hex.getNumber());
			
			//Add the robber if this hexType is Desert
			if (hexType == HexType.DESERT) {
				getView().placeRobber(hexLoc);
			}
			
		}
			
		//If the Model has settlements or cities, add those
		ArrayList<VertexObject> vObjects = map.getCitiesAndSettlements();
		for (VertexObject vObj : vObjects) {
				CatanColor color = vObj.getColor();
				VertexLocation vertLoc = vObj.getLocation();
			if (vObj instanceof Settlement) {
				getView().placeSettlement(vertLoc, color);
			} else if (vObj instanceof City) {
				getView().placeCity(vertLoc, color);
			}
		}
		
		//If the Model has roads, add those
		ArrayList<Road> roads = map.getRoads();
		for (Road road : roads) {
			EdgeLocation edgeLoc = road.getLocation();
			CatanColor color = road.getColor();
			getView().placeRoad(edgeLoc, color);
		}
		
		//Initialize the ports
		ArrayList<Port> ports = map.getPorts();
		for (Port port : ports) {
			EdgeLocation edgeLoc = port.getEdgeLocation();
			PortType type = port.getPortType();
			getView().addPort(edgeLoc, type);
		}
		
		/* TA CODE FOR GENERATING A DEFAULT MODEL VIEW
		//<temp>
		
		Random rand = new Random();

		for (int x = 0; x <= 3; ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {				
				int r = rand.nextInt(HexType.values().length);
				HexType hexType = HexType.values()[r];
				HexLocation hexLoc = new HexLocation(x, y);
				getView().addHex(hexLoc, hexType);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
						CatanColor.RED);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
						CatanColor.BLUE);
				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
						CatanColor.ORANGE);
				getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
				getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
					int r = rand.nextInt(HexType.values().length);
					HexType hexType = HexType.values()[r];
					HexLocation hexLoc = new HexLocation(-x, y);
					getView().addHex(hexLoc, hexType);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
							CatanColor.RED);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
							CatanColor.BLUE);
					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
							CatanColor.ORANGE);
					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
				}
			}
		}
		
		PortType portType = PortType.BRICK;
		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
		
		getView().placeRobber(new HexLocation(0, 0));
		
		getView().addNumber(new HexLocation(-2, 0), 2);
		getView().addNumber(new HexLocation(-2, 1), 3);
		getView().addNumber(new HexLocation(-2, 2), 4);
		getView().addNumber(new HexLocation(-1, 0), 5);
		getView().addNumber(new HexLocation(-1, 1), 6);
		getView().addNumber(new HexLocation(1, -1), 8);
		getView().addNumber(new HexLocation(1, 0), 9);
		getView().addNumber(new HexLocation(2, -2), 10);
		getView().addNumber(new HexLocation(2, -1), 11);
		getView().addNumber(new HexLocation(2, 0), 12);
		
		//</temp>*/
	}

	
	
	
	
	//================================================================
	// For each of the following "Can" methods: call the appropriate canDo(0
	//For each "Do" send the request to the server and update all info as a result of the 
	//action (bank amounts, remaining settlements, map...)
	
	
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		if(CatanFacade.getCurrentState().canBuyRoad() && CatanFacade.getCurrentState().canBuildRoad(edgeLoc))
                {
                    return true;
                }
                return false;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {		
		if(CatanFacade.getCurrentState().canBuySettlement() && CatanFacade.getCurrentState().canBuildSettlement(vertLoc))
                {
                    return true;
                }
                return false;
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		if(CatanFacade.getCurrentState().canBuyCity() && CatanFacade.getCurrentState().canBuildCity(vertLoc))
                {
                    return true;
                }
                return false;
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		return CatanFacade.getCurrentState().canPlaceRobber(hexLoc);		
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		
		getView().placeRoad(edgeLoc, CatanColor.ORANGE);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		
		getView().placeSettlement(vertLoc, CatanColor.ORANGE);
	}

	public void placeCity(VertexLocation vertLoc) {
		
		getView().placeCity(vertLoc, CatanColor.ORANGE);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		
		getView().startDrop(pieceType, CatanColor.ORANGE, true);
	}
	
	public void cancelMove() {
		
	}
	
	public void playSoldierCard() {	
		
	}
	
	public void playRoadBuildingCard() {	
		
	}
	
	public void robPlayer(RobPlayerInfo victim) {
            try {
                CatanFacade.getCurrentState().robPlayer(victim.getPlayerIndex());
            } catch (ServerException ex) {
                Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Updating!");
        initFromModel();
    }
	
}

