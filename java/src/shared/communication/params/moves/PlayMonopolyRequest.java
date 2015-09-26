/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.definitions.ResourceType;

/**
 *
 * Monopoly_ { type (Monopoly), resource (string), playerIndex (integer): Who's
 * playing this dev card }
 */
public class PlayMonopolyRequest {
    private ResourceType resource;

    public ResourceType getResource() {
        return resource;
    }

    public void setResource(ResourceType resource) {
        this.resource = resource;
    }
}
