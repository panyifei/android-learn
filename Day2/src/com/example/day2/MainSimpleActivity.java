package com.example.day2;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;



public class MainSimpleActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
		ad.addAll("1","2","3","4");
		
		new AsyncTask<String, Void, JSONObject>() {
			
			@Override
			protected void onPostExecute(JSONObject result) {
				// TODO Auto-generated method stub
				JSONArray arr;
				try {
					Log.i("mine",result+"");
					arr = result.getJSONArray("list");
					if(arr.length() == 0){
						//isEnd = true;
						Log.i("mine","isend==true");
					}
					for(int i = 0;i < arr.length();i++){
						JSONObject obj = arr.getJSONObject(i);
						String title = obj.getString("title");
						ad.add(title);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			protected JSONObject doInBackground(String... params) {
				try {
					URL url = new URL(params[0]);
					Log.i("mine", "url:"+url);
					//InputStream ins = url.openConnection().getInputStream();
					URLConnection conn = url.openConnection();
					//为什么这一步，connection连接 有问题
					InputStream ins = conn.getInputStream();
					Log.i("mine", "3");
					byte[] buf= new byte[100000];
					Log.i("mine", "4");
					int l = ins.read(buf);
					Log.i("mine", "l:"+l);
					String str = new String(buf,0,l,"utf-8");
					Log.i("mine", "str:"+str);
					JSONObject jso = new JSONObject(str);
					return jso;
				} catch (Exception e) {
					Log.i("mine", "5");
					e.printStackTrace();
					return null;
				} 
			}
		}.execute("http://yimin.dp/pages/1.json");
		
		setListAdapter(ad);
	}
	

}
