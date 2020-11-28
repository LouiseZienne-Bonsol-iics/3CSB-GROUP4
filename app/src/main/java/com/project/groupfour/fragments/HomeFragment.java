package com.project.groupfour.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.project.groupfour.R;
import com.project.groupfour.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ImageSlider imageSlider;
    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://c.ndtvimg.com/2019-02/7ivu40vg_tea_625x300_08_February_19.jpg"));
        slideModels.add(new SlideModel("https://media3.s-nbcnews.com/j/newscms/2019_33/2203981/171026-better-coffee-boost-se-329p_67dfb6820f7d3898b5486975903c2e51.fit-1240w.jpg"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1497515114629-f71d768fd07c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1062&q=80"));
        slideModels.add(new SlideModel("https://static.independent.co.uk/s3fs-public/thumbnails/image/2018/04/09/20/istock-157528129.jpg?width=990"));
        slideModels.add(new SlideModel("https://upload.wikimedia.org/wikipedia/commons/5/54/Ipoh_White_Coffee%2C_Old_Town_Kopitiam_in_Australia.jpg"));
        imageSlider.setImageList(slideModels, true);

        gridView = (GridView) view.findViewById(R.id.homeGridView);

        return view;
    }

    /*
    setContentView(R.layout.fragment_home);
    gv = gv.findViewById(R.id.homeGridView);
    gv.setAdapter(new HomeAdapter(this));
    */

}
