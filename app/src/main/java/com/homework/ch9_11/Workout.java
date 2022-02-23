package com.homework.ch9_11;

public class Workout {
    public String names,description;

    public static Workout[] workouts = {
            new Workout("ABC","aaaaa\nbbbbb\nccccc"),
            new Workout("DEF","ddddd\neeeee\nfffff"),
            new Workout("XYZ","xxxxx\nyyyyy\nzzzzz")
    };

    public Workout(String a, String b) {
        this.names = a;
        this.description = b;
    }

    public String getNames() {
        return names;
    }

    public String getDescription() {
        return description;
    }
}
