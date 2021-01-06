package com.foxminded.formula1;

import java.util.Comparator;

public class BestLapComparator implements Comparator<Racer> {
    public int compare(Racer r1, Racer r2) {
        return Long.compare(r1.getBestLapTime().toMillis(), r2.getBestLapTime().toMillis());
    }
}
