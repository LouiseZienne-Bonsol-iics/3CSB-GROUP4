package com.project.groupfour.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.groupfour.R;
import com.project.groupfour.ResultsConstructor;
import com.project.groupfour.adapters.ResultsAdapter;
import com.project.groupfour.models.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ResultFragment extends Fragment {
    public ResultFragment() {
        // Required empty public constructor
    }

    private RecyclerView rv;
    List<ResultsConstructor> lrc;

    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_result, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Recipes");
        mDatabase.keepSynced(true);

        rv = view.findViewById(R.id.result_view);

        //rv.setHasFixedSize(true);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //initData();
        //setRecyclerView();

        return view;
    }

    private void setRecyclerView() {
        ResultsAdapter ra = new ResultsAdapter(lrc);
        rv.setAdapter(ra);
        rv.setHasFixedSize(true);
    }

    private void initData() {
        lrc = new ArrayList<>();

        lrc.add(new ResultsConstructor("Coffee", R.drawable.temp_c1));
        lrc.add(new ResultsConstructor("Tea", R.drawable.temp_t1));
        lrc.add(new ResultsConstructor("Iced Drink", R.drawable.temp_j1));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<RecipeModel,RecipeViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RecipeModel, RecipeViewHolder>
                (RecipeModel.class, R.layout.result_row, RecipeViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(RecipeViewHolder recipeViewHolder, RecipeModel recipeModel, int i) {
                recipeViewHolder.setRecipeName(recipeModel.getRecipeName());
                recipeViewHolder.setImage(recipeModel.getImageUrl());
            }
        };
        rv.setAdapter(firebaseRecyclerAdapter);
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public RecipeViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setRecipeName(String name){
            TextView recName = (TextView) mView.findViewById(R.id.results_name);
            recName.setText(name);
        }
        public void setImage(String image){
            ImageView recImage = (ImageView)mView.findViewById(R.id.results_pic);
            Picasso.get().load(image).into(recImage);
        }

    }
}