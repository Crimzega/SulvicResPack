package com.sulvic.respacker;

import java.util.Properties;

import javax.annotation.Nullable;

import com.sulvic.engine.util.AssetLocation;
import com.sulvic.respacker.util.AssetCollector;

public abstract class CtmSplitter{

	protected final Properties ctmProperties;

	public CtmSplitter(Properties properties){ ctmProperties = properties; }

	@Nullable
	public static CtmSplitter getSplitter(AssetLocation assetLoc){
		Properties properties = AssetCollector.getCtmProperties(assetLoc);
		Type type = Type.byMethod(properties.getProperty("method", "ctm_compact"));
		CtmSplitter result = type.getSplitter(properties);
		return result;
	}

	public abstract void split(AssetLocation assetLoc);

	public static enum Type{

		CTM,
		CTM_COMPACT,
		HORIZONTAL,
		VERTICAL,
		HORIZONTAL_VERTICAL,
		VERTICAL_HORIZONTAL,
		TOP,
		RANDOM,
		REPEAT,
		FIXED,
		OVERLAY,
		OVERLAY_CTM,
		OVERLAY_RANDOM,
		OVERLAY_REPEAT,
		OVERLAY_FIXED;

		@Nullable
		public static Type byMethod(String method){
			for(Type type: values()) if(type.hasMethod(method)) return type;
			return null;
		}

		public boolean hasMethod(String method){ return getMethod().equals(method); }

		@Nullable
		public CtmSplitter getSplitter(Properties properties){
			switch(this){
				case CTM: return new FullCtmSplitter(properties);
				case CTM_COMPACT: return new CompactCtmSplitter(properties);
				case HORIZONTAL: return new HorizontalCtmSplitter(properties, false);
				case VERTICAL: return new VerticalCtmSplitter(properties, false);
				case HORIZONTAL_VERTICAL: return new HorizontalCtmSplitter(properties, true);
				case VERTICAL_HORIZONTAL: return new VerticalCtmSplitter(properties, true);
				default: return null;
			}
		}

		public String getMethod(){
			switch(this){
				case HORIZONTAL_VERTICAL: return "horizontal+vertical";
				case VERTICAL_HORIZONTAL: return "vertical+horizontal";
				default: return name().toLowerCase();
			}
		}

	}

	private static class FullCtmSplitter extends CtmSplitter{

		public FullCtmSplitter(Properties properties){ super(properties); }

		@Override
		public void split(AssetLocation assetLoc){
			
		}

	}

	private static class CompactCtmSplitter extends CtmSplitter{

		public CompactCtmSplitter(Properties properties){ super(properties); }

		@Override
		public void split(AssetLocation assetLoc){}

	}

	private static class HorizontalCtmSplitter extends CtmSplitter{

		private final boolean hasVertical;

		private HorizontalCtmSplitter(Properties properties, boolean vertical){
			super(properties);
			hasVertical = vertical;
		}

		@Override
		public void split(AssetLocation assetLoc){}

	}

	private static class VerticalCtmSplitter extends CtmSplitter{

		private final boolean hasHorizontal;

		private VerticalCtmSplitter(Properties properties, boolean horizontal){
			super(properties);			
			hasHorizontal = horizontal;
		}

		@Override
		public void split(AssetLocation assetLoc){}

	}

}
