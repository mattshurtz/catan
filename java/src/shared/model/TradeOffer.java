/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

import java.io.Serializable;
import java.util.Objects;
import shared.definitions.ResourceType;

/**
*sender (integer): The index of the person offering the trade,
*receiver (integer): The index of the person the trade was offered to.,
*offer (ResourceList): Positive numbers are resources being offered. Negative are resources
*being asked for.
*/
public class TradeOffer implements Serializable{
    private int sender;
    private int receiver;
    private ResourceList offer;
    
    public TradeOffer(int sender, int receiver, ResourceList offer) {
            this.setSender(sender);
            this.setReceiver(receiver);
            this.setOffer(offer);
    }

   public ResourceList getSendResources(){
       ResourceList sendResources = new ResourceList();
       if(offer.getBrick()>0){
           sendResources.addResource(ResourceType.BRICK, offer.getBrick());
       }
       if(offer.getWheat()>0){
           sendResources.addResource(ResourceType.WHEAT, offer.getWheat());
           
       }
       if(offer.getOre()>0){
           sendResources.addResource(ResourceType.ORE, offer.getOre());
       }
       if(offer.getWood()>0){
           sendResources.addResource(ResourceType.WOOD, offer.getWood());
           
       }
       if(offer.getSheep()>0){
           sendResources.addResource(ResourceType.SHEEP, offer.getSheep());  
       }  
       return sendResources;
   } 
   
   public ResourceList getReceiveResources(){
       ResourceList receiveResources = new ResourceList();
       if(offer.getBrick()<0){
           receiveResources.addResource(ResourceType.BRICK, Math.abs(offer.getBrick()));
       }
       if(offer.getWheat()<0){
           receiveResources.addResource(ResourceType.WHEAT, Math.abs(offer.getWheat()));
           
       }
       if(offer.getOre()<0){
           receiveResources.addResource(ResourceType.ORE, Math.abs(offer.getOre()));
       }
       if(offer.getWood()<0){
           receiveResources.addResource(ResourceType.WOOD, Math.abs(offer.getWood()));
           
       }
       if(offer.getSheep()<0){
           receiveResources.addResource(ResourceType.SHEEP, Math.abs(offer.getSheep()));  
       }  
       return receiveResources;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.sender;
        hash = 29 * hash + this.receiver;
        hash = 29 * hash + Objects.hashCode(this.offer);
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
        final TradeOffer other = (TradeOffer) obj;
        if (this.sender != other.sender) {
            return false;
        }
        if (this.receiver != other.receiver) {
            return false;
        }
        if (!Objects.equals(this.offer, other.offer)) {
            return false;
        }
        return true;
    }
    
    
}
