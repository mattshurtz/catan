/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.commands;

import shared.communication.params.moves.BuildRoadRequest;

/**
 *
 */
public class buildRoad extends Command {
    
    @Override
    public void execute(String json) {
        BuildRoadRequest request = this.getDeserializer().toBuildRoadRequest(json);
        this.getModel().buildRoad(request.getRoadLocation());
    }
    
}
