package com.example.myapp.View.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Controller.Helpers.Constant;
import com.example.myapp.Controller.Helpers.Validation;
import com.example.myapp.Controller.Services.UserService;
import com.example.myapp.Models.Entities.User;
import com.example.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static com.example.myapp.Controller.Helpers.Constant.USER_KEY;

public class CreateUser extends AppCompatActivity {

    private DatabaseReference db;
    private EditText _name, _subject, _login, _password;
    private Validation validation;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_create_user);

        init();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init() {
        Context context = getBaseContext();
        validation = new Validation(context);
        userService =  new UserService(context);
        validation =  new Validation(context);

        _name = findViewById(R.id.Name);
        _subject = findViewById(R.id.Subject);
        _login = findViewById(R.id.Login);
        _password = findViewById(R.id.Password);
    }

    //Кнопка для создания пользователя
    public void onClickCreate(View view) {
        try{
            String login = _login.getText().toString();
            String password = _password.getText().toString();
            String name = _name.getText().toString();
            String subject = _subject.getText().toString();

            User user = new User(login, password, name, subject);
            userService.add(user);

        }
        catch (Exception ex){
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //Кнопка "назад" приложении
    public void onClickReturn(View view) {
        //base.transition(R.id.button_back, CreateUser.this, MainAdmin.class);

        try {
            Intent intent = new Intent(CreateUser.this, MainAdmin.class);
            startActivity(intent);
            finish();
        }
        catch (Exception ignored){ }
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        //base.transition(CreateUser.this, MainAdmin.class);

        try {
            Intent intent = new Intent(CreateUser.this, MainAdmin.class);
            startActivity(intent);
            finish();
        }
        catch (Exception ignored){ }
    }
}
