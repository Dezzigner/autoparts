package dao;

import com.d1l.dao.OrderDetailDao;
import com.d1l.model.OrderDetail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class OrderDetailDaoTest {

    OrderDetail orderDetail;

    @Before
    public void setUp() throws Exception {

        orderDetail = new OrderDetail();
        orderDetail.setDetailId(1);
        orderDetail.setOrderId(1);
        orderDetail.setCount(2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteOrderDetail() throws Exception {
        OrderDetailDao.deleteOrderDetail(1);
    }

    @Test
    public void updateOrderDetail() throws Exception {
        orderDetail.setId(2);
        OrderDetailDao.addOrUpdateOrderDetail(orderDetail);
    }

    @Test
    public void addOrderDetail() throws Exception {
        OrderDetailDao.addOrUpdateOrderDetail(orderDetail);
    }

    @Test
    public void getOrderDetailTest() throws Exception {
        assertNotNull(OrderDetailDao.getOrderDetailsList());
    }
}