package server.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import server.persistence.factory.AbstractFactory;

public class PluginRegistry {
	private final String CONFIG_PATH = "java/src/server/persistence/config.json";
	private HashMap<String, String> pluginMap = new HashMap();
	
	public PluginRegistry()
	{
		loadFile(new File(CONFIG_PATH));
	}
	
	public AbstractFactory CreateFactory(String name)
	{
		if (this.pluginMap.containsKey(name))
		{
			//try to load
			Object newFactory = null;
			String className = this.pluginMap.get(name);
			try {
				Class<?> clazz = Class.forName(className);
				newFactory = clazz.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.exit(0);
			}
			
			return (AbstractFactory)newFactory;		
		}
		else
		{
			//log an error and abort
			System.out.println("Given plugin not supported.  Ensure that the plugin has been registered in the config file.");
			System.exit(0);
			return null;
		}
	}
	
	void loadFile(File config)
	{
		//Load the file
		try {
			FileReader in = new FileReader(config);
			Gson gson = new GsonBuilder().create();
			
			//Store map of all configurations
			this.pluginMap = gson.fromJson(in, HashMap.class);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	 

}
