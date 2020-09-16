package com.gtappdevelopers.howzyourapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Viewholder> {

    private Context mctx;
    private ArrayList<Items> itemsArrayList;

    public RecyclerAdapter(Context mctx, ArrayList<Items> itemsArrayList) {
        this.mctx = mctx;
        this.itemsArrayList = itemsArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View   view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.Viewholder holder, int position) {

        Items model=itemsArrayList.get(position);
        holder.name_txt.setText("Name: "+model.getFull_name());
        holder.desc_txt.setText("Description: "+model.getDescription());
        holder.login_txt.setText("Login: "+model.getOwner().getLogin());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mctx,DetailView.class);
                i.putExtra("name",model.getFull_name());
                i.putExtra("desc",model.getDescription());
                i.putExtra("login",model.getOwner().getLogin());
                mctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView name_txt,login_txt,desc_txt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name_txt=itemView.findViewById(R.id.name_txt);
            login_txt=itemView.findViewById(R.id.login_txt);
            desc_txt=itemView.findViewById(R.id.desc_txt);


        }
    }
}
