package com.project.groupfour.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.groupfour.RecipeActivity;
import com.project.groupfour.R;
import com.project.groupfour.models.RecipeModel;
import com.squareup.picasso.Picasso;

public class FavoritesFragment extends Fragment {
    public FavoritesFragment(){

    }

    RecyclerView recyclerView;

    private DatabaseReference mFavRef;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_favorites, container, false);
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        mFavRef = FirebaseDatabase.getInstance().getReference().child("Users");//.child("Favorites");
        mAuth = FirebaseAuth.getInstance();
        mFavRef.keepSynced(true);

        recyclerView = view.findViewById(R.id.favRecyclerContent);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<RecipeModel, ViewHolderClass> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RecipeModel, ViewHolderClass>
                (RecipeModel.class, R.layout.favorite_row, ViewHolderClass.class, mFavRef.child(mAuth.getCurrentUser().getUid()).child("Favorites")) {
            @Override
            protected void populateViewHolder(ViewHolderClass viewHolderClass, RecipeModel recipeModel, final int i) {
                viewHolderClass.setRecipeName(recipeModel.getRecipeName());
                viewHolderClass.setImage(recipeModel.getImageUrl());

                viewHolderClass.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), RecipeActivity.class);
                        intent.putExtra("RecipeKey", getRef(i).getKey());
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static  class ViewHolderClass extends RecyclerView.ViewHolder{
        View mView;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setRecipeName(String name){
            TextView recName = (TextView) itemView.findViewById(R.id.fav_txt);
            recName.setText(name);
        }
        public void setImage(String image){
            ImageView recImage = (ImageView)itemView.findViewById(R.id.fav_img);
            Picasso.get().load(image).into(recImage);
        }
    }
}