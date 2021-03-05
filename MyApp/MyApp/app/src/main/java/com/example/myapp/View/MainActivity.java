package com.example.myapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapp.Controller.Services.BaseService;
import com.example.myapp.R;

public class MainActivity extends AppCompatActivity{

    private long backPressedTime;
    private Toast backToast;
    private BaseService base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        base = new BaseService();
        onClickEntrance();
        onClickExam();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onClickEntrance() {
        //base.transition(R.id.buttonEntrance,MainActivity.this, Entrance.class);

        Button btnEntrance = findViewById(R.id.buttonEntrance);
        btnEntrance.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, Entrance.class);
                startActivity(intent);
                finish();
            }
            catch (Exception ignored){ }
        });
    }

    public void onClickExam() {
        //base.transition(R.id.buttonExam, MainActivity.this, Exam.class);

        Button btnExam = findViewById(R.id.buttonExam);
        btnExam.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, Exam.class);
                startActivity(intent);
                finish();
            }
            catch (Exception ignored){ }
        });
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed(){
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}