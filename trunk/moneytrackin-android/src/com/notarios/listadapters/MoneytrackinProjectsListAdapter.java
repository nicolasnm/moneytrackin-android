package com.notarios.listadapters;

import java.text.DecimalFormat;
import java.util.List;


import com.notarios.R;
import com.notarios.model.Project;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

public class MoneytrackinProjectsListAdapter extends BaseAdapter {
	DecimalFormat dec = new DecimalFormat("###.##");
	private List<Project> projects;
    private final LayoutInflater layoutInflater;
    public MoneytrackinProjectsListAdapter(Context context, List<Project> projects) {
        this.projects = projects;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public int getCount() {
        return projects == null ? 0 : projects.size();
    }

    public Project getItem(int position) {
        return projects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, final View convertView, final ViewGroup parent) {
    	View row;
		// We need the layoutinflater to pick up the view from xml
		if (null == convertView) {
			row = layoutInflater.inflate(R.layout.project_item, null);
		} else {
			row = convertView;
		}
		((TextView)row.findViewById(R.id.layoutProjectName)).setText(getItem(position).getName());
		((TextView)row.findViewById(R.id.layoutProjectAmount)).setText(dec.format(getItem(position).getAmount()));
		return row;
	}
}
