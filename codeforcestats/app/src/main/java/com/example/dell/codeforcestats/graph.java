package com.example.dell.codeforcestats;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class graph extends AppCompatActivity {

    ArrayList<Step> myList;
    int[] x;
    int[] y;
    int[] y1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        myList = (ArrayList<Step>) getIntent().getSerializableExtra("mylist");

         y=new int[myList.size()];
         x=new int[myList.size()];

y1=new int[myList.size()];

        for (int i = 0; i <myList.size() ; i++) {
            x[i]=i+1;
        }
        for (int i = 0; i <myList.size() ; i++) {
            y[i]= Integer.parseInt(myList.get(i).newrank);
        }

        for (int i = 0; i <myList.size() ; i++) {
            y1[i]= Integer.parseInt(myList.get(i).oldrank);
        }

        GraphView graph;
        graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series;
        series= new LineGraphSeries<>(data());
        series.setTitle("NEW RANKS");
        series.setColor(Color.RED);


        LineGraphSeries<DataPoint> series1;
        series1= new LineGraphSeries<>(data1());
        series1.setTitle("OLD RANKS");

        graph.addSeries(series);
        graph.addSeries(series1);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getViewport().setScrollable(true);

    }
    public DataPoint[] data(){
        DataPoint[] values = new DataPoint[myList.size()];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<myList.size();i++){
            DataPoint v = new DataPoint(x[i],y[i]);
            values[i] = v;
        }
        return values;
    }
    public DataPoint[] data1(){
        DataPoint[] values = new DataPoint[myList.size()];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<myList.size();i++){
            DataPoint v = new DataPoint(x[i],y1[i]);
            values[i] = v;
        }
        return values;
    }
}
