package com.sulvic.respacker;

import java.io.File;
import java.io.FileInputStream;

import com.sulvic.pdn.PdnImage;
import com.sulvic.respacker.translocator.TranslocatorStitcher;

public class ResourcePacker{
	
	public static void main(String[] args) throws Exception{
//		SulvicOptions options = new SulvicOptions();
//		ArgumentAcceptingOptionSpec<String> packVersionArg = options.requiredDefaults("pack-version", "legacy", new String[0]).required();
//		OptionSet optionSet = options.parse(args);
//		PackVersion version = PackVersion.byVersion(optionSet.valueOf(packVersionArg));
//		switch(version){
//			case LEGACY:
//			
//			break;
//			default:
//			
//			break;
//		}
//		FileInputStream stream = new FileInputStream(new File(ResourcePacker.class.getResource("/images/translocator/segmented/all_objects.pdn").toURI()));
//		System.out.println(stream.getChannel() != null);
//		PdnImage img = PdnImage.fromStream(stream);
//		System.out.println(img);
		TranslocatorStitcher.compile(new File("src/main/resources/compiled/assets/"));
	}
	
	public static enum PackVersion{
		
		LEGACY,
		PACK_V01,
		PACK_V02,
		PACK_V03,
		PACK_V04,
		PACK_V05,
		PACK_V06,
		PACK_V07,
		PACK_V08,
		PACK_V09,
		PACK_V10,
		PACK_V11,
		PACK_V12,
		PACK_V13,
		PACK_V14,
		PACK_V15,
		PACK_V16,
		PACK_V17,
		PACK_V18;
		
		public static PackVersion byVersion(String version){
			switch(version){
				case "legacy":
					return LEGACY;
				case "pack01":
					return PACK_V01;
				case "pack02":
					return PACK_V02;
				case "pack03":
					return PACK_V03;
				case "pack04":
					return PACK_V04;
				case "pack05":
					return PACK_V05;
				case "pack06":
					return PACK_V06;
				case "pack07":
					return PACK_V07;
				case "pack08":
					return PACK_V08;
				case "pack09":
					return PACK_V09;
				case "pack10":
					return PACK_V10;
				case "pack11":
					return PACK_V11;
				case "pack12":
					return PACK_V12;
				case "pack13":
					return PACK_V13;
				case "pack14":
					return PACK_V14;
				case "pack15":
					return PACK_V15;
				case "pack16":
					return PACK_V16;
				case "pack17":
					return PACK_V17;
				case "pack18":
					return PACK_V18;
				default:
					return PACK_V01;
			}
		}
		
		public boolean usesModernBlockTextureNames(){ return ordinal() >= 4; }
		
		public boolean usesModernItemTextureNames(){ return ordinal() >= 4; }
		
	}
	
}
