package shared.communication.params;

public class PluginData {
	private String path;
	private String name;
	
	public PluginData() {
		this.path = null;
		this.name = null;
	}
	
	public PluginData(String path, String name) {
		this.path = path;
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
