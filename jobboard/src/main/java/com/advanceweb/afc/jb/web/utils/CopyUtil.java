package com.advanceweb.afc.jb.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CopyUtil {

	public static void copy(List<String> li, String basedirectorypath)
			throws Exception {

		new CopyUtil().doTheCopy(li, basedirectorypath);
	}

	public void doTheCopy(List<String> resourceNames, String basedirectorypath)
			throws Exception {
		try {
			for (String resource : resourceNames) {
				InputStream is = this.getClass().getClassLoader()
						.getResourceAsStream(resource);
				FileOutputStream fos = new FileOutputStream(new File(
						basedirectorypath, resource));
				byte[] buffer = new byte[1024];
				int read = -1;
				while ((read = is.read(buffer)) != -1) {
					fos.write(buffer, 0, read);
				}
				fos.flush();
				fos.close();
				is.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public static void move(String source, String destination) {

		InputStream inStream = null;
		OutputStream outStream = null;

		try {
			File afile = new File(source);
			// File afile =new
			// File("D:\\JobBoardWorkspc_19_July_2012\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\jobboard\\resources\\images\\Resume_doc.doc");
			File bfile = new File(destination);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			// delete the original file
			afile.delete();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}