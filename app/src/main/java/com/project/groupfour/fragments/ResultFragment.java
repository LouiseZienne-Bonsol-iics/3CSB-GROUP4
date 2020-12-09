package com.project.groupfour.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.groupfour.R;
import com.project.groupfour.ResultsConstructor;
import com.project.groupfour.adapters.ResultsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResultFragment extends Fragment {
    public ResultFragment() {
        // Required empty public constructor
    }

    RecyclerView rv;
    List<ResultsConstructor> lrc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_result, container, false);

        rv = view.findViewById(R.id.result_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
        setRecyclerView();

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
}