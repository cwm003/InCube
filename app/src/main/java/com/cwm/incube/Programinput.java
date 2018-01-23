package com.cwm.incube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Programinput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        setContentView(R.layout.activity_programinput);

        //drop down maintree
        Spinner dropdown1 = findViewById(R.id.maintreespinner);
        String[] items1 = new String[]{"--เลือก--","มะม่วง", "ลำไย"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,R.layout.spinner, items1);
        dropdown1.setAdapter(adapter1);

        //drop down 2nd tree
        Spinner dropdown2 = findViewById(R.id.secondtreespinner);
        String[] items2 = new String[]{"--เลือก--", "กล้วย"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner, items2);
        dropdown2.setAdapter(adapter2);

        //drop down 3rd tree
        Spinner dropdown3 = findViewById(R.id.thirdtreespinner);
        String[] items3 = new String[]{"--เลือก--","พริกไทย", "ผักกูด","ตะไคร้","คะน้า","ผักบุ้งจีน"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, R.layout.spinner, items3);
        dropdown3.setAdapter(adapter3);




        Button _tonewsblog =(Button)findViewById(R.id.button3) ;
        _tonewsblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x =new Intent(getApplicationContext(),MainActivity.class) ;
                startActivity(x);
            }
        });
    }
}
