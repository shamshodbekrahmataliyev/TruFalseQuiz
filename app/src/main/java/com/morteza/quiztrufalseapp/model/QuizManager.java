package com.morteza.quiztrufalseapp.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.morteza.quiztrufalseapp.MyApplication;
import com.morteza.quiztrufalseapp.R;
import com.morteza.quiztrufalseapp.VolleySingleton;
import com.morteza.quiztrufalseapp.controller.CardStackAdapter;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuizManager {

    Context mContext;
    private RequestQueue mRequestQueue;
    private String mUrl;


    public QuizManager(Context context) {

        mContext = context;
        mRequestQueue = VolleySingleton.getInstance().getRequestQueue();
        mUrl = "https://opentdb.com/api.php?amount=10&category=11&type=boolean";

    }


    public void getQuizQuestions(Activity activity) {

        List<QuizQuestion> quizQuestions = new ArrayList<>();

        JsonObjectRequest filmsJsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, mUrl,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.i("FILMS", response + "");

                        try {
                            JSONArray results = response.getJSONArray("results");

                            for (int index = 0; index < results.length(); index++) {


                                JSONObject questionJsonObject = results.getJSONObject(index);
                                String questionText = questionJsonObject.getString("question");

                                boolean questionAnswer = questionJsonObject.getBoolean("correct_answer");

                                QuizQuestion myQuestion = new QuizQuestion(questionText, questionAnswer);
                                quizQuestions.add(myQuestion);

                            }


                            CardStackView mCardStackView = activity.findViewById(R.id.myCardStackView);
                            mCardStackView.setLayoutManager(new CardStackLayoutManager(mContext));
                            mCardStackView.setAdapter(new CardStackAdapter(mContext, quizQuestions));


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MyApplication.getAppContext(), error + "" , Toast.LENGTH_LONG).show();

                    }
                });

        mRequestQueue.add(filmsJsonObjectRequest);


    }



}
