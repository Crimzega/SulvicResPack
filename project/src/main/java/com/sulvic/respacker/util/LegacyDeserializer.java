package com.sulvic.respacker.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.compiler.LegacyCompiler;

class LegacyDeserializer implements JsonDeserializer<LegacyData>{

	@Override
	public LegacyData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException{
		JsonObject jsonObj = json.getAsJsonObject();
		JsonArray jsonPattern = jsonObj.get("pattern").getAsJsonArray();
		List<String> patternList = Lists.newArrayList();
		for(int i = 0; i < jsonPattern.size(); i++) patternList.add(jsonPattern.get(i).getAsString());
		LegacyData result = new LegacyData(patternList.toArray(new String[256]));
		JsonObject jsonEntries = jsonObj.get("entries").getAsJsonObject();
		for(Map.Entry<String, JsonElement> entry: jsonEntries.entrySet()){
			String name = entry.getKey();
			JsonElement value = entry.getValue();
			JsonObject jsonAssetObj = value.getAsJsonObject();
			AssetLocation assetLoc = new AssetLocation(jsonAssetObj.get("name").getAsString());
			LegacyCompiler.CompileType type = LegacyCompiler.CompileType.byMethod(jsonAssetObj.get("compile").getAsString());
			JsonObject jsonAsset = value.getAsJsonObject();
			LegacyData.StitchData asset = new LegacyData.StitchData(assetLoc, type);
			JsonArray jsonIndex = jsonAsset.get("index").getAsJsonArray();
			asset.setIndexOffset(jsonIndex.get(0).getAsInt(), jsonIndex.get(1).getAsInt());
			if(jsonAsset.has("withAlpha")) asset.setAlphaOverride(jsonAsset.get("withAlpha").getAsInt());
			if(jsonAsset.has("asOpaque")) asset.setAsOpaque(true);
			result.setEntryPattern(name, asset);
		}
		return result;
	}

}
