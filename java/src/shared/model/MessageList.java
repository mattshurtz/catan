/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author karahartley
 */
public class MessageList {
    private ArrayList<MessageLine> lines;

    public MessageList(ArrayList<MessageLine> lines) {
            this.setLines(lines);
    }

    public MessageList() {
        lines = new ArrayList<MessageLine>();
            // TODO Auto-generated constructor stub
    }

    public ArrayList<MessageLine> getLines() {
            return lines;
    }

    public void setLines(ArrayList<MessageLine> lines) {
            this.lines = lines;
    }
    
    public void addLine(MessageLine line) {
    	lines.add(line);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.lines);
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
        final MessageList other = (MessageList) obj;
        if (!Objects.equals(this.lines, other.lines)) {
            return false;
        }
        return true;
    }
}
