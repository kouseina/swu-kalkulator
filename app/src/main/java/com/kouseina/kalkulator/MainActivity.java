package com.kouseina.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText angka1, angka2;
    Button tambah, kurang, kali, bagi;
    TextView hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        angka1 = (EditText) findViewById(R.id.angka1);
        angka2 = (EditText) findViewById(R.id.angka2);

        tambah = (Button) findViewById(R.id.tambah);
        kurang = (Button) findViewById(R.id.kurang);
        kali = (Button) findViewById(R.id.kali);
        bagi = (Button) findViewById(R.id.bagi);

        hasil = (TextView) findViewById(R.id.hasil);

        View.OnClickListener operasiClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (angka1.getText().length() > 0 && angka2.getText().length() > 0) {
                    double angka_pertama = Double.parseDouble(angka1.getText().toString());
                    double angka_kedua = Double.parseDouble(angka2.getText().toString());
                    double result = 0;

                    int id = view.getId();

                    if (id == R.id.tambah) {
                        result = angka_pertama + angka_kedua;
                    } else if (id == R.id.kurang) {
                        result = angka_pertama - angka_kedua;
                    } else if (id == R.id.kali) {
                        result = angka_pertama * angka_kedua;
                    } else if (id == R.id.bagi) {
                        if (angka_kedua != 0) {
                            result = angka_pertama / angka_kedua;
                        } else {
                            Toast.makeText(MainActivity.this, "Tidak bisa membagi dengan nol", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    hasil.setText(Double.toString(result));
                } else {
                    Toast.makeText(MainActivity.this, "Mohon masukkan angka pertama dan angka kedua", Toast.LENGTH_LONG).show();
                }
            }
        };

        tambah.setOnClickListener(operasiClickListener);
        kurang.setOnClickListener(operasiClickListener);
        kali.setOnClickListener(operasiClickListener);
        bagi.setOnClickListener(operasiClickListener);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}