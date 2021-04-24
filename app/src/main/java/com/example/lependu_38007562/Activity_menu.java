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
    String[] ALea =  {"CHINE", "RUSSIE", "FRANCE", "CAROTTE", "COURGETTE", "OLIVE", "CHIEN", "CHAT", "ANE"};
    String[] test;
    Bundle word_array;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        word_array = getIntent().getExtras();

        if (word_array != null){
            Log.d("myTag", "Mon tableau test se rempli");
            this.test = (String[]) word_array.getSerializable("array");
        }
    }

    public void startGame(View view) {

        Intent i = new Intent(this, Activity_Jeu.class);
        if (test == null){
            Log.d("myTag", "mon test est null");

            i.putExtra("array", (Serializable)ALea);
            startActivity(i);
        } else{
            i.putExtra("array", (Serializable)test);

        }



        startActivity(i);


    }

    public void getSetUp(View view) {
        Intent i = new Intent(this, Activity_liste.class);
        startActivity(i);

    }
}