/*
 * Principal.java March 2011
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
package com.notarios;

import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.notarios.bo.impl.ProjectBasicBO;
import com.notarios.listadapters.MoneytrackinProjectsListAdapter;
import com.notarios.model.Project;

/**
 * Principal Activity of the moneytrackin application.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public class ProjectsActivity extends AbstractAsyncListActivity {
	
	@Override
    public void onStart() {
        super.onStart();
        new FechProjectsTask().execute();
    }

    // ***************************************
    // Private methods
    // ***************************************
    private void showResult(List<Project> projects) {
    	MoneytrackinProjectsListAdapter adapter = new MoneytrackinProjectsListAdapter(this, projects);
        setListAdapter(adapter);
        final ListView lv = getListView();

		// When a project is clicked, pass the selected project and to the transactions activity
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				final Project proyecto = (Project) parent.getItemAtPosition(position);
				final Intent myIntent = new Intent(view.getContext(), TransactionsActivity.class);
				myIntent.putExtra("proyectoSerializable", proyecto);
				startActivityForResult(myIntent, 0);
			}
		});
    }

    // ***************************************
    // Private classes
    // ***************************************
    private class FechProjectsTask extends AsyncTask<Void, Void, List<Project>> {

        @Override
        protected void onPreExecute() {
            // before the network request begins, show a progress indicator
            showProgressDialog("Fetching projects...");
        }

        @Override
        protected List<Project> doInBackground(Void... params) {
            try {
            	return ProjectBasicBO.getProjectBasicBO().getProjects();
            } catch (Exception e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Project> projects) {
            // after the network request completes, hide the progress indicator
            dismissProgressDialog();
            showResult(projects);
        }
    }

}