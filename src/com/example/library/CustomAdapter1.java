package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter1 extends BaseAdapter{

    String [] result;
    Context context;
    String uname,pass;
 int [] imageId;
      private static LayoutInflater inflater=null;
    public CustomAdapter1(MainActivity1 mainActivity, String[] prgmNameList, int[] prgmImages,String unn,String pp) {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        uname=unn;
        pass=pp;
         inflater = ( LayoutInflater )context.
                 getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

             rowView = inflater.inflate(R.layout.programlist, null);
             holder.tv=(TextView) rowView.findViewById(R.id.textView1);
             holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

         holder.tv.setText(result[position]);
         holder.img.setImageResource(imageId[position]);

         rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                if(position==0)
                {
                	Intent vv=new Intent(context,MainActivityP.class);
                	vv.putExtra("uname", uname); 
                	vv.putExtra("pass", pass); 
					context.startActivity(vv);
                }
                
                if(position==2)
                {
                	Intent vv=new Intent(context,ShopHome.class);
                	vv.putExtra("uname", uname); 
                	vv.putExtra("pass", pass); 
					context.startActivity(vv);
                }
                
                if(position==3)
                {
                	Intent vv=new Intent(context,MainActivityLogin.class);
                	vv.putExtra("uname", uname); 
                	vv.putExtra("pass", pass); 
					context.startActivity(vv);
                }
                if(position==1)
                {
                	Intent vv=new Intent(context,MainActivityList.class);
                	vv.putExtra("uname", uname); 
                	vv.putExtra("pass", pass); 
					context.startActivity(vv);
                }
                
                if(position==4)
                {
                	Intent vv=new Intent(context,MainActivityList.class);
                	vv.putExtra("uname", uname); 
                	vv.putExtra("pass", pass); 
					context.startActivity(vv);
                }
                
                if(position==5)
                {
                	Intent vv=new Intent(context,MainActivityList.class);
                	vv.putExtra("uname", uname); 
                	vv.putExtra("pass", pass); 
					context.startActivity(vv);
                }
                if(position==6)
                {
                	Intent vv=new Intent(context,MainActivityLogin.class);
                	vv.putExtra("uname", uname); 
                	vv.putExtra("pass", pass); 
					context.startActivity(vv);
                }
                
                if(position==7)
                {
                	Intent vv=new Intent(context,MainActivityLogin.class);
                	vv.putExtra("uname", uname); 
                	vv.putExtra("pass", pass); 
					context.startActivity(vv);
                }
            }
        });

        return rowView;
    }

} 