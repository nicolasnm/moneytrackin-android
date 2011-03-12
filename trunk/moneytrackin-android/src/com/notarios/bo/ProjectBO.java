/**
 * 
 */
package com.notarios.bo;

import java.util.List;

import com.notarios.model.Project;

/**
 * @author Roberto
 *
 */
public interface ProjectBO {
	public List<Project> getProjects();
	public Project getProject(int id);
	public Project save(Project p);
	public void deleteProject(Project p);
}
