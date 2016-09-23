package controller;

import com.d1l.controller.adminpanel.WarehousesController;
import com.d1l.model.Warehouse;
import com.opensymphony.xwork2.Action;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class WarehouseControllerTest {
    private WarehousesController controller;
    private Warehouse warehouse;

    private int id = 2;
    private String name = "bla";
    private String address = "dadasdadsadas 4";

    @Before
    public void setUp() throws Exception {

        warehouse = new Warehouse();
        warehouse.setName(name);
        warehouse.setAddress(address);

        controller = new WarehousesController();
        controller.setWarehouse(warehouse);
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