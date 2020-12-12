package com.project.groupfour.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.groupfour.AddRecipeActivity;
import com.project.groupfour.R;
import com.project.groupfour.RecipeActivity;
import com.project.groupfour.ResultsConstructor;
import com.project.groupfour.adapters.ResultsAdapter;
import com.project.groupfour.models.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultFragment extends Fragment {
    public ResultFragment() {
        // Required empty public constructor
    }

    private RecyclerView rv;
    List<ResultsConstructor> lrc;
    ProgressDialog pd;

    private DatabaseReference mDatabase;
    private DatabaseReference UserRef;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_result, container, false);

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Recipes");
        mDatabase.keepSynced(true);

        pd = new ProgressDialog(getActivity());

        rv = view.findViewById(R.id.result_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

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

        pd.dismiss();
        FirebaseRecyclerAdapter<RecipeModel,RecipeViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RecipeModel, RecipeViewHolder>
                (RecipeModel.class, R.layout.result_row, RecipeViewHolder.class, firebaseSearchQuery) {
            @Override
            protected void populateViewHolder(RecipeViewHolder recipeViewHolder, final RecipeModel recipeModel, final int i) {
                recipeViewHolder.setRecipeName(recipeModel.getRecipeName());
                recipeViewHolder.setImage(recipeModel.getImageUrl());

                recipeViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), RecipeActivity.class);
                        intent.putExtra("RecipeKey", getRef(i).getKey());

                        String rID = getRef(i).getKey();

                        //adding to recent searches
                        UserRef.child(mAuth.getCurrentUser().getUid()).child("RecentSearches").orderByChild("recipeID").equalTo(rID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot!=null && dataSnapshot.getChildren()!=null &&
                                        dataSnapshot.getChildren().iterator().hasNext()){
                                    Toast.makeText(getActivity(), "ALREADY IN RECENT SEARCHES", Toast.LENGTH_SHORT).show();
                                } else{
                                    String timestamp2 = System.currentTimeMillis() + "";
                                    String rID = getRef(i).getKey();
                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("recipeID", rID);
                                    map.put("recipeName",recipeModel.getRecipeName());
                                    map.put("timestamp", timestamp2);

                                    UserRef.child(mAuth.getCurrentUser().getUid()).child("RecentSearches").push().setValue(map);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

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