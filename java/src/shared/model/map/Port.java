/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import shared.locations.EdgeDirection;
import shared.locations.HexLocation;

/**
*resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What type
*resource this port trades for. If it's omitted, then it's for any resource.,
*location (HexLocation): Which hex this port is on. This shows the (ocean/non-existent) hex to
*draw the port on.,
*direction (string) = ['NW' or 'N' or 'NE' or 'E' or 'SE' or 'SW']: Which edge this port is on.,
*ratio (integer): The ratio for trade in (ie, if this is 2, then it's a 2:1 port.
*/
public class Port {
   String resource;
   HexLocation location;
   EdgeDirection direction;
   int ratio;
}
