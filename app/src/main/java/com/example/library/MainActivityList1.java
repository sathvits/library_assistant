package com.example.library;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
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


public class MainActivityList1 extends Activity {

    ListView SubjectFullFormListView;
    ProgressBar progressBar;
    String HttpURL = "http://sputnikinfotech.com/test_android/EV/SubjectFullForm6.php";
    ListAdapter1 adapter ;
    List<Subject> SubjectFullFormList;
    EditText editText ;
    Subject subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main_list);

        SubjectFullFormListView = (ListView) findViewById(R.id.SubjectFullFormListView);

        //editText = (EditText)findViewById(R.id.edittext1);
        Intent hh=getIntent();
        String una=hh.getStringExtra("bk");
        //String pwd=hh.getStringExtra("pass");
        
        //Toast.makeText(getApplicationContext(),una, Toast.LENGTH_LONG).show();


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

                                subject.Subject_Full_Form = jsonObject.getString("email");

                                subject.Subject_Full_Form1 = jsonObject.getString("tdate");

                                subject.Subject_Full_Form2 = jsonObject.getString("st1");

                                //subject.Subject_Full_Form3 = jsonObject.getString("eid");

                               // subject.Subject_Full_Form4 = jsonObject.getString("typ");

                               // subject.Subject_Full_Form5 = jsonObject.getString("trno");

                               // subject.Subject_Full_Form6 = jsonObject.getString("tbank");

                               // subject.Subject_Full_Form7 = jsonObject.getString("tdate");

                               // subject.Subject_Full_Form8 = jsonObject.getString("st1");

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

                adapter = new ListAdapter1(SubjectFullFormList, context);

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

                }
            });

        }
    }

}