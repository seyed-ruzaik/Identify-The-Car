package com.example.identifythecar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    //state of the switch
    protected static boolean state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the buttons
        Button carMake = findViewById(R.id.btn_Identify_car_make);
        Button hints = findViewById(R.id.btn_hints);
        Button identify_Car = findViewById(R.id.btn_identify_car);
        Button advanceLvl = findViewById(R.id.btn_advancedLevel);
        SwitchCompat switchButtons = findViewById(R.id.switchCompat);

        //go to identify car maker
        carMake.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, IdentifyCarMake.class)));

        //go to hints
        hints.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Hints.class)));

        //go to identify a car
        identify_Car.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,IdentifyCar.class)));

        //go to the advanced level option
        advanceLvl.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,activity_advanced_level.class)));

        //turn on timer for all the screens
        switchButtons.setOnClickListener(v -> {
            if (switchButtons.isChecked()){
                Log.d("Switch-Button","ON");
            }else {
                Log.d("Switch-Button","OFF");
            }
            state = switchButtons.isChecked();
        });

    }
}


/*
 ** References
 * How to set an onclick listener for an imageButton in an alertDialog -
  https://stackoverflow.com/questions/5812744/how-to-set-an-onclick-listener-for-an-imagebutton-in-an-alertdialog
 * Log - https://developer.android.com/reference/android/util/Log
 * AutoSizing TextViews - https://developer.android.com/guide/topics/ui/look-and-feel/autosizing-textview
 * Spinner example in android - https://www.studytonight.com/android/spinner-in-android#
 * Countdown timer - https://stackoverflow.com/questions/10032003/how-to-make-a-countdown-timer-in-android
 * How to Change App Icon in Android Studio - https://www.youtube.com/watch?v=5Y4plQv8c4s
 * android.widget.Switch - on/off event listener? -
 https://stackoverflow.com/questions/11278507/android-widget-switch-on-off-event-listener
 * Difference between android.R.layout.simple_spinner_dropdown_item and android.R.layout.simple_spinner_item -
 https://stackoverflow.com/questions/10764918/difference-between-android-r-layout-simple-spinner-dropdown-item-and-android-r-l
 * Android ListView Ep.09 : Custom Filter/Search - BaseAdapter [With Images and Text] - https://www.youtube.com/watch?v=cC5vz9vIGy8
 * Custom Toast - Android Studio Tutorial - https://www.youtube.com/watch?v=sZ1fLi4QZ-g
 * Disable an ImageButton - https://stackoverflow.com/questions/8196206/disable-an-imagebutton
 * Android Log.v(), Log.d(), Log.i(), Log.w(), Log.e() - When to use each one? -
 https://stackoverflow.com/questions/7959263/android-log-v-log-d-log-i-log-w-log-e-when-to-use-each-one
 * How to read a single character from an EditText in Android? -
 https://stackoverflow.com/questions/11346371/how-to-read-a-single-character-from-an-edittext-in-android
 * Countdown Timer Part 2 - ORIENTATION CHANGES - Android Studio Tutorial - https://www.youtube.com/watch?v=LMYQS1dqfo8
 * Android: Go to another activity onclick button -
 https://stackoverflow.com/questions/32277623/android-go-to-another-activity-onclick-button
 *Image array in android - https://stackoverflow.com/questions/4201879/image-array-in-android
 * What do @SuppressWarnings(“deprecation”) and (“unused”) mean in Java? -
 https://stackoverflow.com/questions/7397996/what-do-suppresswarningsdeprecation-and-unused-mean-in-java
 *Android - How To Override the “Back” button so it doesn't Finish() my Activity? -
https://stackoverflow.com/questions/3141996/android-how-to-override-the-back-button-so-it-doesnt-finish-my-activity
* (Android) Spinner with button and random result? -
https://stackoverflow.com/questions/53974637/android-spinner-with-button-and-random-result
* how to make a textView invisible - https://stackoverflow.com/questions/20159969/how-to-make-a-textview-invisible
* Disabling of EditText in Android - https://stackoverflow.com/questions/4297763/disabling-of-edittext-in-android
* How set the android:gravity to TextView from Java side in Android -
https://stackoverflow.com/questions/3775705/how-set-the-androidgravity-to-textview-from-java-side-in-android
* Check if EditText is empty. [closed] - https://stackoverflow.com/questions/6290531/check-if-edittext-is-empty
* How to stop CountDownTimer in android - https://stackoverflow.com/questions/40203827/how-to-stop-countdowntimer-in-android
* How to use onSaveInstanceState() and onRestoreInstanceState()? -
https://stackoverflow.com/questions/16769654/how-to-use-onsaveinstancestate-and-onrestoreinstancestate
* How to automatically Click a Button in Android after a 5 second delay -
https://stackoverflow.com/questions/35708453/how-to-automatically-click-a-button-in-android-after-a-5-second-delay
* Change Image of ImageView programmatically in Android -
https://stackoverflow.com/questions/16906528/change-image-of-imageview-programmatically-in-android
* Access Modifiers in Java - https://www.javatpoint.com/access-modifiers
* Set text color in Android Spinner - https://stackoverflow.com/questions/39247136/set-text-color-in-android-spinner
* Android Full Course - Learn Android in 9 Hours | Android Development Tutorial for Beginners -
https://www.youtube.com/watch?v=aS__9RbCyHg
 */