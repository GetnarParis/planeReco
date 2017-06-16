package airblink.planerecognizer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartPageActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private boolean mVisible;

   public static questionDAO qController;
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
initiateDB();
        final Button loginButton = (Button) findViewById(R.id.discoverNew);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartPageActivity.this, GenericQuestionPageActivity.class);
                startActivity(intent);
            }
        });

        mVisible = true;
    }
        private void initiateDB () {

            qController = new questionDAO(getApplicationContext());
            qController.resetDb();
            qController.addQuestion(new questionObj(0,"airbus", "350","","nose",1,  "Is the nose like this ?",1, R.drawable.nose_a350));
            qController.addQuestion(new questionObj(1,"airbus", "300","","nose",1,  "Is the nose like this ?",1, R.drawable.nose_a300));
            qController.addQuestion(new questionObj(2,"airbus", "320","","nose",1, "Is the nose like this ?",1, R.drawable.nose_a320));
            qController.addQuestion(new questionObj(3,"airbus","330","","nose",1,  "Is the nose like this ?",1, R.drawable.nose_a330));
            qController.addQuestion(new questionObj(4,"airbus", "340","","nose",1,  "Is the nose like this ?",1, R.drawable.nose_a340));
            qController.addQuestion(new questionObj(5,"airbus", "330","","tail",1,  "Is the tail like this ?",1, R.drawable.tail_a330));
            qController.addQuestion(new questionObj(6,"airbus", "320","","tail",1,  "Is the tail like this ?",1, R.drawable.tail_a320));
            qController.addQuestion(new questionObj(7,"boeing", "737","","nose",1,  "Is the nose like this ?",1, R.drawable.nose_b737));
            qController.addQuestion(new questionObj(8,"boeing", "747","","nose",1,  "Is the nose like this ?",1, R.drawable.nose_b747));
            qController.addQuestion(new questionObj(9,"boeing", "777","","nose",1,  "Is the nose like this ?",1, R.drawable.nose_b777));

            qController.addAircraft(new aircraftObj("airbus","300","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","310","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","318","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","319","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","320","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","321","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","330","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","340","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","350","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("airbus","380","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("boeing","737","",1, R.drawable.tail_a320));
            qController.addAircraft(new aircraftObj("boeing","777","",1, R.drawable.tail_a320));


    }
        // mControlsView = findViewById(R.id.fullscreen_content_controls);
       // mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
      /*  mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  toggle();
            }
        });*/

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //  findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
}


