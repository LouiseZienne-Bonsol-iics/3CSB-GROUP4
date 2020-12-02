package com.project.groupfour.fragments;

import android.os.Bundle;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.project.groupfour.HomeAdapter;
import com.project.groupfour.HomeConstructor;
import com.project.groupfour.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment(){

    }

    ImageSlider imageSlider;
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    List <HomeConstructor> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://c.ndtvimg.com/2019-02/7ivu40vg_tea_625x300_08_February_19.jpg"));
        slideModels.add(new SlideModel("https://media3.s-nbcnews.com/j/newscms/2019_33/2203981/171026-better-coffee-boost-se-329p_67dfb6820f7d3898b5486975903c2e51.fit-1240w.jpg"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1497515114629-f71d768fd07c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1062&q=80"));
        slideModels.add(new SlideModel("https://static.independent.co.uk/s3fs-public/thumbnails/image/2018/04/09/20/istock-157528129.jpg?width=990"));
        slideModels.add(new SlideModel("https://upload.wikimedia.org/wikipedia/commons/5/54/Ipoh_White_Coffee%2C_Old_Town_Kopitiam_in_Australia.jpg"));
        imageSlider.setImageList(slideModels, true);

        recyclerView = view.findViewById(R.id.homeRecyclerContent);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new HomeAdapter(initData()));

        return view;
    }

    private List <HomeConstructor> initData() {

        // Need to change first param to list or something
        itemList = new ArrayList<>();
        itemList.add(new HomeConstructor(R.drawable.ic_launcher_background,"Popular Now"));
        itemList.add(new HomeConstructor(R.drawable.ic_launcher_background,"Recent Searches"));
        itemList.add(new HomeConstructor(R.drawable.ic_launcher_background,"My Favorites"));

        return itemList;

    }

}
