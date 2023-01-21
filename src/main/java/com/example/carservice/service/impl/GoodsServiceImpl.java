package com.example.carservice.service.impl;

import com.example.carservice.model.Goods;
import com.example.carservice.repository.GoodsRepository;
import com.example.carservice.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;

    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Override
    public Goods add(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public Goods update(Goods goods) {
        return goodsRepository.save(goods);
    }
}
