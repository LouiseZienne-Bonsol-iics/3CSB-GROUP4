package com.project.groupfour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    List <HomeConstructor> hItemList;

    public HomeAdapter(List<HomeConstructor> itemList){
        this.hItemList  = itemList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        holder.imageViewTL.setImageResource(hItemList.get(position).getcHomeImageTL());
        holder.imageView.setImageResource(hItemList.get(position).getcHomeImage());
        holder.imageViewTR.setImageResource(hItemList.get(position).getcHomeImageTR());
        holder.imageViewBL.setImageResource(hItemList.get(position).getcHomeImageBL());
        holder.imageViewB.setImageResource(hItemList.get(position).getcHomeImageB());
        holder.imageViewBR.setImageResource(hItemList.get(position).getcHomeImageBR());
        holder.textView.setText(hItemList.get(position).getcHomeName());
    }

    @Override
    public int getItemCount() {
        return hItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, imageViewTL, imageViewTR, imageViewB, imageViewBL, imageViewBR;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.homeText);
            imageViewTL = itemView.findViewById(R.id.homeImageTL);
            imageView = itemView.findViewById(R.id.homeImage);
            imageViewTR = itemView.findViewById(R.id.homeImageTR);
            imageViewBL = itemView.findViewById(R.id.homeImageBL);
            imageViewB = itemView.findViewById(R.id.homeImageB);
            imageViewBR = itemView.findViewById(R.id.homeImageBR);

        }
    }
}