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
    void getDateMergedWithTimeECPValidInputReturns() {
        // arrange
        String time = "8:00";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();
        Date expectedDate = new GregorianCalendar(2023, Calendar.MARCH, 31, 8, 0).getTime();

        // act
        Date date = dateUtils.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Test
    void getDateMergedWithTimeECPInvalidHourMinuteAndDateThrows() {
        // arrange
        String time = "54:79";
        Date noTimeDate = new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateUtils.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void getDateMergedWithTimeECPInvalidTimeThrows() {
        // arrange
        String time = "asd";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateUtils.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void getDateMergedWithTimeBVAInvalidHourThrows1() {
        // arrange
        String time = "-1:15";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.APRIL, 4).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateUtils.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void getDateMergedWithTimeBVAValidReturns1() {
        // arrange
        String time = "0:0";
        Date noTimeDate = new GregorianCalendar(1970, Calendar.JANUARY, 1, 1, 1).getTime();
        Date expectedDate = new GregorianCalendar(1970, Calendar.JANUARY, 1, 0, 0).getTime();

        // act
        Date date = dateUtils.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Test
    void getDateMergedWithTimeBVAValidReturns2() {
        // arrange
        String time = "1:1";
        Date noTimeDate = new GregorianCalendar(1970, Calendar.JANUARY, 2).getTime();
        Date expectedDate = new GregorianCalendar(1970, Calendar.JANUARY, 2, 1, 1).getTime();

        // act
        Date date = dateUtils.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Test
    void getDateMergedWithTimeBVAInvalidHourThrows2() {
        // arrange
        String time = "24:12";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.APRIL, 4).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateUtils.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void getDateMergedWithTimeBVAInvalidMinutesThrows1() {
        // arrange
        String time = "18:60";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.APRIL, 4).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateUtils.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void getDateMergedWithTimeBVAValidReturns3() {
        // arrange
        String time = "23:59";

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(Long.MAX_VALUE));
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        Date noTimeDate = calendar.getTime();

        calendar.setTime(new Date(Long.MAX_VALUE));
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        Date expectedDate = calendar.getTime();

        // act
        Date date = dateUtils.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Test
    void getDateMergedWithTimeBVAInvalidMinutesThrows2() {
        // arrange
        String time = "19:-1";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.APRIL, 4).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateUtils.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void getDateMergedWithTimeBVAInvalidDateThrows() {
        // arrange
        String time = "02:12";
        Date noTimeDate = new GregorianCalendar(1969, Calendar.DECEMBER, 31).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateUtils.getDateMergedWithTime(time, noTimeDate));
    }

    @Test
    void getDateMergedWithTimeBVAValidReturns4() {
        // arrange
        String time = "19:35";

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(Long.MAX_VALUE));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date noTimeDate = calendar.getTime();

        calendar.setTime(new Date(Long.MAX_VALUE));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 35);
        Date expectedDate = calendar.getTime();

        // act
        Date date = dateUtils.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }
}