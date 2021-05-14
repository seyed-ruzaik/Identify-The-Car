package com.example.identifythecar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyCar extends AppCompatActivity {


    protected static int image_set01;
    protected static int image_set02;
    protected static int image_set03;
    protected boolean clicked = false;


    protected CountDownTimer countDownTimer;

    // 3 Image Arrays for 3 random ImageViews
    int[] images_01 = {R.drawable.img_01, R.drawable.img_02, R.drawable.img_03,
            R.drawable.img_04, R.drawable.img_05, R.drawable.img_06,
            R.drawable.img_07, R.drawable.img_08, R.drawable.img_09,
            R.drawable.img_10};
    int[] images_02 = {R.drawable.img_11, R.drawable.img_12, R.drawable.img_13,
            R.drawable.img_14, R.drawable.img_15, R.drawable.img_16,
            R.drawable.img_17, R.drawable.img_18, R.drawable.img_19,
            R.drawable.img_20};
    int[] images_03 = {R.drawable.img_21, R.drawable.img_22, R.drawable.img_23,
            R.drawable.img_24, R.drawable.img_25, R.drawable.img_26,
            R.drawable.img_27, R.drawable.img_28, R.drawable.img_29,
            R.drawable.img_30};

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car);

        final ImageButton randomImage_V1 = findViewById(R.id.random_imageBtn_V1);
        final ImageButton randomImage_V2 = findViewById(R.id.random_imageBtn_V2);
        final ImageButton randomImage_V3 = findViewById(R.id.random_imageBtn_V3);
        final TextView randomImage_text_V1 = findViewById(R.id.tv_random_img_name_V1);
        final TextView randomImage_text_V2 = findViewById(R.id.tv_random_img_name_V2);
        final TextView randomImage_text_V3 = findViewById(R.id.tv_random_img_name_V3);

        randomImage_text_V1.setBackgroundColor(Color.parseColor("#0f99bf"));

        randomImage_text_V1.setGravity(Gravity.CENTER);
        randomImage_text_V2.setGravity(Gravity.CENTER);
        randomImage_text_V3.setGravity(Gravity.CENTER);

        randomImage_text_V1.setTextColor(Color.parseColor("#FFFFFF"));
        randomImage_text_V2.setTextColor(Color.parseColor("#FFFFFF"));
        randomImage_text_V3.setTextColor(Color.parseColor("#FFFFFF"));

        //set random 3 unique random images
        final Random rnd = new Random();
        image_set01 = rnd.nextInt(images_01.length);
        randomImage_V1.setImageResource(images_01[image_set01]);

        image_set02 = rnd.nextInt(images_02.length);
        randomImage_V2.setImageResource(images_02[image_set02]);

        image_set03 = rnd.nextInt(images_03.length);
        randomImage_V3.setImageResource(images_03[image_set03]);


// Prompting right text corresponding to the image that randomly generated
        if (image_set01 == 0) {
            String names_01 = "Audi";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 0) {
            String names_01 = "Tesla";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 0) {
            String names_01 = "Nissan";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 1) {
            String names_01 = "Audi";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 1) {
            String names_01 = "Tesla";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 1) {
            String names_01 = "Nissan";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 2) {
            String names_01 = "Porsche";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 2) {
            String names_01 = "Lexus";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 2){
            String names_01 = "Jaguar";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 3) {
            String names_01 = "Porsche";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 3) {
            String names_01 = "Lexus";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 3){
            String names_01 = "Jaguar";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 4) {
            String names_01 = "BMW";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 4) {
            String names_01 = "Honda";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 4){
            String names_01 = "Mitsubishi";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 5) {
            String names_01 = "BMW";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 5) {
            String names_01 = "Honda";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 5){
            String names_01 = "Mitsubishi";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 6) {
            String names_01 = "Ford";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 6) {
            String names_01 = "Mazda";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 6){
            String names_01 = "Fiat";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 7) {
            String names_01 = "Ford";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 7) {
            String names_01 = "Mazda";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 7){
            String names_01 = "Fiat";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 8) {
            String names_01 = "Toyota";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 8) {
            String names_01 = "Mercedes";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 8){
            String names_01 =  "Chevrolet";
            randomImage_text_V3.setText(names_01);

        }else if (image_set01 == 9) {
            String names_01 = "Toyota";
            randomImage_text_V1.setText(names_01);
        }else if (image_set02 == 9) {
            String names_01 = "Mercedes";
            randomImage_text_V2.setText(names_01);
        }else if (image_set03 == 9) {
            String names_01 = "Chevrolet";
            randomImage_text_V3.setText(names_01);
        }

        for (int i = 0; i < images_01.length ; i++) {
            //System.out.println("Car Image id: " + Arrays.toString(images_01));
            System.out.println(image_set02);
        }

        List<Integer> attempts = new ArrayList<>();
        TextView timer = findViewById(R.id.identify_car_timerText);


        //set the timer
        myTimer(timer,attempts);

        //image button 1
            randomImage_V1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attempts.add(1);
                    clicked = true;
                    Log.d("Size of list", String.valueOf(attempts.size()));
                    String get_name = randomImage_text_V1.getText().toString();
                    if (get_name.equals("Audi")) {
                        showToast_01();
                    } else if (get_name.equals("Porsche")) {
                        showToast_01();
                    } else if (get_name.equals("BMW")) {
                        showToast_01();
                    } else if (get_name.equals("Ford")) {
                        showToast_01();
                    } else if (get_name.equals("Toyota")) {
                        showToast_01();
                    } else {
                        showToast_02();
                    }
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                    }
                    next_activity();
                }
            });

            // Image Button 2
            randomImage_V2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attempts.add(1);
                    clicked = true;
                    Log.d("Size of list", String.valueOf(attempts.size()));
                    String get_name = randomImage_text_V2.getText().toString();
                    if (get_name.equals("Tesla")) {
                        showToast_01();
                    } else if (get_name.equals("Lexus")) {
                        showToast_01();
                    } else if (get_name.equals("Honda")) {
                        showToast_01();
                    } else if (get_name.equals("Mazda")) {
                        showToast_01();
                    } else if (get_name.equals("Mercedes")) {
                        showToast_01();
                    } else {
                        showToast_02();
                    }
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                    }
                    next_activity();
                }
            });
        // Image Button 3
            randomImage_V3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attempts.add(1);
                    clicked = true;
                    Log.d("Size of list", String.valueOf(attempts.size()));
                    String get_name = randomImage_text_V3.getText().toString();
                    if (get_name.equals("Nissan")) {
                        showToast_01();
                    } else if (get_name.equals("Jaguar")) {
                        showToast_01();
                    } else if (get_name.equals("Mitsubishi")) {
                        showToast_01();
                    } else if (get_name.equals("Fiat")) {
                        showToast_01();
                    } else if (get_name.equals("Chevrolet")) {
                        showToast_01();
                    } else {
                        showToast_02();
                    }
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                    }
                    next_activity();
                }
            });

            Button sbt  = findViewById(R.id.btn_submit_02);
            sbt.setOnClickListener(v -> {
                if (!clicked){
                    emptyToast();
                }
            });

    }


    //countdown timer
    public void myTimer(TextView timer, List<Integer> attempts){
        if (MainActivity.state) {
            countDownTimer = new CountDownTimer(21000, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    timer.setText("Time-Left : " + (int) millisUntilFinished / 1000 + "s");
                }

                @Override
                public void onFinish() {
                    //set the clickable to false immediately after user clicks one time so user only gets-
                    //a single attempt only
                    if (attempts.size() == 0){

                        showToast_02();
                    }
                    ImageButton button1 = findViewById(R.id.random_imageBtn_V1);
                    ImageButton button2 = findViewById(R.id.random_imageBtn_V2);
                    ImageButton button3 = findViewById(R.id.random_imageBtn_V3);
                    button1.setClickable(false);
                    button2.setClickable(false);
                    button3.setClickable(false);
                    next_activity();

                }
            }.start();
        }else{
            if(countDownTimer != null){
                countDownTimer.cancel();
            }
            timer.setVisibility(View.INVISIBLE);
        }

    }

    //empty toast
    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    public void emptyToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_correct_car_name_layout));
        TextView correctCarName = layout.findViewById(R.id.tv_correct_car_name);
        correctCarName.setText("Click on any of the images!\nTo proceed".toUpperCase());
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    //override the back button
    @Override
    public void onBackPressed() {
        Intent home = new Intent(IdentifyCar.this, MainActivity.class);
        startActivity(home);
        MainActivity.state = false;
        //cancel the timer first check if its null
        if(countDownTimer != null){
            countDownTimer.cancel();
        }

    }

    //Display a message for correct answer in green color
    @SuppressWarnings("deprecation")
    private void showToast_01() {
        //set the clickable to false immediately after user clicks one time so user only gets-
        //a single attempt only
        ImageButton button1 = findViewById(R.id.random_imageBtn_V1);
        ImageButton button2 = findViewById(R.id.random_imageBtn_V2);
        ImageButton button3 = findViewById(R.id.random_imageBtn_V3);
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
        LayoutInflater inflater =  getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v1, findViewById(R.id.toast_message_layout));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    //display a message for wrong selection in red color
    @SuppressWarnings("deprecation")
    private void showToast_02() {
        //set the clickable to false immediately after user clicks one time so user only gets-
        //a single attempt only
        ImageButton button1 = findViewById(R.id.random_imageBtn_V1);
        ImageButton button2 = findViewById(R.id.random_imageBtn_V2);
        ImageButton button3 = findViewById(R.id.random_imageBtn_V3);
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v2, findViewById(R.id.toast_wrong_message_layout));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }



    //repeat the process
    public void next_activity() {
        Button btn_name_car = findViewById(R.id.btn_submit_02);
        btn_name_car.setOnClickListener(v -> {
            Intent intent =  new Intent(IdentifyCar.this, IdentifyCar.class);
            startActivity(intent);
        });
    }


    //go to the home screen
    public void home(View view) {
        Intent home = new Intent(IdentifyCar.this, MainActivity.class);
        startActivity(home);
        MainActivity.state = false;
        //cancel the timer first check if its null
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}