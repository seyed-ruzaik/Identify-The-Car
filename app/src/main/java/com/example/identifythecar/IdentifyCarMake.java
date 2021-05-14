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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdentifyCarMake extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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
    protected static String[] carNames = {"Audi","Porsche","Bmw","Ford","Toyota","Tesla",
            "Lexus","Honda","Mazda"
    ,"Mercedes","Nissan","Jaguar","Mitsubishi","Fiat","Chevrolet"};

    protected CountDownTimer countDownTimer;
    private static int image_set;
    private static int count;
    private static final List<Integer> randNumbers = new ArrayList<>();
    static int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_car_make);


        //call the generate random method
        generateRandomCars();

        Log.d("Random Numbers List : ", String.valueOf(randNumbers));


        //set a random image when identify car make button is clicked from the home screen
        final ImageView randomImage = findViewById(R.id.rand_image);
        image_set = randNumbers.get(count);
        randomImage.setImageResource(carImages[image_set]);


        // Creating the spinner
        Spinner spinner = findViewById(R.id.list_cars);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }
// Creating ArrayAdapter using the string array
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_cars,
                android.R.layout.simple_spinner_item);

        // Specifying the layout when list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Applying the adapter to the spinner
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
        TextView timer = findViewById(R.id.identify_make);
        Button identify = findViewById(R.id.btn_identify);
        myTimer(timer,identify,spinner);

    }

    //go to home screen
    public void home(View view) {
        Intent home = new Intent(IdentifyCarMake.this, MainActivity.class);
        startActivity(home);
        randNumbers.clear();
        MainActivity.state = false;
        //cancel the timer first check if its null
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
        count = 0;
    }


    //generate non repeating car images
    public void generateRandomCars(){
        if (count == 0 ){
            randNumbers.clear();
        }
            if (randNumbers.size() == carImages.length){
                randNumbers.clear();
                count = 0 ;
                while (true){
                    Random rand = new Random();
                    int num = rand.nextInt(30);
                    if (!randNumbers.contains(num)) {
                        randNumbers.add(num);
                        break;
                    }
                }
            }else {

                while (true){
                    Random rand = new Random();
                    int num = rand.nextInt(30);
                    if (!randNumbers.contains(num)) {
                        randNumbers.add(num);
                        break;
                    }
                }


            }
    }

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("deprecation")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //change the textColor
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        final String spinner_name_equal = carNames[position];
        pos = position;
        final Button identify = findViewById(R.id.btn_identify);
        identify.setOnClickListener(v -> {
            count++;

            //list which contains car names in order
            List<String> cars = new ArrayList<>();

            //using a for loop to add car names to the list
            for (String carName : carNames) {
                if (!cars.contains(carName)) {
                    cars.add(carName);
                    cars.add(carName);
                }


            }

              //
            if (cars.get(image_set).equals(spinner_name_equal)){
                // Message correct
                showToast_01();
                //cancel the timer first check if its null
            }else {
                // Message wrong
                showToast_02();
                // Message correct car
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_correct_car_name_layout));
                TextView correctCarName = layout.findViewById(R.id.tv_correct_car_name);
                correctCarName.setText("Correct Answer Is : " + cars.get(image_set));
                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER, 0 , 0 );
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.getView().setBackgroundColor(Color.parseColor("#F6AE2D"));
                Spinner spinner = findViewById(R.id.list_cars);
                spinner.setEnabled(false);
                spinner.setClickable(false);
                toast.show();
                //cancel the timer first check if its null
            }
            if(countDownTimer != null){
                countDownTimer.cancel();
            }
            //set the button text identify to next
            identify.setText("Next");
            next();

        });



    }


    //countdown timer
    public void myTimer(TextView timer,Button identify, Spinner spinner){
        if (MainActivity.state) {
            //list which contains car names in order
            List<String> cars = new ArrayList<>();

            //using a for loop to add car names to the list
            for (String carName : carNames) {
                if (!cars.contains(carName)) {
                    cars.add(carName);
                    cars.add(carName);
                }


            }
            countDownTimer = new CountDownTimer(21000, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    //set the timer
                    timer.setText("Time-Left : " + (int) millisUntilFinished / 1000 + "s");
                }

                @SuppressLint("SetTextI18n")
                @SuppressWarnings("deprecation")
                @Override
                public void onFinish() {
                    String textSpinner = spinner.getSelectedItem().toString();
                    if (cars.get(image_set).equals(textSpinner)){
                        // Message correct
                        showToast_01();
                        //set the button text identify to next
                    }else {
                        // Message wrong
                        showToast_02();
                        // Message correct car
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_layout_v3, findViewById(R.id.toast_correct_car_name_layout));
                        TextView correctCarName = layout.findViewById(R.id.tv_correct_car_name);
                        correctCarName.setText("Correct Answer Is : " + cars.get(image_set));
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER, 0 , 0 );
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.getView().setBackgroundColor(Color.parseColor("#F6AE2D"));
                        Spinner spinner = findViewById(R.id.list_cars);
                        spinner.setEnabled(false);
                        spinner.setClickable(false);
                        toast.show();
                        //set the button text identify to next
                    }
                    count++;
                    identify.setText("Next");
                    next();

                }
            }.start();
        }else{ //make the count down timer stop
            if(countDownTimer != null){
                countDownTimer.cancel();
            }
            timer.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //go home when pressed back button
    @Override
    public void onBackPressed() {
        Intent home = new Intent(IdentifyCarMake.this, MainActivity.class);
        startActivity(home);
        count = 0;
        randNumbers.clear();
        MainActivity.state = false;
        //cancel the timer first check if its null
        if(countDownTimer != null){
            countDownTimer.cancel();
        }

    }

    //toast message for correct answer
    @SuppressWarnings("deprecation")
    public void showToast_01() {
        Spinner spinner = findViewById(R.id.list_cars);
        spinner.setClickable(false);
        spinner.setEnabled(false);
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
        Spinner spinner = findViewById(R.id.list_cars);
        spinner.setClickable(false);
        spinner.setEnabled(false);
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_v2, findViewById(R.id.toast_wrong_message_layout));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0 , 0 );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


    //go to the next part
    public void next() {
        Button submit_v1 = findViewById(R.id.btn_identify);
        submit_v1.setOnClickListener(v -> {
            Intent intent =  new Intent(IdentifyCarMake.this, IdentifyCarMake.class);
            startActivity(intent);
        });
    }


}
