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

public class Hints extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 21000;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mEndTime;

    //Array which contains all car images
    private static final int[] carImages = {R.drawable.img_01, R.drawable.img_02,
            R.drawable.img_03,
            R.drawable.img_04, R.drawable.img_05, R.drawable.img_06,
            R.drawable.img_07, R.drawable.img_08, R.drawable.img_09,
            R.drawable.img_10, R.drawable.img_11, R.drawable.img_12,
            R.drawable.img_13, R.drawable.img_14, R.drawable.img_15,
            R.drawable.img_16, R.drawable.img_17, R.drawable.img_18,
            R.drawable.img_19, R.drawable.img_20, R.drawable.img_21,
            R.drawable.img_22, R.drawable.img_23, R.drawable.img_24,
            R.drawable.img_25, R.drawable.img_26, R.drawable.img_27,
            R.drawable.img_28, R.drawable.img_29, R.drawable.img_30};


    //names of all the cars are contains in this array
    protected static String[] carNames = {"audi", "porsche", "bmw", "ford", "toyota", "tesla",
            "lexus", "honda", "mazda"
            , "mercedes", "nissan", "jaguar", "mitsubishi", "fiat", "chevrolet"};
    private static int image_set;
    private static int count;
    protected static int correct = 0;
    protected static int attempts = 0;
    private static final List<Integer> randNumbers = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    protected static TextView timer;
    protected CountDownTimer countDownTimer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);


        //timer
       timer = findViewById(R.id.hints_timer);
        Button submit = findViewById(R.id.btn_submit);
        //list which contains car names in order
        List<String> cars = new ArrayList<>();


        //call the generate random method
        generateRandomCars();


        //set a random image when identify car make button is clicked from the home screen
        final ImageView randomImage = findViewById(R.id.rand_image2);
        image_set = randNumbers.get(count);
        randomImage.setImageResource(carImages[image_set]);
        Log.d("Random Numbers List : ", randNumbers + " count" + count);


        //using a for loop to add car names to the list
        for (String carName : carNames) {
            if (!cars.contains(carName)) {
                cars.add(carName);
                cars.add(carName);
            }


        }

        //change the number of text view dashes according the number of words of a car name
        TextView dash = findViewById(R.id.dashes);
        StringBuilder numOfDashes = new StringBuilder();

        while (true) {
            if (numOfDashes.length() == cars.get(image_set).length()) {
                break;
            } else {

                numOfDashes.append("-");
            }

        }


        //set the number of dashes according the given car name
        dash.setText(numOfDashes);

        EditText editText = findViewById(R.id.edit_text);


        List<Character> characterList = new ArrayList<>();

        List<Character> wrong = new ArrayList<>();








        if (MainActivity.state) {
            startTimer();
            updateCountDownText();
        }else {
            timer.setVisibility(View.INVISIBLE);
        }
        //onClick function for the submit button
        submit.setOnClickListener(v -> {



            if (editText.getText().toString().isEmpty()) {

                emptyToast();
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer.start();
                }


            } else {
                //Name of the car
                StringBuilder carName = new StringBuilder(cars.get(image_set));
                //get the text from the user
                String textBox = editText.getText().toString();

                //get the first letter  in the textBox
                char letter = textBox.charAt(0);
                //array list to check if user entered the first letter a lowercase or uppercase
                characterList.add(letter);


                boolean lower = Character.isLowerCase(letter);
                boolean upper = Character.isUpperCase(letter);
                boolean allLetters = Character.isUpperCase(characterList.get(0));

                for (int i = 0; i < carName.length(); i++) {
                    //if user enters in lower case
                    if (lower && !allLetters) {
                        if (carName.charAt(i) == letter) {

                            char lowerC = Character.toLowerCase(letter);
                            numOfDashes.setCharAt(i, lowerC);
                            correct++;
                            if (countDownTimer != null) {
                                countDownTimer.cancel();
                                countDownTimer.start();
                            }


                        }      //if user enters in upper case
                    } else if (upper && allLetters) {
                        char upToLowText = Character.toLowerCase(letter);

                        if (carName.charAt(i) == upToLowText) {
                            char upperC = Character.toUpperCase(letter);
                            numOfDashes.setCharAt(i, upperC);
                            correct++;
                            if (countDownTimer != null) {
                                countDownTimer.cancel();
                                countDownTimer.start();
                            }


                        }

                    }
                }

                for (int i = 0; i < cars.get(image_set).length(); i++) {
                    wrong.add(cars.get(image_set).charAt(i));
                }

                if (!wrong.contains(Character.toLowerCase(letter))) {
                    Log.d("Wrong", String.valueOf(wrong));
                    attempts += 1;
                    showAttempts(3-attempts);
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                        countDownTimer.start();
                    }

                }


                //change the textView
                dash.setText(numOfDashes);
                Log.d("Attempts", String.valueOf(attempts));
                Log.d("Edit Text", dash.getText().toString());
                Log.d("Edit Text", cars.get(image_set));
                //if all letters are correct Display the message as CORRECT! in green
                if (dash.getText().toString().toLowerCase().equals(cars.get(image_set))) {
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }
                    showToast_01();
                    submit.setText("Next");
                    next();

                } else if (attempts >= 3) {
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }
                    showToast_02();
                    showToast_03(cars);
                    submit.setText("Next");
                    next();
                }

            }
        });


        count++;


    }
    //start the countdown timer
    @SuppressWarnings("deprecation")
    private void startTimer() {
        Button submit = findViewById(R.id.btn_submit);
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

    //generate non repeating car images
    public void generateRandomCars() {
        if (count == 0) {
            randNumbers.clear();
        }
        if (randNumbers.size() == carImages.length) {
            randNumbers.clear();
            count = 0;
            while (true) {
                Random rand = new Random();
                int num = rand.nextInt(30);
                if (!randNumbers.contains(num)) {
                    randNumbers.add(num);
                    break;
                }
            }
        } else {

            while (true) {
                Random rand = new Random();
                int num = rand.nextInt(30);
                if (!randNumbers.contains(num)) {
                    randNumbers.add(num);
                    break;
                }
            }


        }
    }




    //button for which goes to home screen
    public void home(View view) {
        Intent home = new Intent(Hints.this, MainActivity.class);
        startActivity(home);
        MainActivity.state = false;
        randNumbers.clear();
        //cancel the timer first check if its null
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        //make count = 0 each time going to home screen
        count = 0;
        attempts = 0;
    }

    //cancel the timer and turn the switch mode to false
    @Override
    public void onBackPressed() {
        attempts = 0;
        randNumbers.clear();
        Intent home = new Intent(Hints.this, MainActivity.class);
        startActivity(home);
        MainActivity.state = false;
        //cancel the timer first check if its null
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        //make count = 0 each time going to home screen
        count = 0;
    }

    //toast message for correct answer
    @SuppressWarnings("deprecation")
    public void showToast_01() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v1, findViewById(R.id.toast_message_layout));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
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
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    //display the correct car name in yellow
    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    public void showToast_03(List<String> cars) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_correct_car_name_layout));
        TextView correctCarName = layout.findViewById(R.id.tv_correct_car_name);
        correctCarName.setText("Correct Answer Is : " + cars.get(image_set).toUpperCase());
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.getView().setBackgroundColor(Color.parseColor("#F6AE2D"));
        toast.show();


    }

    //message when user submits nothing or empty textbox
    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    public void emptyToast() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer.start();

        }
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_correct_car_name_layout));
        TextView correctCarName = layout.findViewById(R.id.tv_correct_car_name);
        correctCarName.setText("please enter something in text box".toUpperCase());
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

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

    //go to the next part
    public void next() {
        attempts = 0;
        Button submit_v1 = findViewById(R.id.btn_submit);
        submit_v1.setOnClickListener(v -> {
            Intent intent = new Intent(Hints.this, Hints.class);
            startActivity(intent);
        });
    }




}