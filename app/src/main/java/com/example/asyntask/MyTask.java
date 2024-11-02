package com.example.asyntask;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;  // Import Log class
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyTask extends AsyncTask<Void, Integer, String> {
    @SuppressLint("StaticFieldLeak")
    Context context;
    @SuppressLint("StaticFieldLeak")
    TextView textView;
    @SuppressLint("StaticFieldLeak")
    Button button;
    ProgressDialog progressDialog;
    private static final String TAG = "MyTask";

    MyTask(Context context, TextView textView, Button button) {
        this.context = context;
        this.textView = textView;
        this.button = button;
    }

    @Override
    protected String doInBackground(Void... params) {
        int i = 0;
        synchronized (this) {
            while (i < 10) {
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    Log.e(TAG, "InterruptedException in doInBackground", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
        return "Download complete...";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in Progress...");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        button.setVisibility(View.GONE);  // Sembunyikan tombol setelah tugas selesai
        progressDialog.dismiss(); // Tutup dialog progress
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Download in Progress...");
    }
}