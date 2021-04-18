package com.example.lependu_38007562;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class Activity_menu extends AppCompatActivity {
    String[] ALea =  {"CHINE", "RUSSIE", "FRANCE", "CAROTTE", "COURGETTE", "OLIVE", "CHIEN", "CHAT", "ANE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startGame(View view) {
        Intent i = new Intent(this, Activity_Jeu.class);
        i.putExtra("array", (Serializable)ALea);
        startActivity(i);
    }

    public void getSetUp(View view) {
        Intent i = new Intent(this, Activity_liste.class);
        startActivity(i);

    }
}