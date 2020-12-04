package com.project.groupfour.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.groupfour.FavoriteAdapter;
import com.project.groupfour.FavoriteConstructor;
import com.project.groupfour.HomeAdapter;
import com.project.groupfour.HomeConstructor;
import com.project.groupfour.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    public FavoritesFragment(){

    }
    LinearLayout linearLayout;
    RecyclerView recyclerView;
    List<FavoriteConstructor> itemList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_favorites, container, false);
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.favRecyclerContent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new FavoriteAdapter(initData()));

        return view;
    }

    private List<FavoriteConstructor> initData() {

        // Need to change first param to list or something
        itemList = new ArrayList<>();
        itemList.add(new FavoriteConstructor(R.drawable.ic_launcher_background,"My Favorites"));

        return itemList;

    }
}
