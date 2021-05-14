package com.example.identifythecar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class activity_advanced_level extends AppCompatActivity {

   protected static int image_set01;
   protected static int image_set02;
   protected static int image_set03;

    // 3 Image Arrays for 3 random ImageViews
   protected int[] images_01 = {R.drawable.img_01, R.drawable.img_02, R.drawable.img_03,
            R.drawable.img_04, R.drawable.img_05, R.drawable.img_06,
            R.drawable.img_07, R.drawable.img_08, R.drawable.img_09,
            R.drawable.img_10};
   protected int[] images_02 = {R.drawable.img_11, R.drawable.img_12, R.drawable.img_13,
            R.drawable.img_14, R.drawable.img_15, R.drawable.img_16,
            R.drawable.img_17, R.drawable.img_18, R.drawable.img_19,
            R.drawable.img_20};
   protected int[] images_03 = {R.drawable.img_21, R.drawable.img_22, R.drawable.img_23,
            R.drawable.img_24, R.drawable.img_25, R.drawable.img_26,
            R.drawable.img_27, R.drawable.img_28, R.drawable.img_29,
            R.drawable.img_30};

  protected static String[] carNames = {"audi","porsche","bmw","ford","toyota","tesla",
            "lexus","honda","mazda"
            ,"mercedes","nissan","jaguar","mitsubishi","fiat","chevrolet"};

    protected static int attempts = 0;
    private static final long START_TIME_IN_MILLIS = 21000;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mEndTime;
    protected CountDownTimer countDownTimer;
    @SuppressLint("StaticFieldLeak")
    protected static TextView timer;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        final ImageView randomImage_V1 = findViewById(R.id.random_imageBtn_V4);
        final ImageView randomImage_V2 = findViewById(R.id.random_imageBtn_V5);
        final ImageView randomImage_V3 = findViewById(R.id.random_imageBtn_V6);

        //set random 3 unique random images
        final Random rnd = new Random();
        image_set01 = rnd.nextInt(images_01.length);
        randomImage_V1.setImageResource(images_01[image_set01]);

        image_set02 = rnd.nextInt(images_02.length);
        randomImage_V2.setImageResource(images_02[image_set02]);

        image_set03 = rnd.nextInt(images_03.length);
        randomImage_V3.setImageResource(images_03[image_set03]);

        //list which contains car names in order
        List<String> cars = new ArrayList<>();

        //using a for loop to add car names to the list
        for (String carName : carNames) {
            if (!cars.contains(carName)) {
                cars.add(carName);
                cars.add(carName);
            }


        }

        Button submit = findViewById(R.id.btn_submit_03);
        EditText firstTextBox = findViewById(R.id.edit_text_img_01);
        EditText secondTextBox = findViewById(R.id.edit_text_img_02);
        EditText thirdTextBox = findViewById(R.id.edit_text_img_03);
        timer = findViewById(R.id.advanced_timer);

        //check for the switch state
        Log.d("Advanced Level State : ", String.valueOf(MainActivity.state));


        if (MainActivity.state) {
            startTimer();
            updateCountDownText();
        }else {
            timer.setVisibility(View.INVISIBLE);
        }

        //set the function for the submit button

        submit.setOnClickListener(v -> {
            TextView finalScore  = findViewById(R.id.score);
            int userScore = 0 ;

            String first_car = cars.get(image_set01);
            String second_car = cars.get(10+image_set02);
            String third_car = cars.get(20+image_set03);

            if (!firstTextBox.getText().toString().isEmpty() && !secondTextBox.getText().toString().isEmpty()
            && !thirdTextBox.getText().toString().isEmpty()) {
                //if correct answer is provided by the user
                if (firstTextBox.getText().toString().equalsIgnoreCase(first_car)) {
                    firstTextBox.setBackgroundColor(Color.parseColor("#20BA49"));
                    firstTextBox.setEnabled(false);
                    userScore += 1;

                }
                if (secondTextBox.getText().toString().equalsIgnoreCase(second_car)) {
                    secondTextBox.setBackgroundColor(Color.parseColor("#20BA49"));
                    secondTextBox.setEnabled(false);
                    userScore += 1;

                }
                if (thirdTextBox.getText().toString().equalsIgnoreCase(third_car)) {
                    thirdTextBox.setBackgroundColor(Color.parseColor("#20BA49"));
                    thirdTextBox.setEnabled(false);
                    userScore += 1;

                }

                //if any incorrect answer is provided by the user
                if (!firstTextBox.getText().toString().equalsIgnoreCase(first_car)) {
                    firstTextBox.setBackgroundColor(Color.parseColor("#BA2D20"));
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                        countDownTimer.start();
                    }



                }
                if (!secondTextBox.getText().toString().equalsIgnoreCase(second_car)) {
                    secondTextBox.setBackgroundColor(Color.parseColor("#BA2D20"));
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                        countDownTimer.start();
                    }

                }
                if (!thirdTextBox.getText().toString().equalsIgnoreCase(third_car)) {
                    thirdTextBox.setBackgroundColor(Color.parseColor("#BA2D20"));
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                        countDownTimer.start();
                    }

                }

                String sc = String.valueOf(userScore);
                //display the final score
                finalScore.setText("Score : "+sc);
                attempts += 1;

                if (userScore != 3){
                    showAttempts(3-attempts);
                }
                //if user gets all correct display a Congrats message
                if (userScore == 3) {
                    showToast_01();
                    submit.setText("NEXT");
                    next();
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                    }
                }
                //if user gets all attempts incorrect
               else if ((attempts == 3) && (userScore == 0)) {
                    showToast_02();
                    showToast_03(first_car, second_car, third_car);
                    firstTextBox.setEnabled(false);
                    secondTextBox.setEnabled(false);
                    thirdTextBox.setEnabled(false);
                    submit.setText("NEXT");
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                    }
                    next();

                } else if (attempts == 3) {
                    showToast_02();
                    showToast_03(first_car,second_car,third_car);
                    firstTextBox.setEnabled(false);
                    secondTextBox.setEnabled(false);
                    thirdTextBox.setEnabled(false);
                    submit.setText("NEXT");
                    next();
                    if(countDownTimer != null){
                        countDownTimer.cancel();
                    }

                }
            }else {
                emptyToast();
            }
        });



    }

    //start the timer
    @SuppressWarnings("deprecation")
    private void startTimer() {
        Button submit = findViewById(R.id.btn_submit_03);
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {

                int noOfSecond = (int) 0.1;
                new Handler().postDelayed(() -> {

                    submit.performClick();
                    Log.d("Attempts", String.valueOf(attempts));
                }, noOfSecond * 1000);

                if (attempts < 3){
                    start();
                }
            }
        }.start();
        mTimerRunning = true;

    }

//update the timer
    @SuppressLint("SetTextI18n")
    private void updateCountDownText() {
        if (MainActivity.state) {
            int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
            timer.setText("Time-Left : " +timeLeftFormatted+"s");
        }else {
            timer.setVisibility(View.INVISIBLE);
        }
    }

    //method used to store data before pausing the activity
    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimerRunning);
        outState.putLong("endTime", mEndTime);
    }
    //method used to retrieve that data back
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimerRunning = savedInstanceState.getBoolean("timerRunning");
        updateCountDownText();
        if (mTimerRunning) {
            mEndTime = savedInstanceState.getLong("endTime");
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            startTimer();
        }
    }

    //cancel the timer when back button is pressed
    @Override
    public void onBackPressed() {
        Intent home = new Intent(activity_advanced_level.this,
                MainActivity.class);
        startActivity(home);
        MainActivity.state = false;
        attempts = 0;
        //cancel the timer first check if its null
        if(countDownTimer != null){
            countDownTimer.cancel();
        }

    }

    //toast message for correct answer
    @SuppressWarnings("deprecation")
    public void showToast_01() {
        LayoutInflater inflater =  getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v1, findViewById(R.id.toast_message_layout));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    //toast message for incorrect answers
    @SuppressWarnings("deprecation")
    public void showToast_02() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v2, findViewById(R.id.toast_wrong_message_layout));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    //show the correct car name when user gets the answer wrong
    @SuppressWarnings("deprecation")
    @SuppressLint("SetTextI18n")
    public void showToast_03(String car1, String car2, String car3){
        EditText firstTextBox = findViewById(R.id.edit_text_img_01);
        EditText secondTextBox = findViewById(R.id.edit_text_img_02);
        EditText thirdTextBox = findViewById(R.id.edit_text_img_03);


        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_correct_car_name_layout));
        TextView correctCarName = layout.findViewById(R.id.tv_correct_car_name);
        if (!firstTextBox.getText().toString().equalsIgnoreCase(car1)) {

            correctCarName.setText("First Car Name is : "+car1);
        }
       if (!secondTextBox.getText().toString().equalsIgnoreCase(car2)){
            correctCarName.setText("Second Car Name is : "+car2);
        } if (!thirdTextBox.getText().toString().equalsIgnoreCase(car3)){
            correctCarName.setText("Third Car Name is : "+car3);
        } if (!firstTextBox.getText().toString().equalsIgnoreCase(car1) &&
                !secondTextBox.getText().toString().equalsIgnoreCase(car2)){
            correctCarName.setText("First Car Name is : "+car1+"\nSecond Car Name is : "+car2);
        } if (!firstTextBox.getText().toString().equalsIgnoreCase(car1) &&
                !thirdTextBox.getText().toString().equalsIgnoreCase(car3)){
            correctCarName.setText("First Car Name is : "+car1+"\nThird Car Name is : "+car3);
        } if (!thirdTextBox.getText().toString().equalsIgnoreCase(car3) &&
                !secondTextBox.getText().toString().equalsIgnoreCase(car2)){
            correctCarName.setText("Second Car Name is : "+car2
                    +"\nThird Car Name is : "+car3);
        }if(!firstTextBox.getText().toString().equalsIgnoreCase(car1) &&
                !secondTextBox.getText().toString().equalsIgnoreCase(car2) &&
                !thirdTextBox.getText().toString().equalsIgnoreCase(car3)) {
            correctCarName.setText("First Car Name is : "+car1+"\nSecond Car Name is : "+car2
                    +"\nThird Car Name is : "+car3);
        }

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.getView().setBackgroundColor(Color.parseColor("#F6AE2D"));
        toast.show();



    }

    //show a message when user enters noting in the text box
    @SuppressWarnings("deprecation")
    @SuppressLint("SetTextI18n")
    public void emptyToast(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_correct_car_name_layout));
        TextView correctCarName = layout.findViewById(R.id.tv_correct_car_name);
        correctCarName.setText("please enter something in text box".toUpperCase());
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        if(countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer.start();
        }

    }
    //go to the next part
    public void next() {
        Button submit_v1 = findViewById(R.id.btn_submit_03);
        submit_v1.setOnClickListener(v -> {
            Intent intent =  new Intent(activity_advanced_level.this, activity_advanced_level.class);
            attempts = 0;
            startActivity(intent);
        });
    }

    //show remaining attempts
    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    public void showAttempts(int attempts){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_correct_car_name_layout));
        TextView correctCarName = layout.findViewById(R.id.tv_correct_car_name);
        correctCarName.setText("Attempts remaining : ".toUpperCase()+attempts);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

    //Go to home screen
    public void home(View view) {
        Intent home = new Intent(activity_advanced_level.this, MainActivity.class);
        startActivity(home);
        MainActivity.state = false;
        if(countDownTimer != null){
            countDownTimer.cancel();
        }

    }


}