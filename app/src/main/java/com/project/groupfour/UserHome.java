package com.project.groupfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.groupfour.fragments.FavoritesFragment;
import com.project.groupfour.fragments.HomeFragment;
import com.project.groupfour.fragments.SearchFragment;
import com.project.groupfour.fragments.SettingsFragment;

import org.w3c.dom.Text;

public class UserHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    NavigationView navigationView;
    private TextView currentUsername;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);    //hide status bar

        //initialize firebase objects
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        pd = new ProgressDialog(this);

        //Users db directory in firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid());

        //Side Navigation Drawer
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        currentUsername = navigationView.getHeaderView(0).findViewById(R.id.nav_currentUser);
        navigationView.setNavigationItemSelectedListener(this);

        //get username from firebase db and setting visibility of "Add Recipe" button
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentUsername.setText(dataSnapshot.child("username").getValue().toString());

                String role = dataSnapshot.child("role").getValue().toString();
                SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("role", role);
                editor.commit();

                addRecipeVisibility();
                Log.d("RecipeTest", "Changed value of currentUserRole");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //opening and closing of nav drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchFragment()).commit();
                break;
            case R.id.nav_favorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoritesFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_addrecipe:
                Intent i = new Intent(this, AddRecipeActivity.class);
                startActivity(i);
                navigationView.setCheckedItem(R.id.nav_home);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addRecipeVisibility(){
        Log.d("RecipeTest", "went inside new method");
        SharedPreferences sp = getSharedPreferences("prefs", MODE_PRIVATE);
        if(sp.getString("role", "").equals("Admin")){
            navigationView.getMenu().findItem(R.id.nav_addrecipe).setVisible(true);
        } else {
            navigationView.getMenu().findItem(R.id.nav_addrecipe).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void logout(View view) {
        pd.setMessage("Logging Out, please wait.");
        pd.show();
        firebaseAuth.signOut();
        finish();
        pd.dismiss();
        Intent i = new Intent(UserHome.this,LoginActivity.class);
        startActivity(i);
        Toast.makeText(UserHome.this, "User logged out.", Toast.LENGTH_SHORT).show();
    }
}

