package com.example.myapp.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Controller.Services.BaseService;
import com.example.myapp.R;
import com.example.myapp.View.Entrance;

public class MainAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);

        onClickBack();
        onClickCreateUser();
        onClickAllUsers();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //Кнопка "назад" приложении
    public void onClickBack() {
        //base.transition(R.id.button_back, MainAdmin.this, Entrance.class);

        Button btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainAdmin.this, Entrance.class);
                startActivity(intent);
                finish();
            }
            catch (Exception ignored){ }
        });
    }

    public void onClickCreateUser() {
        //base.transition(R.id.button_createUser, MainAdmin.this, CreateUser.class);

        Button btnCreateUser = findViewById(R.id.button_createUser);
        btnCreateUser.setOnClickListener(v -> {
            try {
                Intent intent = new Intent( MainAdmin.this, CreateUser.class);
                startActivity(intent);
                finish();
            }
            catch (Exception ignored){ }
        });
    }

    public void onClickAllUsers() {
        //base.transition(R.id.button_allUsers, MainAdmin.this, AllUsers.class);

        Button btnAllUsers = findViewById(R.id.button_allUsers);
        btnAllUsers.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainAdmin.this, AllUsers.class);
                startActivity(intent);
                finish();
            }
            catch (Exception ignored){ }
        });
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        //base.transition(MainAdmin.this, Entrance.class);

        try {
            Intent intent = new Intent(MainAdmin.this, Entrance.class);
            startActivity(intent);
            finish();
        }
        catch (Exception ignored){ }
    }

}
