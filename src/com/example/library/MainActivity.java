package com.example.library;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.GridView;

public class MainActivity extends Activity {

	 GridView gv;
     Context context;    
        ArrayList prgmName;    
        public static String [] prgmNameList={
        	"College Profile",
        	"Add Book",
        	"Student List",
        	"Voice",
        	"Result",
        	"Booking",
        	"Logout"};
    
        public static int [] prgmImages=
        {    	
    	R.drawable.col,
    	R.drawable.book,
    	R.drawable.stud,
    	R.drawable.ico_mic,
    	R.drawable.sea,
    	R.drawable.addicon,
    	R.drawable.lout
    	
    	
    	};    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        Intent hh=getIntent();
        String una=hh.getStringExtra("uname");
        String pwd=hh.getStringExtra("pass");
        
        gv=(GridView) findViewById(R.id.gridView1);      
        gv.setAdapter(new CustomAdapter(this, prgmNameList,prgmImages,una,pwd));
    }


    
}
