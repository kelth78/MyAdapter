package com.creative.myadapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

//
// Demo 1 use arrayAdapter (default use String type only)
// Demo 2 extend arrayAdapter (custom class)
//
public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // Demo 1: Cheese's name only
    String [] cheeses1 =  {
            "Parmesan",
            "Mozzarella",
            "Cheddar",
            "Fontina",
            "Ricotta"
    };

    // Demo 2: Cheese's name and description
    static class ViewHolder{ // Use ViewHolder to cache TextView objects. Better performance
        TextView cheeseName;
        TextView cheeseDesc;
    }
    static class Cheese {
        String name;
        String desc;

        public Cheese(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
    }
    Cheese [] cheeses2 = {
            new Cheese("Parmesan", "Hard, granular cheese"),
            new Cheese("Mozzarella", "Southern Italian buffalo milk cheese"),
            new Cheese("Cheddar", "Firm, cow's milk cheese"),
            new Cheese("Fontina", "Italian cow's milk cheese"),
            new Cheese("Ricotta", "Italian whey cheese")
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //
        // Demo 1
        //
        /*
        ArrayAdapter<String> cheeseAdapter = new ArrayAdapter<String>(
                this,
                R.layout.item,
                R.id.cheese_name,
                cheeses1
        );

        // Programmatically add ListView or GridView
        //ListView cheeseList = new ListView(this);
        //setContentView(cheeseList);
        //cheeseList.setAdapter(cheeseAdapter);
        GridView cheeseGrid = new GridView(this);
        setContentView(cheeseGrid);
        cheeseGrid.setNumColumns(2);
        cheeseGrid.setAdapter(cheeseAdapter);

        cheeseGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long rowId) {
                String msg = "You clicked " + cheeses1[pos];
                Log.d(TAG, msg);
            }
        });
        */

        //
        // Demo 2
        //
        ArrayAdapter<Cheese> cheeseAdapter = new ArrayAdapter<Cheese>(this, 0, cheeses2) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //return super.getView(position, convertView, parent);
                Cheese currentCheese = cheeses2[position];

                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.custom_item, null, false);
                    ViewHolder viewHolder = new ViewHolder();
                    viewHolder.cheeseName = convertView.findViewById(R.id.cheese_name);
                    viewHolder.cheeseDesc = convertView.findViewById(R.id.cheese_description);
                    convertView.setTag(viewHolder); // Save
                }

                /* Use cache objects in viewHolder instead of always query for the object
                TextView name = convertView.findViewById(R.id.cheese_name);
                TextView desc = convertView.findViewById(R.id.cheese_description);
                name.setText(currentCheese.name);
                desc.setText(currentCheese.desc);*/
                TextView cheeseName = ((ViewHolder)convertView.getTag()).cheeseName;
                TextView cheeseDescription = ((ViewHolder)convertView.getTag()).cheeseDesc;
                cheeseName.setText(currentCheese.name);
                cheeseDescription.setText(currentCheese.desc);

                return convertView;
            }
        };

        ListView cheeseList = new ListView(this);
        setContentView(cheeseList);
        cheeseList.setAdapter(cheeseAdapter);
    }
}
