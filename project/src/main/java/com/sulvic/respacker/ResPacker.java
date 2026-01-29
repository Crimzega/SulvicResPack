package com.sulvic.respacker;

import com.sulvic.respacker.common.PackManager;

public class ResPacker{

	public static void main(String[] args) throws Exception{
		PackManager.init();
		PackManager.compilePacks();
	}

}
