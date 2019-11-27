package com.github.rafaelsilvestri;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Java8DateTimeTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldBeTrueWhenDateTwoIsGreaterThanDateOne() {
        LocalDateTime dtOne = LocalDateTime.of(2019, 11, 25, 13, 30);
        LocalDateTime dtTwo = LocalDateTime.of(2019, 11, 26, 13, 30);

        // act
        boolean isGreater = new Java8DateTime().isGreaterThan(dtTwo, dtOne);

        assertTrue("'dtTwo' should be greater than 'dtOne'", isGreater);
    }

    @Test
    public void shouldBeFalseWhenDateTwoIsLowerThanDateOne() {
        LocalDateTime dtOne = LocalDateTime.of(2019, 11, 26, 13, 30);
        LocalDateTime dtTwo = LocalDateTime.of(2019, 11, 25, 13, 30);

        // act
        boolean isGreater = new Java8DateTime().isGreaterThan(dtTwo, dtOne);

        assertFalse("'dtTwo' should be lower than 'dtOne'", isGreater);
    }

    @Test
    public void shouldReturnFalseIfDateTwoIsNull() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("d1 is required");

        LocalDateTime dtOne = LocalDateTime.of(2019, 11, 26, 13, 30);
        LocalDateTime dtTwo = null;

        Java8DateTime instance = new Java8DateTime();
        instance.isGreaterThan(dtTwo, dtOne);
    }

}
