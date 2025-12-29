package com.sulvic.respacker.lib;

import java.lang.reflect.Type;

import com.google.gson.*;

public class CtmDeserializer implements JsonDeserializer<CtmFileInfo>{

	@Override
	public CtmFileInfo deserialize(JsonElement jsonElem, Type t, JsonDeserializationContext ctx) throws JsonParseException{
		JsonObject jsonObj = jsonElem.getAsJsonObject();
		JsonObject handlerJson = jsonObj.get("handler").getAsJsonObject();
		CtmFileInfo result = new CtmFileInfo(handlerJson.get("file").getAsString());
		JsonObject propertiesJson = handlerJson.get("properties").getAsJsonObject();
		for(String key: propertiesJson.keySet()) result.addProperties(key, propertiesJson.get(key).getAsString());
		JsonArray imagesArr = jsonObj.get("images").getAsJsonArray();
		for(int i = 0; i < imagesArr.size(); i++) result.addImage(imagesArr.get(i).getAsString());
		return result;
	}

}
