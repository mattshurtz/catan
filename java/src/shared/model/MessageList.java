/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.util.ArrayList;

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
		// TODO Auto-generated constructor stub
	}

	public ArrayList<MessageLine> getLines() {
		return lines;
	}

	public void setLines(ArrayList<MessageLine> lines) {
		this.lines = lines;
	}
	
	


    
      
}
