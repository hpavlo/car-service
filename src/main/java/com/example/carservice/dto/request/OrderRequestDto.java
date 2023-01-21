package com.example.carservice.dto.request;

import com.example.carservice.model.Order;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String description;
    private List<Long> serviceIds;
    private List<Long> goodsIds;
    private Order.OrderStatus status;
    private BigDecimal price;
}