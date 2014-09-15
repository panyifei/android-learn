package com.example.day3;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		SharedPreferences spf = getSharedPreferences("prefs", MODE_PRIVATE);
//		spf.edit().putString("id","123").commit();
//		Log.i("mine", spf.getString("id", "none"));
		
	}
	


}
