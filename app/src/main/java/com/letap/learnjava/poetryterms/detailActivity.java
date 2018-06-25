package com.letap.learnjava.poetryterms;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Locale;

public class detailActivity extends Activity {

    String title;
    String description;
    String dialoginformation;
    TextToSpeech t1;
    String image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final MediaPlayer mp = new MediaPlayer();
        TextView tvTitleLabel = (TextView)findViewById(R.id.tvTitleLabel);
        TextView tvDescLabel = (TextView)findViewById(R.id.tvDescLabel);
        ImageView ivPrImage = (ImageView)findViewById(R.id.ivPrvImage);
        Button btn = (Button)findViewById(R.id.button1);
        ImageButton btn2 = (ImageButton)findViewById(R.id.imageButton);



        final Bundle extras = getIntent().getExtras();

        if (extras != null) {




            title = extras.getString("province");
            tvTitleLabel.setText(title);
            btn.setText("More about "+ title);
            dialoginformation = extras.getString("dialogmsg");

                image = extras.getString("prvImg");

                File newFile = new File(image);

                if (newFile.exists()) {
                   // System.out.println("here");
                    Bitmap bitmap = BitmapFactory.decodeFile(newFile.getAbsolutePath());
                    ivPrImage.setImageBitmap(bitmap);
                }
                //System.out.println(m.getThumbnailUrl());



          description = extras.getString("provincedesclabel");
            tvDescLabel.setText(description);

        }

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(detailActivity.this);
                dlgAlert.setMessage(dialoginformation);
                dlgAlert.setTitle(title);
                dlgAlert.setNegativeButton("close", null);
                dlgAlert.create().show();

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

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.speak(title, TextToSpeech.QUEUE_FLUSH, null);
                t1.speak(description, TextToSpeech.QUEUE_ADD, null);
                //t1.speak(title, TextToSpeech.QUEUE_FLUSH, null, "1");
                //t1.speak(title, TextToSpeech.QUEUE_ADD, null, "2");
            }
        });


    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }




    }



