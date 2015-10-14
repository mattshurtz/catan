package client.roll;

import client.base.*;
import client.facade.CatanFacade;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.exceptions.ServerException;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

	private IRollResultView resultView;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView) {
		super(view);
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
	
	@Override
	public void rollDice() {
            try {
                getResultView().setRollValue(CatanFacade.getCurrentState().rollNumber());
            } catch (ServerException ex) {
                Logger.getLogger(RollController.class.getName()).log(Level.SEVERE, null, ex);
            }
            getResultView().showModal();
	}

}

