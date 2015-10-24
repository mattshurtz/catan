package client.points;

import client.base.*;
import client.facade.CatanFacade;
import shared.exceptions.GetPlayerException;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController {

	private IGameFinishedView finishedView;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView) {
		
		super(view);
		
		setFinishedView(finishedView);
		
		initFromModel();
	}
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	/**
	 * getPointsView().setPoints( the amount );
	 */
	
	public void setPoints(int amount)
		{
			try {
				getPointsView().setPoints(CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getVictoryPoints());
			} catch (GetPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	/**
	 * getPointsView().setPoints( the amount of points for this player in the model);
	 */
	private void initFromModel() {
		//<temp>		
		
		//</temp>
	}
	
}

