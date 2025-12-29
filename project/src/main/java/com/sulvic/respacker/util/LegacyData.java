package com.sulvic.respacker.util;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.compiler.LegacyCompiler;

public class LegacyData implements Iterable<String>{

	private final Map<String, StitchData> entryData = Maps.newHashMap();
	private String[] entryStructure = new String[256];

	public LegacyData(){ this(makeDefaultPattern()); }

	public LegacyData(String[] pattern){ entryStructure = pattern; }

	private static String[] makeDefaultPattern(){
		String[] result = new String[256];
		for(int i = 0; i < result.length; i++) result[i] = " ";
		return result;
	}

	public int size(){ return entryStructure.length; }

	@Override
	public Iterator<String> iterator(){ return new LegacyIterator(this); }

	@Nullable
	public StitchData getData(String entry){ return entryData.containsKey(entry)? entryData.get(entry): (StitchData)null; }

	public String getEntry(int index){ return entryStructure[index]; }

	public void setEntryPattern(String entry, StitchData data){ entryData.put(entry, data); }

	private static class LegacyIterator implements Iterator<String>{

		private final LegacyData theData;
		private int currentIndex;

		private LegacyIterator(LegacyData data){ theData = data; }

		@Override
		public boolean hasNext(){ return currentIndex < theData.entryStructure.length; }

		@Override
		public String next(){ return theData.entryStructure[currentIndex++]; }

	}

	public static class StitchData{

		private final AssetLocation stitchAssetLoc;
		private final LegacyCompiler.CompileType compileType;
		private boolean asOpaque = false, usesAlphaOverride = false, usesIndexOffset = false;
		private int xIndexOffset, yIndexOffset, alphaOverride = 255;

		public StitchData(AssetLocation assetLoc, LegacyCompiler.CompileType type){
			stitchAssetLoc = assetLoc;
			compileType = type;
		}

		public StitchData setIndexOffset(int x, int y){
			xIndexOffset = x;
			yIndexOffset = y;
			usesIndexOffset = true;
			return this;
		}

		public AssetLocation getAssetLocation(){ return stitchAssetLoc; }

		public LegacyCompiler.CompileType getCompileType(){ return compileType; }

		public boolean asOpaque(){ return asOpaque; }

		public boolean usesAlphaOverride(){ return usesAlphaOverride; }

		public boolean usesIndexOffset(){ return usesIndexOffset; }

		public int getAlphaOverride(){ return alphaOverride; }

		public int getIndexOffsetX(){ return xIndexOffset; }

		public int getIndexOffsetY(){ return yIndexOffset; }

		public StitchData setAsOpaque(boolean opaque){
			asOpaque = opaque;
			return this;
		}

		public StitchData setAlphaOverride(int override){
			alphaOverride = override;
			usesAlphaOverride = true;
			return this;
		}

	}

}
