package shared.communication.params.moves;

import shared.locations.EdgeLocation;

/**
 * Road_Building_ { type (Road_Building), playerIndex (integer): Who's placing
 * the roads, spot1 (EdgeLocation), spot2 (EdgeLocation) } EdgeLocation { x
 * (integer), y (integer), direction (string) = ['SW' or 'SE' or 'S' or 'NE' or
 * 'NW' or 'N'] }
 */
public class PlayRoadBuildingRequest {
    private EdgeLocation spot1;
    private EdgeLocation spot2;

    public EdgeLocation getSpot1() {
        return spot1;
    }

    public void setSpot1(EdgeLocation spot1) {
        this.spot1 = spot1;
    }

    public EdgeLocation getSpot2() {
        return spot2;
    }

    public void setSpot2(EdgeLocation spot2) {
        this.spot2 = spot2;
    }
}
