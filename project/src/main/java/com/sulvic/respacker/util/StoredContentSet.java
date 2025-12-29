package com.sulvic.respacker.util;

import java.awt.image.BufferedImage;
import java.util.Map;

import com.sulvic.respacker.compiler.CopyCompiler;
import com.sulvic.respacker.compiler.ImageCompiler;
import com.sulvic.util.ContentBuilder;

public class StoredContentSet{

	private static final Map<String, BufferedImage> COLLECTED_IMAGES_MAP = ContentBuilder.newHashMap();
	private static final Map<String, ImageCompiler> IMAGE_COMPILER_MAP = ContentBuilder.newHashMap();
	private static final Map<String, CopyCompiler> IMAGE_COPIER_MAP = ContentBuilder.newHashMap();

	public static boolean hasCollectedCopier(String path){ return IMAGE_COPIER_MAP.containsKey(path); }

	public static CopyCompiler getCollectedCopier(String path){ return IMAGE_COPIER_MAP.get(path); }

	public static void setCollectedCopier(String path, CopyCompiler img){ IMAGE_COPIER_MAP.put(path, img); }

	public static boolean hasCollectedCompiler(String path){ return IMAGE_COMPILER_MAP.containsKey(path); }

	public static ImageCompiler getCollectedCompiler(String path){ return IMAGE_COMPILER_MAP.get(path); }

	public static void setCollectedCompiler(String path, ImageCompiler img){ IMAGE_COMPILER_MAP.put(path, img); }

	public static boolean hasCollectedImage(String path){ return COLLECTED_IMAGES_MAP.containsKey(path); }

	public static BufferedImage getCollectedImage(String path){ return COLLECTED_IMAGES_MAP.get(path); }

	public static void setCollectedImage(String path, BufferedImage img){ COLLECTED_IMAGES_MAP.put(path, img); }

}
