package client.points;

import client.base.*;
import client.facade.CatanFacade;
import client.main.Catan;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.exceptions.GetPlayerException;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, Observer {

	private IGameFinishedView finishedView;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView) {
		
		super(view);
		CatanFacade.addObserver(this);
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
                        getPointsView().setPoints(amount);
		}
	
	/**
	 * getPointsView().setPoints( the amount of points for this player in the model);
	 */
	private void initFromModel() {
            try {
                if(CatanFacade.getModel() != null)
                {
                   setPoints(CatanFacade.getModel().getPlayer(CatanFacade.getMyPlayerIndex()).getVictoryPoints());
                }

            }catch (GetPlayerException ex) {
                Logger.getLogger(PointsController.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
    
    private void declareWinner(){
        if(CatanFacade.getModel().getWinner()>-1 && CatanFacade.getModel().getWinner()<4 && CatanFacade.isOver()==false){
            if(CatanFacade.getModel().getWinner()==CatanFacade.getMyPlayerIndex()){
                getFinishedView().setWinner(CatanFacade.getModel().getPlayers().get(CatanFacade.getModel().getWinner()).getName(), true);
            }else{
                getFinishedView().setWinner(CatanFacade.getModel().getPlayers().get(CatanFacade.getModel().getWinner()).getName(), false);
            }
            getFinishedView().showModal();
        }
        
    }

    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        declareWinner();
        
        if(CatanFacade.isOver()){
        	//get rid of the current frame
        	Catan.currentFrame.dispose();
        	//restart game
        	Catan.main(new String[] { "8081", "localhost" } );
        }
    
    }
	
}

