package com.homework.ch9_11;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {
    private long workoutID;

    public void setWorkoutID(long workoutID) {
        this.workoutID = workoutID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            StopWatchFragment stopwatch = new StopWatchFragment();
            FragmentTransaction ft = getChildFragmentManager().beginTransaction();
            ft.add(R.id.stopwatch_container,stopwatch);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            workoutID = savedInstanceState.getLong("workoutID");
        }
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        if (view != null) {
            Workout wo = Workout.workouts[(int) workoutID];

            TextView name = view.findViewById(R.id.name);
            TextView description = view.findViewById(R.id.description);

            name.setText(wo.getNames());
            description.setText(wo.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong("workoutID", workoutID);
    }
}