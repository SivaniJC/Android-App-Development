package com.example.counter_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Looper;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnstart,btnstop;
    TextView lblcounter;
    int counter=0;
    boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnstart=(Button) findViewById(R.id.btn_start);
        btnstop=(Button) findViewById(R.id.btn_stop);
        lblcounter=(TextView) findViewById(R.id.lbl_text);
        btnstop.setOnClickListener(this);
        btnstart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       if(v.equals(btnstart))
       {
           counter =0;
           running=true;
           new MyCounter().start();
       }
       else if(v.equals(btnstop))
       {
           running=false;
       }
    }
    Handler handler = new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(Message msg) {
            lblcounter.setText(String.valueOf(msg.what));
        }
    };
    class MyCounter extends Thread {
        public void run() {
            while (running) {
                counter++;
                handler.sendEmptyMessage(counter);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }
        }
    }
}