package com.example.lependu_38007562;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.Serializable;

public class Activity_liste extends Activity {

    ListView simpleList;
    String countryList[] = {"PAYS", "VILLE", "MARQUE DE VOITURE ", "ANIMAUX", "FRUITS ET LEGUMES", "COULEURS","MARQUE DE VETEMENTS","ALEATOIRE"};
    int flags[] = {R.drawable.pays, R.drawable.ville, R.drawable.voiture, R.drawable.animaux, R.drawable.fruit, R.drawable.couleur,R.drawable.vetement,R.drawable.pays};
    String pays[]={"CHINE","RUSSIE","FRANCE"};
    String fruit[]={"CAROTTE","COURGETTE","OLIVE"};
    String animaux[]={"CHIEN","CHAT","ANE"};
    String ville[]={"TOKYO","DELHIE","PARIS"};
    String marque_voiture[]={"TOYOTA","AUDI","PEUGEOT"};
    String couleur[]={"BLEU","ROUGE","NOIR"};
    String vetement[]={"LACOSTE","KIABI","ASSOS"};
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

                sendInfo(position);
            }
        });
    }

    public void sendInfo(int nombre){


        Intent i= new Intent(this, Activity_Jeu.class);

        switch(nombre){
            case 0:
                i.putExtra("array", (Serializable)pays);
                break;
            case 1:
                i.putExtra("array", (Serializable)ville);
                break;

            case 2:
                i.putExtra("array", (Serializable)marque_voiture);
                break;
            case 3:
                i.putExtra("array", (Serializable)animaux);
                break;
            case 4:
                i.putExtra("array", (Serializable)fruit);
                break;
            case 5:
                i.putExtra("array", (Serializable)couleur);
                break;

            case 6:
                i.putExtra("array", (Serializable)vetement);
                break;


        }
        startActivity(i);
    }


}