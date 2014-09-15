package com.example.day2;
import java.io.InputStream;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MainTeachersActivity extends ListActivity {
	ArrayAdapter<String> adapter;
	int page;
	boolean isEnd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (position == getCount() - 1 && !isEnd) {
					page++;
					new Task().execute("http://yimin.dp/pages/" + page
							+ ".json");
				}
				return super.getView(position, convertView, parent);
			}
		};
		setListAdapter(adapter);
		Log.i("abc123", "start get json");
		new Task().execute("http://yimin.dp/pages/1.json");
		page = 1;
	}

	class Task extends AsyncTask<String, Void, JSONObject> {
		@Override
		protected JSONObject doInBackground(String... params) {
			try {
				Log.i("abc123", "open connection");
				URL url = new URL(params[0]);
				InputStream ins = url.openConnection().getInputStream();
				byte[] buf = new byte[1000000];
				int l = ins.read(buf);
				String str = new String(buf, 0, l, "utf-8");
				JSONObject json = new JSONObject(str);
				Log.i("abc123", json.toString());
				Thread.sleep(1500);
				return json;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			try {
				JSONArray arr = result.getJSONArray("list");
				if (arr.length() == 0) {
					isEnd = true;
					Log.i("abc123", "isEnd=true");
				}
				for (int i = 0; i < arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
					String title = obj.getString("title");
					adapter.add(title);
				}
			} catch (Exception e) {
			}
		}
	}
}