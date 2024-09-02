package com.sulvic.csharp;

import com.sulvic.csharp.CSharpObject.CSharpComponent;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CSharpInputStream extends DataInputStream{
	
	private InputStream theStream;
	
	public CSharpInputStream(InputStream stream){ super(stream); }
	
	public synchronized void mark(int readlimit){ super.mark(readlimit); }
	
	public synchronized void reset() throws IOException{ super.reset(); }
	
	public boolean markSupported(){ return super.markSupported(); }
	
	public int available() throws IOException{ return super.available(); }
	
	public int read() throws IOException{ return super.read(); }
	
	public long skip(long n) throws IOException{ return super.skip(n); }
	
	public CSharpObject deserialize() throws IOException{
		CSharpObject result = new CSharpObject();
		result.activeInHierarchy = readBoolean();
		result.activeSelf = readBoolean();
		result.isStatic = readBoolean();
		result.layer = readInt();
		result.tag = readInt() == -1? "Untagged": "";
		int size = readInt();
		result.name = readUTF();
		readUTF();
		readByte();
		int count = readByte();
		for(int i = 0; i < count; i++){
			CSharpObject.CSharpComponent component = new CSharpObject.CSharpComponent();
			result.components.add(component);
		}
		return result;
	}
	
	public void close() throws IOException{ theStream.close(); }
	
}
