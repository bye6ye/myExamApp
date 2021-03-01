package com.example.myapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Controller.Services.BaseService;
import com.example.myapp.Controller.Helpers.Validation;
import com.example.myapp.R;
import com.example.myapp.View.Admin.MainAdmin;

public class Entrance extends AppCompatActivity {

    private BaseService base;
    private Validation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance);

        onClickBack();
        onClickEntrance();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init(){
        Context context = getBaseContext();
        base = new BaseService();
        validation = new Validation(context);
    }

    //Кнопка "назад" приложении
    public void onClickBack() {
        //base.transition(R.id.buttonBack, Entrance.this, MainActivity.class);

        Button btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(Entrance.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            catch (Exception ignored){ }
        });
    }

    //Кнопка для входа
    public void onClickEntrance() {
        Button btn_comeIn = findViewById(R.id.buttonComeIn);
        btn_comeIn.setOnClickListener(v -> {
            try {
                EditText login = findViewById(R.id.Login);
                EditText password = findViewById(R.id.Password);
                if (login.getText().toString().equals("admin") && password.getText().toString().equals("12345")) {
                    try {
                        Intent intent = new Intent(Entrance.this, MainAdmin.class);
                        startActivity(intent);
                        finish();
                    }
                    catch (Exception ignored){ }
                }
                else {
                    validation.message("Такого пользователя не существует");
                }
            }
            catch (Exception ignored){ }
        });
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        //base.transition(Entrance.this, MainActivity.class);

        try {
            Intent intent = new Intent(Entrance.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        catch (Exception ignored){ }
    }
}
