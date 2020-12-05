package com.example.android.learneasybangla;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class DirectionPhrases extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;


    private AudioManager mAudioManager;


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayer();
        }
    };


    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Excuse me,how do i get to the library?", "Ami kibhabe library te jete pari?","আমি কিভাবে লাইব্রেরিতে যেতে পারি?", R.raw.my_name));
        words.add(new Word("Where is the nearest  shop?", "Nikot thomo dokan kothai?", "নিকটতম দোকান কোথায়?",R.raw.where_r_u_going));
        words.add(new Word("Where are you going?", "Tumi kothai jaccho?", "আপনি কোথায় যাচ্ছেন?",R.raw.where_r_u_going));
        words.add(new Word("How do i find.....?", "Ami kibhabe.....khuje pete pari?","আমি কিভাবে...... খুঁজে পেতে পারি", R.raw.my_name));
        words.add(new Word("Can you tell me the way to....", "Apni amake....path bolte paren?"," আপনি আমাকে ......পথ বলতে পারেন?", R.raw.my_name));
        words.add(new Word("Where is....?", "Kothai?","কোথায়", R.raw.my_name));
        words.add(new Word("Is this the right way to....?", "Eta ki.......jaoar shotik path?", "এটা কি......যাওয়ার  সঠিক পথ?",R.raw.what_is_your_name));
        words.add(new Word("I am looking for this address", "Ami ei thikana khujjchi", "আমি এই ঠিকানা খুঁজছি",R.raw.what_is_your_name));
        words.add(new Word("Is it far?", "Eta ki dure?", "এটা কি দূরে?",R.raw.what_is_your_name));

        WordAdapter  adapter = new WordAdapter(this, words, R.color.category_phrases);


        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();


                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(DirectionPhrases.this, word.getAudioResourceId());


                    mMediaPlayer.start();


                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }


    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {

            mMediaPlayer.release();


            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
