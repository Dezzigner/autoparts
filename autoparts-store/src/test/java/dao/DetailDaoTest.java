package dao;

import com.d1l.dao.CarDao;
import com.d1l.dao.DetailDao;
import com.d1l.dao.SupplierDao;
import com.d1l.dao.WarehouseDao;
import com.d1l.model.Detail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class DetailDaoTest {

    Detail detail;

    @Before
    public void setUp() throws Exception {

        detail = new Detail();
        detail.setName("sdad");
        detail.setSupplier(SupplierDao.getSupplierById(1));
        detail.setCountInWarehouse(20);
        detail.setCar(CarDao.getCarById(1));
        detail.setId(2);
        detail.setWarehouse(WarehouseDao.getWarehouseById(1));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteDetail() throws Exception {
        DetailDao.deleteDetail(2);
    }

    @Test
    public void updateDetail() throws Exception {
        detail.setId(2);
        DetailDao.addOrUpdateDetail(detail);
    }

    @Test
    public void addDetail() throws Exception {
        DetailDao.addOrUpdateDetail(detail);
    }

    @Test
    public void getDetailTest() throws Exception {
        assertNotNull(DetailDao.getDetailsList());
    }
}