package com.example.lependu_38007562;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class Activity_Jeu extends Activity {

    int a=0;
    int number=0;
    String word;
    int letter_found;
    int error;
    ArrayList<Button> listeButton;
    LinearLayout container;
    private ImageView images;

    Dialog dialog_result,dialog_help;
    ArrayList<String> letter_found_clavier;
    ArrayList<String> letter_word;
    String letter_revealed;
    String[] ALeatoire =  {"CHINE", "RUSSIE", "FRANCE", "CAROTTE", "COURGETTE", "OLIVE", "CHIEN", "CHAT", "ANE"};
    String[]  myList;
   // Intent i= new Intent(this, MainActivity2.class);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        letter_found_clavier = new ArrayList<String>();
        letter_word = new ArrayList<String>();
        container = (LinearLayout)findViewById(R.id.word_container);
        images=(ImageView)findViewById(R.id.img_pendu);
        listeButton = new ArrayList<Button>();
        dialog_result = new Dialog(this);
        dialog_help = new Dialog(this);

        Bundle word_array = getIntent().getExtras();
        if (word_array != null){
            Log.d("myTag", "j'ai recupere mon tableu Activity_jeux");

            this.myList = (String[] ) word_array.getSerializable("array");
        }


            initJeux();




    }

    public void initJeux() {
        if (number <= Math.max(ALeatoire.length,myList.length)) {
            if (myList != null) word = myList[number];
            else word = ALeatoire[number];
            number++;
        }else number=0;


        error = 0;
        letter_found = 0;
        images.setBackgroundResource(R.drawable.first);

        //Création dynamique de textView
        reStart();
        container.removeAllViews();
        for (int i = 0; i < word.length(); i++) {
            TextView view = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            container.addView(view);
        }





        }


    public ArrayList<String> ArrayLetterWord(String word){
        for (int i=0; i <word.length();i++){
            letter_word.add(String.valueOf(word.charAt(i)));


        }
        return letter_word;
    }


    public void isIn(String l, String word) {

        for (int i = 0; i < word.length(); i++) {
            if (l.equals(String.valueOf(word.charAt(i)))) {
                TextView view = (TextView) container.getChildAt(i);
                view.setText((String.valueOf(word.charAt(i))));
                letter_found++;
                letter_found_clavier.add(l);

            }
        }
    }




    public void winOrnot(String b){

        if(letter_found==word.length()){

            closeWinDialog(true);


        }

        //La lettre n'est pas dans le mot
        if(!word.contains(b)){
            error++;
        }
        setImageError(error);
        if(error==7){


            closeWinDialog(false);


        }

    }

    public void setImageError(int e){
        switch(e){
            case 1:
                images.setBackgroundResource(R.drawable.second);
                break;

            case 2:
                images.setBackgroundResource(R.drawable.third);
                break;

            case 3:
                images.setBackgroundResource(R.drawable.four);
                break;


            case 4:
                images.setBackgroundResource(R.drawable.five);
                break;

            case 5:
                images.setBackgroundResource(R.drawable.six);
                break;

            case 6:
                images.setBackgroundResource(R.drawable.seven);
                break;



        }


    }

    public void reStart(){


             for(int i=0;i<listeButton.size();i++){
                 Button button_activate=(Button)listeButton.get(i);
                 button_activate.setEnabled(true);


             }


    }


    private void closeWinDialog(Boolean win){
        Intent j = new Intent(this, Activity_menu.class);
        if (win ) dialog_result.setContentView(R.layout.victoire);
        else dialog_result.setContentView(R.layout.defaite);

      //  dialog_lose.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        Button btn= dialog_result.findViewById(R.id.btn_rejouer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initJeux();
                dialog_result.dismiss();


            }
        });
        Button btn2= dialog_result.findViewById(R.id.menu);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(j);
                dialog_result.dismiss();




            }
        });
        dialog_result.show();


    }


    public void pressButton(View v) {
        Button btn = (Button) findViewById(v.getId());
        btn.setEnabled(false);
        Log.d("my tag","CCCCCCCCCCCCCCCCC");
        //on ajoute le bouton dans un tableau de bouton pour le réactiver par la suite
        listeButton.add(btn);
        String textButton = btn.getText().toString();
        letter_found_clavier.add(textButton);

        isIn(textButton,word); //on incremente ou non trouver



        //Gestion des erreurs ou victoire
        winOrnot(textButton);
        a++;


    }

    public void aide(View view) {
        Intent i= new Intent(this, Activity_menu.class);
        dialog_help.setContentView(R.layout.aide);
        dialog_help.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn_close=dialog_help.findViewById(R.id.close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_help.dismiss();
            }
        });
        Button btn_menu=dialog_help.findViewById(R.id.menu_jeux);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
                dialog_help.dismiss();
            }
        });
        Button btn_lettre=dialog_help.findViewById(R.id.lettre);

        btn_lettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLetter();
                btn_lettre.setText(letter_revealed);

            }
        });




        dialog_help.show();

    }
    public void showLetter( ) {

    }



    public void retour(View view) {
        Intent i= new Intent(this, Activity_menu.class);
        startActivity(i);
    }
}
