package com.example.myapp.View.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Controller.Helpers.Constant;
import com.example.myapp.Controller.Helpers.Validation;
import com.example.myapp.Controller.Services.BaseService;
import com.example.myapp.Controller.Services.UserService;
import com.example.myapp.Models.Entities.Exam;
import com.example.myapp.Models.Entities.User;
import com.example.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConcreteUser extends AppCompatActivity {

    private DatabaseReference db;
    private UserService userService;
    private Validation validation;
    private Context context;
    private String id;
    private User user;
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
        context = getBaseContext();
        _login = findViewById(R.id.login);
        _password = findViewById(R.id.password);
        _name= findViewById(R.id.name);
        _subject = findViewById(R.id.subject);
        _count_exam = findViewById(R.id.count_exams);
        db = FirebaseDatabase.getInstance().getReference(Constant.USER_KEY);
    }

    public void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            id = i.getStringExtra(Constant.USER_ID);
            user = (User)i.getSerializableExtra(Constant.USER);

            _name.setText(user.name);
            _subject.setText(user.subject);
            _login.setText(user.login);
            _password.setText(user.password);
            if (user.exams == null)  _count_exam.setText("0");
            else  _count_exam.setText(user.exams.size());
        }
    }

    public void onClickDelete(View view) {
        db.child(id).removeValue();
        Toast.makeText(context, "Пользователь удалён", Toast.LENGTH_SHORT).show();
        try {
            Intent intent = new Intent(ConcreteUser.this, AllUsers.class);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }

    public void onClickEdit(View view) {
        try {
            Intent intent = new Intent(ConcreteUser.this, ConcreteUserEdit.class);
            intent.putExtra(Constant.USER, user);
            intent.putExtra(Constant.USER_ID, id);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }

    //Кнопка "назад" приложении
    public void onClickReturn(View view) {
        try {
            Intent intent = new Intent(ConcreteUser.this, AllUsers.class);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(ConcreteUser.this, AllUsers.class);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }
}
