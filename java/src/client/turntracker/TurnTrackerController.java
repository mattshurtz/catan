package client.turntracker;

import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.Player;
import client.base.*;
import client.facade.CatanFacade;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.util.Pair;
import shared.definitions.TurnStatus;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {
    
    private static Map<TurnStatus, Pair<String, Boolean> > states;
    
    static {
        states.put( TurnStatus.ROLLING, new Pair<>( "Roll the Dice", false ) );
        states.put( TurnStatus.PLAYING, new Pair<>( "Finish Turn", true ) );
        states.put( TurnStatus.FIRST_ROUND, new Pair<>( "Setup", false ) );
        states.put( TurnStatus.SECOUND_ROUND, new Pair<>( "Setup", false ) );
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
			getView().updateGameState("waiting for other player", false);
		}
	}
	
	/**
	 * getView().setLocalPlayerColor( your Player's color);
	 * call getView().updatePlayer(...) to update the player's acheivements and score.
	 */
	private void initFromModel() {
		if(CatanFacade.getModel()==null)
			return;
		
		
		Player player;
		try {
			player = CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex());
			boolean largestArmy = false;
			boolean longestRoad = false;
			
			if(CatanFacade.getModel().getTurnTracker().getLargestArmy() == player.getPlayerIndex())
				largestArmy = true;
			if(CatanFacade.getModel().getTurnTracker().getLongestRoad() == player.getPlayerIndex())
				longestRoad = true;
				
			getView().setLocalPlayerColor(CatanFacade.getMyPlayerInfo().getColor());
			getView().updatePlayer(CatanFacade.getMyPlayerIndex(), player.getVictoryPoints(), false, largestArmy, longestRoad);
		} catch (GetPlayerException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void update(Observable o, Object arg) {
        // Just set the text & enabled of the game state button
        try {
            if ( CatanFacade.isMyTurn() ) {
                getView().updateGameState( "Waiting for other players", false );
            }
            
            TurnStatus currStatus = CatanFacade.getModel().getTurnTracker().getStatus();
            Pair<String, Boolean> msgEnabled = states.get( currStatus );
            getView().updateGameState(msgEnabled.getKey(), msgEnabled.getValue());
        } catch (NullPointerException e ) {
            // do nothing, it's FINE
        }
    }
}

