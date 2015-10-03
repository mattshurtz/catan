/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.model.ResourceList;

/**
 *
 * @author JanPaul
 */
public class DiscardCardsRequest extends MoveRequest {
    private ResourceList discardedCards;

    public DiscardCardsRequest(int playerIndex, ResourceList discardedCards) {
    	this.setType("discardCards");
    	this.playerIndex = playerIndex;
    	this.discardedCards = discardedCards;
    }
    
    public ResourceList getDiscardedCards() {
        return discardedCards;
    }

    public void setDiscardedCards(ResourceList discardedCards) {
        this.discardedCards = discardedCards;
    }
    
}
