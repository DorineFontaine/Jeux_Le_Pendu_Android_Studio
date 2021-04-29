package com.example.lependu_38007562;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Activity_liste extends Activity {

    //TABLEAU ET VARIABLE
    ListView simpleList;
    String countryList[] = {"PAYS", "VILLE", "MARQUE DE VOITURE ", "ANIMAUX", "FRUITS ET LEGUMES", "COULEURS","MARQUE DE VETEMENTS","ALEATOIRE"};
    int flags[] = {R.drawable.drapeau, R.drawable.ville, R.drawable.voiture, R.drawable.animaux, R.drawable.fruit, R.drawable.couleur,R.drawable.vetement,R.drawable.aleatoire};

    Intent i;
    ArrayList<View> listeView = new ArrayList<View>();


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
                        listeView.get(p).setBackgroundColor(Color.WHITE); } }

                //coloration du themes selectionne
                View text = customAdapter.getView2(view);
                text.setBackgroundColor(Color.rgb(105,83,95));
                sendInfo(position);
                view.setBackgroundColor(Color.rgb(105,83,95));


                listeView.add(text);
                listeView.add(view);
            }});
        }

    public void sendInfo(int nombre){
        i = new Intent(this, Activity_menu.class);



        //Envoie du tableau de mot au menu selon le theme selectionné
        switch(nombre){
            case 0:

                i.putExtra("theme","PAYS.txt");
                break;

            case 1:
                i.putExtra("theme","VILLE.txt");
                break;

            case 2:
                i.putExtra("theme","VOITURE.txt");
                break;

            case 3:
                i.putExtra("theme","ANIMAUX.txt");
                break;

            case 4:
                i.putExtra("theme","FRUIT.txt");
                break;

            case 5:
                i.putExtra("theme","COULEUR.txt");
                break;

            case 6:
                i.putExtra("theme","VETEMENT.txt");
                break;

            case 7:
                i.putExtra("theme","ALEA.txt");
                break;
        }
        startActivity(i);
         }
}