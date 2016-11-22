package com.techstar.dateutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] dayarray = {"09月18", "10月21", "11月21", "11月22", "11月23", "11月27", "12月2", "12月3", "12月23",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String str = Utils.convertTime(dayarray[2]);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
