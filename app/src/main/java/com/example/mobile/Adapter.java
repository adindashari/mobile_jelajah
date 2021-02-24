package com.example.mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<ListPerson> listPersonList;

    public Adapter(Context ctx, List<ListPerson> listPrs){
        this.inflater = LayoutInflater.from(ctx);
        this.listPersonList = listPrs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_person,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.name.setText(listPersonList.get(position).getName());
        holder.age.setText(listPersonList.get(position).getAge());
        holder.city.setText(listPersonList.get(position).getCity());
        Picasso.get().load(listPersonList.get(position).getImg1()).into(holder.img1);

    }

    @Override
    public int getItemCount() {
        return listPersonList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView name, age, city;
        ImageView img1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            city = itemView.findViewById(R.id.city);
            img1 = itemView.findViewById(R.id.img1);

            // handle onClick

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
