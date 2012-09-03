package com.advanceweb.afc.jb.web.utils;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFile {
	public static String htmlReader(String filepath) throws IOException {
		FileInputStream fstream = new FileInputStream(filepath);
		DataInputStream stream = new DataInputStream(fstream);
		Reader reader = new InputStreamReader(stream);
		int value;
		StringBuffer htmlsource=new StringBuffer();
		htmlsource.delete(0, htmlsource.length());
		while((value = reader.read()) != -1){
			htmlsource.append((char)value);
		}
		return htmlsource.toString();
	}

}
