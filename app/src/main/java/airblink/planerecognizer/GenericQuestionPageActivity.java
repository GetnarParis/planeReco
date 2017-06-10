package airblink.planerecognizer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Alexandre on 05/06/2017.
 */

public class GenericQuestionPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPicture();
        setContentView(R.layout.generic_question_page);
    }

    private void getPicture() {
        new DownloadImageTask().execute();
    }

    protected class DownloadImageTask extends AsyncTask<Void, Integer, Long> {
        Bitmap bitmap = null;
        protected Long doInBackground(Void... urls) {
            bitmap = DownloadImage("http://www.airbus-shop.com/modules/homeslider/images/763bfe3f0449161ff3786f8d465213d9d72a94f2_a350.png");

            return null;
        }

        protected void onPostExecute(Long result) {
            if(bitmap != null) {
                ImageView img = (ImageView) findViewById(R.id.question_image);
                img.setImageBitmap(bitmap);
            }
        }
    }

    private Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;
    }

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;

        int response;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK)
                in = httpConn.getInputStream();
           Log.d ("mydebug", "debug message");
        } catch (Exception ex) {
            throw new IOException("Error connecting");
        }
        return in;
    }
}
