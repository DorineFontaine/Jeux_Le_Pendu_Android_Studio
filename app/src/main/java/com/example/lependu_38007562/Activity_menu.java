package com.example.lependu_38007562;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class Activity_menu extends AppCompatActivity {

    //TABLEAUX ET VARIABLES
    Bundle word_array;
    String myArray;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        word_array = getIntent().getExtras();

        //On récupére le tableau de mot
        if (word_array != null){
            myArray = word_array.getString("theme");
        } }

    //REDIRECTION VERS LE JEUX
    public void startGame(View view) {

        //On transmet le tableau de mot à l'activité Jeux
        Intent i = new Intent(this, Activity_Jeu.class);
        i.putExtra("theme", myArray);
        startActivity(i); }


    //REDIRECTION VERS LE JEUX
    public void getSetUp(View view) {
        Intent i = new Intent(this, Activity_liste.class);
        startActivity(i);

    }
}