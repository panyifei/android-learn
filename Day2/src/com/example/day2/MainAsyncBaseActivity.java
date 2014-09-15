package com.example.day2;

import java.util.ArrayList;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class MainAsyncBaseActivity extends ListActivity {
	Myadapter ad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ad = new Myadapter();
		setListAdapter(ad);
		getListView().setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						if (position == ad.getCount() - 1) {
							ad.errMessage = null;
							TwitterRestClient.get("pages/"+(++ad.page)+".json", null, jhrh);
							ad.notifyDataSetChanged();
						}
					}
				});
	}
	
	JsonHttpResponseHandler jhrh=new JsonHttpResponseHandler() {
		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			//ad.isLoading = false;
			try {
				Log.i("mine", response.toString());
                JSONArray arr =  response.getJSONArray("list");
                if (arr.length() == 0) {
					ad.isEnd = true;
				}
                for (int i = 0; i < arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
					String title = obj.getString("title");
					ad.arrlist.add(title);
				}
                //用来提示已经有修改
				ad.notifyDataSetChanged();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.onSuccess(statusCode, headers, response);
		}
    };
	
	class Myadapter extends BaseAdapter{
		ArrayList<String> arrlist = new ArrayList<String>();
		int page;
		boolean isEnd = false;
		boolean isLoading;
		String errMessage;
		
		@Override
		public int getCount() {
			return arrlist.size()+(isEnd ? 0 : 1);
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(position < arrlist.size()){
				String title = arrlist.get(position);
				TextView tv = (TextView) getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent ,false);
				tv.setText(title);
				return tv;
			}else if(errMessage != null){
				return getLayoutInflater().inflate(R.layout.error, parent,
						false);
			}else{
				Log.i("mine", "loading exist");
				if (!isLoading) {
					isLoading = true;
					errMessage = null;
					TwitterRestClient.get("pages/"+(++page)+".json", null, jhrh);
				}
				return getLayoutInflater().inflate(R.layout.loading, parent,
						false);
			}
		}
		
	}
}
