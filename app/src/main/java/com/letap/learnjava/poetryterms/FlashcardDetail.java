package com.letap.learnjava.poetryterms;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by Dhvani on 08/12/2015.
 */
public class FlashcardDetail extends Activity {

    TextView imgFront;
    AutoResizeTextView imgBack;
    TextView number;
    ImageButton btnFlip;
    ImageButton speak;
    TextToSpeech t1;
    Button btnNext;
    int counter = 0;
    ArrayList<String> front;
    ArrayList<String> back;
    float x1,x2;
    float y1, y2;
    boolean didNext = false;
    private GestureDetector gestureDetector;

    boolean isBackVisible = false; // Boolean variable to check if the back image is visible currently
    String getTitle;

    String[] instructions = new String[]{
            "This is the front of a flashcard. Tap on me!",
            "Now you can swipe to the right to go back!",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashdetail);

        imgFront= (TextView)findViewById(R.id.front);
        imgBack = (AutoResizeTextView)findViewById(R.id.back);
        number = (TextView)findViewById(R.id.number);
        speak = (ImageButton)findViewById(R.id.voiceButton);

        // imgBack.setMovementMethod(new ScrollingMovementMethod());

        btnFlip = (ImageButton)findViewById(R.id.btnFlip);
        // btnNext = (Button)findViewById(R.id.btnNext);
        gestureDetector = new GestureDetector(this, new SingleTapConfirm());

        Intent otherIntent = getIntent();

        Bundle myBundle = otherIntent.getExtras();

        if(myBundle != null){
            getTitle = (String) myBundle.get("Category");
            // title.setText(getTitle);
            getActionBar().setTitle(getTitle);

        }

        RecycleAdapter.pos = -1;



        System.out.println(getTitle);
        if(getTitle.equals("Language Arts") == true){
            String[] dialogmessage = getResources().getStringArray(R.array.la_quiz);
            back = new ArrayList<String>(Arrays.asList(dialogmessage));

            front = new ArrayList<String>(Arrays.asList(PagesFragment.names_list));
        }
        if(getTitle.equals("Instructions") == true){
            String[] dialogmessage = getResources().getStringArray(R.array.instructions);
            back = new ArrayList<String>(Arrays.asList(dialogmessage));

            front = new ArrayList<String>(Arrays.asList(instructions));
        }

        else if(getTitle.equals("Biology") == true){
            String[] dialogmessage = getResources().getStringArray(R.array.bio);
            back = new ArrayList<String>(Arrays.asList(dialogmessage));
            PhotosFragment.loadSomeData();
            imgBack.setMaxLines(5);
            front = new ArrayList<String>(PhotosFragment.biology);
        }
        else if(getTitle.equals("Chemistry") == true){
            String[] dialogmessage = getResources().getStringArray(R.array.chem);
            back = new ArrayList<String>(Arrays.asList(dialogmessage));
            PhotosFragment.loadSomeData();
            imgBack.setMaxLines(5);
            front = new ArrayList<String>(PhotosFragment.chemistry);
        }
        else if(getTitle.equals("Geology") == true){
            String[] dialogmessage = getResources().getStringArray(R.array.geology);
            back = new ArrayList<String>(Arrays.asList(dialogmessage));
            PhotosFragment.loadSomeData();
            imgBack.setMaxLines(5);
            front = new ArrayList<String>(PhotosFragment.geology);
        }
        else if(getTitle.equals("Math") == true){
            String[] dialogmessage = getResources().getStringArray(R.array.math);
            back = new ArrayList<String>(Arrays.asList(dialogmessage));

            front = new ArrayList<String>(Arrays.asList(FindPeopleFragment.names_list_organ));
        }
        else if(getTitle.equals("Physics") == true){
        String[] dialogmessage = getResources().getStringArray(R.array.phy);
        back = new ArrayList<String>(Arrays.asList(dialogmessage));
            PhotosFragment.loadSomeData();
            imgBack.setMaxLines(5);
        front = new ArrayList<String>(PhotosFragment.physics);
    }
        if(getTitle.equals("Social Studies") == true){
            String[] dialogmessage = getResources().getStringArray(R.array.social);
            back = new ArrayList<String>(Arrays.asList(dialogmessage));
            imgBack.setMaxLines(5);
            front = new ArrayList<String>(Arrays.asList(CommunityFragment.names_list));
        }

        imgBack.setText(back.get(counter));
        imgFront.setText(front.get(counter));
        number.setText(counter + 1 + " / " + back.size());

        final AnimatorSet setRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.flight_right_out);

        final AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                R.animator.flight_left_in);

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
                if(isBackVisible == true){
                    String speak = imgBack.getText().toString();
                    t1.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
                }
                else {
                    String speak = imgFront.getText().toString();
                    t1.speak(speak, TextToSpeech.QUEUE_FLUSH, null);
                }


            }
        });

        btnFlip.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    // single tap
                    if (!isBackVisible) {
                        setRightOut.setTarget(imgFront);
                        setLeftIn.setTarget(imgBack);
                        setRightOut.start();
                        setLeftIn.start();
                        isBackVisible = true;
                    } else {
                        setRightOut.setTarget(imgBack);
                        setLeftIn.setTarget(imgFront);
                        setRightOut.start();
                        setLeftIn.start();
                        isBackVisible = false;
                    }
                    return true;
                } else {
                    // your code for move and drag
                    onTouchEvent(event);
                }

                return false;
            }
        });


    }


    // onTouchEvent () method gets called when User performs any touch event on screen
    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

                //if left to right sweep event on screen
                if (x1 < x2)
                {
                    // Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    didNext = true;
                    if(counter > 0) {
                        counter--;
                        imgBack.setText(back.get(counter));
                        imgFront.setText(front.get(counter));
                        number.setText(counter + 1 + " / " + back.size());
                    }
                 //   System.out.println(didNext);
                }

                // if right to left sweep event on screen
                if (x1 > x2)
                {
                    // Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    didNext = true;
                    if(counter < back.size()) {
                        counter++;
                        if(counter < back.size()) {
                            imgBack.setText(back.get(counter));
                            imgFront.setText(front.get(counter));
                            number.setText(counter + 1 + " / " + back.size());
                        }
                    }
                   // System.out.println(didNext);

                }

                // if UP to Down sweep event on screen
                if (y1 < y2)
                {
                    //Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }

                //if Down to UP sweep event on screen
                if (y1 > y2)
                {
                    //Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

        return false;
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }

    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

    }
