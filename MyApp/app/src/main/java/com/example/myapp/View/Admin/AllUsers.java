package com.example.myapp.View.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Controller.Helpers.Constant;
import com.example.myapp.Controller.Helpers.Validation;
import com.example.myapp.Controller.Services.BaseService;
import com.example.myapp.Controller.Services.UserService;
import com.example.myapp.Models.Entities.Exam;
import com.example.myapp.Models.Entities.User;
import com.example.myapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllUsers extends AppCompatActivity {

    private DatabaseReference db;
    private UserService userService;
    private Validation validation;
    private BaseService baseService;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    private List<User> concrete;
    private Exception ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_all_users);

        init();
        getUsers();
        setOnClickItem();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init() {
        Context context = getBaseContext();
        userService = new UserService(context);
        validation = new Validation(context);
        baseService = new BaseService();

        listView = findViewById(R.id.listView);
        list = new ArrayList<>();
        concrete = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        db = FirebaseDatabase.getInstance().getReference(Constant.USER_KEY);
    }

    private void getUsers(){

        ValueEventListener valueListener =  new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if (list.size() > 0) list.clear();
                 if (concrete.size() > 0) list.clear();

                 for(DataSnapshot ds : dataSnapshot.getChildren()) {
                     User user = ds.getValue(User.class);
                     assert user != null;
                     list.add(user.name);
                     concrete.add(user);
                 }
                 adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        };

        db.addValueEventListener(valueListener);
    }


    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    User user = concrete.get(i);
                    Intent intent = new Intent(AllUsers.this, ConcreteUser.class);
                    intent.putExtra(Constant.USER_NAME, user.name);
                    intent.putExtra(Constant.USER_SUBJECT, user.subject);
                    intent.putExtra(Constant.USER_LOGIN, user.login);
                    intent.putExtra(Constant.USER_PASSWORD, user.password);
                    List<Exam> exams = user.exams;
                    if (exams == null)
                        intent.putExtra(Constant.USER_COUNT_EXAMS, "0");
                    else
                        intent.putExtra(Constant.USER_COUNT_EXAMS, user.exams.size());

                    startActivity(intent);
                }
                catch (Exception _ex){
                    ex = _ex;
                }
            }
        });
    }


    //Кнопка "назад" приложении
    public void onClickReturn(View view) {
       // base.transition(R.id.button_back, AllUsers.this, MainAdmin.class);
        try {
            Intent intent = new Intent(AllUsers.this, MainAdmin.class);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        //base.transition(AllUsers.this, MainAdmin.class);
        try {
            Intent intent = new Intent(AllUsers.this, MainAdmin.class);
            startActivity(intent);
        }
        catch (Exception ignored){ }
    }
}
