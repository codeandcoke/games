package com.svalero.games;

import com.svalero.games.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTests {

    @Test
    public void testGetDaysBetweenDates() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 2, 1);

        long actualDays = DateUtil.getDaysBetweenDates(startDate, endDate);
        assertEquals(31, actualDays);

        startDate = LocalDate.of(2025, 1, 15);
        endDate = LocalDate.of(2025, 1, 20);
        actualDays = DateUtil.getDaysBetweenDates(startDate, endDate);
        assertEquals(5, actualDays);
    }
}
