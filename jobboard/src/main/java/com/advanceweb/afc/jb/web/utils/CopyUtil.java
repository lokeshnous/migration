package com.advanceweb.afc.jb.web.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class CopyUtil {

	public static void copy( List<String> li ,String basedirectorypath) throws Exception{
		
		new CopyUtil().doTheCopy(li,basedirectorypath);
	}

	public  void doTheCopy( List<String> resourceNames ,String basedirectorypath) throws Exception {
		try{
			for ( String resource : resourceNames ) { 
				InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
				FileOutputStream fos =
						new FileOutputStream( new File(basedirectorypath, resource));
				byte[] buffer = new byte[1024];
				int read = -1;
				while( (read = is.read(buffer)) != -1 ) {
					fos.write( buffer,0,read);
				}
				fos.flush();
				fos.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
}