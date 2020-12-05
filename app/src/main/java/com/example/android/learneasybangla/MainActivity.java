package com.example.android.learneasybangla;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        TextView numbers = (TextView) findViewById(R.id.numbers);


        numbers.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);


                startActivity(numbersIntent);
            }
        });

        // Find the View that shows the family category
        TextView family = (TextView) findViewById(R.id.family);

        // Set a click listener on that View
        family.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the family category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link FamilyActivity}
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);

                // Start the new activity
                startActivity(familyIntent);
            }
        });

        // Find the View that shows the colors category
        TextView colors = (TextView) findViewById(R.id.colors);

        // Set a click listener on that View
        colors.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the colors category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link ColorsActivity}
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);

                // Start the new activity
                startActivity(colorsIntent);
            }
        });

        // Find the View that shows the phrases category
        TextView phrases = (TextView) findViewById(R.id.phrases);

        // Set a click listener on that View
        phrases.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View view) {

                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);

                // Start the new activity
                startActivity(phrasesIntent);
            }
        });


        //Find the view that shows alphabet category
         TextView alphabet = (TextView) findViewById(R.id.alphabets);

        // Set a click listener on that View
       alphabet.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the phrases category is clicked on.
            @Override
            public void onClick(View view) {

                Intent  alphabetIntent = new Intent(MainActivity.this, AlphabetActivity.class);

                // Start the new activity
                startActivity(alphabetIntent);
               // AlphabetActivity alpha =new AlphabetActivity(this,null) ;
                //setContentView(alpha);



            }
        });


    }
}

