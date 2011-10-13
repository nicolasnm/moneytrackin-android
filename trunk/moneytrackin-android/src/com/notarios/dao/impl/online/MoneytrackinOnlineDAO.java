package com.notarios.dao.impl.online;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class MoneytrackinOnlineDAO {
	private final RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * ProjectOnlineDAO constructor Initializes the dato with the given username and password. The password should be
	 * the MD5 hash of the real password.
	 * 
	 * @param username The username
	 * @param password The MD5 hash of the password
	 */
	public MoneytrackinOnlineDAO(final String username, final String password) {
		final HttpComponentsClientHttpRequestFactory factory = (HttpComponentsClientHttpRequestFactory) getRestTemplate()
				.getRequestFactory();
		final DefaultHttpClient client = (DefaultHttpClient) factory.getHttpClient();
		final Credentials creds = new UsernamePasswordCredentials(username, password);
		client.getCredentialsProvider().setCredentials(AuthScope.ANY, creds);

		// Ads an interceptor that establishes the headers. Don't know why but if it's not used, the XML transformation
		// won't work
		client.addRequestInterceptor(new HttpRequestInterceptor() {

			public void process(final HttpRequest arg0, final HttpContext arg1) throws HttpException, IOException {
				arg0.setHeader("User-Agent",
						"Mozilla/5.0 (Windows; U; Windows NT 6.1; es-ES; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13");
				arg0.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				arg0.setHeader("Accept-Language", "es-es,es;q=0.8,en-us;q=0.5,en;q=0.3");
			}
		});
	}

	/**
	 * @return the restTemplate
	 */
	protected RestTemplate getRestTemplate() {
		return restTemplate;
	}

}
