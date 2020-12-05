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

public class Greetings extends AppCompatActivity {
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
        words.add(new Word("Hello,How are you?", "Hello,apni  kemon acho?","হ্যালো, আপনি কেমন আছেন?", R.raw.my_name));
        words.add(new Word("I am fine", "ami Bhalo achi", "আমি ভালো আছি",R.raw.where_r_u_going));
        words.add(new Word("Where are you going?", "Tumi kothai jaccho?", "আপনি কোথায় যাচ্ছেন?",R.raw.where_r_u_going));
        words.add(new Word("Good Morning", "Suprabhāta","সুপ্রভাত", R.raw.my_name));
        words.add(new Word("Good Afternoon", "Śubha aparāhnna","শুভ অপরাহ্ন", R.raw.my_name));
        words.add(new Word("Good Evening", "Śubha sandhyā","শুভ সন্ধ্যা", R.raw.my_name));
        words.add(new Word("Good Night", "Śubha rātri","শুভ রাত্রি", R.raw.my_name));
        words.add(new Word("Thank you", "Dhan'yabād", "ধন্যবাদ",R.raw.what_is_your_name));
        words.add(new Word("Sorry", "Duḥkhita", "দুঃখিত",R.raw.what_is_your_name));
        words.add(new Word("What is your name?", "Apnar  nam ki?", "আপনার নাম কি?",R.raw.what_is_your_name));
        words.add(new Word("My name is...", "Amar nam...","আমার নাম", R.raw.my_name));
        words.add(new Word("Let’s go.", "Cholo jai","চলো যাই", R.raw.lets_go));
        words.add(new Word("Come here.", "Ekhane  esho","এখানে আসো", R.raw.come_here));
        words.add(new Word("Where do you come from?", "Apni kotha theke eshechen?","আপনি কোথা থেকে এসেছেন?" ,R.raw.come_here));
        words.add(new Word("I come from...(country)?", "Āmi ēsēchi...(desh) theke","আমি  এসেছি ...থেকে " ,R.raw.come_here));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);


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

                    mMediaPlayer = MediaPlayer.create(Greetings.this, word.getAudioResourceId());


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
