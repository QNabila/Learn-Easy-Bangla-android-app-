/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.learneasybangla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phraseslayout);
        //For Greetings
        Button greetings = (Button) findViewById(R.id.greetings);

        greetings.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                Intent greetingsIntent = new Intent(PhrasesActivity.this, Greetings.class);


                startActivity(greetingsIntent);
            }
        });
        //For  Conversation
        Button conversation = (Button) findViewById(R.id.conversation);

        conversation.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                Intent conversationIntent = new Intent(PhrasesActivity.this, Conversation.class);


                startActivity(conversationIntent);
            }
        });

        //For Time and date
        Button TimeDate = (Button) findViewById(R.id.TimeDate);

        TimeDate.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                Intent TimeDateIntent = new Intent(PhrasesActivity.this, TimeDate.class);


                startActivity(TimeDateIntent);
            }
        });

        //For Direction Phrases
        Button DirectionPhrases = (Button) findViewById(R.id.directionphrases);

        DirectionPhrases.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                Intent DirectionPhrasesIntent = new Intent(PhrasesActivity.this, DirectionPhrases.class);


                startActivity(DirectionPhrasesIntent);
            }
        });

        //For Direction Words
        Button DirectionWords = (Button) findViewById(R.id.directionword);

        DirectionWords.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                Intent DirectionWordsIntent = new Intent(PhrasesActivity.this, DirectionWords.class);


                startActivity(DirectionWordsIntent);
            }
        });

        //For Eating Out
        Button EatingOut = (Button) findViewById(R.id.eatingout);

       EatingOut.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {

                Intent EatingOutIntent = new Intent(PhrasesActivity.this, EatingOut.class);


                startActivity(EatingOutIntent);
            }
        });
    }
}