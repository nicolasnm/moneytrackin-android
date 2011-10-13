/*
 * ProjectBasicBO.java March 2011
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
package com.notarios.bo.impl;

import java.util.List;

import com.notarios.bo.ProjectBO;
import com.notarios.dao.ProjectDAO;
import com.notarios.model.Project;


/**
 * The projects Business Object.
 * 
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell Nicolás Notario McDonnell
 */
public final class ProjectBasicBO implements ProjectBO {
	private static ProjectBasicBO singletonProjectBasicBO = null;
	private final ProjectDAO projectDAO;
	
	/**
	 * getProjectBasicBO Gets the singleton instance of the ProjectBasicBO
	 * 
	 * @return The singleton instance of the ProjectBasicBO
	 */
	public static ProjectBasicBO getProjectBasicBO() {
		return singletonProjectBasicBO;
	}
	
	/**
	 * initProjectBasicBO Initializes the ProjectBasicBO with a projectDAO
	 * 
	 * @param projectDAO The ProjectDAO to use in the ProjectBO
	 */
	public static void initProjectBasicBO(ProjectDAO projectDAO) {
		singletonProjectBasicBO = new ProjectBasicBO(projectDAO);
	}
	
	
	/**
	 * ProjectBasicBO constructor
	 * @param projectDAO The ProjectDAO to use in the ProjectBO
	 */
	private ProjectBasicBO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/**
	 * @see com.notarios.bo.ProjectBO#getProjects()
	 */
	public List<Project> getProjects() {
		return projectDAO.getProjects();
	}

	/**
	 * @see com.notarios.bo.ProjectBO#getProject(int)
	 */
	public Project getProject(int id) {
		return projectDAO.getProject(id);
	}

	/**
	 * @see com.notarios.bo.ProjectBO#save(com.notarios.model.Project)
	 */
	public Project save(Project p) {
		return projectDAO.save(p);
	}

	/**
	 * @see com.notarios.bo.ProjectBO#deleteProject(com.notarios.model.Project)
	 */
	public void deleteProject(Project p) {
		projectDAO.deleteProject(p);
	}
}
