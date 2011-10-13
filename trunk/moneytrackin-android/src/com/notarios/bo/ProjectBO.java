/*
 * ProjectBO.java March 2011
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
package com.notarios.bo;

import java.util.List;

import com.notarios.model.Project;

/**
 * The projects Business Object
 * 
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell 
 * @author Nicolás Notario McDonnell
 */
public interface ProjectBO {
	/**
	 * getProjects Gets the list of projects
	 * 
	 * @return The list of projects
	 */
	public List<Project> getProjects();

	/**
	 * getProject Gets the project with the given id
	 * 
	 * @param id The project id
	 * @return The project
	 */
	public Project getProject(int id);

	/**
	 * save Saves the given project.
	 * @param p The project
	 * @return The project
	 */
	public Project save(Project p);

	/**
	 * deleteProject Deletes the given project
	 * 
	 * @param p The project to delete
	 */
	public void deleteProject(Project p);
}
