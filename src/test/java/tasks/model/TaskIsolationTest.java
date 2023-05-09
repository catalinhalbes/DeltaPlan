package tasks.model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class TaskIsolationTest {

    @Test
    void testCreate_Task() {
        // arrange
        Date date = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();
        Task task = new Task("task1", date);

        // act

        // assert
        assertEquals(date, task.getTime() );
        assertEquals("task1", task.getTitle());
    }

    @Test
    void testSetTime_Task() {
        // arrange
        Date date = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();
        Task task = new Task("task1", date);

        // act

        task.setActive(true);

        // assert
        assertTrue(task.isActive());
        
    }

}