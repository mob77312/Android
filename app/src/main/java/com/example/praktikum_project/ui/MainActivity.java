package com.example.praktikum_project.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.praktikum_project.R;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // inisialisasi key pada data yang akan dikirim lewat intent
    public static final String EXTRA_USERNAME = "USERNAME";

    // deklarasi variabel view sesuai dengan layout xml
    private TextInputEditText inputUsername, inputPassword;
    private Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // mengganti tulisan di action bar (paling atas)
        getSupportActionBar().setTitle("Login");

        // koneksikan variabel dengan view berdasarkan id
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);

        // inisialisasi fungsi klik pada tombol
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        // identifikasi tombol mana yang diklik berdasarkan id
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_signup:
                signup();
                break;
        }
    }

    private void signup() {
        // menampilkan notif
        Toast.makeText(this, "you have been registered", Toast.LENGTH_SHORT).show();
    }

    private void login() {
        // mengambil nilai string pada text input di view
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        // memeriksa apakah inputnya kosong atau tidak
        // jika kosong diberikan error "username couldn't be empty"
        if (username.isEmpty()) inputUsername.setError("username couldn't be empty");
        else if (password.isEmpty()) inputPassword.setError("password couldn't be empty");
        else {
            // melakukan pemeriksaan login
            if (password.equals("admin123")) {
                // deklarasi intent untuk pindah halaman ke HomeActivity
                Intent intent = new Intent(this, HomeActivity.class);
                // memasukkan data yang akan dikirimkan dengan nama kunci EXTRA_USERNAME
                intent.putExtra(EXTRA_USERNAME, username);
                // memulai pindah halaman
                startActivity(intent);
                // mengakhiri halaman agar tidak dapat kembali ke halaman ini (MainActivity)
                finish();

                Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

