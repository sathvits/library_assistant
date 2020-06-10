package com.example.library;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
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
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") public class MainActivityP extends Activity {

	private TextView txtSpeechInput;
	private EditText ed1;
	private ImageButton btnSpeak;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	
	private Button btn1;	
	
	ImageView Btnhew,BtnEnt;
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
	String targetamount1,una,pwd,pwd1;
	//EditText ed1,ed2;
	WebView wbb;
	String kk;
	int m;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainp);

		txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
		ed1 = (EditText) findViewById(R.id.editText1);
		btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

		// hide the action bar
		getActionBar().hide();
		btn1 = (Button) findViewById(R.id.button1);
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				promptSpeechInput();
				
				if(txtSpeechInput.getText().toString().length()>0)
				{
					ed1.setText(txtSpeechInput.getText().toString());
				}
			}
		});
		
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Intent vv=new Intent(getApplicationContext(),ShopHome.class);
            	//vv.putExtra("un", txtSpeechInput.getText().toString());             	 
				//startActivity(vv);
				new LoadData().execute();
			}
		});

	}

	/**
	 * Showing google speech input dialog
	 * */
	private void promptSpeechInput() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				getString(R.string.speech_prompt));		
		btn1.setVisibility(View.VISIBLE);
		ed1.setVisibility(View.VISIBLE);
		
		
		try {
			startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
		} catch (ActivityNotFoundException a) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.speech_not_supported),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Receiving speech input
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case REQ_CODE_SPEECH_INPUT: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				txtSpeechInput.setText(result.get(0));
			}
			break;
		}

		}
	}

	private class LoadData extends AsyncTask<Void, Void, Void> { 
		private ProgressDialog progressDialog;  
		@Override
		// can use UI thread here
		protected void onPreExecute() {
		this.progressDialog = ProgressDialog.show(MainActivityP.this, "Searching"," Loading...");  
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
						nameValuePairs.add(new BasicNameValuePair("name", txtSpeechInput.getText().toString()));						
						String U_selectProcess;
						U_selectProcess="http://sputnikinfotech.com/test_android/EV/selectlogin.php";						
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
							shopaddres2 = json_data.getString("eid"); 
							//al.add(targetmonth); 
							//al1.add(targetyear);
							//al2.add(targetamount);  
							 
							//listItemCount=al2.size();
							m=5;
						}
						if(m==5)
						{
						Intent it = new Intent(MainActivityP.this,Shop_Item_Home.class);
						Bundle bi=new Bundle();						
						bi.putString("ee1", shopid);
						bi.putString("ee2", shopname);
						bi.putString("ee3", shoparea);
						bi.putString("ee4", shopcity);
						bi.putString("ee5", shopph);
						bi.putString("ee6", shopaddres);
						bi.putString("ee7", shopaddres1);
						bi.putString("ee8", una);
						bi.putString("ee9", pwd);
						bi.putString("e1", shopaddres2);
						it.putExtras(bi);
					    startActivity(it);
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

