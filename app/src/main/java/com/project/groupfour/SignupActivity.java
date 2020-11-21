package com.project.groupfour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;


public class SignupActivity extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPass;
    private Button createProfile;

    private DatabaseReference mRootRef;
    private FirebaseAuth mAuth;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.et_username);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        confirmPass = findViewById(R.id.et_confirmpass);
        createProfile = findViewById(R.id.signup);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = username.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPass = password.getText().toString();
                String txtConfirmPass = confirmPass.getText().toString();
                String txtRole = "User";

                if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPass) || TextUtils.isEmpty(txtConfirmPass)) {
                    Toast.makeText(SignupActivity.this, "Empty Fields!", Toast.LENGTH_SHORT).show();
                }   else if (txtPass.length() < 5){
                    Toast.makeText(SignupActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else if (!txtPass.equals(txtConfirmPass)){
                    Toast.makeText(SignupActivity.this, "Password do not match!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtUsername,txtEmail,txtRole,txtPass);
                }

                //startActivity(new Intent((SignupActivity.this,)));
            }
        });

    }

    private void registerUser(final String username, final String email, final String role, final String password) {
        pd.setMessage("Please Wait");
        pd.show();

        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("username", username);
                map.put("email", email);
                map.put("role", role);
                map.put("uid", mAuth.getCurrentUser().getUid());

                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            pd.dismiss();
                            Toast.makeText(SignupActivity.this, "Profile Created Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void displayLogin(View v) {
        Intent i = new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}