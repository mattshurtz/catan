package shared.locations;

import com.google.gson.annotations.SerializedName;

public enum VertexDirection
{
	@SerializedName("W")
    West, 
    
    @SerializedName("NW")
    NorthWest, 
    
    @SerializedName("NE")
    NorthEast, 
    
    @SerializedName("E")
    East, 
    
    @SerializedName("SE")
    SouthEast, 
    
    @SerializedName("SW")
    SouthWest;
	
	private VertexDirection opposite;
	
	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}
    
    public String toString() {
        switch (this) {
            case West:
                return "W";
            case East:
                return "E";
            case NorthEast:
                return "NE";
            case NorthWest:
                return "NW";
            case SouthEast:
                return "SE";
            case SouthWest:
                return "SW";
        }
        return null;
    }
}

