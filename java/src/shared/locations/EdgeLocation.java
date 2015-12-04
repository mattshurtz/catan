package shared.locations;

import java.io.Serializable;

/**
 * Represents the location of an edge on a hex map
 */
public class EdgeLocation implements Serializable
{
	private int x, y;
	private EdgeDirection direction;
	
	public EdgeLocation(HexLocation hexLoc, EdgeDirection dir)
	{
		setHexLoc(hexLoc);
		setDir(dir);
	}
	
	public HexLocation getHexLoc()
	{
            return new HexLocation(x, y);
        
	}
	
	private void setHexLoc(HexLocation hexLoc)
	{
		if(hexLoc == null)
		{
			throw new IllegalArgumentException("hexLoc cannot be null");
		}
		x = hexLoc.getX();
        y = hexLoc.getY();
	}
	
	public EdgeDirection getDir()
	{
		return direction;
	}
	
	private void setDir(EdgeDirection dir)
	{
		this.direction = dir;
	}
	
	@Override
	public String toString()
	{
		return "EdgeLocation [hexLoc=" + getHexLoc() + ", dir=" + direction + "]";
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		return result;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EdgeLocation other = (EdgeLocation) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.direction != other.direction) {
            return false;
        }
        return true;
    }
	

	
	/**
	 * Returns a canonical (i.e., unique) value for this edge location. Since
	 * each edge has two different locations on a map, this method converts a
	 * hex location to a single canonical form. This is useful for using hex
	 * locations as map keys.
	 * 
	 * @return Normalized hex location
	 */
	public EdgeLocation getNormalizedLocation()
	{
        // Loads hex location from x & y if null
		HexLocation hexLoc = getHexLoc();
        
		// Return an EdgeLocation that has direction NW, N, or NE
		switch (direction)
		{
			case NorthWest:
			case North:
			case NorthEast:
				return this;
			case SouthWest:
			case South:
			case SouthEast:
				return new EdgeLocation(hexLoc.getNeighborLoc(direction),
                                    direction.getOppositeDirection());
			default:
				assert false;
				return null;
		}
	}
}

