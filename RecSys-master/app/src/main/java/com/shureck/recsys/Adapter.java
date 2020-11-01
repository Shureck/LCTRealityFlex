package com.shureck.recsys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class Adapter extends RecyclerView.Adapter<Adapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView name;
        TextView date;
        TextView discr;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            //cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.textView);
            date = (TextView)itemView.findViewById(R.id.textView2);
            discr = (TextView)itemView.findViewById(R.id.textView3);
            personPhoto = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }

    ArrayList<Recommend> persons;

    Adapter(ArrayList<Recommend> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(persons.get(i).name);
        personViewHolder.date.setText(persons.get(i).name);
        personViewHolder.discr.setText(persons.get(i).discr);
        personViewHolder.personPhoto.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}