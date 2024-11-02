package com.example.asyntask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Button dan TextView
        button = findViewById(R.id.button);  // Pastikan ID sesuai dengan di XML
        textView = findViewById(R.id.textView);

        // Atur onClickListener setelah inisialisasi button
        if (button != null) {  // Pastikan button tidak null sebelum mengatur listener
            button.setOnClickListener(v -> {
                button.setEnabled(false); // Nonaktifkan tombol sementara
                new MyTask(MainActivity.this, textView, button).execute(); // Mulai tugas asinkron
            });
        } else {
            throw new NullPointerException("Button tidak ditemukan. Periksa ID di activity_main.xml");
        }
    }
}
