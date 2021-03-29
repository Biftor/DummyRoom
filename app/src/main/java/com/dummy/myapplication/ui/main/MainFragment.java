package com.dummy.myapplication.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dummy.myapplication.data.local.entity.Word;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private MainViewModel mViewModel;
    private FrameLayout root;
    private EditText editText;
    private Button add;
    private WordListAdapter adapter;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    /*
        Don't care about UI and sizes
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        root = new FrameLayout(requireContext());

        FrameLayout enterTextContent = new FrameLayout(requireContext());
        root.addView(enterTextContent);

        add = new Button(getContext());
        add.setTextSize(14);
        add.setText("Add");

        editText = new EditText(requireContext());
        editText.setHint("enter text you want add to db");
        editText.setMaxLines(2);
        editText.setTextSize(14);
        FrameLayout.LayoutParams editTextLayoutParam =  new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200, Gravity.START);
        editTextLayoutParam.rightMargin = 210;
        editTextLayoutParam.leftMargin = 5;
        editTextLayoutParam.gravity = Gravity.START;

        FrameLayout.LayoutParams buttonLayoutParam =  new FrameLayout.LayoutParams(200, 200, Gravity.END);
        buttonLayoutParam.rightMargin = 5;
        enterTextContent.addView(editText, editTextLayoutParam);
        enterTextContent.addView(add, buttonLayoutParam);

        RecyclerView recyclerView = new RecyclerView(requireContext());
        adapter = new WordListAdapter(new WordListAdapter.WordDiff(), new WordListAdapter.ItemClickHandler() {
            @Override
            public void onItemClicked(View view, int position) {
               Toast.makeText(requireContext(), adapter.getCurrentList().get(position).getWord(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClicked(View view, int position) {
                mViewModel.removeWord(adapter.getCurrentList().get(position));
                return true;
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        FrameLayout.LayoutParams recyclerViewLayoutParam =  new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT, Gravity.START);
        recyclerViewLayoutParam.rightMargin = 5;
        recyclerViewLayoutParam.leftMargin = 5;
        recyclerViewLayoutParam.topMargin = 200;
        recyclerViewLayoutParam.gravity = Gravity.TOP;
        root.addView(recyclerView, recyclerViewLayoutParam);

        add.setOnClickListener(v -> {
            if(!TextUtils.isEmpty(editText.getText().toString())){
                mViewModel.insert(new Word(editText.getText().toString()));
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity(), getDefaultViewModelProviderFactory()).get(MainViewModel.class);
        mViewModel.getAllWords().observe(getViewLifecycleOwner(), words -> {
            adapter.submitList(words);
            Log.d(TAG, "data got changed" );
        });
    }

}