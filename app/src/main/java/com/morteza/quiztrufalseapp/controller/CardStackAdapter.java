package com.morteza.quiztrufalseapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.morteza.quiztrufalseapp.R;
import com.morteza.quiztrufalseapp.model.QuizQuestion;
import com.morteza.quiztrufalseapp.view.FilmViewHolder;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    private Context mContext;
    private List<QuizQuestion> mFilmQuestions;
    private LayoutInflater mLayoutInflater;

    public CardStackAdapter(Context context, List<QuizQuestion> filmQuestions) {

        mContext = context;
        mFilmQuestions = filmQuestions;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.film_view, parent, false);


        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {

        holder.getTxtFilmQuestion().setText(mFilmQuestions.get(position).getQuestionText());

        holder.getImgButtonTrue().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mFilmQuestions.get(position).isTrueAnswer()) {

                    Toast.makeText(mContext, "You have answered correctly. Congratulation!", Toast.LENGTH_SHORT).show();



                } else {

                    Toast.makeText(mContext, "Sorry. you were wrong. Try again!!!", Toast.LENGTH_SHORT).show();


                }

            }
        });

        holder.getImgButtonFalse().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mFilmQuestions.get(position).isTrueAnswer()) {

                    Toast.makeText(mContext, "Sorry. you were wrong. Try again!!!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(mContext, "You have answered correctly. Congratulation!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return mFilmQuestions.size();
    }
}
