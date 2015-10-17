package shared.definitions;

import com.google.gson.annotations.SerializedName;

public enum TurnStatus {
	//'Rolling' or 'Robbing' or 'Playing' or 'Discarding' or 'FirstRound' or
	//*'SecondRound'
	
	@SerializedName("Rolling")
    ROLLING, 
    
    @SerializedName("Robbing")
    ROBBING, 
    
    @SerializedName("Playing")
    PLAYING, 
    
    @SerializedName("Discarding")
    DISCARDING, 
    
    @SerializedName("FirstRound")
    FIRST_ROUND, 
    
    @SerializedName("SecondRound")
    SECOUND_ROUND
}
