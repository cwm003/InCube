package com.cwm.incube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Button _toapp =(Button)findViewById(R.id.button2) ;
        _toapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x =new Intent(getApplicationContext(),MapsActivity.class) ;
                startActivity(x);
            }
        });
    }
}
