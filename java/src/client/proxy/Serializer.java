/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 */
public class Serializer {
    
    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();
    
    /**
     * @param o The Java object to be sent to the server. 
     * @return the json representation of package class
     */
    public String toJson(Object o){
        String json = gson.toJson(o);
        System.out.println( "Returning json: " + json );
        return json;
    }
    
    
    
    
}
