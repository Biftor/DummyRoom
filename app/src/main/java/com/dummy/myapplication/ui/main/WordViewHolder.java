/*
 * Copyright (C) 2020 Google Inc.
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

package com.dummy.myapplication.ui.main;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private WordViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewWithTag("textView");
    }

    public void bind(String text) {
        wordItemView.setText(text);
    }

    public static WordViewHolder create(ViewGroup parent) {


        FrameLayout root = new FrameLayout(parent.getContext());

        TextView wordsText = new TextView(parent.getContext());
        wordsText.setMaxLines(2);
        wordsText.setTextSize(16);
        wordsText.setPadding(10, 10, 10, 10);
        wordsText.setTag("textView");

        FrameLayout.LayoutParams wordsTextLayoutPrams =  new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.START);
        wordsTextLayoutPrams.topMargin = 5;
        wordsTextLayoutPrams.bottomMargin = 5;
        root.addView(wordsText, wordsTextLayoutPrams);

        return new WordViewHolder(root);
    }
}
