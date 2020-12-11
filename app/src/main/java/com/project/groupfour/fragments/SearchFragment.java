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

    EditText searchByName;
    ImageButton homeImageButton;
    Button homeButtonC, homeButtonT, homeButtonJ, homeButtonD;

    Bundle catBundle = new Bundle();
    ResultFragment fragment = new ResultFragment();

    private AlertDialog.Builder homeDialogBuilder;
    private AlertDialog homeDialog;
    private RadioButton homeRadioButton;
    private Button homePopupButton;

    private String c1;
    private String c2;
    private String c3;
    private String searchChecker;

    FragmentTransaction ft;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        Log.d("systemHell","Entered SearchFragment");

        // for the search bar
        homeImageButton = (ImageButton) v.findViewById(R.id.btn_search);
        searchByName   = (EditText) v.findViewById(R.id.et_searchByName);

        // for the category search
        homeButtonC = (Button) v.findViewById(R.id.catCoffee);
        homeButtonT = (Button) v.findViewById(R.id.catTea);
        homeButtonJ = (Button) v.findViewById(R.id.catIceBlend);
        homeButtonD = (Button) v.findViewById(R.id.catFrappe);

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
        if(R.id.btn_search == view.getId()){
            // if the user was searching by name
            searchChecker = "searchByName";

            Bundle bundle = new Bundle();
            bundle.putString("recipeName",searchByName.getText().toString());
            bundle.putString("checker",searchChecker);

            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        } else{
            // if the user wanted the search by category system
            switch (view.getId()) {
                case R.id.catCoffee:
                    //put something to save the Coffee category
                    c1 = "coffee";
                    c3 = "coffee";
                    Log.d("systemHell", "Message 1");
                    createNewContactDialog();
                    break;
                case R.id.catIceBlend:
                    //put something to save the Ice Blended category
                    c1 = "ice";
                    c3 = "ice";
                    Log.d("systemHell", "Message 2");
                    createOtherSubContactDialog();
                    break;
                case R.id.catTea:
                    //put something to save the Tea category
                    c1 = "tea";
                    c3 = "tea";
                    Log.d("systemHell", "Message 3");
                    createNewContactDialog();
                    break;
                case R.id.catFrappe:
                    //put something to save the Frappe category
                    c1 = "frappe";
                    c3 = "frappe";
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
                        //if user chooses "Fruit-Based"
                        c2 = "_one";
                        c1 = c1.concat(c2);
                        catBundle.putString("subcats", c1);
                        c1 = null;
                        c1 = c3;
                        break;
                    case 1:
                        //if user chooses "Coffee-Based"
                        c2 = "_two";
                        c1 = c1.concat(c2);
                        catBundle.putString("subcats", c1);
                        c1 = null;
                        c1 = c3;
                        break;
                    case 2:
                        //if user chooses "Milk-Based"
                        c2 = "_three";
                        c1 = c1.concat(c2);
                        catBundle.putString("subcats", c1);
                        c1 = null;
                        c1 = c3;
                        break;
                }
            }
        });

        homeDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        searchChecker = "searchByCategory";
                        catBundle.putString("checker", searchChecker);
                        fragment.setArguments(catBundle);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
                        //Toast.makeText(getActivity(), "Here are the results", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
        homeDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
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
                                //if user chooses "Hot"
                                c2 = "_one";
                                c1 = c1.concat(c2);
                                catBundle.putString("subcats", c1);
                                c1 = null;
                                c1 = c3;
                                break;
                            case 1:
                                //if user chooses "Cold"
                                c2 = "_two";
                                c1 = c1.concat(c2);
                                catBundle.putString("subcats", c1);
                                c1 = null;
                                c1 = c3;
                                break;
                        }
//                        homeDialog.dismiss();
                    }
                });

        homeDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        searchChecker = "searchByCategory";
                        catBundle.putString("checker", searchChecker);
                        fragment.setArguments(catBundle);
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
                        //Toast.makeText(getActivity(), "Here are the Reults", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
        homeDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

        homeDialog = homeDialogBuilder.create();
        homeDialog.show();
    }

}
