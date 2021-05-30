package com.wxl.sms.service.impl;

import com.wxl.sms.bean.Product;
import com.wxl.sms.bean.to.Check;
import com.wxl.sms.dao.ProductMapper;
import com.wxl.sms.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxl on 2021/4/24 23:37
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }

    @Override
    public Product getProductByProductName(String productName) {
        return productMapper.getProductByProductName(productName);
    }

    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public float getProductSalePriceByProductId(Integer id) {
        return productMapper.getProductSalePriceByProductId(id);
    }

    @Override
    public Check getProductByProductIdToCheckService(Integer productId) {
        Product product = productMapper.getProductByProductIdForCheckService(productId);
        Check check = new Check();
        check.setProductId(product.getId());
        check.setProductName(product.getProductName());
//        check.setCheckPrice(product.getSalePrice());
        check.setProductSalePrice(product.getSalePrice());

        return check;
    }

    @Override
    public int deleteProductByProductId(Integer productId) {
        return productMapper.deleteProductByProductId(productId);
    }

    @Override
    public int updateProductByProductId(Product product) {
        return productMapper.updateProductByProductId(product);
    }

    @Override
    public Product getProductByProductId(Integer productId) {
        return productMapper.getProductByProductId(productId);
    }

    @Override
    public List<Product> getProductByProductNameOrProductGroup(String productName, Integer groupId) {
        return productMapper.getProductByProductNameOrProductGroup(productName, groupId);
    }

    @Override
    @Deprecated
    public int updateProductStock(Integer productId, Integer saleCount) {
        return productMapper.updateProductStock(productId, saleCount);
    }

    @Override
    public int updateProductStockPlus(Integer productId, Integer saleCount, Integer stock) {
        return productMapper.updateProductStockPlus(productId, saleCount, stock);
    }

}
