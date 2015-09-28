/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + Objects.hashCode(this.source);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MessageLine other = (MessageLine) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.source, other.source)) {
            return false;
        }
        return true;
    }
    
    
    
}
