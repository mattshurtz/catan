package client.roll;

import client.base.*;
import client.facade.CatanFacade;
import java.util.Observable;
import java.util.Observer;
import shared.exceptions.ServerException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, Observer {

	private IRollResultView resultView;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
        CatanFacade.addObserver(this);
		
		setResultView(resultView);
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	
	/**
	 *Gets called by the view when the roll dice button is pressed
	 *To Do (not necessarily in this class):
	 *close the rollView
	 *Generate Dice results
	 *Send Roll Results to server
	 *set Result view value to roll value
	 *getResultView.showModel()
	 *
	 */
	@Override
	public void rollDice() {		
//        getRollView().closeModal();
        try {
            getResultView().setRollValue(CatanFacade.getCurrentState().rollNumber());
        } catch (ServerException ex) {
            Logger.getLogger(RollController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getResultView().showModal();

	}

    @Override
    public void update(Observable o, Object arg) {
        if ( CatanFacade.isRolling() )
            getRollView().showModal();
    }

}

