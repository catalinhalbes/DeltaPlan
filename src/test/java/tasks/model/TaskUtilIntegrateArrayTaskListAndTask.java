package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.utils.TasksUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskUtilIntegrateArrayTaskListAndTask {
    private TasksUtils taskUtils;
    private ArrayTaskList arrayTaskList;
    private Date date;

    @BeforeEach
    public void setUp() {
        date = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();
        arrayTaskList = new ArrayTaskList();
        taskUtils = new TasksUtils(arrayTaskList);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void test_addTask_addsInRepo() {
        // arrange
        Task task = new Task("task1", date);

        // act
        int lenBeforeAdd = arrayTaskList.size();
        taskUtils.addTask(task);
        int lenAfterAdd = arrayTaskList.size();

        // assert
        assertEquals(1, lenAfterAdd - lenBeforeAdd);            // one task added
        assertEquals(task, arrayTaskList.getTask(lenAfterAdd - 1));     // task was added
    }

    @Test
    public void test_removeTask_removesFromRepo() {
        // arrange
        Task task = new Task("task1", date);
        arrayTaskList.add(task);
        arrayTaskList.add(new Task("task2", date));
        arrayTaskList.add(new Task("task3", date));

        // act
        int lenBeforeAdd = arrayTaskList.size();
        boolean result = taskUtils.removeTask(task);
        int lenAfterAdd = arrayTaskList.size();

        // assert
        assertTrue(result);
        assertEquals(1, lenBeforeAdd - lenAfterAdd);            // one task removed
        for (Task t: arrayTaskList.getAll()) {
            assertNotEquals(task, t);
        }
    }

    @Test
    public void test_removeTask_taskNotFound() {
        // arrange
        Task task = new Task("task1", date);
        arrayTaskList.add(new Task("task2", date));
        arrayTaskList.add(new Task("task3", date));

        // act
        int lenBeforeAdd = arrayTaskList.size();
        boolean result = taskUtils.removeTask(task);
        int lenAfterAdd = arrayTaskList.size();

        // assert
        assertFalse(result);
        assertEquals(0, lenBeforeAdd - lenAfterAdd);            // no task removed
        for (Task t: arrayTaskList.getAll()) {
            assertNotEquals(task, t);
        }
    }

    @Test
    public void test_removeTask_emptyRepo() {
        // arrange
        Task task = new Task("task1", date);

        // act
        boolean result = taskUtils.removeTask(task);

        // assert
        assertFalse(result);
        assertEquals(0, arrayTaskList.size());
    }
}