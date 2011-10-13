/*
 * ProjectStubDAO.java March 2011
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
package com.notarios.dao.impl.stub;

import java.util.ArrayList;
import java.util.List;

import com.notarios.dao.ProjectDAO;
import com.notarios.model.Project;

/**
 * A stub implementation for the ProjectDAP
 * 
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell Nicolás Notario McDonnell
 */
public class ProjectStubDAO implements ProjectDAO {

	private static final double MIN_AMOUNT = 1000.0;
	private static final int NUM_PROJECTS = 10;
	private final List<Project> projects;

	public ProjectStubDAO() {
		projects = new ArrayList<Project>();
		for (int i = 0; i < NUM_PROJECTS; i++) {
			final Project p = new Project();
			p.setId(i);
			p.setName("Name" + i);
			p.setAmount(MIN_AMOUNT * (i + 1));
			p.setCurrency("EUR");
			p.setCurrencySymbol('€');
			projects.add(p);
		}
	}

	/**
	 * @see com.notarios.dao.ProjectDAO#getProjects()
	 */
	public final List<Project> getProjects() {
		return projects;
	}

	/**
	 * @see com.notarios.dao.ProjectDAO#getProject(int)
	 */
	public final Project getProject(final int id) {
		for (final Project p : projects) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	/**
	 * @see com.notarios.dao.ProjectDAO#save(com.notarios.model.Project)
	 */
	public final Project save(final Project editProject) {
		for (int i = 0; i < projects.size(); i++) {
			final Project p = projects.get(i);
			if (p.getId() == editProject.getId()) {
				projects.set(i, editProject);
				return editProject;
			}
		}
		projects.add(editProject);
		return editProject;
	}

	/**
	 * @see com.notarios.dao.ProjectDAO#deleteProject(com.notarios.model.Project)
	 */
	public final void deleteProject(final Project deleteProject) {
		for (int i = 0; i < projects.size(); i++) {
			final Project p = projects.get(i);
			if (p.getId() == deleteProject.getId()) {
				projects.remove(i);
			}
		}
	}

}
