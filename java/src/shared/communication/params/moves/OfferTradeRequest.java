/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.model.ResourceList;

/**
 * OfferTrade { type (offerTrade), playerIndex (integer): Who's sending the
 * offer, offer (ResourceList): What you get (+) and what you give (-), receiver
 * (integer): Who you're offering the trade to (0-3) } ResourceList { brick
 * (integer), ore (integer), sheep (integer), wheat (integer), wood (integer) }
 */
public class OfferTradeRequest {
    private ResourceList offer;
    private int receiver;

    public ResourceList getOffer() {
        return offer;
    }

    public void setOffer(ResourceList offer) {
        this.offer = offer;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }
    
    
}
