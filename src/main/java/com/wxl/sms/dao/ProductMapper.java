package com.wxl.sms.dao;

import com.wxl.sms.bean.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wxl on 2021/4/24 22:24
 */
public interface ProductMapper {

    /**
     * 查询所有商品信息并返回集合封装数据, 用于直接查询返回所有的商品信息
     * @return List
     */
    List<Product> getAllProducts();


    /**
     * 用于搜索显示, 根据商品名称查询后显示
     * @param productName 商品名称
     * @return 单个Product实体
     */
    Product getProductByProductName(String productName);


    /**
     * 用于搜索显示, 根据商品名称查询后显示 ---> PLUS
     *
     * @param productName 商品名称
     * @param groupId 商品类别id
     * @return Product集合
     */
    List<Product> getProductByProductNameOrProductGroup(@Param("productName") String productName,
                                                        @Param("groupId") Integer groupId);


//    List<Product> getProductsByProductGroup();

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
     * 根据商品的编号获取到该商品(用于结算服务)
     *
     * @param productId 商品的编号
     * @return Product
     */
    Product getProductByProductIdForCheckService(Integer productId);

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
     * 更新id对应的商品库存
     *
     * @param productId 商品id
     * @param saleCount 售出数量
     * @return 影响行数
     */
    int updateProductStock(@Param("productId") Integer productId, @Param("saleCount") Integer saleCount);

    /**
     * 更新id对应的商品库存(PLUS)
     *
     * @param productId 商品id
     * @param saleCount 售出数量
     * @param stock 库存
     * @return 影响行数
     */
    int updateProductStockPlus(@Param("productId") Integer productId,
                               @Param("saleCount") Integer saleCount,
                               @Param("stock") Integer stock);
}
