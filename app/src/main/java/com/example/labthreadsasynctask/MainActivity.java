package com.example.labthreadsasynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView labelEtat;
    private ProgressBar barreProgression;
    private ImageView imageAffichee;
    private Handler handlerUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labelEtat       = findViewById(R.id.labelEtat);
        barreProgression = findViewById(R.id.barreProgression);
        imageAffichee   = findViewById(R.id.imageAffichee);

        Button btnThread       = findViewById(R.id.btnThread);
        Button btnAsyncTask    = findViewById(R.id.btnAsyncTask);
        Button btnNotification = findViewById(R.id.btnNotification);

        handlerUI = new Handler(Looper.getMainLooper());

        btnNotification.setOnClickListener(v ->
                Toast.makeText(getApplicationContext(), "Interface toujours réactive !", Toast.LENGTH_SHORT).show()
        );

        btnThread.setOnClickListener(v -> chargerImageThread());

        btnAsyncTask.setOnClickListener(v -> new TacheCalculLourde().execute());
    }

    private void chargerImageThread() {

        barreProgression.setVisibility(View.VISIBLE);
        barreProgression.setProgress(0);
        labelEtat.setText("État : chargement en cours (Thread)...");

        new Thread(() -> {

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            handlerUI.post(() -> {
                imageAffichee.setImageBitmap(bmp);
                barreProgression.setVisibility(View.INVISIBLE);
                labelEtat.setText("État : image chargée avec succès (Thread)");
            });

        }).start();
    }

    private class TacheCalculLourde extends AsyncTask<Void, Integer, Long> {

        @Override
        protected void onPreExecute() {
            barreProgression.setVisibility(View.VISIBLE);
            barreProgression.setProgress(0);
            labelEtat.setText("État : calcul en cours (AsyncTask)...");
        }

        @Override
        protected Long doInBackground(Void... params) {
            long total = 0;

            for (int i = 1; i <= 100; i++) {
                for (int j = 0; j < 200000; j++) {
                    total += (i * j) % 7;
                }
                publishProgress(i);
            }

            return total;
        }

        @Override
        protected void onProgressUpdate(Integer... valeurs) {
            barreProgression.setProgress(valeurs[0]);
        }

        @Override
        protected void onPostExecute(Long resultat) {
            barreProgression.setVisibility(View.INVISIBLE);
            labelEtat.setText("État : calcul terminé — résultat = " + resultat);
        }
    }
}