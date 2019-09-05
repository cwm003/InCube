package com.cwm.incube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Select_apps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        setContentView(R.layout.activity_select_apps);

        ImageButton _goprograminput =(ImageButton) findViewById(R.id.imageButton3) ;
        _goprograminput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x =new Intent(getApplicationContext(),MapsActivity.class) ;
                startActivity(x);
            }
        });

        ImageButton _gonews =(ImageButton) findViewById(R.id.imageButton) ;
        _gonews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x =new Intent(getApplicationContext(),news.class) ;
                startActivity(x);
            }
        });

//        ImageButton _goprice =(ImageButton) findViewById(R.id.imageButton4) ;
//        _goprice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent x =new Intent(getApplicationContext(),price_app.class) ;
//                startActivity(x);
//            }
//        });
    }
}
