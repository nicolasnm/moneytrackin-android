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

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TwoLineListItem;

import com.notarios.bo.ProjectBO;
import com.notarios.bo.impl.ProjectBasicBO;
import com.notarios.bo.impl.TransactionBasicBO;
import com.notarios.dao.impl.online.ProjectOnlineDAO;
import com.notarios.dao.impl.stub.TransactionStubDAO;
import com.notarios.model.Project;

/**
 * Principal Activity of the moneytrackin application.
 *
 * @version 1.0 12/03/2011
 * @author Roberto Notario McDonnell
 *         Nicolás Notario McDonnell
 */
public class Principal extends ListActivity {
	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Inits the Business Objects.
		ProjectBasicBO.initProjectBasicBO(new ProjectOnlineDAO("nicolasnotario", "cac21b2f8af71ab99bd389ad17fb3dc5"));
		TransactionBasicBO.initTransactionBasicBO(new TransactionStubDAO());

		final ProjectBO projectBo = ProjectBasicBO.getProjectBasicBO();
		final List<Project> projects = projectBo.getProjects();

		// Set a list adapter for the list of projects
		setListAdapter(new ArrayAdapter<Project>(this, R.layout.list_item, projects) {
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

		});

		final ListView lv = getListView();

		// When a project is clicked, pass the selected project and to the transactions activity
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
				final Project proyecto = (Project) parent.getItemAtPosition(position);
				final Intent myIntent = new Intent(view.getContext(), ActivityProject.class);
				myIntent.putExtra("proyectoSerializable", proyecto);
				startActivityForResult(myIntent, 0);
			}
		});

	}

}