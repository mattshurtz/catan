/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.locations.VertexLocation;

/**
 * string type "buildCity"
 * int playerIndex
 * VertexLocation location
 *      int x
 *      int y
 *      String direction
 */
public class BuildCityRequest {
    private VertexLocation vertexLocation;

    public VertexLocation getVertexLocation() {
        return vertexLocation;
    }

    public void setVertexLocation(VertexLocation vertexLocation) {
        this.vertexLocation = vertexLocation;
    }
    
}