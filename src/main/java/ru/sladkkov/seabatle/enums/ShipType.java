package ru.sladkkov.seabatle.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShipType {
    FOUR_DECK(1, 4),
    THIRD_DECK(2, 3),
    TWO_DECK(3, 2),
    SINGLE_DECK(4, 1);

    private final int countShip;
    private final int size;

}
