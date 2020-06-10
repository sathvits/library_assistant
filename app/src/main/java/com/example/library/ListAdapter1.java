package com.example.library;

/**
 * Created by Juned on 1/30/2017.
 */

import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter1 extends BaseAdapter
{
    Context context;

    List<Subject> subject_list;

    public ListAdapter1(List<Subject> listValue, Context context)
    {
        this.context = context;
        this.subject_list = listValue;
    }

    @Override
    public int getCount()
    {
        return this.subject_list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.subject_list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem1 viewItem = null;
        if(convertView == null)
        {
            viewItem = new ViewItem1();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.listvv, null);

            viewItem.SubNameTextView = (TextView)convertView.findViewById(R.id.days);

            viewItem.SubFullFormTextView = (TextView)convertView.findViewById(R.id.username);

            viewItem.SubFullFormTextView1 = (TextView)convertView.findViewById(R.id.cost);

            viewItem.SubFullFormTextView2 = (TextView)convertView.findViewById(R.id.cost1);

          //  viewItem.SubFullFormTextView3 = (TextView)convertView.findViewById(R.id.cost2);

           // viewItem.SubFullFormTextView4 = (TextView)convertView.findViewById(R.id.username);

        //    viewItem.SubFullFormTextView5 = (TextView)convertView.findViewById(R.id.datee);

          //  viewItem.SubFullFormTextView6 = (TextView)convertView.findViewById(R.id.datee);


            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem1) convertView.getTag();
        }

        viewItem.SubNameTextView.setText(""+subject_list.get(position).Subject_Name);

        viewItem.SubFullFormTextView.setText(""+subject_list.get(position).Subject_Full_Form);

        viewItem.SubFullFormTextView1.setText(""+subject_list.get(position).Subject_Full_Form1);

        viewItem.SubFullFormTextView2.setText(""+subject_list.get(position).Subject_Full_Form2);

      //  viewItem.SubFullFormTextView3.setText(""+subject_list.get(position).Subject_Full_Form3);

      //.SubFullFormTextView4.setText(""+subject_list.get(position).Subject_Full_Form4);

       // viewItem.SubFullFormTextView5.setText(""+subject_list.get(position).Subject_Full_Form5);

      //  viewItem.SubFullFormTextView6.setText(""+subject_list.get(position).Subject_Full_Form6+"\nRs:"+subject_list.get(position).Subject_Full_Form7+"\nDate:"+subject_list.get(position).Subject_Full_Form8);


        return convertView;
    }
}

class ViewItem1
{
    TextView SubNameTextView;
    TextView SubFullFormTextView;
    TextView SubFullFormTextView1;
    TextView SubFullFormTextView2;
    TextView SubFullFormTextView3;
    TextView SubFullFormTextView4;
    TextView SubFullFormTextView5;
    TextView SubFullFormTextView6;

}



