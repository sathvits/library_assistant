package com.example.library;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Ncc_profile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ncc_profile);
		
		 Intent hh=getIntent();
	        String una=hh.getStringExtra("uname");
	        String pwd=hh.getStringExtra("pass");
	}


}
