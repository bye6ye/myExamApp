package com.example.myapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp.R;

public class Ticket1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_tickets = findViewById(R.id.text_tickets);
        text_tickets.setText(R.string.ticket1);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Кнопка "назад" - начало
        Button btn_back = findViewById(R.id.button_back);
        //btn_back.setOnClickListener(v -> {
           // try {
           //     Intent intent = new Intent(Ticket1.this, Tickets.class);
           //     startActivity(intent);
           //     finish();
           // } catch (Exception ignored){ }
      //  });

    }

    //Системная кнопака "назад"
    @Override
    public void onBackPressed() {
//        try {
//            Intent intent = new Intent(Ticket1.this, Tickets.class);
//            startActivity(intent);
//            finish();
//        }
//        catch (Exception ignored){ }
    }
}