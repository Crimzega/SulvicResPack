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
			LegacyData.AssetData asset;
			if(value.isJsonPrimitive()) asset = new LegacyData.AssetData(value.getAsString());
			else{
				JsonObject jsonAsset = value.getAsJsonObject();
				asset = new LegacyData.AssetData(jsonAsset.get("name").getAsString());
				JsonArray jsonIndex = jsonAsset.get("index").getAsJsonArray();
				asset.setIndexOffset(jsonIndex.get(0).getAsInt(), jsonIndex.get(1).getAsInt());
				if(jsonAsset.has("withAlpha")) asset.setTextureAlpha(jsonAsset.get("withAlpha").getAsInt());
				if(jsonAsset.has("asOpaque")) asset.setAsOpaque(true);
			}
			result.setEntryPattern(name, asset);
		}
		return result;
	}

}
