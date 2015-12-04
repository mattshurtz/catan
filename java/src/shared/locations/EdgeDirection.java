package shared.locations;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public enum EdgeDirection implements Serializable
{
	@SerializedName("NW")
	NorthWest, 
    
    @SerializedName("N")
    North, 
    
    @SerializedName("NE")
    NorthEast, 
    
    @SerializedName("SE")
    SouthEast, 
    
    @SerializedName("S")
    South, 
    
    @SerializedName("SW")
    SouthWest;
	
	private EdgeDirection opposite;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
	}
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
    
    public String toString() {
        switch (this) {
            case North:
                return "N";
            case South:
                return "S";
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

