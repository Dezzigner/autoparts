package dao;

import com.d1l.dao.*;
import com.d1l.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CarDaoTest {

    Car car;

    @Before
    public void setUp() throws Exception {

        car = new Car();
        car.setName("sdad");
        car.setReleaseYear(2000);
    }

    @After
    public void tearDown() throws Exception {
        
    }

    @Test
    public void deleteCar() throws Exception {
        CarDao.deleteCar(1);
    }

    @Test
    public void updateCar() throws Exception {
        car.setId(2);
        CarDao.addOrUpdateCar(car);
    }

    @Test
    public void addCar() throws Exception {
        CarDao.addOrUpdateCar(car);
    }

    @Test
    public void getCarTest() throws Exception {
        assertNotNull(CarDao.getCarsList());
    }
}