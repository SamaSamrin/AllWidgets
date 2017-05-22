package com.example.user.allwidgets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewDemo extends Activity {

    final static String TAG = "**ExpandableListViewDemo**";

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

    public void goToTable(View view){
        Intent i = new Intent(ExpandableListViewDemo.this, TableDemo.class);
        startActivity(i);
    }
}

class ExpandableListAdapterDemo extends BaseExpandableListAdapter{

    static final String TAG = "ExListAdapterDemo";

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

//    ExpandableListAdapterDemo(Context context){
//        this.context = context;
//    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.listDataChild.get(this.listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.listDataChild.get(this.listDataHeader.get(i)).get(i1);
        //i group position or header position and i1 child position
        //i positioned group/header-er i1 position er child ke ferot pathacche
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;//i1 is child position
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String groupHeaderTitle = (String) getGroup(i);
        if(view == null){
            Log.e(TAG, "getGroupView : null view");
            LayoutInflater inf = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.list_headers, null);
        }
        TextView header = (TextView) view.findViewById(R.id.header);
        header.setText(groupHeaderTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String childTitle = (String) getChild(i, i1);
        if (view == null){
            LayoutInflater inf = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.list_children, null);
        }
        TextView child = (TextView) view.findViewById(R.id.list_item) ;
        child.setText(childTitle);
//        if(i1 == 0)
//            child = (TextView) view.findViewById(R.id.list_item_apple);
//        else if (i1 == 1)
//            child = (TextView) view.findViewById(R.id.list_item_orange);
//        else if (i1 == 2)
//            child = (TextView) view.findViewById(R.id.list_item_mango);
//        if (child != null)
//            child.setText(childTitle);
//        else
//            Log.e(TAG, "child textview is null, child position greater than 2");
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
