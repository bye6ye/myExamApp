package com.example.myapp.Controller.Services;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Controller.Helpers.Constant;
import com.example.myapp.Controller.Helpers.Validation;
import com.example.myapp.Models.Entities.User;
import com.example.myapp.View.Admin.MainAdmin;
import com.example.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserService extends AppCompatActivity {

    private DatabaseReference db;
    private Validation validation;
    private Context context;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> list;


    public UserService(Context _context){
        context = _context;
        validation = new Validation(context);
        db = FirebaseDatabase.getInstance(Constant.DB_LINK).getReference(Constant.USER_KEY);
    }

    public void list(ListView view, ArrayAdapter arrayAdapter){
        listView = view;
        adapter = arrayAdapter;
        list = new ArrayList<>();
        listView.setAdapter(adapter);
        db = FirebaseDatabase.getInstance(Constant.DB_LINK).getReference(Constant.USER_KEY);

        db.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 if (list.size() > 0) list.clear();

                 for(DataSnapshot ds : dataSnapshot.getChildren()) {
                     User user = ds.getValue(User.class);
                     assert user != null;
                     list.add(user.name);
                 }
                 adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void add(User user){
        if (validation.is_validate_user(user.name,user. subject, user.login, user.password)){
            db.push().setValue(user);
            validation.message("Пользователь добавлен");
        }
    }

    public void remove(Context context, String id){
        //User user = db.child(Constant.USER_KEY).
        //db.push().removeValue();
        //validation.message(getBaseContext(),"Пользователь добавлен");
    }
}
