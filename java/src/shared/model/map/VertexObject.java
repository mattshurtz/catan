/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import shared.locations.VertexLocation;

/**
 *
 */
public abstract class VertexObject {
   int owner;
   VertexLocation location;  
   public abstract void receiveNewResources(); 
}
