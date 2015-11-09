/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.facade;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import server.commands.*;

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
     */
    @Override
    public String doFunction(String command, String content, String gameId) {
    	Class<?> c;
    	Method method;
		try {
			c = Class.forName("server.commands." + command);
			method = c.getDeclaredMethod ("execute", new Class[] {String.class, String.class});
			Object instance = c.newInstance();
			Object value = method.invoke (instance, content, gameId);
			return (String) value;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    	
    }
    
}
