/*
 * ProjectOnlineDAO.java March 2011
 *
 * Copyright (C) 2011, 
 * 		Nicolás Notario McDonnell <nicolasnm@gmail.com>
 *      Roberto Notario McDonnell <zanaskw@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package com.notarios.dao.impl.online;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.notarios.dao.ProjectDAO;
import com.notarios.model.Project;

/**
 * The implementation of the ProjectDAO that works online against moneytrackin
 * 
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell Nicolás Notario McDonnell
 */
public class ProjectOnlineDAO implements ProjectDAO {
	private final String tempUrl = "https://www.moneytrackin.com/api/rest/listProjects";
	private final RestTemplate restTemplate = new RestTemplate();

	/**
	 * ProjectOnlineDAO constructor Initializes the dato with the given username and password. The password should be
	 * the MD5 hash of the real password.
	 * 
	 * @param username The username
	 * @param password The MD5 hash of the password
	 */
	public ProjectOnlineDAO(final String username, final String password) {
		final HttpComponentsClientHttpRequestFactory factory = (HttpComponentsClientHttpRequestFactory) restTemplate
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
	 * @see com.notarios.dao.ProjectDAO#getProjects()
	 */
	public List<Project> getProjects() {

		final List<Project> projects = new ArrayList<Project>();
		// Calls the service and parses the result into a ListProjectsResult class
		final ListProjectsResult proj = restTemplate.getForObject(tempUrl, ListProjectsResult.class);
		for (final MoneytrackinProject mtp : proj.getProjects()) {
			final Project p = new Project();
			p.setAmount(mtp.getBalance());
			p.setName(mtp.getName());
			p.setCurrency(mtp.getCurrency());
			p.setCurrencySymbol(mtp.getHtmlchar());
			if ((mtp.getId() != null) && (!mtp.getId().equals(""))) {
				p.setId(Integer.parseInt(mtp.getId()));
			} else {
				p.setId(0);
			}
			projects.add(p);
		}

		return projects;
	}

	/**
	 * @see com.notarios.dao.ProjectDAO#getProject(int)
	 */
	public Project getProject(final int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see com.notarios.dao.ProjectDAO#save(com.notarios.model.Project)
	 */
	public Project save(final Project p) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteProject(final Project p) {
		// TODO Auto-generated method stub

	}

}
