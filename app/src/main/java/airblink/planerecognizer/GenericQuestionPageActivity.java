package airblink.planerecognizer;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;



/**
 * Created by Alexandre on 05/06/2017.
 */

public class GenericQuestionPageActivity extends AppCompatActivity {
   // private questionModel myQuestionModel = new questionModel (GenericQuestionPageActivity.this);
private questionObj qObj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_question_page);
        final Button yesButton = (Button) findViewById(R.id.buttonTrue);
        final Button noButton = (Button) findViewById(R.id.buttonFalse);
        final Button dunnoButton = (Button) findViewById(R.id.buttonDunno);
        final Button goBackButton = (Button) findViewById(R.id.buttonGoBackToStart);
        yesButton.setOnClickListener(new View.OnClickListener() {

                                         @Override
                                         public void onClick(View v) {
                                             StartPageActivity.qController.updateTablesWeight();
                                             Intent intent = getIntent();
                                             finish();
                                             startActivity(intent);
                                         }


                                     });
        noButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }


        });
        dunnoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }


        });

        goBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GenericQuestionPageActivity.this, StartPageActivity.class);
              //  StartPageActivity.qController.resetWeightOfTables();
                StartPageActivity.qController.resetDb();
                finish();
                startActivity(intent);
            }


        });
        qObj = StartPageActivity.qController.select();
        Bitmap b = BitmapFactory.decodeResource(getResources(), qObj.getAssociatedDrawable());
        ImageView view = (ImageView) findViewById(R.id.question_image);
        TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        textViewQuestion.setText(qObj.getQuestionString());
        view.setImageBitmap(b);
    }



}
