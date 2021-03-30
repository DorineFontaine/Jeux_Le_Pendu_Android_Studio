package com.example.lependu_38007562;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    String word;
    int letter_found;
    int error;
    ArrayList<Button> listeButton= new ArrayList<Button>();
    LinearLayout container;
    private ImageView images;
    private boolean win;
    Dialog dialog_open,dialog_close;
    String[] myList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container=(LinearLayout)findViewById(R.id.word_container);
        images=(ImageView)findViewById(R.id.img_pendu);
        dialog_open=new Dialog(this);
        dialog_close=new Dialog(this);
        Bundle word_array = getIntent().getExtras();
        if (word_array!= null){
            this.myList = (String[]) word_array.getSerializable("array");
        }

        initJeux();
    }
    public void initJeux() {
        int rand=(int)Math.random()*(2-0);
        word = myList[rand];
        win= false;
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
    public void reStart(){
        do{
            for(int i=0;i>listeButton.size();i++){
                Button btn=(Button)listeButton.get(i);
                //Button btn = (Button) findViewById(R.id.);
                btn.setEnabled(true);


            }
        }while(!listeButton.isEmpty());

    }


    private void openWinDialog(){
        dialog_open.setContentView(R.layout.victoire_layout);
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
        dialog_close.setContentView(R.layout.defaite_layout);
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
        listeButton.add(btn);
        String textButton = btn.getText().toString();
        isIn(textButton,word); //on incremente ou non trouver
        btn.setEnabled(false);

        //Gestion des erreurs ou victoire
        winOrnot(textButton);
        //reStart();

    }
}