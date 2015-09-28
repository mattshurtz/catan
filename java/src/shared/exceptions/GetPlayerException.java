package shared.exceptions;

/**
 * Thrown within the Model class when an invalid player is requested via Model.getPlayer(index)
 * @author Alex
 *
 */
@SuppressWarnings("serial")
public class GetPlayerException extends Exception {
	private String message;
	
	public GetPlayerException() {}
	public GetPlayerException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
