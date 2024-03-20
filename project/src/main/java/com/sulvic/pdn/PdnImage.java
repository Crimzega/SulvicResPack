package com.sulvic.pdn;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sulvic.csharp.CSharpInputStream;

public class PdnImage{
	
	private static final String MAGIC_PDN_V21 = "PDN3";
	private transient boolean isDirty;
	private Document headerXml;
	
	public static PdnImage fromStream(FileInputStream stream) throws IOException, SAXException, ParserConfigurationException, ClassNotFoundException{
		long oldPos = stream.getChannel().position();
		boolean pdn21Format = true;
		for(int i = 0; i < MAGIC_PDN_V21.length(); i++){
			if(stream.available() == 0) throw new EOFException();
			int num = stream.read();
			if(num != MAGIC_PDN_V21.codePointAt(i)) pdn21Format = false;
		}
		Document xmlDoc = null;
		if(pdn21Format){
			if(stream.available() < 3) throw new EOFException();
			int num = stream.read(), mid = stream.read(), high = stream.read();
			byte[] data = new byte[num + (mid << 8) + (high << 16)];
			stream.read(data);
			String xmlStr = new String(data, StandardCharsets.UTF_8);
			System.out.println(xmlStr);
			xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmlStr)));
		}
		else stream.getChannel().position(oldPos);
		long oldPos2 = stream.getChannel().position();
		if(stream.available() < 2) throw new EOFException();
		int first = stream.read(), second = stream.read();
		Object obj = null;
		if(first != 0 || second != 1){
			if(first == 31 && second == 139){
				stream.getChannel().position(oldPos2);
				try(GZIPInputStream gzipStream = new GZIPInputStream(stream)){
					try(CSharpInputStream csStream = new CSharpInputStream(stream)){
						obj = csStream.deserialize();
					}
//					try(ObjectInputStream objStream = new ObjectInputStream(gzipStream)){
//						docObject = formatter.Deserialize(gzipStream);
//					}
				}
			}
			else throw new StreamCorruptedException("file is not a valid paint.net document");
			String str = "\"[^\"]+\"";
		}
//		DeferredFormatter deferred = new DeferredFormatter();
//		deferred.Context = new StreamingContext(formatter.Context.State, deferred);
//		pdnObj = formatter.Deserialize(stream);
		PdnImage result = (PdnImage)obj;
		if(result != null){
			result.isDirty = true;
			result.headerXml = xmlDoc;
			result.invalidate();
		}
		return result;
	}
	
	public void invalidate(){}
	
}
