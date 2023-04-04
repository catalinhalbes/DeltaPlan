package tasks.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DateUtilsTest {
    private DateUtils dateUtils;
    private TasksUtils mockTaskUtils;
    @BeforeEach
    void setUp() {
        mockTaskUtils = mock(TasksUtils.class);
        dateUtils = new DateUtils(mockTaskUtils);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getDateMergedWithTimeValidInputReturns() {
        // arrange
        String time = "8:00";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();
        Date expectedDate = new GregorianCalendar(2023, Calendar.MARCH, 31, 8, 0).getTime();

        // act
        Date date = dateUtils.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }
}