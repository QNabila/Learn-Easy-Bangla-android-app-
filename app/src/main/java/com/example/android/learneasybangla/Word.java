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


public class Word {


    private String mDefaultTranslation;


    private String mbanglaTranslation;


    private int mAudioResourceId;
    private  String mbangla;


    private int mImageResourceId = NO_IMAGE_PROVIDED;


    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation, String banglaTranslation, String bangla,int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mbanglaTranslation = banglaTranslation;
        mAudioResourceId = audioResourceId;
        mbangla=bangla;
    }


    public Word(String defaultTranslation, String banglaTranslation, int imageResourceId,
                int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mbanglaTranslation = banglaTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }


    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mbanglaTranslation;
    }

    public String getbanglaTranslation() { return mbangla; }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}