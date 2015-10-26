package client.login;

import client.base.*;
import client.facade.CatanFacade;
import client.misc.*;

import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView) {

		super(view);
		
		this.messageView = messageView;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	
	/**
	 * get username and password from the view
	 * Send verification request to server
	 * if it works:
	 * Create any data objects you need for a player in the pre-game state
	 * close theModal, loginAction.execute
	 * else
	 * show a dialogue to reprompt user for info
	 */
	@Override
	public void signIn() {
        String username = getLoginView().getLoginUsername();
        String pass = getLoginView().getLoginPassword();
        boolean success = CatanFacade.getGameHubFacade().login( username, pass );
        
        if ( success ) {
            System.out.println("logged in!");
            // If log in succeeded
            getLoginView().closeModal();
            loginAction.execute();
        } else {
            // didn't work
            MessageView error = new MessageView();
            error.setTitle("You're so in the wrong");
            error.setMessage("Invalid username or password. Try registering. Dope.");
            error.showModal();
        }
	}

        public boolean validatePass(String input)
        {
            final int MIN_PASS_LENGTH = 5;

            if (input.length() < MIN_PASS_LENGTH)
            {
                return false;
            }
            else
            {
                for (char c : input.toCharArray())
                {
                    if (!Character.isLetterOrDigit(c)
                            && c != '_' && c != '-')
                    {
                        return false;
                    }
                }
            }

            return true;
        }

	/**
	 * pretty much the same as above but with different error message
	 */
	@Override
	public void register() {
		
    	String username = getLoginView().getRegisterUsername();
        String pass = getLoginView().getRegisterPassword();
        
        //more than 3 characters or less than 7 unsername
        if (username.length() < 3 || username.length() > 7){
        	MessageView error = new MessageView();
            error.setTitle("Warning!");
            error.setMessage("Username should be greater than 2 characters and less than 8.");

            error.showModal();
        	
        	return;
        }
        
        //password less than 5
        if (pass.length() < 5 || !validatePass(pass)) {
        	MessageView error = new MessageView();
            error.setTitle("Warning!");
            error.setMessage("Password can only contains alphanumerics, underscores, and hyphens");

            error.showModal();
        	
        	return;
        }
        
        //check if password matches
        if(!pass.equals(getLoginView().getRegisterPasswordRepeat())) {
        	MessageView error = new MessageView();
            error.setTitle("Warning!");
            error.setMessage("Password did not match.");

            error.showModal();
        	
        	return;
        }
        	
        boolean success = CatanFacade.getGameHubFacade().register( username, pass );
        
        if ( success ) {
            System.out.println("registered!");
            // If register succeeded
            getLoginView().closeModal();
            loginAction.execute();
        } else {
            MessageView error = new MessageView();
            error.setTitle("Warning!");
            error.setMessage("Invalid username or password.");

            error.showModal();
        }
	}

}

