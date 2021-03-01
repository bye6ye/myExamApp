package com.example.myapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Controller.Services.BaseService;
import com.example.myapp.R;
import com.example.myapp.View.Admin.AllUsers;
import com.example.myapp.View.Admin.MainAdmin;

public class Exam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam);

        onClickBack();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //Кнопка "назад" приложении
    public void onClickBack() {

        Button btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Exam.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            catch (Exception ignored){ }
        });
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {

        try {
            Intent intent = new Intent(Exam.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception ignored){ }
    }
}
