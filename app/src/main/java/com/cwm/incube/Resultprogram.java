package com.cwm.incube;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

public class Resultprogram extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        setContentView(R.layout.resultprogram);

        //receive Data tree in Programinput clss
        String main_tree= getIntent().getStringExtra("main_tree"); // 1st tree
        String second_tree= getIntent().getStringExtra("second_tree"); // 2nd tree
        String third_tree= getIntent().getStringExtra("third_tree"); // 3rd tree

        //Example use varriable
        TextView maintree = findViewById(R.id.maintree);
        TextView secondtree = findViewById(R.id.secondtree);
        TextView thirdtree = findViewById(R.id.thirdtree);
        maintree.setText(main_tree);
        secondtree.setText(second_tree);
        thirdtree.setText(third_tree);



    }
}
