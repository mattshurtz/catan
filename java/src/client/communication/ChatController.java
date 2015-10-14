package client.communication;

import client.base.*;
import client.facade.CatanFacade;
import shared.exceptions.ServerException;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

	public ChatController(IChatView view) {
		
		super(view);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		try {
			CatanFacade.getCurrentState().sendChat(message);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

