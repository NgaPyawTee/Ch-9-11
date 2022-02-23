package com.homework.ch9_11;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void ItemClicked(long id) {
        FrameLayout frameLayout = findViewById(R.id.workout_detail_frag);
        if(frameLayout !=null){
            WorkoutDetailFragment workoutDetailFragment = new WorkoutDetailFragment();
            workoutDetailFragment.setWorkoutID(id);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.workout_detail_frag,workoutDetailFragment);
            fragmentTransaction.commit();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        }else{
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID,(int) id);
            startActivity(intent);
        }
    }
}