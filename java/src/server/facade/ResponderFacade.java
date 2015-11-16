/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.facade;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import server.commands.*;
import shared.exceptions.HTTPBadRequest;

/**
     * Create an appropriate Command object, by converting the string using the class.forName function,
     * for the request, and call execute on it.
     * This command function will perform the logic for the operation. 
     */
public class ResponderFacade implements IServerFacade {

    /**
     * Create an appropriate Command object, by converting the string using the class.forName function,
     * for the request, and call execute on it.
     * This command function will perform the logic for the operation. 
     * @throws Throwable 
     */
    @Override
    public String doFunction(String command, String content, String gameId, String user) throws HTTPBadRequest {
    	Class<?> c;
    	Method method;
		try {
			c = Class.forName("server.commands." + command);
			method = c.getDeclaredMethod ("execute", new Class[] {String.class, String.class, String.class});
			Object instance = c.newInstance();
			
			if(!command.equals("game.model")) {
				System.out.println("ResponderFacade - Calling command " + command + "...");
			}
						
			Object value = method.invoke (instance, content, gameId, user);
			
			if(!command.equals("game.model")) {
				System.out.println("ResponderFacade - Recieved string: " + (String) value);
			}
			
			
			return (String) value;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new HTTPBadRequest("Command not found");
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//throw new HTTPBadRequest("Bad arguments");
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			if (e.getTargetException().getClass() == HTTPBadRequest.class) {
				throw (HTTPBadRequest) e.getTargetException();
			} else {
				e.getTargetException().printStackTrace();
			}			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    }
    
}
