package com.project.groupfour.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.groupfour.HomeConstructor;
import com.project.groupfour.R;

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

        boolean isExpandable = hItemList.get(position).isExpandable();

        holder.linearCollapse.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return hItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, imageViewTL, imageViewTR, imageViewB, imageViewBL, imageViewBR;
        TextView textView;

        LinearLayout linearPrime, linearCollapse, linearInner;
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.homeText);
            imageViewTL = itemView.findViewById(R.id.homeImageTL);
            imageView = itemView.findViewById(R.id.homeImage);
            imageViewTR = itemView.findViewById(R.id.homeImageTR);
            imageViewBL = itemView.findViewById(R.id.homeImageBL);
            imageViewB = itemView.findViewById(R.id.homeImageB);
            imageViewBR = itemView.findViewById(R.id.homeImageBR);

            linearPrime = itemView.findViewById(R.id.linear_layout);
            linearCollapse = itemView.findViewById(R.id.homeCollapsable);
            linearInner = itemView.findViewById(R.id.linear_inner);

            linearPrime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HomeConstructor hc = hItemList.get(getAdapterPosition());
                    hc.setExpandable(!hc.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}