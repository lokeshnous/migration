package com.advanceweb.afc.jb.security;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;

public interface DatabaseAuthenticationDelegate {

	public boolean validateUser(String email, String password);
	public URLConnection getConnection(String email, String password)throws IOException, MalformedURLException;
	public String getCookieValue(URLConnection connection);
}
