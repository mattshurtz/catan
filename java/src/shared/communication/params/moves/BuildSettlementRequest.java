/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.communication.params.moves;

import shared.locations.VertexLocation;

/**
 *BuildSettlement {
 *type (buildSettlement),
 *playerIndex (integer): Who's placing the settlement,
 *vertexLocation (VertexLocation),
 *free (Boolean): Whether this is placed for free (setup)
 *}
 *VertexLocation {
 *x (integer),
 *y (integer),
 *direction (string) = ['SW' or 'SE' or 'E' or 'NE' or 'NW' or 'W']
 *}
 */
public class BuildSettlementRequest extends MoveRequest {
    private VertexLocation vertexLocation;
    private boolean free;

    public BuildSettlementRequest(VertexLocation location, boolean free) {
        this.vertexLocation = location;
        this.free = free;
    }
    
    public VertexLocation getVertexLocation() {
        return vertexLocation;
    }

    public void setVertexLocation(VertexLocation vertexLocation) {
        this.vertexLocation = vertexLocation;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
    
}
