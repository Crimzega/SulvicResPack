package com.sulvic.csharp;

import java.util.List;
import java.util.Map;

import com.sulvic.util.ContentBuilder;

public class CSharpObject{
	
	protected boolean activeInHierarchy, activeSelf, isStatic;
	protected int layer;
	protected String tag, name, objectId;
	protected List<CSharpComponent> components = ContentBuilder.newArrayList();
	
	public static class CSharpComponent{
		
		public boolean isEnabled, isMonoBehaviour;
		public String ownerName, typePath;
		public Map<String, Object> propertyValues = ContentBuilder.newHashMap(), propertyReference = ContentBuilder.newHashMap(), fieldValues = ContentBuilder.newHashMap(),
			fieldReferences = ContentBuilder.newHashMap(), propertyRecerenceArrays = ContentBuilder.newHashMap(), fieldReferenceArrays = ContentBuilder.newHashMap();
		
	}
	
}
