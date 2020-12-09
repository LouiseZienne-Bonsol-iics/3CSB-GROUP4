package com.project.groupfour.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.groupfour.R;
import com.project.groupfour.ResultsConstructor;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {
    List<ResultsConstructor> lrc;

    public ResultsAdapter(List<ResultsConstructor> list){
        lrc = list;
    }

    @NonNull
    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsAdapter.ViewHolder holder, int position) {
        holder.iv.setImageResource(lrc.get(position).getPic());
        holder.tv.setText(lrc.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return lrc.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll;
        ImageView iv;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll = itemView.findViewById(R.id.results_linear);
            iv = itemView.findViewById(R.id.results_pic);
            tv = itemView.findViewById(R.id.results_name);
        }
    }
}
