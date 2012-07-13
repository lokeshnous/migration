package com.advanceweb.afc.jb.webapp.web.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class CopyUtil {
	
	public static void copy( List<String> li ){
		
		new CopyUtil().doTheCopy(li);
	}

	public  void doTheCopy( List<String> resourceNames ) {
		try{
			for ( String resource : resourceNames ) { 
				InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
				FileOutputStream fos =
						new FileOutputStream( new File("D:\\", resource));
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
		}
	}
}