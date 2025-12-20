package com.sulvic.respacker.compiler;

import java.lang.reflect.Type;

import com.google.gson.*;
import com.sulvic.color.EnumBlendMode;

public class ImageDeserializer implements JsonDeserializer<ImageCompiler.LayerCompiler[]>{

	@Override
	public ImageCompiler.LayerCompiler[] deserialize(JsonElement jsonElem, Type t, JsonDeserializationContext ctx) throws JsonParseException{
		JsonArray jsonArr = jsonElem.getAsJsonArray();
		ImageCompiler.LayerCompiler[] result = new ImageCompiler.LayerCompiler[jsonArr.size()];
		for(int i = 0; i < jsonArr.size(); i++){
			JsonObject jsonObj = jsonArr.get(i).getAsJsonObject();
			String layerName = jsonObj.get("name").getAsString();
			EnumBlendMode blendMode = EnumBlendMode.NORMAL;
			if(jsonObj.has("blend")){
				String blendModeStr = jsonObj.get("blend").getAsString().toUpperCase();
				blendMode = EnumBlendMode.valueOf(blendModeStr);
			}
			result[i] = jsonObj.has("opacity")? new ImageCompiler.LayerCompiler(layerName, blendMode, jsonObj.get("opacity").getAsInt()): new ImageCompiler.LayerCompiler(layerName, blendMode);
		}
		return result;
	}

}
