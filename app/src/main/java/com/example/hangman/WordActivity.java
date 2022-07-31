package com.example.hangman;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.hangman.adapter.WordAdapter;
import com.example.hangman.service.HangMan;

public class WordActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent intent = result.getData();
            if (intent != null) {
                String newWord = intent.getStringExtra("new_word");
                HangMan.getInstance().getWords().add(newWord);
                if (rvWord.getAdapter() != null) {
                    rvWord.getAdapter().notifyItemInserted(HangMan.getInstance().getWords().size() - 1);
                }
            }

        }
    });

    private RecyclerView rvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        System.out.println(HangMan.getInstance().getSecretWord());

        initView();
    }

    private void initView() {
        rvWord = findViewById(R.id.rv_word);
        rvWord.setAdapter(new WordAdapter(HangMan.getInstance().getWords(), this::onDeleteItemClick, this::onItemClick));
        rvWord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void onItemClick(int i) {
        if (i >= HangMan.getInstance().getWords().size()) {
            System.out.println(i);
            Intent intent = new Intent(this, AddWordActivity.class);
            activityResult.launch(intent);
        }
    }

    private void onDeleteItemClick(String word) {
        System.out.println(word);
        int position = HangMan.getInstance().getWords().indexOf(word);
        HangMan.getInstance().getWords().remove(word);
        if (rvWord.getAdapter() != null) {
            this.rvWord.getAdapter().notifyItemRemoved(position);
        }

    }
}