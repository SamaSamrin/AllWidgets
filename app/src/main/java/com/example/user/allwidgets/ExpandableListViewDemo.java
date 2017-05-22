package com.example.user.allwidgets;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewDemo extends Activity {

    ExpandableListView elv;
    ExpandableListAdapterDemo adapter;
    List<String> headerList;
    HashMap<String, List<String>> childrenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view_demo);

        elv = (ExpandableListView) findViewById(R.id.expandable_listview);

        populateLists();

        adapter = new ExpandableListAdapterDemo(this, headerList, childrenList);
        elv.setAdapter(adapter);
    }

    void populateLists(){
        headerList = new ArrayList<String>();
        childrenList = new HashMap<String, List<String>>();

        //populate headers list
        headerList.add("Fruits");
        headerList.add("Bakery");
        headerList.add("Smoothies");

        //populate children list
        List<String> fruitsList = new ArrayList<String>();
        List<String> bakeryList = new ArrayList<String>();
        List<String> smoothiesList = new ArrayList<String>();

        fruitsList.add("Apple");
        fruitsList.add("Orange");
        fruitsList.add("Grapes");

        bakeryList.add("Cake");
        bakeryList.add("Muffin");
        bakeryList.add("Biscuits");

        smoothiesList.add("Mango Smmothie");
        smoothiesList.add("Mojito");
        smoothiesList.add("Fruit Punch");

        //assigning values to hash map keys
        childrenList.put(headerList.get(0), fruitsList);//prothom position e fruits List thakbe
        childrenList.put(headerList.get(1), bakeryList);
        childrenList.put(headerList.get(2), smoothiesList);
    }
}

class ExpandableListAdapterDemo extends BaseExpandableListAdapter{

    Context context = null;
    private List<String> listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> listDataChild;
    // Hashmap<Key, Value>, so prottekta string header-er jonne ekta string list mapped thakbe

    ExpandableListAdapterDemo(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    ExpandableListAdapterDemo(Context context){
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
