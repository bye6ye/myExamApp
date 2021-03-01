package com.example.myapp.View.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Controller.Helpers.Constant;
import com.example.myapp.Controller.Helpers.Validation;
import com.example.myapp.Controller.Services.BaseService;
import com.example.myapp.Controller.Services.UserService;
import com.example.myapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ConcreteUser extends AppCompatActivity {

    private DatabaseReference db;
    private UserService userService;
    private Validation validation;

    private TextView _login, _password, _name, _subject, _count_exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_concrete_user);

        init();
        getIntentMain();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init() {
        //Context context = getBaseContext();
        _login = findViewById(R.id.login);
        _password = findViewById(R.id.password);
        _name= findViewById(R.id.name);
        _subject = findViewById(R.id.subject);
        _count_exam = findViewById(R.id.count_exams);

        //db = FirebaseDatabase.getInstance().getReference(Constant.USER_KEY);
    }

    public void getIntentMain() {
        Intent i = getIntent();
        if (i != null){
            _name.setText(i.getStringExtra(Constant.USER_NAME));
            _subject.setText(i.getStringExtra(Constant.USER_SUBJECT));
            _login.setText(i.getStringExtra(Constant.USER_LOGIN));
            _password.setText(i.getStringExtra(Constant.USER_PASSWORD));
            _count_exam.setText(i.getStringExtra(Constant.USER_COUNT_EXAMS));
        }
    }

    //Кнопка "назад" приложении
    public void onClickReturn(View view) {
        // base.transition(R.id.button_back, AllUsers.this, MainAdmin.class);
        try {
            Intent intent = new Intent(ConcreteUser.this, AllUsers.class);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        //base.transition(AllUsers.this, MainAdmin.class);
        try {
            Intent intent = new Intent(ConcreteUser.this, AllUsers.class);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }
}
