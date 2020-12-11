package com.project.groupfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class RecipeActivity extends AppCompatActivity {
    private ImageView recipeImg;
    private TextView recipeName;
    private RatingBar recipeRating;
    private TextView  prepTime;
    private TextView recipeIngred;
    private TextView recipe;

    Toolbar toolbar;
    private TextView toolName;
    String favItem;
    boolean checkFav;

    DatabaseReference RecipeRef;
    DatabaseReference UserRef;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //setup toolbar
        toolbar = (Toolbar) findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        toolName = findViewById(R.id.recipe_toolbar_name);

        //adding the back arrow in toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        recipeImg = findViewById(R.id.recipe_image_view);
        recipeName = findViewById(R.id.txt_recipe_name);
        recipeRating = findViewById(R.id.recipe_rating_view);
        prepTime = findViewById(R.id.txt_prep_time);
        recipeIngred = findViewById(R.id.txt_ingredview);
        recipe = findViewById(R.id.txt_recipe_procedure);

        RecipeRef = FirebaseDatabase.getInstance().getReference().child("Recipes");
        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();


        String RecipeKey = getIntent().getStringExtra("RecipeKey");

        RecipeRef.child(RecipeKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String img = dataSnapshot.child("imageUrl").getValue().toString();
                    String rName = dataSnapshot.child("recipeName").getValue().toString();
                    String rate = dataSnapshot.child("recipeRating").getValue().toString();
                    String pTime = dataSnapshot.child("prepTime").getValue().toString();
                    String ingred = dataSnapshot.child("ingredients").getValue().toString();
                    String recip = dataSnapshot.child("recipe").getValue().toString();

                    String tb = rName;
                    favItem = rName;

                    Picasso.get().load(img).into(recipeImg);
                    recipeName.setText(rName);
                    recipeRating.setRating(Float.parseFloat(rate));
                    prepTime.setText(pTime);
                    recipeIngred.setText(ingred);
                    recipe.setText(recip);

                    toolName.setText(tb);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addToFavorites(View v) {
        UserRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                checkFav = dataSnapshot.child("Favorites").hasChild(favItem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        String UserKey = getIntent().getStringExtra("RecipeKey");
        HashMap<String, Object> map = new HashMap<>();
        map.put(favItem, UserKey);
        if(!checkFav){
            UserRef.child(mAuth.getCurrentUser().getUid()).child("Favorites").updateChildren(map);
        }else{
            UserRef.child(mAuth.getCurrentUser().getUid()).child("Favorites").child(favItem).removeValue();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            /*Intent i = new Intent(RecipeActivity.this,UserHome.class);
            startActivity(i);*/
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

}

