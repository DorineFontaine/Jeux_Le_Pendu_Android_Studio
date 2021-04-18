package com.example.lependu_38007562;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;




import java.util.ArrayList;

public class Activity_Jeu extends Activity {
    String[] ALea =  {"CHINE", "RUSSIE", "FRANCE", "CAROTTE", "COURGETTE", "OLIVE", "CHIEN", "CHAT", "ANE"};

    String word;
    int letter_found;
    int error;
    ArrayList<Button> listeButton;
    LinearLayout container;
    private ImageView images;
    private boolean win;
    Dialog dialog_win, dialog_lose,dialog_help;
    ArrayList<String> letter_use_clavier;
    String letter_revealed;

    String[] myList;
   // Intent i= new Intent(this, MainActivity2.class);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);
        letter_use_clavier = new ArrayList<String>();
        container = (LinearLayout)findViewById(R.id.word_container);
        images=(ImageView)findViewById(R.id.img_pendu);
        listeButton = new ArrayList<Button>();
        dialog_win = new Dialog(this);
        dialog_lose = new Dialog(this);
        dialog_help = new Dialog(this);
        Bundle word_array = getIntent().getExtras();
        if (word_array != null){
            this.myList = (String[]) word_array.getSerializable("array");
        }

         word = myList[0];
        initJeux();


    }
    public void initJeux() {

        win= false;
        error = 0;
        letter_found = 0;
        images.setBackgroundResource(R.drawable.first);
        //Création dynamique de textView

        container.removeAllViews();
        //reStart();
        for (int i = 0; i < word.length(); i++) {
            TextView view = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            container.addView(view);
        }

    }

    public void isIn(String l, String word){
        for(int i=0;i<word.length();i++){
            if(l.equals(String.valueOf(word.charAt(i)))){
                TextView view=(TextView)container.getChildAt(i);
                view.setText((String.valueOf(word.charAt(i))));
                letter_found++;
            }
        }
    }



    public void winOrnot(String b){
        if(letter_found==word.length()){
            win=true;
            openWinDialog();


        }

        //La lettre n'est pas dans le mot
        if(!word.contains(b)){
            error++;
        }
        setImageError(error);
        if(error==7){
            win=false;

            closeWinDialog();


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
/*
    public void reStart(){
     if (!listeButton.isEmpty()){
         do{
             for(int i=0;i>listeButton.size();i++){
                 Button button_activate=(Button)listeButton.get(i);
                 button_activate.setEnabled(true);


             }
         }while(!listeButton.isEmpty());


     }

    }*/

    private void openWinDialog(){
        dialog_win.setContentView(R.layout.victoire);
        //dialog_win.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        Button btn= dialog_win.findViewById(R.id.btn_rejouer);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initJeux();
                dialog_win.dismiss();


            }
        });
        dialog_win.show();
    }
    private void closeWinDialog(){
        dialog_lose.setContentView(R.layout.defaite);
      //  dialog_lose.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        Button btn= dialog_lose.findViewById(R.id.btn_rejouer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initJeux();
                dialog_lose.dismiss();


            }
        });
        dialog_lose.show();


    }


    public void pressButton(View v) {
        Button btn = (Button) findViewById(v.getId());
        btn.setEnabled(false);
        //on ajoute le bouton dans un tableau de bouton pour le réactiver par la suite
        listeButton.add(btn);
        String textButton = btn.getText().toString();
        letter_use_clavier.add(textButton);
        isIn(textButton,word); //on incremente ou non trouver



        //Gestion des erreurs ou victoire
        winOrnot(textButton);


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
    public void showLetter(){

       if(!letter_use_clavier.isEmpty()) {


            for (int i = 0; i <word.length() ; i++) {
                String l = String.valueOf(word.charAt(i));
                for (int j = 0; j < letter_use_clavier.size(); j++) {
                    if (!l.equals(letter_use_clavier.get(j))){

                    letter_revealed =  l;
                    return;


                }



            }
        }}else{
            letter_revealed=String.valueOf(word.charAt(0)) ;
        }

    }



    public void retour(View view) {
        Intent i= new Intent(this, Activity_menu.class);
        startActivity(i);
    }
}
