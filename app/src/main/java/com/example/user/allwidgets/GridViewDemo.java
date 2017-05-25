package com.example.user.allwidgets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GridViewDemo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(GridViewDemo.this, ""+String.valueOf(i+1), Toast.LENGTH_SHORT).show();
                Intent intent= null;
                if(i==0){
                    intent = new Intent(GridViewDemo.this, TableDemo.class);
                    Toast.makeText(GridViewDemo.this, "Table Layout", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else if(i==1){
                    intent = new Intent(GridViewDemo.this, ExpandableListViewDemo.class);
                    Toast.makeText(GridViewDemo.this, "Expandable ListView", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else if(i==2){
                    intent = new Intent(GridViewDemo.this, TabDemo.class);
                    Toast.makeText(GridViewDemo.this, "Tabs Host", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }

    public void goToExpandableListView(View view){
        Intent i = new Intent(GridViewDemo.this, ExpandableListViewDemo.class);
        startActivity(i);
    }
}

class ImageAdapter extends BaseAdapter{//mandatory to override 4 methods

    Context context;
    Integer[] imageIDs = {R.drawable.blue_table, R.drawable.collapse_arrow, R.drawable.tab,
                          R.drawable.la_mode_4, R.drawable.la_mode_5, R.drawable.la_mode_6};

    ImageAdapter(Context context){
        super();
        this.context = context;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    // create a new ImageView for each item referenced by the Adapter
    /*  This method creates a new View for each image added to the ImageAdapter. When this is called,
    a View is passed in, which is normally a recycled object (at least after this has been called once),
    so there's a check to see if the object is null. If it is null, an ImageView is instantiated and
    configured with desired properties for the image presentation
     */
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imgView = null;
        if (view == null){
            //initialize some attributes
            imgView = new ImageView(context);
            imgView.setLayoutParams(new GridView.LayoutParams(200, 200));
            //so that all images appear with the same width and height regardless of their actual size
        }else{
            imgView = (ImageView) view;
        }
        if(position>=imageIDs.length){
            position = 0;
        }
        imgView.setImageResource(imageIDs[position]);
        //At the end of the getView() method, the position integer passed into the method is used to
        // select an image from the imageIDs array, which is set as the image resource for the ImageView.
        return imgView;
    }

    @Override
    public int getCount() {
        return imageIDs.length;
    }
}