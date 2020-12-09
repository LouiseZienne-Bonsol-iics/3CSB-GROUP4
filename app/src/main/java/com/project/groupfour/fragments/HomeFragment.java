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
        initData();
        setRecyclerView();

        return view;
    }

    private void setRecyclerView() {
        HomeAdapter ha = new HomeAdapter(itemList);
        recyclerView.setAdapter(ha);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {

        // This is where you change the photos
        itemList = new ArrayList<>();
        itemList.add(new HomeConstructor(
                R.drawable.temp_c1,
                R.drawable.temp_c2,
                R.drawable.temp_c3,
                R.drawable.temp_c4,
                R.drawable.temp_c5,
                R.drawable.temp_c6,
                "Popular Now"));
        itemList.add(new HomeConstructor(
                R.drawable.temp_t1,
                R.drawable.temp_t2,
                R.drawable.temp_t3,
                R.drawable.temp_t4,
                R.drawable.temp_t5,
                R.drawable.temp_t6,
                "Recent Searches"));
        itemList.add(new HomeConstructor(
                R.drawable.temp_j1,
                R.drawable.temp_j2,
                R.drawable.temp_j3,
                R.drawable.temp_j4,
                R.drawable.temp_j5,
                R.drawable.temp_j6,
                "My Favorites"));
    }

}
