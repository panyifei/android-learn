package com.example.day3;



import com.activeandroid.query.Select;

import android.app.Activity;
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
		
//		File f = new File(getFilesDir(),"abc.txt");
//
//		try {
//			FileOutputStream fs = new FileOutputStream(f);
//			fs.write("write".getBytes("utf-8"));
//			fs.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Person p = new Person();
//		p.name = "panyifei";
//		Log.i("mine",p.name);
//		p.save();
//		Log.i("mine","success");
		
		Person p = new Select().from(Person.class).where("Name=?","panyifei").executeSingle();
		Log.i("mine",p.name);
	}
	


}
