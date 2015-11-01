package client.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.facade.CatanFacade;
import shared.definitions.CatanColor;
import shared.exceptions.ServerException;
import shared.model.MessageLine;
import shared.model.MessageList;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, Observer {

	public ChatController(IChatView view) {
		
		super(view);
		CatanFacade.addObserver(this);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
        try {
            CatanFacade.getCurrentState().sendChat(message);
            CatanFacade.toggleHackPlayer(message);
        } catch (ServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	private void initFromModel() {
        if ( CatanFacade.getModel() == null )
            return;
        
		List<LogEntry> entries = new ArrayList<LogEntry>();
		MessageList msgs = CatanFacade.getModel().getChat();
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

