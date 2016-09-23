package dao;

import com.d1l.dao.WarehouseDao;
import com.d1l.model.Warehouse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class WarehouseDaoTest {

    Warehouse warehouse;

    @Before
    public void setUp() throws Exception {

        warehouse = new Warehouse();
        warehouse.setName("sdad");
        warehouse.setAddress("dsadsadas, 2");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteWarehouse() throws Exception {
        WarehouseDao.deleteWarehouse(1);
    }

    @Test
    public void updateWarehouse() throws Exception {
        warehouse.setId(2);
        WarehouseDao.addOrUpdateWarehouse(warehouse);
    }

    @Test
    public void addWarehouse() throws Exception {
        WarehouseDao.addOrUpdateWarehouse(warehouse);
    }

    @Test
    public void getWarehouseTest() throws Exception {
        assertNotNull(WarehouseDao.getWarehousesList());
    }
}