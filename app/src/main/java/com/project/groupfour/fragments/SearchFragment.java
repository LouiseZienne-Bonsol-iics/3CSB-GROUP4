package com.project.groupfour.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.project.groupfour.R;

public class SearchFragment extends Fragment implements View.OnClickListener{

    EditText homeEditText;
    ImageButton homeImageButton;
    Button homeButtonC, homeButtonT, homeButtonJ, homeButtonD;

    private AlertDialog.Builder homeDialogBuilder;
    private AlertDialog homeDialog;
    private RadioButton homeRadioButton;
    private Button homePopupButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        Log.d("systemHell","Entered SearchFragment");

        // for the search bar
        homeImageButton = (ImageButton) v.findViewById(R.id.homeSearchIcon);
        homeEditText   = (EditText) v.findViewById(R.id.homeSearch);

        // for the category search
        homeButtonC = (Button) v.findViewById(R.id.homeCoffee);
        homeButtonT = (Button) v.findViewById(R.id.homeTea);
        homeButtonJ = (Button) v.findViewById(R.id.homeJuice);
        homeButtonD = (Button) v.findViewById(R.id.homeDairy);

        homeButtonC.setOnClickListener(this);
        homeButtonT.setOnClickListener(this);
        homeButtonJ.setOnClickListener(this);
        homeButtonD.setOnClickListener(this);

        // The user search button
        homeImageButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {

        // Specify which button was clicked
        if(R.id.homeSearchIcon == view.getId()){
            // if the user was searching
            Log.d("systemHell", homeEditText.getText().toString());
            Toast.makeText(getActivity(), homeEditText.getText().toString(),Toast.LENGTH_SHORT).show();
        } else{
            // if the user wanted the search by category system
            switch (view.getId()) {
                case R.id.homeCoffee:
                    Log.d("systemHell", "Message 1");
                    break;
                case R.id.homeJuice:
                    Log.d("systemHell", "Message 2");
                    break;
                case R.id.homeTea:
                    Log.d("systemHell", "Message 3");
                    break;
                case R.id.homeDairy:
                    Log.d("systemHell", "Message 4");
                    break;
            }

            // The popup
            createNewContactDialog();

        }
    }

    private void createNewContactDialog(){
        homeDialogBuilder = new AlertDialog.Builder(getActivity()).setTitle("How would you like your drink?");

        // Radio Button Starts here
        final String[] temp = {"Hot","Cold"};
        int checkedItem = -1;
        homeDialogBuilder.setSingleChoiceItems(temp, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Toast.makeText(getActivity(), "Clicked on Hot", Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                Toast.makeText(getActivity(), "Clicked on Cold", Toast.LENGTH_LONG).show();
                                break;
                        }
                        homeDialog.dismiss();
                    }
                });

        homeDialog = homeDialogBuilder.create();
        homeDialogBuilder.setCancelable(true);
        homeDialog.show();
    }

}
