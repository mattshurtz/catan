/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

/**
 *
 * @author karahartley
 */
public class MessageLine {
        String message;
        String source; //TODO: Is this just the name of the player that sent it??  JSON documentation didn't specify.
    public MessageLine() {}
    public MessageLine(String source, String message) {
    	this.source = source;
    	this.message = message;
    }
    
    
    
}
