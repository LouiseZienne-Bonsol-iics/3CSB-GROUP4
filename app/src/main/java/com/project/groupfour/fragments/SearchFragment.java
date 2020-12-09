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
import androidx.fragment.app.FragmentTransaction;

import com.project.groupfour.R;

public class SearchFragment extends Fragment implements View.OnClickListener{

    EditText homeEditText;
    ImageButton homeImageButton;
    Button homeButtonC, homeButtonT, homeButtonJ, homeButtonD;

    private AlertDialog.Builder homeDialogBuilder;
    private AlertDialog homeDialog;
    private RadioButton homeRadioButton;
    private Button homePopupButton;

    FragmentTransaction ft;

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
            //Log.d("systemHell", homeEditText.getText().toString());
            //Toast.makeText(getActivity(), homeEditText.getText().toString(),Toast.LENGTH_SHORT).show();

            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new ResultFragment()).commit();
        } else{
            // if the user wanted the search by category system
            switch (view.getId()) {
                case R.id.homeCoffee:
                    Log.d("systemHell", "Message 1");
                    createNewContactDialog();
                    break;
                case R.id.homeJuice:
                    Log.d("systemHell", "Message 2");
                    createOtherSubContactDialog();
                    break;
                case R.id.homeTea:
                    Log.d("systemHell", "Message 3");
                    createNewContactDialog();
                    break;
                case R.id.homeDairy:
                    Log.d("systemHell", "Message 4");
                    createOtherSubContactDialog();
                    break;
            }
        }
    }

    private void createOtherSubContactDialog() {
        homeDialogBuilder = new AlertDialog.Builder(getActivity()).setTitle("How would you like your drink?");
        homeDialogBuilder.setCancelable(true);

        // Radio Button Starts here
        final String[] temp = {"Fruit Based","Coffee Based", "Milk Based"};
        int checkedItem = -1;
        homeDialogBuilder.setSingleChoiceItems(temp, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(getActivity(), "Clicked on Fruit", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "Clicked on Coffee", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Clicked on Milk", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        homeDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Something has been selected", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
        homeDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Cancel Culture Legoo", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

        homeDialog = homeDialogBuilder.create();
        homeDialog.show();
    }


    private void createNewContactDialog(){
        homeDialogBuilder = new AlertDialog.Builder(getActivity()).setTitle("How would you like your drink?");
        homeDialogBuilder.setCancelable(true);

        // Radio Button Starts here
        final String[] temp = {"Hot","Cold"};
        int checkedItem = -1;
        homeDialogBuilder.setSingleChoiceItems(temp, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                //Toast.makeText(getActivity(), "Clicked on Hot", Toast.LENGTH_LONG).show();
                                break;
                            case 1:
                                //Toast.makeText(getActivity(), "Clicked on Cold", Toast.LENGTH_LONG).show();
                                break;
                        }
//                        homeDialog.dismiss();
                    }
                });

        homeDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(getActivity(), "Something has been selected", Toast.LENGTH_LONG).show();
                        //dialog.cancel();
                        ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, new ResultFragment()).commit();
                    }
                });
        homeDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getActivity(), "Cancel Culture Legoo", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

        homeDialog = homeDialogBuilder.create();
        homeDialog.show();
    }

}
