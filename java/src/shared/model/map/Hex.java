/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model.map;

import shared.locations.HexLocation;

/**
*location (HexLocation),
*resource (string, optional) = ['Wood' or 'Brick' or 'Sheep' or 'Wheat' or 'Ore']: What resource
*this tile gives - it's only here if the tile is not desert.,
*number (integer, optional): What number is on this tile. It's omitted if this is a desert hex.
*/
public class Hex {
    HexLocation location;
    String resource;
    int number;
    
}
