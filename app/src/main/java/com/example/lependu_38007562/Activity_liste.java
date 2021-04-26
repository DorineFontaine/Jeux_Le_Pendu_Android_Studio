package com.example.lependu_38007562;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;


public class Activity_liste extends Activity {

    //TABLEAU ET VARIABLE
    ListView simpleList;
    String countryList[] = {String.valueOf(R.string.pays), "VILLE", "MARQUE DE VOITURE ", "ANIMAUX", "FRUITS ET LEGUMES", "COULEURS","MARQUE DE VETEMENTS","ALEATOIRE"};
    int flags[] = {R.drawable.drapeau, R.drawable.ville, R.drawable.voiture, R.drawable.animaux, R.drawable.fruit, R.drawable.couleur,R.drawable.vetement,R.drawable.aleatoire};

    Intent i;
    String themeString;
    ArrayList<String> wordList;
    ArrayList<String> comList;
    ArrayList<View> listeView = new ArrayList<View>();
    int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), countryList, flags);
        simpleList.setAdapter(customAdapter);
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //décoloration du themes aprés selection
                if(listeView!=null){
                    for(int p =0; p < listeView.size(); p++){
                        listeView.get(p).setBackgroundColor(Color.WHITE);
                    }
                }

                //coloration du themes selectionne
                View text = customAdapter.getView2(view);
                text.setBackgroundColor(Color.rgb(105,83,95));
                sendInfo(position);
                view.setBackgroundColor(Color.rgb(105,83,95));


                listeView.add(text);
                listeView.add(view);
            }});
        i = new Intent(this, Activity_menu.class);
        wordList = new ArrayList<String>();
        comList = new ArrayList<String>();

    }

    public void sendInfo(int nombre){
        comList = getArrayWord("INDICE.txt");


        //Envoie du tableau de mot au menu selon le theme selectionné

        switch(nombre){
            case 0:
                Log.d("test","je suis dans le cas 0");
               // wordList = getArrayWord("PAYS.txt" );
                themeString = "PAYS.txt";

                theme = 0;
                i.putExtra("String",themeString);
                startActivity(i);
                break;

            case 1:
                wordList = getArrayWord("VILLE.txt" );
                theme = 1;
                break;

            case 2:
                wordList = getArrayWord("VOITURE.txt" );
                theme = 2;
                break;

            case 3:
                wordList = getArrayWord("ANIMAUX.txt" );
                theme = 3;
                break;

            case 4:
                wordList = getArrayWord("FRUIT.txt" );
                theme = 4;
                break;

            case 5:
                wordList = getArrayWord("COULEUR.txt" );
                theme = 5;
                break;

            case 6:
                wordList = getArrayWord("VETEMENT.txt" );
                theme = 6;
                break;

            case 7:
                wordList = getArrayWord("ALEA.txt" );
                theme = 7;
                break;
            }


      //  i.putExtra("array", (Serializable)wordList);
       // i.putExtra("indice", (Serializable)comList);
        i.putExtra("theme", theme );


        startActivity(i);
    }

    public  ArrayList<String> getArrayWord(String fichier){
        ArrayList<String> tab = new ArrayList<String>();
        try {
            //Lecture du fichier dans lequel se trouve les mots et affectation a un tableau
            BufferedReader buffer = new BufferedReader( new InputStreamReader(getAssets().open(fichier)));
            String line;
            while((line = buffer.readLine()) != null){
                tab.add(line); }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace(); }

        return tab; }




}