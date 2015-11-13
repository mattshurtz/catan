package shared.definitions;

public enum HexType
{
	WOOD, BRICK, SHEEP, WHEAT, ORE, DESERT, WATER;
    
    public ResourceType getResourceType() {
        switch (this) {
            case WOOD:
                return ResourceType.WOOD;
            case BRICK:
                return ResourceType.BRICK;
            case SHEEP:
                return ResourceType.SHEEP;
            case WHEAT:
                return ResourceType.WHEAT;
            case ORE:
                return ResourceType.ORE;
            default:
                return null;
        }
    }
}

