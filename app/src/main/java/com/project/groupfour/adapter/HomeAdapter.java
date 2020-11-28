package com.project.groupfour.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.groupfour.R;
import com.project.groupfour.fragments.HomeFragment;
import com.project.groupfour.model.AndroidVersion;

import java.util.ArrayList;

public class HomeAdapter extends BaseAdapter {
    ArrayList<AndroidVersion> androidList;
    Context context;

     HomeAdapter(Context c){
        this.context = c;

        androidList = new ArrayList<AndroidVersion>();

        String[] homeText = c.getResources().getStringArray(R.array.homeText);
        TypedArray tArray = c.getResources().obtainTypedArray(R.array.homeImage);

        int count = tArray.length();
        int[] homeImage = new int[count];
        for(int i = 0; i < homeImage.length; i++){
            homeImage[i]=tArray.getResourceId(i, 0);
        }

        for(int i=0; i < homeText.length; i++){
            androidList.add(new AndroidVersion(homeText[i],homeImage[i]));
        }

    }

    @Override
    public int getCount() {

        return androidList.size();

    }

    @Override
    public Object getItem(int i) {

        return androidList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.home_row, viewGroup, false);

        TextView tText = row.findViewById(R.id.homeText);
        ImageView tImage = row.findViewById(R.id.homeImage);

        AndroidVersion temp = androidList.get(i);

        tText.setText(temp.homeName);
        tImage.setImageResource(temp.homePic);

        return null;
        //not null
    }
}
