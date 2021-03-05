package com.example.myapp.View.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.Controller.Helpers.Constant;
import com.example.myapp.Controller.Helpers.Validation;
import com.example.myapp.Controller.Services.UserService;
import com.example.myapp.Models.Entities.Exam;
import com.example.myapp.Models.Entities.User;
import com.example.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class ConcreteUserEdit extends AppCompatActivity {

    private DatabaseReference db;
    private UserService userService;
    private Validation validation;
    private Context context;
    private String id;
    private User user;
    private EditText _login, _password, _name, _subject;
    private TextView _count_exam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_concrete_user_edit);

        init();
        getIntentMain();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init() {
        context = getBaseContext();
        _login = findViewById(R.id.login);
        _password = findViewById(R.id.password);
        _name = findViewById(R.id.name);
        _subject = findViewById(R.id.subject);
        _count_exam = findViewById(R.id.count_exams);
    }

    public void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            user = (User)i.getSerializableExtra(Constant.USER);
            id = (String)i.getSerializableExtra(Constant.USER_ID);
            db = FirebaseDatabase.getInstance().getReference(Constant.USER_KEY).child(id);
        }

        _name.setText(user.name);
        _login.setText(user.login);
        _password.setText(user.password);
        _subject.setText(user.subject);
        if (user.exams == null)  _count_exam.setText("0");
        else  _count_exam.setText(user.exams.size());
    }

    //Кнопка для создания пользователя
    public void onClickSave(View view) {
        try{
            String login = _login.getText().toString();
            String password = _password.getText().toString();
            String name = _name.getText().toString();
            String subject = _subject.getText().toString();

            db.child("login").setValue(login);
            db.child("password").setValue(password);
            db.child("name").setValue(name);
            db.child("subject").setValue(subject);

            Toast.makeText(context, "Пользователь изменен", Toast.LENGTH_SHORT).show();

        }
        catch (Exception ex){
            Toast.makeText(context, "Ошибка при изменении", Toast.LENGTH_SHORT).show();
        }
    }

    //Кнопка "назад" приложении
    public void onClickReturn(View view) {
        try {
            Intent intent = new Intent(ConcreteUserEdit.this, ConcreteUser.class);
            intent.putExtra(Constant.USER, user);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(ConcreteUserEdit.this, ConcreteUser.class);
            intent.putExtra(Constant.USER, user);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }
}
