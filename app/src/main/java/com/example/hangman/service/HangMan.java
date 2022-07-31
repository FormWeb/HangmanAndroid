package com.example.hangman.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HangMan {

    private SecureRandom rand;

    private List<String> words;

    private String hiddenWord;
    private String secretWord;
    private int tries;
    private int maximumTries;

    public static HangMan holder = new HangMan();
    public static HangMan getInstance() {
        return holder;
    }
    //region constructeur
    public HangMan() {
        this.rand = new SecureRandom();
        this.words = new ArrayList<String>(Arrays.asList("maison", "jardin", "parc"));
        this.secretWord = words.get(rand.nextInt(words.size()));
        this.hiddenWord = generateHiddenWord();
        this.maximumTries = 6;
        this.tries = 0;
    }
    //endregion

    //region Getter and setter

    public SecureRandom getRand() {
        return rand;
    }

    public void setRand(SecureRandom rand) {
        this.rand = rand;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public void setHiddenWord(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public int getMaximumTries() {
        return maximumTries;
    }

    public void setMaximumTries(int maximumTries) {
        this.maximumTries = maximumTries;
    }

    // custom getter

    public boolean gameWin() {
        return this.hiddenWord.equals(this.secretWord);
    }

    public boolean gameLost() {
        return this.tries >= this.maximumTries;
    }

    //endregion

    public String generateHiddenWord() {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < this.secretWord.length(); i++) {
            word.append("*");
        }
        return word.toString();
    }

    public String updateHiddenWord(char guess) {
        StringBuilder word = new StringBuilder();
        boolean win = false;
        for (int i = 0; i < this.secretWord.length(); i++) {
            if (guess == secretWord.charAt(i)) {
                word.append(guess);
                win = true;
            }
            else {
                word.append(this.hiddenWord.charAt(i));
            }
        }
        if (!win) {
            this.tries++;
        }
        this.hiddenWord = word.toString();
        return word.toString();
    }

    public void reset() {
        this.secretWord = words.get(rand.nextInt(words.size()));
        this.hiddenWord = generateHiddenWord();
        this.maximumTries = 6;
        this.tries = 0;
    }
}
