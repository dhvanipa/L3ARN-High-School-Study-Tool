package com.letap.learnjava.poetryterms;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by Dhvani on 27/11/2015.
 */
public class LAQuiz extends Activity {

    static ArrayList<Integer> list;
    String fQuestion = "";
    String option1 = "";
    String option2 = "";
    String option3 = "";
    String option4 = "";
    String answer = "";
    int optAnswer = 0;
    int answerInd = 0;
    Dialog finishedDialog;
    TextView question;
    Button opt1;
    Button opt2;
    Button opt3;
    Button opt4;
    // ArrayList<Integer> ques = new ArrayList<Integer>();
    // ArrayList<Integer> together = new ArrayList<Integer>();
    TextView number;
    TextView time;
    String[] la_questions;
    ArrayList<String> fla_answers;
    ArrayList<String> dla_questions;
    ArrayList<String> fla_questions;
    ArrayList<Button> btn_options;
    boolean finished = false;
    int sizeArray = 0;
    int realSizeArray = 0;
    int correct = 0;
   ArrayList<String> answerSetArray;

    int quesCounter = 0;

    private long startTime = 0L;
    private Handler myHandler = new Handler();
    long timeInMillies = 0L;
    long timeSwap = 0L;
    long finalTime = 0L;
    boolean btnActive = false;
    String getTitle;
    ImageButton speak;
    TextToSpeech t1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_quiz);

       number = (TextView)findViewById(R.id.textViewPoints);
        time = (TextView)findViewById(R.id.textViewTime);
       question = (TextView)findViewById(R.id.question);
        opt1 = (Button)findViewById(R.id.button1);
        opt2 = (Button)findViewById(R.id.button2);
        opt3 = (Button)findViewById(R.id.button3);
        opt4 = (Button)findViewById(R.id.button4);
        btn_options = new ArrayList<Button>();
        btn_options.add(opt1);
        btn_options.add(opt2);
        btn_options.add(opt3);
        btn_options.add(opt4);
        speak = (ImageButton)findViewById(R.id.speakIcon);


        // question.setTextSize(TypedValue.COMPLEX_UNIT_DIP, );




        Intent otherIntent = getIntent();

        Bundle myBundle = otherIntent.getExtras();

        if(myBundle != null){
            getTitle = (String) myBundle.get("Title");
            // title.setText(getTitle);
            setTitle(getTitle);
        }

        if(getTitle.equals("Language Arts")){
            la_questions = getResources().getStringArray(R.array.la_quiz);
            fla_answers = new ArrayList<String>(Arrays.asList(PagesFragment.names_list));
            sizeArray = PagesFragment.names_list.length;

            answerSetArray = new ArrayList<String>(Arrays.asList(PagesFragment.names_list));
        }

        else if(getTitle.equals("Biology")){
            la_questions = getResources().getStringArray(R.array.bio_quiz);
            PhotosFragment.loadSomeData();
            fla_answers = new ArrayList<String>(PhotosFragment.biology);
            sizeArray = PhotosFragment.biology.size();
            answerSetArray = new ArrayList<String>(PhotosFragment.biology);
        }
        else if(getTitle.equals("Chemistry")){
            la_questions = getResources().getStringArray(R.array.chem);
            PhotosFragment.loadSomeData();
            fla_answers = new ArrayList<String>(PhotosFragment.chemistry);
            sizeArray = PhotosFragment.chemistry.size();
            answerSetArray = new ArrayList<String>(PhotosFragment.chemistry);
        }
        else if(getTitle.equals("Geology")){
            la_questions = getResources().getStringArray(R.array.geology);
            PhotosFragment.loadSomeData();
            fla_answers = new ArrayList<String>(PhotosFragment.geology);
            sizeArray = PhotosFragment.geology.size();
            answerSetArray = new ArrayList<String>(PhotosFragment.geology);
        }
        else if(getTitle.equals("Physics")){
            la_questions = getResources().getStringArray(R.array.phy);
            PhotosFragment.loadSomeData();
            fla_answers = new ArrayList<String>(PhotosFragment.physics);
            sizeArray = PhotosFragment.physics.size();
            answerSetArray = new ArrayList<String>(PhotosFragment.physics);
        }
        else if(getTitle.equals("Social Studies")){
            la_questions = getResources().getStringArray(R.array.social);
            fla_answers = new ArrayList<String>(Arrays.asList(CommunityFragment.names_list));
            sizeArray = CommunityFragment.names_list.length;

            answerSetArray = new ArrayList<String>(Arrays.asList(CommunityFragment.names_list));
        }

        else if(getTitle.equals("Math")){
            la_questions = getResources().getStringArray(R.array.math);
            fla_answers = new ArrayList<String>(Arrays.asList(FindPeopleFragment.names_list_organ));
            sizeArray = FindPeopleFragment.names_list_organ.length;

            answerSetArray = new ArrayList<String>(Arrays.asList(FindPeopleFragment.names_list_organ));
        }




        realSizeArray = sizeArray - 2;
        dla_questions = new ArrayList<String>(Arrays.asList(la_questions));
        fla_questions = new ArrayList<String>(Arrays.asList(la_questions));

        Collections.shuffle(fla_questions);

        quiz();
        setOptions();
        startTimer();
        btnActive = true;





                opt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(btnActive == true) {
                            btnActive = false;
                            if (quesCounter < 11) {
                                if (answerInd == 1) {
                                    opt1.setBackgroundResource(R.drawable.buttongreen);
                                    correct++;
                                    // System.out.println("Correct");
                                    fla_questions.remove(0);

                                    if(quesCounter == 10){
                                        done();
                                    }
                                    else {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {

                                                opt1.setBackgroundResource(R.drawable.buttonstandard);
                                                quiz();
                                                setOptions();
                                                btnActive = true;
                                            }
                                        }, 200);
                                    }
                                } else {
                                    // System.out.println("Try Again");
                                    fla_questions.remove(0);
                                    opt1.setBackgroundResource(R.drawable.buttonred);
                                    btn_options.get(answerInd - 1).setBackgroundResource(R.drawable.buttongreen);
                                    if(quesCounter == 10){
                                        done();
                                    }
                                    else {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                opt1.setBackgroundResource(R.drawable.buttonstandard);
                                                btn_options.get(answerInd - 1).setBackgroundResource(R.drawable.buttonstandard);
                                                quiz();
                                                setOptions();
                                                btnActive = true;
                                            }
                                        }, 1000);
                                    }

                                }
                            }
                        }


                    }
                });

                opt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(btnActive == true) {
                            btnActive = false;
                            if (quesCounter < 11) {
                                if (answerInd == 2) {
                                    opt2.setBackgroundResource(R.drawable.buttongreen);
                                    correct++;
                                    // System.out.println("Correct");
                                    fla_questions.remove(0);
                                    if(quesCounter == 10){
                                        done();
                                    }
                                    else {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                opt2.setBackgroundResource(R.drawable.buttonstandard);
                                                quiz();
                                                setOptions();
                                                btnActive = true;
                                            }
                                        }, 200);
                                    }
                                } else {
                                    // System.out.println("Try Again");
                                    fla_questions.remove(0);
                                    opt2.setBackgroundResource(R.drawable.buttonred);
                                    btn_options.get(answerInd - 1).setBackgroundResource(R.drawable.buttongreen);

                                    if(quesCounter == 10){
                                        done();
                                    }
                                    else {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                opt2.setBackgroundResource(R.drawable.buttonstandard);
                                                btn_options.get(answerInd - 1).setBackgroundResource(R.drawable.buttonstandard);
                                                quiz();
                                                setOptions();
                                                btnActive = true;
                                            }
                                        }, 1000);
                                    }

                                }
                            }
                        }


                    }
                });

                opt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(btnActive == true) {
                            btnActive = false;
                            if (quesCounter < 11) {
                                if (answerInd == 3) {
                                    opt3.setBackgroundResource(R.drawable.buttongreen);
                                    correct++;
                                    // System.out.println("Correct");
                                    fla_questions.remove(0);
                                    if(quesCounter == 10){
                                        done();
                                    }
                                    else {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                opt3.setBackgroundResource(R.drawable.buttonstandard);
                                                quiz();
                                                setOptions();
                                                btnActive = true;
                                            }
                                        }, 200);
                                    }
                                } else {
                                    // System.out.println("Try Again");
                                    fla_questions.remove(0);
                                    opt3.setBackgroundResource(R.drawable.buttonred);
                                    btn_options.get(answerInd - 1).setBackgroundResource(R.drawable.buttongreen);
                                    if(quesCounter == 10){
                                        done();
                                    }
                                    else {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                opt3.setBackgroundResource(R.drawable.buttonstandard);
                                                btn_options.get(answerInd - 1).setBackgroundResource(R.drawable.buttonstandard);
                                                quiz();
                                                setOptions();
                                                btnActive = true;
                                            }
                                        }, 1000);
                                    }

                                }
                            }

                        }
                    }
                });

                opt4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btnActive == true) {
                            btnActive = false;
                            if (quesCounter < 11) {
                                if (answerInd == 4) {
                                    opt4.setBackgroundResource(R.drawable.buttongreen);
                                    correct++;
                                    // System.out.println("Correct");
                                    fla_questions.remove(0);
                                    if(quesCounter == 10){
                                        done();
                                    }
                                    else {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                opt4.setBackgroundResource(R.drawable.buttonstandard);
                                                quiz();
                                                setOptions();
                                                btnActive = true;
                                            }
                                        }, 200);
                                    }
                                } else {
                                    // System.out.println("Try Again");
                                    fla_questions.remove(0);
                                    opt4.setBackgroundResource(R.drawable.buttonred);
                                    btn_options.get(answerInd - 1).setBackgroundResource(R.drawable.buttongreen);
                                    if(quesCounter == 10){
                                        done();
                                    }
                                    else {
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                opt4.setBackgroundResource(R.drawable.buttonstandard);
                                                btn_options.get(answerInd - 1).setBackgroundResource(R.drawable.buttonstandard);
                                                quiz();
                                                setOptions();
                                                btnActive = true;
                                            }
                                        }, 1000);
                                    }

                                }
                            }


                        }
                    }
                });

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.CANADA);
                }
            }
        });

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = question.getText().toString();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    public void done(){
       //  System.out.println("Done!");
        finished = true;
        pauseTimer();
        String endTime = (String) time.getText();
        finishedDialog = new Dialog(LAQuiz.this);
        finishedDialog.setContentView(R.layout.quiz_finished_dialog);
        finishedDialog.setCancelable(false);
        finishedDialog.setTitle("Quiz Finished");
        finishedDialog.show();
        CharSequence statement;
        if(correct == 0){
            statement = "Better luck next time!";
        }
        else if(correct > 0 && correct < 5){
            statement = "Not bad. Keep it up.";
        }
        else if(correct == 5){
            statement = "Half way there!";
        }
        else if(correct > 5 && correct < 10){
            statement = "Wow, good job.";
        }
        else if(correct == 10){
            statement = "Amazing!";
        }
        else{
            statement = "Hacked the game?";
        }

        ((TextView) finishedDialog.findViewById(R.id.textViewHighscore)).setText(statement + "\n" + "Correct Answers: " + correct + "/10" + "\n" + "Time Taken: " + endTime);
        Button leave = (Button) finishedDialog.findViewById(R.id.buttonHighscore);
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finishedDialog.dismiss();
            LAQuiz.this.finish();
            }
        });
    }

    public void setOptions(){
        question.setText(fQuestion);
        opt1.setText(option1);
        opt2.setText(option2);
        opt3.setText(option3);
        opt4.setText(option4);
        number.setText(quesCounter + " / 10");
    }


    public void quiz(){

        quesCounter++;

        fQuestion = fla_questions.get(0);
        // System.out.println(fQuestion + "");
        int quesIndex = dla_questions.indexOf(fQuestion);

        list = new ArrayList<Integer>();
        for (int i = 0; i < sizeArray; i++) { // 26 = size of array
            list.add(new Integer(i));
        }
        Collections.shuffle(list);

        // String toRemove = fla_answers.get(quesIndex);
        // int removeIndex = fla_answers.indexOf(toRemove);
        int removeIndex = list.indexOf(quesIndex);
        list.remove(removeIndex);
        for (int i = realSizeArray; i > 2; i--) { // size of array-2
            list.remove(i);

        }

        ArrayList<Integer> rand = new ArrayList<Integer>();
        for (int i = 1; i < 5; i++) {
            rand.add(new Integer(i));
        }
        Collections.shuffle(rand);
        answerInd = rand.get(0);



        if(answerInd == 1){

             //set answer
            option1 = answerSetArray.get(quesIndex);
            answer = option1;
            optAnswer = 1;

            // set other
            option2 = answerSetArray.get(list.get(0));

            option3 = answerSetArray.get(list.get(1));

            option4 = answerSetArray.get(list.get(2));

        }
        else if(answerInd == 2){
            option2 = answerSetArray.get(quesIndex);
            answer = option2;
            optAnswer = 2;

            option1 = answerSetArray.get(list.get(0));

            option3 = answerSetArray.get(list.get(1));

            option4 = answerSetArray.get(list.get(2));
        }
        else if(answerInd == 3){
            option3 = answerSetArray.get(quesIndex);
            answer = option3;
            optAnswer = 3;

            option2 = answerSetArray.get(list.get(0));

            option1 = answerSetArray.get(list.get(1));

            option4 = answerSetArray.get(list.get(2));
        }
        else if(answerInd == 4){
            option4 = answerSetArray.get(quesIndex);
            answer = option4;
            optAnswer = 4;

            option2 = answerSetArray.get(list.get(0));

            option3 = answerSetArray.get(list.get(1));

            option1 = answerSetArray.get(list.get(2));
        }


    }

    private Runnable updateTimerMethod = new Runnable() {

        public void run() {
            timeInMillies = SystemClock.uptimeMillis() - startTime;
            finalTime = timeSwap + timeInMillies;

            int seconds = (int) (finalTime / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            int milliseconds = (int) (finalTime % 1000);
            time.setText("" + minutes + ":"
            + String.format("%02d", seconds) + ":"
            + String.format("%03d", milliseconds));
            myHandler.postDelayed(this, 0);
        }

    };

    public void startTimer(){
        startTime = SystemClock.uptimeMillis();
        myHandler.postDelayed(updateTimerMethod, 0);
    }

    public void pauseTimer(){
        timeSwap += timeInMillies;
        myHandler.removeCallbacks(updateTimerMethod);
    }

    @Override
    public void onBackPressed() {
        if(finished == false) {
            pause();
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        if(finished == false) {
            pause();
        }
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        // pause();
        return true;
    }

    protected void pause(){
        pauseTimer();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz Paused");
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startTimer();
               // paused = false;
            }
        });
        builder.setNegativeButton("Back To Menu", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                time.setText("00:00:00");
                finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            public void onCancel(DialogInterface dialog) {
               startTimer();
                // paused = false;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }



}
