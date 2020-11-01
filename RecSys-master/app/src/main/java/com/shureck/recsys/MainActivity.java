package com.shureck.recsys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    public String str;
    CardView card;
    Button btn_all;
    Button btn_book;
    Button btn_courses;
    Button btn_events;
    Button btn_other;
    Button btn_decline;
    Button btn_accept;
    Button transfer;
    int flag = 0;
    int iterator = 0;

    float x;
    float y;

    ImageView eventImage;
    TextView eventName;
    TextView eventTime;
    TextView eventDescription;

    ArrayList<Recommend> persons = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<CardsRating> cardsRating = new ArrayList<>();
    RecyclerView bookList;
    private final OkHttpClient client = new OkHttpClient();

    class IOAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return sendData(params[0]);
        }

        @Override
        protected void onPostExecute(String response) {
            str = response;
            Log.d("DDD",response);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cards.add(new Card(1, R.drawable.duglas,"\"Автостопом по галактике\"", "", "Фантастика, приключения"));
        cards.add(new Card(1, R.drawable.potter,"\"Гарри Поттер и философский камень\"", "", "Фентези, приключения"));
        cards.add(new Card(1, R.drawable.barr,"\"Барраярский цикл - Цетаганда\"", "", "Фантастика, боевик, приключения"));
        cards.add(new Card(1, R.drawable.dip,"\"Диптаун\"", "", "Фентези, приключения"));
        cards.add(new Card(2, R.drawable.orig,"Оригами для детей", "13:00", "Освойте азы оригами"));
        cards.add(new Card(2, R.drawable.robot,"Робототехника для детей", "15:00", "Создание управляемых роботов"));
        cards.add(new Card(2, R.drawable.buis,"Бизнесс-тренинг", "18:00", "Научим вас управлять финансами и коллективом"));
        cards.add(new Card(3, R.drawable.rock,"Симфонический рок", "20:00", "Новое звучание известных песен"));
        cards.add(new Card(3, R.drawable.expo,"Выставка ВУЗПРОМЭКСПО", "13:00", "Новейшие разработки университетов и не только"));
        cards.add(new Card(3, R.drawable.mirea,"День Открытых Дверей в МИРЭА", "12:00", "Посмотрите на разработки университета и задайте интересующие вас вопросы"));

        Collections.shuffle(cards);

        card = findViewById(R.id.card);
        btn_all = findViewById(R.id.btn_all);
        btn_book = findViewById(R.id.btn_book);
        btn_courses = findViewById(R.id.btn_courses);
        btn_other = findViewById(R.id.btn_other);
        btn_events = findViewById(R.id.btn_events);
        transfer = btn_all;
        btn_decline = findViewById(R.id.imageButtonDecline);
        btn_accept = findViewById(R.id.imageButtonAccept);

        eventImage = findViewById(R.id.eventImage);
        eventName = findViewById(R.id.eventName);
        eventTime = findViewById(R.id.eventTime);
        eventDescription = findViewById(R.id.eventDescription);

        new IOAsyncTask().execute("?id=2057764");

        flag = 0;
        iterator = 0;

        eventImage.setImageResource(cards.get(0).getImgID());
        eventName.setText(cards.get(0).getEventName());
        eventTime.setText(cards.get(0).getEventTime());
        eventDescription.setText(cards.get(0).getEventDescription());

        AlphaAnimation sa = new AlphaAnimation(0.5f, 1.0f);
        sa.setDuration(300);
        sa.setFillAfter(true);
        card.startAnimation(sa);

//        bookList = (RecyclerView) findViewById(R.id.recyclerView);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        bookList.setLayoutManager(llm);
//        Adapter adapter = new Adapter(persons);
//        bookList.setAdapter(adapter);

        btn_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation ta = new TranslateAnimation(0, -1000, Animation.RELATIVE_TO_SELF, 0);
                ta.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        card.clearAnimation();
                        ScaleAnimation sa = new ScaleAnimation(0.8f,1.0f,0.8f,1.0f, card.getPivotX(), card.getPivotY());
                        sa.setDuration(300);
                        sa.setFillAfter(true);
                        if(iterator < cards.size()) {
                            cards.remove(iterator);
                        }

                        if(cards.size()>0) {
                            card.setVisibility(View.INVISIBLE);
                            for (int i = iterator; i < cards.size(); i++) {
                                if (flag == 0) {

                                    card.setVisibility(View.VISIBLE);
                                    iterator = i;
                                    eventImage.setImageResource(cards.get(i).getImgID());
                                    eventName.setText(cards.get(i).getEventName());
                                    eventTime.setText(cards.get(i).getEventTime());
                                    eventDescription.setText(cards.get(i).getEventDescription());
                                    cardsRating.add(new CardsRating(cards.get(i), false));
                                    break;
                                }
                                if (cards.get(i).getTypeFlag() == flag) {
                                    card.setVisibility(View.VISIBLE);
                                    iterator = i;
                                    eventImage.setImageResource(cards.get(i).getImgID());
                                    eventName.setText(cards.get(i).getEventName());
                                    eventTime.setText(cards.get(i).getEventTime());
                                    eventDescription.setText(cards.get(i).getEventDescription());
                                    cardsRating.add(new CardsRating(cards.get(i), false));
                                    break;
                                }

                            }

                            card.startAnimation(sa);
                        }
                        else{
                            card.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                ta.setDuration(300);
                ta.setFillAfter(true);
                card.startAnimation(ta);
            }
        });

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation ta = new TranslateAnimation(0, 1000, Animation.RELATIVE_TO_SELF, 0);
                ta.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        card.clearAnimation();
                        ScaleAnimation sa = new ScaleAnimation(1.0f,1.0f,1.0f,1.0f, card.getPivotX(), card.getPivotY());
                        sa.setDuration(1);
                        sa.setFillAfter(true);
                        card.startAnimation(sa);
                        if(iterator < cards.size()) {
                            cards.remove(iterator);
                        }

                        if(cards.size()>0) {
                            card.setVisibility(View.INVISIBLE);
                            for (int i = iterator; i < cards.size(); i++) {
                                if (flag == 0) {
                                    card.setVisibility(View.VISIBLE);
                                    iterator = i;
                                    eventImage.setImageResource(cards.get(i).getImgID());
                                    eventName.setText(cards.get(i).getEventName());
                                    eventTime.setText(cards.get(i).getEventTime());
                                    eventDescription.setText(cards.get(i).getEventDescription());
                                    cardsRating.add(new CardsRating(cards.get(i), true));
                                    break;
                                }
                                if (cards.get(i).getTypeFlag() == flag) {
                                    card.setVisibility(View.VISIBLE);
                                    iterator = i;
                                    eventImage.setImageResource(cards.get(i).getImgID());
                                    eventName.setText(cards.get(i).getEventName());
                                    eventTime.setText(cards.get(i).getEventTime());
                                    eventDescription.setText(cards.get(i).getEventDescription());
                                    cardsRating.add(new CardsRating(cards.get(i), true));
                                    break;
                                }

                            }

                            card.startAnimation(sa);
                        }
                        else{
                            card.setVisibility(View.INVISIBLE);
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                ta.setDuration(300);
                ta.setFillAfter(true);
                card.startAnimation(ta);

            }
        });

        btn_all.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                card.clearAnimation();
                if(transfer != null){
                    transfer.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                    transfer.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                }
                transfer = btn_all;
                btn_all.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                btn_all.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                flag = 0;
                iterator = 0;
                if(cards.size()>0) {
                    card.setVisibility(View.VISIBLE);
                    eventImage.setImageResource(cards.get(0).getImgID());
                    eventName.setText(cards.get(0).getEventName());
                    eventTime.setText(cards.get(0).getEventTime());
                    eventDescription.setText(cards.get(0).getEventDescription());

                    AlphaAnimation sa = new AlphaAnimation(0.5f, 1.0f);
                    sa.setDuration(300);
                    sa.setFillAfter(true);
                    card.startAnimation(sa);
                }
            }
        });

        btn_book.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                card.clearAnimation();
                if(transfer != null){
                    transfer.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                    transfer.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                }
                transfer = btn_book;
                btn_book.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                btn_book.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                flag = 1;
                if(cards.size()>0) {
                    card.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < cards.size(); i++) {
                        if (cards.get(i).getTypeFlag() == flag) {
                            card.setVisibility(View.VISIBLE);
                            iterator = i;
                            eventImage.setImageResource(cards.get(i).getImgID());
                            eventName.setText(cards.get(i).getEventName());
                            eventTime.setText(cards.get(i).getEventTime());
                            eventDescription.setText(cards.get(i).getEventDescription());

                            AlphaAnimation sa = new AlphaAnimation(0.0f, 1.0f);
                            sa.setDuration(300);
                            sa.setFillAfter(true);
                            card.startAnimation(sa);
                            break;
                        }
                    }
                }
            }
        });

        btn_courses.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                card.clearAnimation();
                if(transfer != null){
                    transfer.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                    transfer.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                }
                transfer = btn_courses;
                btn_courses.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                btn_courses.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                flag = 2;
                if(cards.size()>0) {
                    card.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < cards.size(); i++) {
                        if (cards.get(i).getTypeFlag() == flag) {
                            card.setVisibility(View.VISIBLE);
                            iterator = i;
                            eventImage.setImageResource(cards.get(i).getImgID());
                            eventName.setText(cards.get(i).getEventName());
                            eventTime.setText(cards.get(i).getEventTime());
                            eventDescription.setText(cards.get(i).getEventDescription());

                            AlphaAnimation sa = new AlphaAnimation(0.0f, 1.0f);
                            sa.setDuration(300);
                            sa.setFillAfter(true);
                            card.startAnimation(sa);
                            break;
                        }
                    }

                }
            }
        });

        btn_events.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                card.clearAnimation();
                if(transfer != null){
                    transfer.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                    transfer.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                }
                transfer = btn_events;
                btn_events.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                btn_events.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                flag = 3;
                if(cards.size()>0) {
                    card.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < cards.size(); i++) {
                        if (cards.get(i).getTypeFlag() == flag) {
                            card.setVisibility(View.VISIBLE);
                            iterator = i;
                            eventImage.setImageResource(cards.get(i).getImgID());
                            eventName.setText(cards.get(i).getEventName());
                            eventTime.setText(cards.get(i).getEventTime());
                            eventDescription.setText(cards.get(i).getEventDescription());

                            AlphaAnimation sa = new AlphaAnimation(0.0f, 1.0f);
                            sa.setDuration(300);
                            sa.setFillAfter(true);
                            card.startAnimation(sa);
                            break;
                        }
                    }

                }
            }
        });

        btn_other.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                card.clearAnimation();
                if(transfer != null){
                    transfer.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.white));
                    transfer.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                }
                transfer = btn_other;
                btn_other.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.red));
                btn_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                flag = 4;
                if(cards.size()>0) {
                    card.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < cards.size(); i++) {
                        if (cards.get(i).getTypeFlag() == flag) {
                            card.setVisibility(View.VISIBLE);
                            iterator = i;
                            eventImage.setImageResource(cards.get(i).getImgID());
                            eventName.setText(cards.get(i).getEventName());
                            eventTime.setText(cards.get(i).getEventTime());
                            eventDescription.setText(cards.get(i).getEventDescription());

                            AlphaAnimation sa = new AlphaAnimation(0.0f, 1.0f);
                            sa.setDuration(300);
                            sa.setFillAfter(true);
                            card.startAnimation(sa);
                            break;
                        }
                    }
                }

            }
        });

    }

    public String sendData(String str){
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("LatLong", str)
                    .build();

            Request request = new Request.Builder()
                    .url("http://25.101.65.98:8080/api"+str)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}