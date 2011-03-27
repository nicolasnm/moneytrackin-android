/*
 * ProjectDAO.java March 2011
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
package com.notarios.dao;

import java.util.List;

import com.notarios.model.Project;

/**
 * The projects DAO.
 * 
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell Nicolás Notario McDonnell
 */
public interface ProjectDAO {
	/**
	 * getProjects Gets the list of projects.
	 * 
	 * @return The list of projects
	 */
	public List<Project> getProjects();

	/**
	 * getProject Gets the project identified by the id
	 * 
	 * @param id The id of the project.
	 * @return The project
	 */
	public Project getProject(int id);

	/**
	 * save Saves the project.
	 * 
	 * @param p The project
	 * @return The project allready saved.
	 */
	public Project save(Project p);

	/**
	 * deleteProject Deletes the project.
	 * 
	 * @param p The project.
	 */
	public void deleteProject(Project p);
}
