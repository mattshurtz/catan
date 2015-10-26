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
import shared.exceptions.GetPlayerException;

import shared.exceptions.ServerException;
import shared.model.Player;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private IRobView robView;
	
	private boolean robbingModalUp = false;
	//private boolean isRobbing
	
	private HexLocation robLocation;
    
    private static HexLocation[] waterHexes = new HexLocation[] {
        new HexLocation( 0, -3 ),
        new HexLocation( 1, -3 ),
        new HexLocation( 2, -3 ),
        new HexLocation( 3, -3 ),
        new HexLocation( 3, -2 ),
        new HexLocation( 3, -1 ),
        new HexLocation( 3, 0 ),
        new HexLocation( 2, 1 ),
        new HexLocation( 1, 2 ),
        new HexLocation( 0, 3 ),
        new HexLocation( -1, 3 ),
        new HexLocation( -2, 3 ),
        new HexLocation( -3, 3 ),
        new HexLocation( -3, 2 ),
        new HexLocation( -3, 1 ),
        new HexLocation( -3, 0 ),
        new HexLocation( -2, -1 ),
        new HexLocation( -1, -2 )
    };
	
	public MapController(IMapView view, IRobView robView) {
		super(view);
        CatanFacade.addObserver( this );
		
		setRobView(robView);
		//isRobbing = false;
		
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
		//Get Model's map from CatanFacade
		Model model  = CatanFacade.getModel();
        if ( model == null )
            return;
        
		CatanMap map = model.getMap();
        if ( map == null )
            return;
		
        //Add all the hexes
		ArrayList<Hex> hexList = map.getHexes();
		for (Hex hex : hexList) {
			//Set the hex type
			HexType hexType = hex.getHexType();
			HexLocation hexLoc = hex.getLocation();
			getView().addHex(hexLoc, hexType);
			
			//Add the number to this hex
			getView().addNumber(hexLoc, hex.getNumber());
			
			/*
			//Add the robber if this hexType is Desert
			//Do not update the robber position if the robber modal is up
			if (hexType == HexType.DESERT && !isRobbing) {
				getView().placeRobber(hexLoc);
			}
			*/
		}
		
		//Place the robber
		HexLocation robLocation = CatanFacade.getModel().getMap().getRobber();
		getView().placeRobber(robLocation);
        
        for ( HexLocation waterHex : waterHexes ) {
            getView().addHex( waterHex, HexType.WATER );
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
		
		// TA CODE FOR GENERATING A DEFAULT MODEL VIEW
		//<temp>
		
//		Random rand = new Random();
//
//		for (int x = 0; x <= 3; ++x) {
//			
//			int maxY = 3 - x;			
//			for (int y = -3; y <= maxY; ++y) {				
//				int r = rand.nextInt(HexType.values().length);
//				HexType hexType = HexType.values()[r];
//				HexLocation hexLoc = new HexLocation(x, y);
//				getView().addHex(hexLoc, hexType);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//						CatanColor.RED);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//						CatanColor.BLUE);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//						CatanColor.ORANGE);
//				getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
//				getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
//			}
//			
//			if (x != 0) {
//				int minY = x - 3;
//				for (int y = minY; y <= 3; ++y) {
//					int r = rand.nextInt(HexType.values().length);
//					HexType hexType = HexType.values()[r];
//					HexLocation hexLoc = new HexLocation(-x, y);
//					getView().addHex(hexLoc, hexType);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//							CatanColor.RED);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//							CatanColor.BLUE);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//							CatanColor.ORANGE);
//					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
//					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
//				}
//			}
//		}
//		
//		PortType portType = PortType.BRICK;
//		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
//		
//		getView().placeRobber(new HexLocation(0, 0));
//		
//		getView().addNumber(new HexLocation(-2, 0), 2);
//		getView().addNumber(new HexLocation(-2, 1), 3);
//		getView().addNumber(new HexLocation(-2, 2), 4);
//		getView().addNumber(new HexLocation(-1, 0), 5);
//		getView().addNumber(new HexLocation(-1, 1), 6);
//		getView().addNumber(new HexLocation(1, -1), 8);
//		getView().addNumber(new HexLocation(1, 0), 9);
//		getView().addNumber(new HexLocation(2, -2), 10);
//		getView().addNumber(new HexLocation(2, -1), 11);
//		getView().addNumber(new HexLocation(2, 0), 12);
		
		//</temp>
	}

	
	
	
	
	//================================================================
	// For each of the following "Can" methods: call the appropriate canDo(0
	//For each "Do" send the request to the server and update all info as a result of the 
	//action (bank amounts, remaining settlements, map...)
	
	
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		if(CatanFacade.getCurrentState().canBuildRoad(edgeLoc))
                {
                    return true;
                }
                return false;

	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {		
		 return CatanFacade.getCurrentState().canBuySettlement() 
            && CatanFacade.getCurrentState().canBuildSettlement(vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		return CatanFacade.getCurrentState().canBuyCity() 
            && CatanFacade.getCurrentState().canBuildCity(vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
        System.out.println("this is the hexlocation of robber: "+hexLoc);
        System.out.println("current state: "+CatanFacade.getCurrentState().toString());
        
		return CatanFacade.getCurrentState().canPlaceRobber(hexLoc);		
	}

	public void placeRoad(EdgeLocation edgeLoc) {
        try {
            boolean free = CatanFacade.isSetup();
            CatanFacade.getCurrentState().buildRoad(edgeLoc,free);
            getView().placeRoad(edgeLoc, getMyColor());
        } catch (ServerException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            CatanFacade.triggerUpdate();
        }
	}

	public void placeSettlement(VertexLocation vertLoc) {
        try {
            CatanFacade.getCurrentState().buildSettlement(vertLoc);
    		getView().placeSettlement(vertLoc, getMyColor());
        } catch (ServerException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            CatanFacade.triggerUpdate();
        }
	}

	public void placeCity(VertexLocation vertLoc) {
        try {
            CatanFacade.getCurrentState().buildCity(vertLoc);
    		getView().placeCity(vertLoc, getMyColor());
        } catch (ServerException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            CatanFacade.triggerUpdate();
        }
	}

	public void placeRobber(HexLocation hexLoc) {
		robbingModalUp = false;
       
		//Move the robber to the rob hex
		robLocation = hexLoc;
		//isRobbing = true; //Robber position won't revert back when the poller updates the model 
		getView().placeRobber(hexLoc);
		
		//Get all the players on the Hex where robber got moved to
		ArrayList<RobPlayerInfo> playersToRob = new ArrayList<RobPlayerInfo>();
		
		int numPlayers = CatanFacade.getCurrentGamePlayers().length;
		assert(numPlayers == 4); //Assuming there are 4 players in the game
		for (int i = 0; i < numPlayers; i++) {
			//If the player is rob-able at this hex
			if (CatanFacade.getModel().canRobPlayer(i, hexLoc)) {
				//Add them to the arraylist of players to rob
				RobPlayerInfo playerToRob = CatanFacade.getModel().getRobPlayerInfo(i);
				playersToRob.add(playerToRob);
			}
		}
		
		//Convert playersToRob from ArrayList to Array
		RobPlayerInfo[] robPlayerArray = new RobPlayerInfo[playersToRob.size()];
		robPlayerArray = playersToRob.toArray(robPlayerArray);
		
		//Set the RobPlayerInfo objects in the RobView modal
		getRobView().setPlayers(robPlayerArray);
		
		//Bring up the RobView's modal
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
        // Allow to cancel if it's in normal gameplay rather than setup
		boolean isCancelAllowed = CatanFacade.isPlaying();
        
		getView().startDrop(pieceType, getMyColor(), isCancelAllowed);
	}
	
	public void cancelMove() {
		((MapView)getView()).cancelDrop();
	}
	
	public void playSoldierCard() {	
		getView().startDrop(PieceType.ROBBER, getMyColor(), false);
	}
	
	public void playRoadBuildingCard() {
        
        getView().startDrop(PieceType.ROAD, getMyColor(), true);
        getView().startDrop(PieceType.ROAD, getMyColor(), true);
        
		
	}
	
	public void robPlayer(RobPlayerInfo victim) {
        try {
            CatanFacade.getCurrentState().robPlayer(victim.getPlayerIndex(), robLocation);
            OverlayView.closeAllModals();
        } catch (ServerException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

    @Override
    public void robNoPlayer() {
        RobPlayerInfo noPlayer = new RobPlayerInfo();
        noPlayer.setPlayerIndex(-1);
        this.robPlayer( noPlayer );
    }

    @Override
    public void update(Observable o, Object arg) {
        //Initialize map from the Model
    	initFromModel();
        
        //Show the placeRobber modal if turnTracker status is "ROBBING" and if this is the current player
        if (CatanFacade.isRobbing()) {
        	if ( !robbingModalUp && CatanFacade.getModel().getTurnTracker().getCurrentTurn() == CatanFacade.getMyPlayerIndex()) {
        		// Sometimes update() gets called twice. This prevents it from calling startDrop() multiple times.
                robbingModalUp = true;
                delayedStartRobber();
        	}
        }
    }
    
    private void delayedStartRobber() {
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    getView().startDrop(PieceType.ROBBER, getMyColor(), false);
                }
            }, 
            750
        );
    }
    
    private CatanColor getMyColor() {
        Model theModel = CatanFacade.getModel();
        int mypidx = CatanFacade.getMyPlayerIndex();
        Player thePlayer = null;
        try {
            thePlayer = theModel.getPlayer(mypidx);
        } catch (GetPlayerException ex) {
            Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        CatanColor myColor = thePlayer.getColor();
        
        return myColor;
    }
	
}

