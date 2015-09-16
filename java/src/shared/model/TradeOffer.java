/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.model;

/**
*sender (integer): The index of the person offering the trade,
*receiver (integer): The index of the person the trade was offered to.,
*offer (ResourceList): Positive numbers are resources being offered. Negative are resources
*being asked for.
*/
public class TradeOffer {
    int sender;
    int receiver;
    ResourceList offer;
}
