package com.example.myapp.Controller.Services;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapp.Models.Entities.User;
import com.example.myapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionException;

public class BaseService extends AppCompatActivity {

    private Context context;

    public BaseService() {
    }

    public void transition(int id, Context from, Class to){
        Button btnExam = findViewById(id);
        btnExam.setOnClickListener(v -> {
            transition(from, to);
        });
    }

    public void transition(Context from, Class to){
        try {
            Intent intent = new Intent(from, to);
            startActivity(intent);
            finish();
        }
        catch (Exception ignored){ }
    }
}
