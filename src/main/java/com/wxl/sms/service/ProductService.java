package com.wxl.sms.service;

import com.wxl.sms.bean.Product;
import com.wxl.sms.bean.to.Check;

import java.util.List;

/**
 * @author wxl on 2021/4/24 23:37
 */
public interface ProductService {

    /**
     * 获取所有商品信息
     *
     * @return List集合
     */
    List<Product> getAllProducts();

    /**
     * 用于搜索显示, 根据商品名称查询后显示
     *
     * @param productName 商品名称
     * @return 单个Product实体
     */
    Product getProductByProductName(String productName);

    /**
     * 新增商品信息
     *
     * @param product product
     * @return 影响的行数
     */
    int addProduct(Product product);

    /**
     * 根据商品的编号获取到该商品的价格
     *
     * @param id 商品id
     * @return 商品的价格
     */
    float getProductSalePriceByProductId(Integer id);

    /**
     * 根据商品id查询商品的信息之后封装到Check的TO类中, 用于结算服务显示
     *
     * @param productId 商品id
     * @return TO -> Check
     */
    Check getProductByProductIdToCheckService(Integer productId);

    /**
     * 按照商品id删除商品
     *
     * @param productId 商品id
     * @return 影响的行数
     */
    int deleteProductByProductId(Integer productId);

    /**
     * 按照商品id修改商品
     *
     * @param product 表单数据封装成的Product对象
     * @return 影响的行数
     */
    int updateProductByProductId(Product product);

    /**
     * 根据商品id获取该商品对象
     *
     * @param productId 商品id
     * @return 商品对象
     */
    Product getProductByProductId(Integer productId);


    /**
     * 用于搜索显示, 根据商品名称查询后显示 ---> PLUS
     *      2021年4月30日01:44:36
     *
     * @param productName 商品名称
     * @param groupId 商品类别id
     * @return Product集合
     */
    List<Product> getProductByProductNameOrProductGroup(String productName, Integer groupId);

    /**
     * 更新id对应的商品库存
     *
     * @param productId 商品id
     * @param saleCount 售出数量
     * @return 影响行数
     */
    @Deprecated
    int updateProductStock(Integer productId, Integer saleCount);

    /**
     * 更新id对应的商品库存(PLUS)
     *
     * @param productId 商品id
     * @param saleCount 售出数量
     * @param stock 库存
     * @return 影响行数
     */
    int updateProductStockPlus(Integer productId,
                               Integer saleCount,
                               Integer stock);

}
