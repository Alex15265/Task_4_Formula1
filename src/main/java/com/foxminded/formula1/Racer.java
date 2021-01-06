package com.foxminded.formula1;

import java.time.Duration;

public class Racer {

    private final String name;
    private final String team;
    private final Duration bestLapTime;

    public Racer(String name, String team, Duration bestLapTime) {
        this.name = name;
        this.team = team;
        this.bestLapTime = bestLapTime;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Duration getBestLapTime() {
        return bestLapTime;
    }

}
