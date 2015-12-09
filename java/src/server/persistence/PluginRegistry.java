package server.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import server.persistence.factory.AbstractFactory;
import shared.json.Serializer;

public class PluginRegistry {
	private final String CONFIG_PATH = "src/server/persistence/config.json";
	private HashMap<String, PluginData> pluginMap = new HashMap<String,PluginData>();
	
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
			PluginData data = this.pluginMap.get(name);
			try {
				
				File jarFile = new File(data.getPath());
				String absPath = jarFile.getAbsolutePath();
//				if (!jarFile.isFile()){
//					throw new FileNotFoundException("Missing required JAR: " + data.getPath());
//				}
				URL jarURL = jarFile.toURI().toURL();
				
				URLClassLoader loader = new URLClassLoader(new URL[] {jarURL}, this.getClass().getClassLoader());
				Class clazz = Class.forName(data.getName(), true, loader);
				newFactory = clazz.newInstance();
				
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
			
			System.out.println(name + " plugin loaded!");
			return (AbstractFactory) newFactory;		
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
        FileReader in = null;
		try {
			in = new FileReader(config);
		} catch (FileNotFoundException e) {
			try {
                in = new FileReader("java/"+config);
            } catch ( FileNotFoundException eX ) {
                eX.printStackTrace();
            }
		}
        Gson gson = new GsonBuilder().create();
        
        //DEBUG
//        HashMap<String, PluginData> m = new HashMap<String, PluginData>();
//        PluginData p1 = new PluginData("lib/file-plugin.jar","server.persistence.filePlugin.FileFactory");
//        PluginData p2 = new PluginData("lib/sql-plugin.jar", "server.persistence.sqlPlugin.SQLFactory");
//        m.put("file", p1);
//        m.put("sql", p2);
//        Serializer s = new Serializer();
//        String json = s.toJson(m);
//        
//        System.out.println(json);
        //END DEBUG
        
        //Store map of all configurations
        Type type = new TypeToken<HashMap<String, PluginData>>(){}.getType();
        this.pluginMap = gson.fromJson(in, type);
        //this.pluginMap = gson.fromJson(in, HashMap.class);
			
		
	}
	
	 

}
