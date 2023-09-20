package ru.sladkkov.seabatle.model;

import ru.sladkkov.seabatle.enums.HitStatus;

import java.util.Arrays;


public class Ship {
    private final int size;
    private final HitStatus[] shipHits;

    public Ship(int size) {
        this.size = size;
        this.shipHits = defaultHitStatusList();
    }

    private HitStatus[] defaultHitStatusList() {
        HitStatus[] hitStatuses = new HitStatus[size];
        Arrays.fill(hitStatuses, HitStatus.ALIVE);

        return hitStatuses;
    }

    public boolean isAlive() {
        for (HitStatus hit : shipHits) {
            if (hit.equals(HitStatus.ALIVE)) {
                return false;
            }
        }

        return true;
    }

}
