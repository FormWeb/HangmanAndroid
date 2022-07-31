package com.example.hangman.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HangManTest {

    private HangMan hangMan;

    @Before
    public void setUp() {
        this.hangMan = new HangMan();
    }

    @Test
    public void testGenerateHiddenWord() {
        this.hangMan.setSecretWord("maison");
        Assert.assertEquals("******", this.hangMan.generateHiddenWord());
    }

    @Test
    public void testUpdateHiddenWord() {
        this.hangMan.setSecretWord("maison");
        this.hangMan.setHiddenWord("m*****");
        this.hangMan.updateHiddenWord('a');
        Assert.assertEquals("ma****", this.hangMan.getHiddenWord());
        Assert.assertEquals(0, this.hangMan.getTries());
    }
}