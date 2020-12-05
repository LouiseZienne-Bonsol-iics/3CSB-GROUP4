package com.project.groupfour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RecipeActivity extends AppCompatActivity {


    String[] IG, SP;
    ListView items, steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        IG = getResources().getStringArray(R.array.ingredients);
        SP = getResources().getStringArray(R.array.step);
        items =findViewById(R.id.list_ingredient);
        steps =findViewById(R.id.list_recipe);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.recipe_row, R.id.item, IG);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.recipe_row, R.id.item, SP);
        items.setAdapter(adapter);
        steps.setAdapter(adapter2);
    }
}