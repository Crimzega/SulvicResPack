package com.sulvic.respacker.util;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.sulvic.engine.util.AssetLocation;
import com.sulvic.util.SulvicMath;

public class LegacyData implements Iterable<String>{

	private String[] entryStructure = new String[256];
	private Map<String, AssetData> entryData = Maps.newHashMap();

	public LegacyData(){ this(makeDefaultPattern()); }

	public LegacyData(String[] pattern){ entryStructure = pattern; }

	private static String[] makeDefaultPattern(){
		String[] result = new String[256];
		for(int i = 0; i < result.length; i++) result[i] = " ";
		return result;
	}

	@Nullable
	public AssetData getData(String entry){ return entryData.containsKey(entry)? entryData.get(entry): (AssetData)null; }

	@Override
	public Iterator<String> iterator(){ return new LegacyIterator(this); }

	public void setEntryPattern(String entry, AssetData data){ entryData.put(entry, data); }

	private static class LegacyIterator implements Iterator<String>{

		private int currentIndex;
		private LegacyData theData;

		private LegacyIterator(LegacyData data){ theData = data; }

		@Override
		public boolean hasNext(){ return currentIndex < theData.entryStructure.length; }

		@Override
		public String next(){ return theData.entryStructure[currentIndex++]; }

	}

	public static class AssetData{

		private final AssetLocation assetLoc;
		private int[] indexOffset = new int[2];
		private boolean asOpqaue = false;
		private boolean usesAlphaOverride = false, usesIndexOffset = false;
		private int alphaOverride = 255;

		public AssetData(String name){ assetLoc = new AssetLocation(name); }
		
		public int[] getIndexOffset(){ return indexOffset; }

		public AssetData setIndexOffset(int x, int y){
			indexOffset[0] = x;
			indexOffset[1] = y;
			usesIndexOffset = true;
			return this;
		}

		public AssetData setTextureAlpha(int alpha){
			alphaOverride = SulvicMath.clampInt(alpha, 0, 255);
			usesAlphaOverride = true;
			return this;
		}

		public AssetData setAsOpaque(boolean value){
			asOpqaue = value;
			return this;
		}

		public AssetLocation getAssetLocation(){ return assetLoc; }

		public boolean asOpqaue(){ return asOpqaue; }

		public boolean usesAlphaOverride(){ return usesAlphaOverride; }

		public boolean usesIndexOffset(){ return usesIndexOffset; }

		public int getAlphaOverride(){ return alphaOverride; }

	}

}
