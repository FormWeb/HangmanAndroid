package com.example.hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hangman.databinding.ActivityMainBinding;
import com.example.hangman.service.HangMan;

public class MainActivity extends AppCompatActivity {

    private HangMan hangMan;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        hangMan = new HangMan();

        binding.tvMainHiddenWord.setText(this.hangMan.getHiddenWord());

        binding.btnMainGuess.setOnClickListener(view -> {
            makeGuess();
        });

//        binding.btnMainAddWords.setOnClickListener(view -> {
//            Intent intent = new Intent(this, WordActivity.class);
//            startActivity(intent);
//        });
    }

    public void makeGuess() {
        char guess;
        if (binding.etMainGuess.getText().length() <= 0) {
            return;
        }

        guess = binding.etMainGuess.getText().toString().charAt(0);
        hangMan.updateHiddenWord(guess);

        if (hangMan.gameWin()) {
            String winMessage = "Bravo ! Le mot était bien : " + hangMan.getSecretWord();
            hangMan.reset();
            winMessage += "\nNouveau mot : " + hangMan.getHiddenWord();
            binding.tvMainHiddenWord.setText(winMessage);
            resetUi();
        }
        else if (hangMan.gameLost()) {
            String lostMessage = "Perdu ! Le mot était : " + hangMan.getSecretWord();
            hangMan.reset();
            lostMessage += "\nNouveau mot : " + hangMan.getHiddenWord();
            binding.tvMainHiddenWord.setText(lostMessage);
            resetUi();
        }
        else {
            binding.tvMainHiddenWord.setText(hangMan.getHiddenWord());
            updateUi();
        }
        binding.etMainGuess.setText("");
    }

    private void updateUi() {
        for (int i = 0; i < hangMan.getTries(); i++) {
            binding.llTries.getChildAt(i).setAlpha(1);
        }
    }

    private void resetUi() {
        for (int i = 0; i < hangMan.getMaximumTries(); i++) {
            binding.llTries.getChildAt(i).setAlpha(0.2F);
        }
    }
}