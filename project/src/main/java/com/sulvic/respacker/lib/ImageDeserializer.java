package com.sulvic.respacker.lib;

import java.lang.reflect.Type;

import com.google.gson.*;
import com.sulvic.color.EnumBlendMode;

public class ImageDeserializer implements JsonDeserializer<ImageDeserializer.LayerData[]>{

	@Override
	public ImageDeserializer.LayerData[] deserialize(JsonElement jsonElem, Type t, JsonDeserializationContext ctx) throws JsonParseException{
		JsonArray jsonArr = jsonElem.getAsJsonArray();
		LayerData[] result = new LayerData[jsonArr.size()];
		for(int i = 0; i < result.length; i++){
			JsonObject jsonObj = jsonArr.get(i).getAsJsonObject();
			String layerName = jsonObj.get("name").getAsString();
			EnumBlendMode blendMode = EnumBlendMode.NORMAL;
			if(jsonObj.has("blend")){
				String blendModeStr = jsonObj.get("blend").getAsString();
				blendMode = EnumBlendMode.byMethod(blendModeStr.toLowerCase());
			}
			result[i] = jsonObj.has("opacity")? new LayerData(layerName, blendMode, jsonObj.get("opacity").getAsInt()): new LayerData(layerName, blendMode);
		}
		return result;
	}

	public static class LayerData{

		private final EnumBlendMode blendMode;
		private final String layerName;
		private int layerOpacity = 255;

		public LayerData(String name, EnumBlendMode mode){
			layerName = name;
			blendMode = mode;
		}

		public LayerData(String name, EnumBlendMode mode, int opacity){
			this(name, mode);
			layerOpacity = opacity;
		}

        public EnumBlendMode getBlendMode() {
            return blendMode;
        }

		public int getLayerOpacity(){ return layerOpacity; }

        public String getLayerName(){ return layerName; }

	}

}
