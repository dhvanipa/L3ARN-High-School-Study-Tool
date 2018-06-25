package com.letap.learnjava.poetryterms;

/**
 * Created by Dhvani on 04/12/2015.
 */

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Continent> continentList;
    private ArrayList<Continent> originalList;

    public ExpandableListAdapter(Context context, ArrayList<Continent> continentList) {
        this.context = context;
        this.continentList = new ArrayList<Continent>();
        this.continentList.addAll(continentList);
        this.originalList = new ArrayList<Continent>();
        this.originalList.addAll(continentList);
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
        return countryList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Country country = (Country) getChild(groupPosition, childPosition);
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expand_list_item, null);
        }


        TextView name = (TextView) convertView.findViewById(R.id.name);

        name.setText(country.getName().trim());


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
        return countryList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return continentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return continentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Continent continent = (Continent) getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView heading = (TextView) convertView.findViewById(R.id.heading);
        heading.setText(continent.getName().trim());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

    public void filterData(String query)
    {
        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        continentList.clear();

        if(query.isEmpty())
        {
            continentList.addAll(originalList);
        } else {
            for(Continent continent: originalList)
            {
                ArrayList<Country> countryList = continent.getCountryList();
                ArrayList<Country> newList = new ArrayList<Country>();
                for(Country country: countryList)
                {
                    if(country.getName().toLowerCase().contains(query))
                    {
                        newList.add(country);
                    }
                }
                if(newList.size() > 0)
                {
                    Continent nContinent = new Continent(continent.getName(), newList);
                    continentList.add(nContinent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        notifyDataSetChanged();
    }
}
