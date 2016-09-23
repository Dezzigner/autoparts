package controller;

import com.d1l.controller.adminpanel.CarsController;
import com.d1l.model.Car;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CarsControllerTest {
    private CarsController controller;
    private Car car;

    private int id = 2;
    private String name = "bla";

    @Before
    public void setUp() throws Exception {

        car = new Car();
        car.setName(name);
        car.setReleaseYear(2000);

        controller = new CarsController();
        controller.setCar(car);
        controller.setId(id);
    }

    @After
    public void tearDown() throws Exception {
        controller.delete();
    }

    @Test
    public void execute() throws Exception {
        assertEquals(Action.SUCCESS,controller.execute());
    }

    @Test
    public void add() throws Exception {
        assertEquals(Action.SUCCESS,controller.add());
    }

    @Test
    public void update() throws Exception {
        assertEquals(Action.SUCCESS,controller.update());
    }

    @Test
    public void delete() throws Exception {
        assertEquals(Action.SUCCESS,controller.delete());
    }
}