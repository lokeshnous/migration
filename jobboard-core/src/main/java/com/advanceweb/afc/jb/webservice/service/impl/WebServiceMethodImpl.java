package com.advanceweb.afc.jb.webservice.service.impl;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.stereotype.Service;

import com.advanceweb.afc.jb.webservice.service.WebServiceMethod;


@Service("webServiceMethod")
public class WebServiceMethodImpl implements WebServiceMethod{

	public Response netSuiteGet(WebClient client) {
		return client.get();
	}

	public Response netSuitePost(WebClient client, Object obj) {
		return client.post(obj);
	}

}
