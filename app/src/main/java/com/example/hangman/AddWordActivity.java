package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddWordActivity extends Activity {

    private EditText etWord;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        initView();

    }

    private void initView() {
        etWord = findViewById(R.id.et_modal_new_word);
        btnAdd = findViewById(R.id.btn_modal_add);

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("new_word", etWord.getText().toString());
            setResult(1, intent);
            finish();
        });
    }
}