package com.sulvic.respacker.lib;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.google.common.collect.Lists;
import com.sulvic.engine.util.AssetLocation;

public class CtmFileInfo implements Iterable<String>{

	private final String ctmFilename;
	private final List<String> ctmImageFiles = Lists.newArrayList();
	private final Properties ctmProperties;
	private AssetLocation ctmBaseLocation;

	public CtmFileInfo(String filename){
		ctmFilename = filename;
		ctmProperties = new Properties();
	}

	public AssetLocation getBaseLocation(){ return ctmBaseLocation; }

	public CtmFileInfo addBaseLocation(AssetLocation assetLoc){
		ctmBaseLocation = assetLoc;
		return this;
	}

	public CtmFileInfo addImage(String file){
		ctmImageFiles.add(file);
		return this;
	}

	public CtmFileInfo addProperties(String key, String value){
		ctmProperties.put(key, value);
		return this;
	}

	@Override
	public Iterator<String> iterator(){ return ctmImageFiles.iterator(); }

	public Properties getProperties(){ return ctmProperties; }

	public String getProperty(String name){ return ctmProperties.getProperty(name, ""); }

	public String getFilename(){ return ctmFilename; }

}
