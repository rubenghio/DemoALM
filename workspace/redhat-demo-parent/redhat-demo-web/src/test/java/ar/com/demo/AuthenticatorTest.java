package ar.com.demo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AuthenticatorTest {
	private static final String USER = "admin";
	private static final String ROLE = "admin";
	private Authenticator auth;
	private Log logMock;
	private Credentials credentialsMock;
	private Identity identityMock;
	
	@Before
	public void setup() {
		auth = new Authenticator();
		logMock = mock(Log.class);
		credentialsMock = mock(Credentials.class);
		identityMock = mock(Identity.class);
		
		auth.setLog(logMock);
		auth.setCredentials(credentialsMock);
	}
	
	@Test
	public void authenticateTrue() {
		auth.setIdentity(identityMock);
		when(credentialsMock.getUsername()).thenReturn(USER);
		Assert.assertTrue(auth.authenticate());
		verify(credentialsMock, times(2)).getUsername();
		verify(logMock).info("authenticating {0}", USER);
		verify(identityMock).addRole(ROLE);
	}

	@Test
	public void authenticateFalse() {
		when(credentialsMock.getUsername()).thenReturn(USER + "Fail");
		Assert.assertFalse(auth.authenticate());
		verify(credentialsMock, times(2)).getUsername();
		verify(logMock).info("authenticating {0}", USER + "Fail");
	}
	
	@After
	public void clean() {
		auth = null;
		logMock = null;
		credentialsMock = null;
		identityMock = null;		
	}
}
