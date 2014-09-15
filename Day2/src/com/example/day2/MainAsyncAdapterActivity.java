package com.example.day2;



import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import com.loopj.android.http.*;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;



public class MainAsyncAdapterActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
		ad.addAll("1","2","3","4");
		Log.i("mine", "start");
		
		JsonHttpResponseHandler jhrh=new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				Log.i("mine", "success jsonobject");
				try {
					Log.i("mine", response.toString());
	                JSONArray arr =  response.getJSONArray("list");
	                for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						String title = obj.getString("title");
						ad.add(title);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				super.onSuccess(statusCode, headers, response);
			}
        };
        
		TwitterRestClient.get("pages/1.json", null, jhrh);
		
		setListAdapter(ad);
	}
	

}
