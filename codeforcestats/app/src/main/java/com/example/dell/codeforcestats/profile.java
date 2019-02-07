package com.example.dell.codeforcestats;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class profile extends AppCompatActivity {

    String handlename;
    ImageView image;
    TextView handle;
    TextView name;
    TextView country;
    TextView max;
    TextView current;
    TextView rank;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        Intent getme=getIntent();
        b1=findViewById(R.id.seestat);

        handlename=getme.getStringExtra("handle");

        image=findViewById(R.id.image);
        handle=findViewById(R.id.handle);
        name=findViewById(R.id.name);
        country=findViewById(R.id.country);
        max=findViewById(R.id.max);
        current=findViewById(R.id.current);
        rank=findViewById(R.id.rank);

b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(profile.this,rating.class);
        i.putExtra("handle",handlename);
        startActivity(i);
    }
});
updateview(handlename);


    }

    private void updateview(String topic) {

        networktask nt=new networktask();
        nt.execute("https://codeforces.com/api/user.info?handles="+topic);

    }
   class networktask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String url=strings[0];
            try {
                URL url1=new URL(url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url1.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                Scanner scanner=new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext())
                {
                    String s=scanner.next();
                    return s;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "failed to load";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList<userinfo> user=parsejson(s);

            Log.e("Mainact",""+user.size());
            try{
Picasso.get().load(user.get(0).getImage()).into(image);

            handle.setText(user.get(0).getHandle());
            name.setText("NAME : "+user.get(0).getFirstname());
            country.setText("COUNTRY : "+user.get(0).getCountry());
            current.setText("CURRENT RATING : "+user.get(0).getCurrent());
            rank.setText("RANK : "+user.get(0).getRank());
            max.setText("MAXRATING : "+user.get(0).getMaxrating());

            Log.e("Mainact",user.get(0).getImage());

        }
        catch (Exception e)
        {
            Toast.makeText(profile.this,"INCORRECT HANDLE",Toast.LENGTH_SHORT).show();
        }
        }
    }
    ArrayList<userinfo> parsejson(String s)
    {
        ArrayList<userinfo> ar=new ArrayList<>();
        try {
            JSONObject root=new JSONObject(s);
            JSONArray item=root.getJSONArray("result");
            for (int i = 0; i < item.length(); i++) {
                JSONObject user=item.getJSONObject(i);
                String handle=user.getString("handle");
                String image=user.getString("titlePhoto");
                String name=user.getString("firstName");
                String country=user.getString("country");
                String rating=user.getString("rating");
                String rank=user.getString("rank");
                String maxrating=user.getString("maxRating");

                userinfo users=new userinfo(handle,name,rating,country,image,rank,maxrating);
                ar.add(users);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //parseing json

        return ar;
    }
}
