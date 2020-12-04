package com.project.groupfour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    List <FavoriteConstructor> hItemList;

    public FavoriteAdapter(List<FavoriteConstructor> itemList){
        this.hItemList  = itemList;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_row,parent,false);
        //FavoriteAdapter.ViewHolder viewHolder = new FavoriteAdapter.ViewHolder(view);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(hItemList.get(position).getcFavoriteImage());
        holder.textView.setText(hItemList.get(position).getcFavoriteName());
    }

    @Override
    public int getItemCount() {
        return hItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.favoriteImage);
            textView = itemView.findViewById(R.id.favoriteText);
        }
    }
}