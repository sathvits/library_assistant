package com.example.library;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Shop_Item_Home extends Activity {

	Button Btnhew,BtnEnt,btn1,btn2,btn3,btn4;
	String sname,sarea,sphon,snear,sadd,sid,sid1,un,pw;
	TextView txtsname,txtarea,txtadd,txtadd1;
	
	ImageView Btnhew1,BtnEnt1;
	EditText edd;
	Button login,reg;
	JSONArray jArray;
	String result = null;
	InputStream is = null;
	StringBuilder sb = null; 
	ArrayList<String> al = new ArrayList<String>();
	ArrayList<String> al1 = new ArrayList<String>();
	ArrayList<String> al2 = new ArrayList<String>();
	String shopname;
	String shopcity;
	String shoparea;
	String shoploc;
	String shopph;
	String shopid;
	String shopaddres,shopaddres1,shopaddres2;
	String targetyear;
	String targetamount;
	String targetmonth1;
	String targetyear1;
	String targetamount1,una,pwd;
	EditText ed1,ed2;
	WebView wbb;
	int m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop__item__home);	
		
		
		txtsname=(TextView)findViewById(R.id.bookname);
		txtarea=(TextView)findViewById(R.id.bookavail);
		txtadd=(TextView)findViewById(R.id.bookrack);
		txtadd1=(TextView)findViewById(R.id.bookedition);
		
		Bundle b = getIntent().getExtras();       
		if(b !=null)
		{
			
			sname=getIntent().getExtras().getString("ee1");
			sarea=getIntent().getExtras().getString("ee2");
			snear=getIntent().getExtras().getString("ee3");
			sphon=getIntent().getExtras().getString("ee4");
			sadd=getIntent().getExtras().getString("ee5");
			sid=getIntent().getExtras().getString("ee6");
			sid1=getIntent().getExtras().getString("ee7");
			un=getIntent().getExtras().getString("ee8");
			pw=getIntent().getExtras().getString("ee9");
			shopaddres2=getIntent().getExtras().getString("e1");
			//Toast.makeText(getApplicationContext(), dname, Toast.LENGTH_LONG).show();
		}
		
		txtsname.setText(snear);
		txtarea.setText(sid1);
		txtadd.setText(sadd);
		txtadd1.setText(sid);
		
		Btnhew=(Button)findViewById(R.id.button1);
		BtnEnt=(Button)findViewById(R.id.button2);
		
		Btnhew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String avail=txtarea.getText().toString();
				
				if(avail.equals("Yes"))
				{				
					//Toast.makeText(getApplicationContext(), snear+" book is not available"+shopaddres2, Toast.LENGTH_LONG).show();
					new LoadData().execute();
				}else
				{
					Toast.makeText(getApplicationContext(), snear+" book is not available", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		BtnEnt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String avail=txtarea.getText().toString();
				
				if(avail.equals("No"))
				{				
					Toast.makeText(getApplicationContext(), snear+" book is available on Next Week", Toast.LENGTH_LONG).show();
					//new LoadData().execute();
				}else
				{
					Toast.makeText(getApplicationContext(), snear+" book is available", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}
		
	private class LoadData extends AsyncTask<Void, Void, Void> { 
		private ProgressDialog progressDialog;  
		@Override
		// can use UI thread here
		protected void onPreExecute() {
		this.progressDialog = ProgressDialog.show(Shop_Item_Home.this, "Updating..."," Loading...");  
		}
		@Override
		protected void onPostExecute(final Void unused) {  
			try{
				
	 				 //listview.setAdapter(new DataAdapter(ReportComplaint.this,al.toArray(new String[al.size()]),al1.toArray(new String[al1.size()]),al2.toArray(new String[al2.size()])));
				     this.progressDialog.dismiss();
				}
	 			catch(Exception e){ 
	 				Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
	 			}  
		}
		@Override
		protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub  
			// HTTP post 
					try { 
											
						ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();						
						nameValuePairs.add(new BasicNameValuePair("name", shopaddres2));						
						String U_selectProcess;
						U_selectProcess="http://sputnikinfotech.com/test_android/EV/search.php";						
						String paramString = URLEncodedUtils.format(nameValuePairs, "utf-8");
						U_selectProcess += "?" + paramString;						
						HttpClient httpclient = new DefaultHttpClient(); 
						try{
						HttpPost httppost = new HttpPost(U_selectProcess);
					
						StringEntity se = new StringEntity("envelope",HTTP.UTF_8);
						httppost.setEntity(se); 
						HttpParams httpParameters = new BasicHttpParams();
						// Set the timeout in milliseconds until a connection is established.
						int timeoutConnection = 3000;
						HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
						// Set the default socket timeout (SO_TIMEOUT) 
						// in milliseconds which is the timeout for waiting for data.
						int timeoutSocket = 3000;
						HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket); 
						
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						HttpResponse response = httpclient.execute(httppost);
						HttpEntity entity = response.getEntity();
						is = entity.getContent(); 
						}
						catch(Exception e){
							Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
						}						
						try{
						BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 80);
						sb = new StringBuilder();
						sb.append(reader.readLine() + "\n");
						String line = "0";
						while ((line = reader.readLine()) != null) {
							sb.append(line + "\n");
						}
						is.close();
						result = sb.toString();
						}
						catch(Exception e){
							Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
						}
						try{
						jArray = new JSONArray(result);
						JSONObject json_data = null;
						for (int i = 0; i < jArray.length(); i++) {
							json_data = jArray.getJSONObject(i); 
							shopid=json_data.getString("username");
							shopname=json_data.getString("password");
							shoparea = json_data.getString("email"); 
							shopcity = json_data.getString("ph"); 
							shopph = json_data.getString("typ"); 
							shopaddres = json_data.getString("trno"); 
							shopaddres1 = json_data.getString("tbank"); 
							//al.add(targetmonth); 
							//al1.add(targetyear);
							//al2.add(targetamount);  
							 
							//listItemCount=al2.size();
							m=5;
						}
						if(m==5)
						{											
							Toast.makeText(getApplicationContext(), "Records Updated", Toast.LENGTH_LONG).show();
						}else
						{
							Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_LONG).show();
						}
							
					}
					catch(JSONException e){
						Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
					}	
					} catch (ParseException e) {
					//	Log.e("log_tag", "Error in http connection" + e.toString());
						Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
					}  
					 catch (Exception e) {
							//	Log.e("log_tag", "Error in http connection" + e.toString());
								Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
							}  		 
			return null; 
		}
	} 	

}
