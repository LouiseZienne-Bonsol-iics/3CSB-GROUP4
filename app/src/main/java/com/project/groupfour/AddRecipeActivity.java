package com.project.groupfour;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.project.groupfour.models.UploadRecipeModel;
import com.squareup.picasso.Picasso;

public class AddRecipeActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView recipeImg;
    private RatingBar recipeRating;
    private EditText recipeName;
    private EditText prepTime;
    private EditText ingredients;
    private EditText recipe;
    private ImageButton uploadPhoto;
    private Button saveRecipe;
    private Button deleteRecipe;

    private Uri imageUri;
    ProgressDialog pd;

    //firebase stuff
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recipeImg = findViewById(R.id.recipe_image);
        recipeRating = findViewById(R.id.recipe_rating);
        recipeName = findViewById(R.id.et_recipe_name);
        prepTime = findViewById(R.id.et_prep_time);
        ingredients = findViewById(R.id.et_ingredients);
        recipe = findViewById(R.id.et_recipe);
        uploadPhoto = findViewById(R.id.upload_photo);
        saveRecipe = findViewById(R.id.save_button);
        deleteRecipe = findViewById(R.id.delete_button);

        mStorageRef = FirebaseStorage.getInstance().getReference("Recipes");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Recipes");
        pd = new ProgressDialog(this);

        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        saveRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadRecipeImage();
            }
        });

        deleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private String getFileExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    //upload recipe image to Firebase Storage
    private void uploadRecipeImage() {
        pd.setMessage("Uploading, please Wait");
        pd.show();

        if(imageUri != null){
            //sets the name of images based on your computer time
            final StorageReference fileRef = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));

            StorageTask uploadTask = fileRef.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot,Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();

                        pd.dismiss();

                        String rname = recipeName.getText().toString().trim();
                        String rating = String.valueOf(recipeRating.getRating());
                        String ptime = prepTime.getText().toString().trim();
                        String ingred = ingredients.getText().toString();
                        String rec = recipe.getText().toString();

                        Toast.makeText(AddRecipeActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();
                        UploadRecipeModel uploads = new UploadRecipeModel(rname, rating, ptime, ingred, rec, downloadUri.toString());

                        //add to Realtime DB in Firebase
                        mDatabaseRef.push().setValue(uploads);

                    } else {
                        pd.dismiss();
                        Toast.makeText(AddRecipeActivity.this, "Upload Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddRecipeActivity.this, "Upload Failed no. 2", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            //Picasso.get().load(imageUri).into(recipeImg);
            recipeImg.setImageURI(imageUri);
        }

    }
}
