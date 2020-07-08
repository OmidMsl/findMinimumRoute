package com.OmidMosalmani.algorithmproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText lengthText = findViewById(R.id.length);
        final EditText widthText = findViewById(R.id.width);
        Button button = findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lstr=lengthText.getText().toString();
                String wstr=widthText.getText().toString();
                if(lstr.isEmpty() && wstr.isEmpty())
                    Toast.makeText(getApplicationContext() , "please enter length and width" , Toast.LENGTH_SHORT).show();
                else if (lstr.isEmpty())
                    Toast.makeText(getApplicationContext() , "please enter length" , Toast.LENGTH_SHORT).show();
                else if (wstr.isEmpty())
                    Toast.makeText(getApplicationContext() , "please enter width" , Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(MainActivity.this , InputsActivity.class);
                    intent.putExtra("length" , Integer.parseInt(lstr));
                    intent.putExtra("width" , Integer.parseInt(wstr));
                    startActivity(intent);
                }
            }
        });
    }
}
