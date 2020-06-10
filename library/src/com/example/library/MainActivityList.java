package com.example.library;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.app.AlertDialog;  
import android.widget.Toast;  

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;


public class MainActivityList extends Activity {

    ListView SubjectFullFormListView;
    ProgressBar progressBar;
    String HttpURL = "http://sputnikinfotech.com/test_android/EV/searchall.php";
    ListAdapter adapter ;
    List<Subject> SubjectFullFormList;
    EditText editText ;
    AlertDialog.Builder builder; 
    Subject subject;
    String una,pwd;
    
    private static String URLL = "http://sputnikinfotech.com/test_android/index1.php";

    private static final String KEY_EMPTY = "";


    JSONParser jsonParser = new JSONParser();

    int i = 0;

    private String plae;
    private String ddate;
    private String institute;
    private String address;
    private String Email;
    private String Phone,Phone1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main_list);

        SubjectFullFormListView = (ListView) findViewById(R.id.SubjectFullFormListView);
        builder = new AlertDialog.Builder(this);  
        //editText = (EditText)findViewById(R.id.edittext1);
        Intent hh=getIntent();
         una=hh.getStringExtra("uname");
         pwd=hh.getStringExtra("pass");
        
        Toast.makeText(getApplicationContext(),una, Toast.LENGTH_LONG).show();


        progressBar = (ProgressBar) findViewById(R.id.ProgressBar1);

        new ParseJSonDataClass(this).execute();


    }

    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }
                               
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpURL);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);
                            JSONObject jsonObject;
                           

                            SubjectFullFormList = new ArrayList<Subject>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                subject = new Subject();

                                jsonObject = jsonArray.getJSONObject(i);

                                subject.Subject_Name = jsonObject.getString("username");

                                subject.Subject_Full_Form = jsonObject.getString("password");

                                subject.Subject_Full_Form1 = jsonObject.getString("email");

                                subject.Subject_Full_Form2 = jsonObject.getString("ph");

                                subject.Subject_Full_Form3 = jsonObject.getString("eid");

                               subject.Subject_Full_Form4 = jsonObject.getString("typ");

                                subject.Subject_Full_Form5 = jsonObject.getString("trno");

                               // subject.Subject_Full_Form6 = jsonObject.getString("tbank");

                                //subject.Subject_Full_Form7 = jsonObject.getString("tdate");

                                //subject.Subject_Full_Form8 = jsonObject.getString("st1");

                                //subject.Subject_Full_Form9 = jsonObject.getString("st2");

                                SubjectFullFormList.add(subject);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            progressBar.setVisibility(View.GONE);

            SubjectFullFormListView.setVisibility(View.VISIBLE);

                adapter = new ListAdapter(SubjectFullFormList, context);

                SubjectFullFormListView.setAdapter(adapter);

            SubjectFullFormListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                	//Intent vv=new Intent(context,MainActivityList.class);
                	//vv.putExtra("kk", subject.Subject_Name.toString()); 
                	//vv.putExtra("jj", subject.Subject_Full_Form.toString()); 
                	//vv.putExtra("hh", subject.Subject_Full_Form1.toString()); 
                	//vv.putExtra("gg", subject.Subject_Full_Form2.toString()); 
                //	vv.putExtra("dd", uu); 
                	//vv.putExtra("ss", pp); 
					//context.startActivity(vv);
                	   plae =subject.Subject_Name.toString();
                	   ddate=subject.Subject_Full_Form.toString();
                	      institute=subject.Subject_Full_Form1.toString();
                	      address=subject.Subject_Full_Form2.toString();
                	      Email=subject.Subject_Full_Form3.toString();
                	      Phone=subject.Subject_Full_Form4.toString();
                	      Phone1=subject.Subject_Full_Form5.toString();
                	
                	  builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);  
                	  
                      //Setting message manually and performing action on button click  
                      builder.setMessage("Do you want to close this application ?")  
                              .setCancelable(false)  
                              .setPositiveButton("Booking", new DialogInterface.OnClickListener() {  
                                  public void onClick(DialogInterface dialog, int id) {  
                                	  
                                	  AttemptLogin attemptLogin= new AttemptLogin();
              	                    attemptLogin.execute(
              	                    		plae,
              	                    		ddate,
              	                    		institute,
              	                    		address,
              	                    		Email,
              	                    		Phone,
              	                    		"EVEB",
              	                    		"No",
              	                    		Phone1,
              	                    		una,
              	                    		pwd,
              	                    		"1");

                                      finish();  
                                      Toast.makeText(getApplicationContext(),"you choose book is booking",  
                                      Toast.LENGTH_SHORT).show();  
                                  }  
                              })  
                              .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
                                  public void onClick(DialogInterface dialog, int id) {  
                                      //  Action for 'NO' Button  
                                      dialog.cancel();  
                                      Toast.makeText(getApplicationContext(),"Booking is cancel",  
                                      Toast.LENGTH_SHORT).show();  
                                  }  
                              });  
                      //Creating dialog box  
                      AlertDialog alert = builder.create();  
                      //Setting the title manually  
                      alert.setTitle("AlertDialogExample");  
                      alert.show();  

                }
            });

        }
    }
    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override

        protected JSONObject doInBackground(String... args) {



            String email = args[2];
            String password = args[1];
            String name= args[0];

            String email1 = args[5];
            String password1 = args[4];
            String name1= args[3];

            String email2 = args[8];
            String password2 = args[7];
            String name2= args[6];

            // String email3 = args[11];
            String password3 = args[10];
            String name3= args[9];

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", name));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("email",email));
            params.add(new BasicNameValuePair("username1", name1));
            params.add(new BasicNameValuePair("password1", password1));
            params.add(new BasicNameValuePair("email1",email1));

            params.add(new BasicNameValuePair("username2", name2));
            params.add(new BasicNameValuePair("password2", password2));
            params.add(new BasicNameValuePair("email2",email2));

            params.add(new BasicNameValuePair("username3", name3));
            params.add(new BasicNameValuePair("password3", password3));


            JSONObject json = jsonParser.makeHttpRequest(URLL, "POST", params);


            return json;

        }

        protected void onPostExecute(JSONObject result) {

            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),"DDDDDD", Toast.LENGTH_LONG).show();

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}


