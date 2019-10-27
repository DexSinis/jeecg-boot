package org.jeecg.modules.qasystem.service;

import org.jeecg.modules.qasystem.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();

    Car getCarById(long id);

    Car addCar(Car car);
}
