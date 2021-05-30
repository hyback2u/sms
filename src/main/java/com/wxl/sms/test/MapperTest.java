package com.wxl.sms.test;

import com.wxl.sms.bean.Product;
import com.wxl.sms.dao.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author wxl on 2021/4/24 22:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testProductMapper() {
        System.out.println("xxx");
        System.out.println(productMapper);

        List<Product> products = productMapper.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("Auto Compile... ...");
        System.out.println("Auto Compile... ...Success");
    }
}
