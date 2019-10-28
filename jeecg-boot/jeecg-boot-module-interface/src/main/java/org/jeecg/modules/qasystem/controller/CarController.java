//package org.jeecg.modules.qasystem.controller;
//
//import org.jeecg.modules.qasystem.entity.Car;
//import org.jeecg.modules.qasystem.service.CarService;
//import io.swagger.annotations.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("car")
//public class CarController {
//    @Autowired
//    CarService carService;
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    @ResponseBody
//    @ApiOperation(value = "查询所有汽车", notes = "无需添加参数", code = 200, produces = "application/json")
//    public List<Car> getCars() {
//        return carService.getAllCars();
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    @ApiOperation(value = "根据汽车id查询汽车", notes = "根据车辆编号查询", code = 200, produces = "application/json")
//    public Car getCar(@ApiParam(name = "id", value = "编号", required = true) @PathVariable("id") int id) {
//        return carService.getCarById((long) id);
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation(value = "新增车辆", notes = "需要添加名字和价格", code = 200, produces = "application/json")
//    public Car addCar(@ModelAttribute Car car) {
//        return carService.addCar(car);
//    }
//
//
//    @RequestMapping(value = "/newCat", method = RequestMethod.POST)
//    @ResponseBody
//    @ApiOperation(value = "新增车辆", notes = "需要添加名字和价格", code = 200, produces = "application/json")
//    public Car newCat(@PathVariable("id") int id) {
//        return new Car();
//    }
//
//
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    @ApiOperation(value = "更新车辆", notes = "更新车名或者价格", code = 200, produces = "application/json")
//    public Car addCar(@PathVariable("id") int id, @ModelAttribute Car car) {
//        car.setId((long)id);
//        return carService.addCar(car);
//    }
//}