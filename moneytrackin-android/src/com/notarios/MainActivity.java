/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.notarios;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.notarios.bo.impl.ProjectBasicBO;
import com.notarios.bo.impl.TransactionBasicBO;
import com.notarios.dao.impl.online.ProjectOnlineDAO;
import com.notarios.dao.impl.online.TransactionOnlineDAO;
import com.notarios.util.SimpleMD5;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	public static final String PREFS_NAME ="MoneytrackinPrefsFile";

	/**
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity_layout);
		final Button b = (Button) findViewById(R.id.saveCredendtialsButton);
		loadSharedPreferences();
		
		// If save button is pressed, save and return
		b.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				saveSharedPreferences();
				initalizeBO();
				startActivity(new Intent(view.getContext(), ProjectsActivity.class));
			}
		});
	}
	
	private void loadSharedPreferences() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		final EditText usernameText = (EditText) findViewById(R.id.username);
		final EditText passwordText = (EditText) findViewById(R.id.password);
		final CheckBox saveCredentials = (CheckBox) findViewById(R.id.save);
		usernameText.setText(settings.getString("username", ""));
		passwordText.setText(settings.getString("password", ""));
		saveCredentials.setChecked(settings.getBoolean("saveCredentials", false));
	}
	
	private void saveSharedPreferences() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		final EditText usernameText = (EditText) findViewById(R.id.username);
		final EditText passwordText = (EditText) findViewById(R.id.password);
		final CheckBox saveCredentials = (CheckBox) findViewById(R.id.save);
		editor.putBoolean("saveCredentials", saveCredentials.isChecked());
		if (saveCredentials.isChecked()) {
			editor.putString("username", usernameText.getText().toString());
			editor.putString("password", passwordText.getText().toString());
		} else {
			editor.putString("username", "");
			editor.putString("password", "");
		}
		editor.commit();
	}
	
	private void initalizeBO() {
		final EditText usernameText = (EditText) findViewById(R.id.username);
		final EditText passwordText = (EditText) findViewById(R.id.password);
		try {
			ProjectBasicBO.initProjectBasicBO(new ProjectOnlineDAO(usernameText.getText().toString(), SimpleMD5.MD5(passwordText.getText().toString())));
			TransactionBasicBO.initTransactionBasicBO(new TransactionOnlineDAO(usernameText.getText().toString(), SimpleMD5.MD5(passwordText.getText().toString())));
			Log.i("MONEYTRACKIN", "USername: " + usernameText.getText().toString());
			Log.i("MONEYTRACKIN", "PAssword: " + SimpleMD5.MD5(passwordText.getText().toString()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
