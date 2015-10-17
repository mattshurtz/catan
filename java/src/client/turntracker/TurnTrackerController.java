package client.turntracker;

import shared.definitions.CatanColor;
import shared.exceptions.GetPlayerException;
import shared.exceptions.ServerException;
import shared.model.Player;
import client.base.*;
import client.facade.CatanFacade;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

	public TurnTrackerController(ITurnTrackerView view) {
		
		super(view);
		
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//setGameStateButton("waiting for other player", false);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

