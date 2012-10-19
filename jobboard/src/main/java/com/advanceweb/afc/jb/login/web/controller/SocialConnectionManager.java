package com.advanceweb.afc.jb.login.web.controller;

import java.io.Serializable;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;

/**
 * Models an attempt to login to the application using a service provider user
 * identity. Instances are created when the service provider sign-in process
 * could not be completed because no local user is associated with the service
 * provider user identity.
 */

public class SocialConnectionManager implements Serializable {
	private static final long serialVersionUID = 2029671162112256788L;
	/**
	 * Name of the session attribute SocialConnectionManager instances are
	 * indexed under.
	 */
	public static final String SESSION_ATTRIBUTE = SocialConnectionManager.class
			.getName();

	private final ConnectionData connectionData;

	private final ConnectionFactoryLocator connectionFactoryLocator;

	public SocialConnectionManager(Connection<?> connection,
			ConnectionFactoryLocator connectionFactoryLocator) {
		this.connectionData = connection.createData();
		this.connectionFactoryLocator = connectionFactoryLocator;

	}

	/**
	 * Get the connection to the service provider user account the client
	 * attempted to sign-in as. This connection is used to fetch a service
	 * provider user profile and use that to pre-populate a local user
	 * registration form. Or to display a provider-specific flash message."
	 */
	public Connection<?> getConnection() {
		return connectionFactoryLocator.getConnectionFactory(
				connectionData.getProviderId())
				.createConnection(connectionData);

	}
}
