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


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

 public class Activity_Jeu extends Activity {


     // VARIABLES

    int score = 0;
    String s = String.valueOf(score);
    int random;
    int letter_found;
    int error;
    String word;
    String letter_revealed;
    String indice;
    String fichier;


    //CONSTANTE
     private static final String FILE_NAME = "SCORE.txt";

    LinearLayout container;
    private ImageView images;
    Dialog dialog_result,dialog_help;
    TextView Showscore;

    //TABLEAUX
    ArrayList<String> letter_found_clavier;
    ArrayList<String> letter_word;
    ArrayList<Button> listeButton;
    ArrayList<String> myArray;
    ArrayList<String> myArrayCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        //INITIALISATION
        listeButton = new ArrayList<Button>();
        letter_found_clavier = new ArrayList<String>();
        letter_word = new ArrayList<String>();
        myArrayCom = new ArrayList<String>();

        container = (LinearLayout)findViewById(R.id.word_container);
        images =(ImageView)findViewById(R.id.img_pendu);
        Showscore = findViewById(R.id.textView3);


        dialog_result = new Dialog(this);
        dialog_help = new Dialog(this);


        //RECUPERATION DU TABLEAU DE MOT
        Bundle word_array = getIntent().getExtras();
        if (word_array!=null){
            this.fichier = word_array.getString("theme","ALEA.txt");
            myArray = getArrayWord(fichier); }


        //DEBUT DU JEUX
        initJeux(); }


    public void initJeux() {
     //   s = load();
        Showscore.setText(String.valueOf(score) + "$");
        word = generatedWord();
        error = 0;
        letter_found = 0;
        images.setBackgroundResource(R.drawable.first);
        //ACTIVATION DE TOUT LES BOUTONS
        reStart();
        //CREATION DYNAMIQUE DE TEXTVIEW
        container.removeAllViews();
        for (int i = 0; i < word.length(); i++) {
            TextView view = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            container.addView(view); } }





    public String generatedWord(){
        String mot;
        random = (int) Math.floor(Math.random() *  myArray.size());
        mot = myArray.get(random).trim();
        return mot;}


    public ArrayList<String> ArrayLetterWord(String word){
        for (int i = 0; i < word.length(); i++){
            letter_word.add(String.valueOf(word.charAt(i))); }
        return letter_word; }


    public void isIn(String l, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (l.equals(String.valueOf(word.charAt(i)))) {
                TextView view = (TextView) container.getChildAt(i);
                view.setText((String.valueOf(word.charAt(i))));
                letter_found++;
                letter_found_clavier.add(l); } } }




    public void winOrnot(String b){

        //PARTIE GAGNER
        if(letter_found == word.length()){
            closeWinDialog(true);
            score += 5;
            Showscore.setText(String.valueOf(score) + "$");
           // s = String.valueOf(score);
            //save();
        }

        //INCREMENTATION DE ERREUR SI LA LETTRE N'EST PAS DANS LE MOT
        if(!word.contains(b)){
            error++; }
        setImageError(error);

        //PARTIE PERDU
        if(error==7){
            closeWinDialog(false);} }

    public void setImageError(int e){
        //ON CHANGE D'IMAGE EN FONCTION DU NOMBRE D'ERREUR FAIT
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
                break; } }

    public void reStart(){
        //REACTIVE LES BOUTONS UTILISER
        for(int i=0;i<listeButton.size();i++){
            Button button_activate=(Button)listeButton.get(i);
            button_activate.setEnabled(true); } }


    private void closeWinDialog(Boolean win){
        //BOITE DE DIALOGUE DU RESULTAT
        Intent j = new Intent(this, Activity_menu.class);

        if (win ) dialog_result.setContentView(R.layout.victoire);
        else{
            dialog_result.setContentView(R.layout.defaite);
            TextView mot = dialog_result.findViewById(R.id.textView4);
            mot.setText("Le mot à deviner était "+""+ word); }

        //BOUTON REJOUER
        Button btn= dialog_result.findViewById(R.id.btn_rejouer);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //COMMENCEMENT D'UNE NOUVELLE PARTIE
                initJeux();
                dialog_result.dismiss(); }});

        //BOUTON MENU
        Button btn2= dialog_result.findViewById(R.id.menu);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //REDIRECTION MENU
                startActivity(j);
                dialog_result.dismiss(); }});

        dialog_result.show(); }


    public void pressButton(View v) {
        //ACTION DU CLAVIER
        Button btn = (Button) findViewById(v.getId());
        btn.setEnabled(false);

        //ON GARDE EN MEMOIRE LE BOUTON ACTIONNER
        listeButton.add(btn);
        String textButton = btn.getText().toString();
        letter_found_clavier.add(textButton);

        //ON VERIFIE SI LA LETTRE TAPER APPARTIENT AU MOT
        isIn(textButton,word);

        //GESTION DES ERREURS ET VICTOIRE
        winOrnot(textButton); }

    public void aide(View view) {
        Intent i = new Intent(this, Activity_menu.class);

        dialog_help.setContentView(R.layout.aide);
        dialog_help.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //BOUTON DE FERMETURE
        Button btn_close = dialog_help.findViewById(R.id.close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_help.dismiss(); }});

        //BOUTON DE REDIRECTION MENU
        Button btn_menu = dialog_help.findViewById(R.id.menu_jeux);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
                dialog_help.dismiss(); }});

        //BOUTON POUR DEVOILER UNE LETTRE
        Button btn_lettre = dialog_help.findViewById(R.id.lettre);
        btn_lettre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score>= 5){
                    showLetter();
                    btn_lettre.setText(letter_revealed);
                    score -=5;
                    Showscore.setText(String.valueOf(score) + "$");
                  //  save();
                 //   s = String.valueOf(score);
                 //   Showscore.setText(s + "$");
                    }
                else btn_lettre.setText("Score insuffisant"); }});



        Button btn_commentaire = dialog_help.findViewById(R.id.indice);
        btn_commentaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score>=5){
                    showComment(fichier);
                    btn_commentaire.setText(indice);
                    score -=5;
                    Showscore.setText(String.valueOf(score) + "$");
                  //  save();
                  //  s = String.valueOf(score);
                 //   Showscore.setText(s + "$");

                }
                else btn_commentaire.setText("Score insuffisant");
                 }});

        dialog_help.show();}

    public void showLetter( ) {
        letter_word = ArrayLetterWord(word);
        Log.d("myTAG","Je suis arriver dans la fonction showLetter");
        if( letter_found_clavier == null){
            for (String str : letter_word) {
                letter_revealed = str;
               // letter_word.remove(letter_word.indexOf(str));
                } }
        else{
            for (String str : letter_word) {
                if(letter_found_clavier.contains(str)) Log.d("myTAG","On a trouver une lettre"); //letter_word.remove(letter_word.indexOf(str));
                else{
                    letter_revealed = str;
                  //  letter_word.remove(letter_word.indexOf(str));
                    Log.d("myTAG","On a trouver une lettre");} } } }



    public void showComment(String fichier){

        myArrayCom = getArrayWord("INDICE.txt");
        switch (fichier){
            case "PAYS.txt" :
                indice = myArrayCom.get(random);
                break;

            case "VILLE.txt" :

                indice = myArrayCom.get(random + 3);
                break;

            case "VOITURE.txt" :
                indice = myArrayCom.get(random + 6);
                break;

            case "ANIMAUX.txt" :
                indice = myArrayCom.get(random + 9);
                break;

            case "FRUIT.txt" :
                indice = myArrayCom.get(random + 12);
                break;

            case "COULEUR.txt" :
                indice = myArrayCom.get(random + 15);
                break;

            case "VETEMENT.txt" :
                indice = myArrayCom.get(random + 18);
                break;
            case "ALEA.txt" :
                indice = myArrayCom.get(random + 21);
                break; } }

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


    //REDIRECTION MENU
    public void retour(View view) {
        Intent i= new Intent(this, Activity_menu.class);
        startActivity(i); }


    /*******************************PERSISTANCE DES DONNEES***************************/
    public void save(){
        BufferedWriter save = null;
        Log.d("my","DANS MON SAVE HORS TRY");
        try {
            Log.d("my","DANS MON SAVE DEDANS TRY");
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME,MODE_WORLD_WRITEABLE);
            save = new BufferedWriter( new OutputStreamWriter(fileOutputStream));
            save.write(s);
            save.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if( save != null) {
                try {
                    Log.d("my","FERMETURE FICHIER");
                    save.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String load(){
       String t = " ";
        try {
            //Lecture du fichier dans lequel se trouve les mots et affectation a un tableau
            BufferedReader buffer = new BufferedReader( new InputStreamReader(getAssets().open("SCORE.txt")));
            String line;
            if ((line = buffer.readLine()) != null){
                t = line; }
            else t = String.valueOf(0);
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace(); }

        return t;
    }



}
