/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.definitions.ResourceType;

/**
 * MaritimeTrade { type (maritimeTrade), playerIndex (integer): Who's doing the
 * trading, ratio (integer, optional): The ratio of the trade your doing as an
 * integer (ie. put 3 for a 3:1 trade), inputResource (String, optional): What
 * type of resource you're giving., outputResource (String, optional): What type
 * of resource you're getting. }
 */
public class MaritimeTradeRequest extends MoveRequest {
    private int ratio;
    private ResourceType inputResource;
    private ResourceType outputResource;

    public MaritimeTradeRequest(int playerIndex, int ratio, ResourceType inputResource, ResourceType ouput) {
        this.ratio = ratio;
        this.playerIndex = playerIndex;
        this.inputResource = inputResource;
        this.outputResource = ouput;
    }
    
    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public ResourceType getInputResource() {
        return inputResource;
    }

    public void setInputResource(ResourceType inputResource) {
        this.inputResource = inputResource;
    }

    public ResourceType getOutputResource() {
        return outputResource;
    }

    public void setOutputResource(ResourceType outputResource) {
        this.outputResource = outputResource;
    }
    
    
}
