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

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Credentials;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TwoLineListItem;

import com.notarios.bo.ProjectBO;
import com.notarios.bo.impl.ProjectBasicBO;
import com.notarios.bo.impl.TransactionBasicBO;
import com.notarios.dao.impl.online.ProjectOnlineDAO;
import com.notarios.dao.impl.stub.TransactionStubDAO;
import com.notarios.model.Project;
import com.notarios.util.Utils;

/**
 * Principal Activity of the moneytrackin application.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public class Principal extends ListActivity {
	public static final String PREFS_NAME = "MoneytrackinPrefFile";
	public static final int CREDENTIALS_CODE = 5;
	private final List<Project> projects = new ArrayList<Project>();
	private ArrayAdapter<Project> projectAdapter;
	
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Creates a project adapter that will show the projects
		projectAdapter = getListProjectAdapter();
		// Set the project adapter as the list adapter for this activity
		setListAdapter(projectAdapter);

		// Sets the listener for the project clicks
		final ListView listView = getListView();
		listView.setOnItemClickListener(getProjectClickListener());
		// Initializes the business objects with the user credentials
		intializeBoWithUserCredentials();
		updateProjectListView();
	}


	/**
	 * Returns the ArrayAdapter for projects
	 * @return The ArrayAdapter for projects
	 */
	private ArrayAdapter<Project> getListProjectAdapter() {
		return new ArrayAdapter<Project>(this, R.layout.list_item, projects) {
			@Override
			public View getView(final int position, final View convertView, final ViewGroup parent) {
				View row;
				// We need the layoutinflater to pick up the view from xml
				final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
				if (null == convertView) {
					row = inflater.inflate(R.layout.list_item, null);
				} else {
					row = convertView;
				}
				final TwoLineListItem view = (TwoLineListItem) row;
				view.getText1().setText((getItem(position)).getName());
				view.getText2().setText(String.valueOf((getItem(position)).getAmount()));
				return row;
			}
		};
	}
	/**
	 * Returns the OnItemClickListener for projects
	 * @return The click listener for project list items
	 */
	private OnItemClickListener getProjectClickListener() {
		return new OnItemClickListener() {
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				final Project proyecto = (Project) parent.getItemAtPosition(position);
				final Intent myIntent = new Intent(view.getContext(), ActivityProject.class);
				myIntent.putExtra("proyectoSerializable", proyecto);
				startActivityForResult(myIntent, 0);
			}
		};
	}
	/**
	 * Initializes business objects
	 */
	private void initBusinessObjects(String[] credentials) {
		ProjectBasicBO.initProjectBasicBO(new ProjectOnlineDAO(credentials[0], credentials[1]));
		TransactionBasicBO.initTransactionBasicBO(new TransactionStubDAO());
	}
	
	/**
	 * Initializes the BO with the username and password saved in shared preferences. If no one are found
	 * it shows a modal window and asks for them 
	 */
	private void intializeBoWithUserCredentials() {
		final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		if (settings != null && settings.contains("USERNAME")) {
			initBusinessObjects(new String[] {settings.getString("USERNAME", ""), settings.getString("PASSWORD", "")});
		} else {
			final Dialog dialog = new Dialog(this);
			dialog.setContentView(R.layout.login_activity);
			dialog.setTitle("User credentials");
			dialog.show();
			Button buttonOK = (Button) dialog.findViewById(R.id.saveCredentials);
			buttonOK.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final String username = ((EditText) dialog.findViewById(R.id.username)).getText().toString();
					final String password = ((EditText) dialog.findViewById(R.id.password)).getText().toString();
					final boolean save = ((CheckBox) dialog.findViewById(R.id.saveUserCredentials)).isChecked();
					if (save) {
						Editor editor = settings.edit();
						editor.putString("USERNAME", username);
						editor.putString("PASSWORD", Utils.md5(password));
						editor.commit();	
					}
					initBusinessObjects(new String[] {username, Utils.md5(password)});
					dialog.dismiss();
				}
			});

		}
	}
	
	/**
	 * Update the list of projects in the view
	 */
	private void updateProjectListView() {
		final ProjectBO projectBo = ProjectBasicBO.getProjectBasicBO();
		projectAdapter.clear();
		for (Project p : projectBo.getProjects()) {
			projectAdapter.add(p);
		}
	}

}