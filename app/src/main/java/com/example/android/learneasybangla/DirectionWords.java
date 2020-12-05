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

public class DirectionWords extends AppCompatActivity{
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
            words.add(new Word("Go straight", "Shoja jao","সোজা যাও", R.raw.my_name));
            words.add(new Word("Turn left", "Bame ghurun", "বামে ঘুরুন",R.raw.where_r_u_going));
            words.add(new Word("Turn right", "Dane ghurun", "ডানে ঘুরুন",R.raw.where_r_u_going));
            words.add(new Word("Texi", "Texi", "টেক্সি",R.raw.where_r_u_going));
            words.add(new Word("Bus", "Bus", "বাস",R.raw.where_r_u_going));
            words.add(new Word("Car", "Car", "কার",R.raw.where_r_u_going));
            words.add(new Word("Boat", "Nouka", "নৌকা",R.raw.where_r_u_going));
            words.add(new Word("By texi", "Texite","টেক্সিতে", R.raw.my_name));
            words.add(new Word("By boat", "Noukate","নৌকাতে", R.raw.my_name));
            words.add(new Word("Road", "Rasta","রাস্তা", R.raw.my_name));
            words.add(new Word("Next to", "pashe","পাশে", R.raw.my_name));
            words.add(new Word("Behind", "Pichone", "পিছনে",R.raw.what_is_your_name));
            words.add(new Word("In front of", "Shamne", "সামনে",R.raw.what_is_your_name));
            words.add(new Word("Up", "Upore", "উপরে",R.raw.what_is_your_name));
            words.add(new Word("Down", "Niche","নিচে", R.raw.my_name));

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

                        mMediaPlayer = MediaPlayer.create(DirectionWords.this, word.getAudioResourceId());


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
