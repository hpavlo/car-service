package com.example.carservice.dto.mapper.impl;

import com.example.carservice.dto.mapper.RequestDtoMapper;
import com.example.carservice.dto.mapper.ResponseDtoMapper;
import com.example.carservice.dto.request.CarRequestDto;
import com.example.carservice.dto.response.CarResponseDto;
import com.example.carservice.model.Car;
import com.example.carservice.repository.OwnerRepository;
import org.springframework.stereotype.Component;

@Component
public class CarMapper implements RequestDtoMapper<CarRequestDto, Car>,
        ResponseDtoMapper<CarResponseDto, Car> {
    private final OwnerRepository ownerRepository;

    public CarMapper(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Car mapToModel(CarRequestDto dto) {
        Car car = new Car();
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setYear(dto.getYear());
        car.setNumber(dto.getNumber());
        return car;
    }

    @Override
    public CarResponseDto mapToDto(Car car) {
        CarResponseDto dto = new CarResponseDto();
        dto.setId(car.getId());
        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setYear(car.getYear());
        dto.setNumber(car.getNumber());
        return dto;
    }
}
