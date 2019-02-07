package com.example.dell.codeforcestats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ratingadapt extends RecyclerView.Adapter<ratingadapt.viewholder>{
    ArrayList<Step> steps;

    public ratingadapt(ArrayList<Step> steps) {
        this.steps = steps;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=(LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemview=layoutInflater.inflate(R.layout.card,viewGroup,false);
        return new viewholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder viewholder, int i) {

        Step step=steps.get(i);
        viewholder.contestname.setText(String.valueOf(step.getContestname()));

        viewholder.rank.setText("RANK IN CONTEST : "+String.valueOf(step.getRank()));
        viewholder.oldrank.setText("OLD OVERALL RANK : "+String.valueOf(step.getOldrank()));
        viewholder.newrank.setText("NEW OVERALL RANK : "+String.valueOf(step.getNewrank()));

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class viewholder extends RecyclerView.ViewHolder
    {
        TextView contestname,rank,oldrank,newrank;
        public viewholder(@NonNull View itemView) {
            super(itemView);
contestname=itemView.findViewById(R.id.contestname);
rank=itemView.findViewById(R.id.rank);
oldrank=itemView.findViewById(R.id.oldrank);
newrank=itemView.findViewById(R.id.newrank);

        }
    }
}
