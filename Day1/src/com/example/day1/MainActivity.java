package com.example.day1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
      final List<String> data = new ArrayList<String>();
      for(int i=0;i<10000;i++){
      	data.add(""+i);
      }
      
      final Activity act = this;
		
		BaseAdapter ad = new BaseAdapter() {
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				// TODO Auto-generated method stub
				TextView tv = new TextView(MainActivity.this);
				tv.setText(Integer.toString(arg0));;
				return tv;
			}
			
			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}
			
			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return arg0;
			}
			
			@Override
			public int getCount() {
				return 100;
			}
		};
		ListView listView = new ListView(this);
        listView.setAdapter(ad);
		

//		ListView listView = new ListView(this);
//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,data));
        setContentView(listView);
        
//        setContentView(R.layout.activity_main);
//		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				((TextView)findViewById(R.id.textView1)).setText("hahah");
//			}
//		});
        
	}
	

}
