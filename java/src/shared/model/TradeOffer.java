/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

/**
*sender (integer): The index of the person offering the trade,
*receiver (integer): The index of the person the trade was offered to.,
*offer (ResourceList): Positive numbers are resources being offered. Negative are resources
*being asked for.
*/
public class TradeOffer {
    private int sender;
    private int receiver;
    private ResourceList offer;
    
	public TradeOffer(int sender, int receiver, ResourceList offer) {
		this.setSender(sender);
		this.setReceiver(receiver);
		this.setOffer(offer);
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public ResourceList getOffer() {
		return offer;
	}

	public void setOffer(ResourceList offer) {
		this.offer = offer;
	}
    
    
}
