package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Transactional
    @Override
    public void addListCar(List<Car> carList) {
        carDao.addListCar(carList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCar() {
        return carDao.listCar();
    }
}
