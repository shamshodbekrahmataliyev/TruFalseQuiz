package com.morteza.quiztrufalseapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.morteza.quiztrufalseapp.model.QuizManager;

public class MainActivity extends AppCompatActivity {

    private QuizManager mQuizManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuizManager = new QuizManager(this);
        mQuizManager.getQuizQuestions(this);










    }
}