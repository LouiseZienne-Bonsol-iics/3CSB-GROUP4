package com.project.groupfour.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.groupfour.R;
import com.project.groupfour.RecipeActivity;
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
    ProgressDialog pd;

    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_result, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Recipes");
        mDatabase.keepSynced(true);

        pd = new ProgressDialog(getActivity());

        rv = view.findViewById(R.id.result_view);
        //rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //initData();
        //setRecyclerView();

        return view;
    }

    /*private void setRecyclerView() {
        ResultsAdapter ra = new ResultsAdapter(lrc);
        rv.setAdapter(ra);
        rv.setHasFixedSize(true);
    }

    private void initData() {
        lrc = new ArrayList<>();

        lrc.add(new ResultsConstructor("Coffee", R.drawable.temp_c1));
        lrc.add(new ResultsConstructor("Tea", R.drawable.temp_t1));
        lrc.add(new ResultsConstructor("Iced Drink", R.drawable.temp_j1));
    }*/

    @Override
    public void onStart() {
        super.onStart();

        pd.setMessage("Searching, please wait.");
        pd.show();

        Bundle bundle = this.getArguments();
        String recipeNameSearch = bundle.getString("recipeName");
        String cat_sub = bundle.getString("subcats");
        String searchCheck = bundle.getString("checker");

        Query firebaseSearchQuery;

        if (searchCheck.equals("searchByName")){
            firebaseSearchQuery = mDatabase.orderByChild("recipeName").startAt(recipeNameSearch).endAt(recipeNameSearch + "\uf8ff");
        } else if (searchCheck.equals("searchByCategory")) {
            firebaseSearchQuery = mDatabase.orderByChild("cat_sub").equalTo(cat_sub);
        } else {
            firebaseSearchQuery = mDatabase;
        }

        //Query firebaseSearchQuery = mDatabase.orderByChild("cat_sub").equalTo(cat_sub);

        FirebaseRecyclerAdapter<RecipeModel,RecipeViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RecipeModel, RecipeViewHolder>
                (RecipeModel.class, R.layout.result_row, RecipeViewHolder.class, firebaseSearchQuery) {
            @Override
            protected void populateViewHolder(RecipeViewHolder recipeViewHolder, RecipeModel recipeModel, final int i) {
                recipeViewHolder.setRecipeName(recipeModel.getRecipeName());
                recipeViewHolder.setImage(recipeModel.getImageUrl());

                pd.dismiss();

                recipeViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), RecipeActivity.class);
                        intent.putExtra("RecipeKey", getRef(i).getKey());
                        startActivity(intent);
                    }
                });
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