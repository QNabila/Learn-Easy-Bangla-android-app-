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

public class Conversation extends AppCompatActivity {

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
        words.add(new Word("Hi/Hello!", "Hi/Hello","হাই /হ্যালো!",R.raw.where_r_u_going));
        words.add(new Word("Goodbye", "Bidāẏa","বিদায়", R.raw.what_is_your_name));
        words.add(new Word("Never mind", "Kichu mone korona","কিছু মনে করো না", R.raw.my_name));
        words.add(new Word("Do you speak english?", "Tumi ki english ē kathā baltē pāro?","তুমি কি ইংলিশ এ কথা বলতে পার?", R.raw.how_are_you));
        words.add(new Word("Please speak more slowly.", "Daya karē ārō dhīrē kathā bolun", "দয়া করে আরো ধীরে কথা বলুন",R.raw.i_am_feeling_fine));
        words.add(new Word("Yes", "Ha", "হাঁ",R.raw.ami_ashchi));
        words.add(new Word("No.", "Na", "না",R.raw.yes_i_am_coming));
        words.add(new Word("I’m coming.", "Ami ashchi"," আমি আসছি.",R.raw.ami_ashchi));
        words.add(new Word("Okay.", "Tik ache", "ঠিক আছে",R.raw.lets_go));
        words.add(new Word("I see/Got it.", "Bujhechi","বুঝেছি", R.raw.come_here));
        words.add(new Word("I dont know", "Ami janina","আমি জানি না", R.raw.come_here));
        words.add(new Word("No problem", "Shomosya nei","সমস্যা নেই", R.raw.come_here));
        words.add(new Word("where do you live?", "Apni kothai thaken?","আপনি কোথায় থাকেন?", R.raw.come_here));
        words.add(new Word("I am sorry,i didn't hear that,could you please repeat?", "Ami duhkhito,ami shunte paini,daya kore abr bolben?","আমি দুঃখিত,আমি শুনতে পাইনি,দয়া করে আবার বলবেন?", R.raw.come_here));
        words.add(new Word("Take care", "Jotno niben","যত্ন নিবেন", R.raw.come_here));


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

                    mMediaPlayer = MediaPlayer.create(Conversation.this, word.getAudioResourceId());


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
