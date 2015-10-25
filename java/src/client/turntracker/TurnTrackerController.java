package client.turntracker;

import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.Player;
import client.base.*;
import client.facade.CatanFacade;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.util.Pair;
import shared.definitions.TurnStatus;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {
    
    private boolean initializedPlayers = false;
    
    private static Map<TurnStatus, Pair<String, Boolean> > states;
    
    static {
        states = new HashMap<>();
        states.put( TurnStatus.ROLLING, new Pair<>( "Roll the Dice", false ) );
        states.put( TurnStatus.PLAYING, new Pair<>( "Finish Turn", true ) );
        states.put( TurnStatus.FIRST_ROUND, new Pair<>( "Setup", false ) );
        states.put(TurnStatus.SECOND_ROUND, new Pair<>( "Setup", false ) );
        states.put( TurnStatus.ROBBING, new Pair<>( "Place the Robber", false ) );
        states.put( TurnStatus.DISCARDING, new Pair<>( "Discard Cards", false ) );
    }

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		CatanFacade.addObserver(this);
        
		initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	
	/**
	 * verify you can end turn
	 * send server request to end turn,
	 * setGameStateButton("waiting for other player", false);
	 */
	@Override
	public void endTurn() {
		if (CatanFacade.getCurrentState().canFinishTurn()) {
			try {
				CatanFacade.getCurrentState().finishTurn();
			} catch (ServerException e) {
				e.printStackTrace();
			}
			getView().updateGameState("Waiting for other players", false);
		}
	}
	
	/**
	 * getView().setLocalPlayerColor( your Player's color);
	 * call getView().updatePlayer(...) to update the player's achievements and score.
	 */
	private void initFromModel() {
		if( CatanFacade.getModel() == null || CatanFacade.isWaitingForPlayers() )
			return;
        
        if ( !initializedPlayers ) {
            for ( Player p : CatanFacade.getModel().getPlayers() ) {
                if ( p == null )
                    continue;
                getView().initializePlayer(p.getPlayerIndex(), p.getName(), p.getColor());
            }
            initializedPlayers = true;
        }
		
				
			getView().setLocalPlayerColor(CatanFacade.getMyPlayerInfo().getColor());
            
            for ( Player p : CatanFacade.getModel().getPlayers() ) {
                boolean highlight = ( p.getPlayerIndex() == CatanFacade.getModel().getTurnTracker().getCurrentTurn() );
                boolean largestArmy = CatanFacade.getModel().getTurnTracker().getLargestArmy() == p.getPlayerIndex();
                boolean longestRoad = CatanFacade.getModel().getTurnTracker().getLongestRoad() == p.getPlayerIndex();
                
                getView().updatePlayer( p.getPlayerIndex(), p.getVictoryPoints(), highlight, largestArmy, longestRoad);
            }

	}

    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
        
        // set the text & enabled of the game state button
        try {
            TurnStatus currStatus = null;
            if ( ! CatanFacade.isMyTurn() || CatanFacade.isWaitingForPlayers() ) {
                getView().updateGameState( "Waiting for other players", false );
            }
            else {
                // Get current turn status and update game state button
                currStatus = CatanFacade.getModel().getTurnTracker().getStatus();
                Pair<String, Boolean> msgEnabled = states.get( currStatus );
                getView().updateGameState(msgEnabled.getKey(), msgEnabled.getValue());
            }
            
            // Do current state actions, whatever it is
            doStateActions( currStatus );
            
        } catch (NullPointerException e ) {
            // do nothing, it's FINE
        }
    }
    
    private void doStateActions( TurnStatus currStatus ) {
        int playerIndex = CatanFacade.getMyPlayerIndex();
        
        if ( CatanFacade.isMyTurn() ) {
            
        }
    }
}

