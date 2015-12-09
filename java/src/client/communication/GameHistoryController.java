package client.communication;

import java.util.*;
import java.util.List;

import client.base.*;
import client.facade.CatanFacade;
import shared.definitions.*;
import shared.model.MessageLine;
import shared.model.MessageList;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, Observer {

	public GameHistoryController(IGameHistoryView view) {
		
		super(view);
        
        CatanFacade.addObserver( this );
		
		initFromModel();
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
        if ( CatanFacade.getModel() == null )
            return;
        
		List<LogEntry> entries = new ArrayList<LogEntry>();
		MessageList msgs = CatanFacade.getModel().getLog();
        for ( MessageLine msg : msgs.getLines() ) {
            CatanColor theColor = CatanFacade.getColorFromPlayerName( msg.getSource() );
            
    		if ( theColor != null )
                entries.add(new LogEntry( theColor, msg.getMessage() ));
            else
                System.err.println("Error while getting color from player name!");
        }
		
        getView().setEntries(entries);
	}

    @Override
    public void update(Observable o, Object arg) {
        initFromModel();
    }
	
}

