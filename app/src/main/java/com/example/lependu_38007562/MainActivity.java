package com.example.lependu_38007562;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String word;
    int letter_found;
    int error;
    ArrayList<Integer> listeIdButton= new ArrayList<Integer>();
    LinearLayout container;
    private ImageView images;
    private boolean win;
    Dialog dialog_open,dialog_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container=(LinearLayout)findViewById(R.id.word_container);
        images=(ImageView)findViewById(R.id.img_pendu);
        dialog_open=new Dialog(this);
        dialog_close=new Dialog(this);
        initJeux();
    }
    public void initJeux() {
        word = "MICHELINE";
        win= false;
        error = 0;
        letter_found = 0;
        images.setBackgroundResource(R.drawable.first);
        //Création dynamique de textView
        container.removeAllViews();
        for (int i = 0; i < word.length(); i++) {
            TextView view = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            container.addView(view);
        }

    }

    public void estDedans(String l, String word){
        for(int i=0;i<word.length();i++){
            if(l.equals(String.valueOf(word.charAt(i)))){
                TextView view=(TextView)container.getChildAt(i);
                view.setText((String.valueOf(word.charAt(i))));
                letter_found++;
            }


        }

    }



    public void gagnerOupas(String b){
        if(letter_found==word.length()){
            win=true;
            openWinDialog();


        }

        //La lettre n'est pas dans le mot
        if(!word.contains(b)){
            error++;
        }
        setImageErreur(error);
        if(error==11){
            win=false;

            closeWinDialog();


        }

    }

    public void setImageErreur(int e){
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
        do{
            for(int i=0;i>listeIdButton.size();i++){
                int id=(int)listeIdButton.get(i);
                //Button btn = (Button) findViewById(R.id.);
                //btn.setEnabled(true);


            }
        }while(!listeIdButton.isEmpty());

    }
    public void boiteDialog(boolean win){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(!win){
            builder.setTitle("Vous avez perdu");
            builder.setMessage("Le mot à trouver était :"+ word);


        }else{
            builder.setTitle("vous avez gagné ");
        }
        builder.setPositiveButton(getResources().getString(R.string.rejouer), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                initJeux();
            }
        });
        builder.create().show();

    }

    private void openWinDialog(){
        dialog_open.setContentView(R.layout.victoire_layout_dialog);
        dialog_close.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btn=dialog_open.findViewById(R.id.btn_rejouer);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initJeux();
                dialog_open.dismiss();


            }
        });
        dialog_open.show();
    }
    private void closeWinDialog(){
        dialog_close.setContentView(R.layout.defaite_layout_dialog);
        dialog_close.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btn=dialog_close.findViewById(R.id.btn_rejouer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initJeux();
                dialog_close.dismiss();


            }
        });
        dialog_close.show();
    }


    public void pressButton(View v) {
        Button btn = (Button) findViewById(v.getId());
        listeIdButton.add(v.getId());
        String textButton = btn.getText().toString();
        estDedans(textButton,word); //on incremente ou non trouver
        btn.setEnabled(false);

        //Gestion des erreurs ou victoire
        gagnerOupas(textButton);
        //reStart();

    }
}