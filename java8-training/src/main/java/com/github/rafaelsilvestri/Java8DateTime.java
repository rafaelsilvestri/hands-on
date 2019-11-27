package com.github.rafaelsilvestri;

import java.time.LocalDateTime;
import java.util.Objects;

public class Java8DateTime {

    /**
     * Return true if the first param is greater than the second one
     *
     * @param d1 datetime to be validated
     * @param d2 datetime to be compared
     * @return
     */
    public boolean isGreaterThan(LocalDateTime d1, LocalDateTime d2) {
        Objects.requireNonNull(d1, "d1 is required");
        Objects.requireNonNull(d2, "d2 is required");

        return d1.isAfter(d2);
    }


}
