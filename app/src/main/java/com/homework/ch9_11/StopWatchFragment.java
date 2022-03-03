package com.homework.ch9_11;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopWatchFragment extends Fragment implements View.OnClickListener {
    private int seconds=0;
    private boolean running;
    private boolean wasrunning;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        runTimer(layout);

        Button startBtn = layout.findViewById(R.id.start_btn);
        startBtn.setOnClickListener(this);
        Button stopBtn = layout.findViewById(R.id.stop_btn);
        stopBtn.setOnClickListener(this);
        Button resetBtn = layout.findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(this);

        return layout;
    }

    private void runTimer(View view) {
        TextView textView = view.findViewById(R.id.time_view);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int second = seconds%60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours,minutes,second);
                textView.setText(time);

                if(running){
                    seconds++;
                }

                handler.postDelayed(this,1000);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_btn:
                onTimerStart();
                break;
            case R.id.stop_btn:
                onTimerStop();
                break;
            case R.id.reset_btn:
                onTimerReset();
                break;
        }
    }

    private void onTimerStart() {
        running = true;
    }

    private void onTimerStop(){
        running = false;
        wasrunning = true;
    }

    private void onTimerReset(){
        running = false;
        seconds = 0;
    }

    public void onPause() {
        super.onPause();
        wasrunning = running;
        running = false;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (wasrunning) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasrunning",wasrunning);
    }
}