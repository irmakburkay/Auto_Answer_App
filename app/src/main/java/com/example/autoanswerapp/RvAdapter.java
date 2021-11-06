package com.example.autoanswerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {

    public ArrayList<Class2> class2s;

    public static class RvViewHolder extends RecyclerView.ViewHolder {

        public TextView textView1, textView2, textView3;
        public Switch switch1;
        public FloatingActionButton fab1, fab2;

        public RvViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            switch1 = itemView.findViewById(R.id.switch1);
            fab1 = itemView.findViewById(R.id.floatingActionButton1);
            fab2 = itemView.findViewById(R.id.floatingActionButton2);
        }
    }

    public RvAdapter(ArrayList<Class2> class2s){
        this.class2s = class2s;
    }

    @NonNull
    @Override
    public RvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        RvViewHolder viewHolder = new RvViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvViewHolder holder, int position) {
        Class2 item = class2s.get(position);

        holder.textView1.setText(item.name);
        holder.textView2.setText(item.tel);
        holder.textView3.setText(item.date1.getHours()+" - "+item.date2.getHours());
        holder.switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });
        holder.fab1.setOnClickListener(v -> {
            //düzenleme fab
        });
        holder.fab2.setOnClickListener(v -> {
            class2s.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
        });

    }

    @Override
    public int getItemCount() {
        return class2s.size();
    }

}
