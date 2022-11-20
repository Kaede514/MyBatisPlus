package com.kaede.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaede.mybatisplus.mapper.ProductMapper;
import com.kaede.mybatisplus.pojo.Product;
import com.kaede.mybatisplus.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author kaede
 * @create 2022-10-03
 */

@DS("slave_1")
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
