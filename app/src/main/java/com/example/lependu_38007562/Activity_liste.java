package com.example.lependu_38007562;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Activity_liste extends Activity {

    ListView simpleList;
    String countryList[] = {"PAYS", "VILLE", "MARQUE DE VOITURE ", "ANIMAUX", "FRUITS ET LEGUMES", "COULEURS","MARQUE DE VETEMENTS","ALEATOIRE"};
    int flags[] = {R.drawable.drapeau, R.drawable.ville, R.drawable.voiture, R.drawable.animaux, R.drawable.fruit, R.drawable.couleur,R.drawable.vetement,R.drawable.aleatoire};
    String pays[]={"CHINE","RUSSIE","FRANCE"};
    String fruit[]={"CAROTTE","COURGETTE","OLIVE"};
    String animaux[]={"CHIEN","CHAT","ANE"};
    String ville[]={"TOKYO","DELHIE","PARIS"};
    String marque_voiture[]={"TOYOTA","AUDI","PEUGEOT"};
    String couleur[]={"BLEU","ROUGE","NOIR"};
    String vetement[]={"LACOSTE","KIABI","ASSOS"};
    String[] ALeatoire =  {"CHINE", "RUSSIE", "FRANCE", "CAROTTE", "COURGETTE", "OLIVE", "CHIEN", "CHAT", "ANE"};
    Intent i;
    ArrayList<String> wordList;
    ArrayList<View> listeView=new ArrayList<View>();
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
                if(listeView!=null){
                    for(int p =0; p<listeView.size();p++){
                        listeView.get(p).setBackgroundColor(Color.WHITE);

                    }

                }
                View text = customAdapter.getView2(view);
                text.setBackgroundColor(Color.rgb(105,83,95));
                sendInfo(position);
                view.setBackgroundColor(Color.rgb(105,83,95));


                listeView.add(text);
                listeView.add(view);
            }
        });
        i = new Intent(this, Activity_menu.class);

    }

    public void sendInfo(int nombre){




        switch(nombre){
            case 0:
                i.putExtra("array", (Serializable)pays);
                startActivity(i);
                break;
            case 1:

                i.putExtra("array", (Serializable)ville);
                startActivity(i);
                break;

            case 2:

                i.putExtra("array", (Serializable)marque_voiture);
                startActivity(i);
                break;
            case 3:

                i.putExtra("array", (Serializable)animaux);
                startActivity(i);
                break;
            case 4:

                i.putExtra("array", (Serializable)fruit);
                startActivity(i);
                break;
            case 5:

                i.putExtra("array", (Serializable)couleur);
                startActivity(i);
                break;

            case 6:

                i.putExtra("array", (Serializable)vetement);
                startActivity(i);
                break;
            case 7:

                i.putExtra("array", (Serializable)ALeatoire);
                startActivity(i);
                break;


        }

    }

   public ArrayList<String> getArrayWorld(String fichier){

       try {
           BufferedReader buffer = new BufferedReader( new InputStreamReader(getAssets().open(fichier)));
           String word;
           while((word = buffer.readLine()) != null){
               wordList.add(word);


           }
           buffer.close();

       } catch (IOException e) {
           e.printStackTrace();
       }


       return wordList;
   }


}