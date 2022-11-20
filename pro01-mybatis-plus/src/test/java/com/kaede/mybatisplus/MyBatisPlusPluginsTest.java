package com.kaede.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaede.mybatisplus.mapper.ProductMapper;
import com.kaede.mybatisplus.mapper.UserMapper;
import com.kaede.mybatisplus.pojo.Product;
import com.kaede.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kaede
 * @create 2022-10-03
 */

@SpringBootTest
public class MyBatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    void testPage() {
        //SELECT COUNT(*) AS total FROM t_user WHERE is_deleted = 0
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 LIMIT ?,?
        Page<User> page = new Page<>(2,3);
        userMapper.selectPage(page, null);
        System.out.println(page);
        System.out.println("page.getRecords() = " + page.getRecords());
        //总页数
        System.out.println("page.getPages() = " + page.getPages());
        //总记录数
        System.out.println("page.getTotal() = " + page.getTotal());
        //是否有上一页
        System.out.println("page.hasPrevious() = " + page.hasPrevious());
        //是否有下一页
        System.out.println("page.hasNext() = " + page.hasNext());
    }

    @Test
    void testPageVo() {
        Page<User> page = new Page<>(2,3);
        userMapper.selectPageVo(page,24);
        System.out.println("page.getRecords() = " + page.getRecords());
        //总页数
        System.out.println("page.getPages() = " + page.getPages());
        //总记录数
        System.out.println("page.getTotal() = " + page.getTotal());
        //是否有上一页
        System.out.println("page.hasPrevious() = " + page.hasPrevious());
        //是否有下一页
        System.out.println("page.hasNext() = " + page.hasNext());
    }

    @Test
    void testProduct01() {
        //小李查询商品价格
        Product product1 = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + product1.getPrice());
        //小王查询商品价格
        Product product2 = productMapper.selectById(1);
        //UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
        System.out.println("小王查询的商品价格：" + product2.getPrice());

        //小李将商品价格加50
        product1.setPrice(product1.getPrice() + 50);
        productMapper.updateById(product1);
        //小王将商品价格减30
        product2.setPrice(product2.getPrice() - 30);
        //UPDATE t_product SET name=?, price=?, version=? WHERE id=? AND version=?
        int result = productMapper.updateById(product2);
        if(result == 0) {
            //操作失败，重试
            product2 = productMapper.selectById(1);
            product2.setPrice(product2.getPrice() - 30);
            productMapper.updateById(product2);
        }

        //老板查询商品价格
        Product product3 = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + product3.getPrice());
    }

}
