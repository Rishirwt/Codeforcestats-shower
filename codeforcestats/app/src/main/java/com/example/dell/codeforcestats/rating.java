package com.example.dell.codeforcestats;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class rating extends AppCompatActivity {

    ArrayList<Step> user;
    Button b1;
    RecyclerView rv;
    String handlename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        b1=findViewById(R.id.pics);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(rating.this,graph.class);
                intent.putExtra("mylist", user);
                startActivity(intent);
            }
        });
        Intent getme=getIntent();
         handlename=getme.getStringExtra("handle");
updateview(handlename);

    }
    private void updateview(String topic) {

        networktask nt=new networktask();
        Log.e("sss","https://codeforces.com/api/user.rating?handle="+handlename);
        nt.execute("https://codeforces.com/api/user.rating?handle="+handlename);


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
            user=parsejson(s);


            rv=findViewById(R.id.rv);

            ratingadapt na=new ratingadapt(user);

            rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));


            rv.setAdapter(na);

            Log.e("Mainact",""+user.size());
//            t1=findViewById(R.id.t1);
//            t1.setText(s);
        }
    }
    ArrayList<Step> parsejson(String s)
    {
        ArrayList<Step> ar=new ArrayList<>();
        try {
            JSONObject root=new JSONObject(s);
            JSONArray item=root.getJSONArray("result");
            for (int i = 0; i < item.length(); i++) {
                JSONObject user=item.getJSONObject(i);
                String contestname=user.getString("contestName");
                String rank=user.getString("rank");
                String oldrank=user.getString("oldRating");
                String newrank=user.getString("newRating");

                Step git=new Step(contestname,rank,oldrank,newrank);
                ar.add(git);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //parseing json

        return ar;
    }
}
