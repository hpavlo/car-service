package com.example.carservice.controller;

import com.example.carservice.dto.mapper.RequestDtoMapper;
import com.example.carservice.dto.mapper.ResponseDtoMapper;
import com.example.carservice.dto.request.GoodsRequestDto;
import com.example.carservice.dto.request.OrderRequestDto;
import com.example.carservice.dto.response.OrderResponseDto;
import com.example.carservice.model.Goods;
import com.example.carservice.model.Order;
import com.example.carservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final RequestDtoMapper<OrderRequestDto, Order> requestDtoMapper;
    private final ResponseDtoMapper<OrderResponseDto, Order> responseDtoMapper;
    private final RequestDtoMapper<GoodsRequestDto, Goods> goodsRequestDtoMapper;

    public OrderController(OrderService orderService,
                           RequestDtoMapper<OrderRequestDto, Order> requestDtoMapper,
                           ResponseDtoMapper<OrderResponseDto, Order> responseDtoMapper,
                           RequestDtoMapper<GoodsRequestDto, Goods> goodsRequestDtoMapper) {
        this.orderService = orderService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.goodsRequestDtoMapper = goodsRequestDtoMapper;
    }

    @PostMapping
    @ApiOperation("Add a new order")
    public OrderResponseDto create(@RequestBody OrderRequestDto requestDto) {
        Order order = requestDtoMapper.mapToModel(requestDto);
        return responseDtoMapper.mapToDto(orderService.add(order));
    }

    @PostMapping("/{id}")
    @ApiOperation("Update order by id")
    public OrderResponseDto update(@PathVariable Long id,
                                   @RequestBody OrderRequestDto requestDto) {
        Order order = requestDtoMapper.mapToModel(requestDto);
        order.setId(id);
        return responseDtoMapper.mapToDto(orderService.update(order));
    }

    @PostMapping("/{id}/goods")
    @ApiOperation("Add a new goods to order by id")
    public OrderResponseDto addGoods(@PathVariable Long id,
                                     @RequestBody GoodsRequestDto goodsRequestDto) {
        Goods goods = goodsRequestDtoMapper.mapToModel(goodsRequestDto);
        return responseDtoMapper.mapToDto(orderService.addGoods(id, goods));
    }

    @GetMapping("/{id}/status")
    @ApiOperation("Update status by order id")
    public OrderResponseDto updateStatus(@PathVariable Long id,
                                         @ApiParam("Status name") @RequestParam String name) {
        Order.OrderStatus status = Order.OrderStatus.valueOf(name);
        return responseDtoMapper.mapToDto(orderService.updateStatus(id, status));
    }

    @GetMapping("/{id}/price")
    @ApiOperation("Calculate price by order id")
    public BigDecimal calculatePrice(@PathVariable Long id) {
        return orderService.calculatePrice(id);
    }
}