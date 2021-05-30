package com.wxl.sms.dao;

import com.wxl.sms.bean.to.Sale;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售表Mapper
 *
 * @author wxl on 2021/5/2 1:10
 */
public interface SaleMapper {

    /**
     * 增加一条销售记录(事务操作)
     *
     * @return 影响的行数
     */
    int insertSaleItemFinal(@Param("productId") Integer productId,
                            @Param("productName") String productName,
                            @Param("groupId") Integer groupId,
                            @Param("purchasePrice") float purchasePrice,
                            @Param("salePrice") float salePrice,
                            @Param("saleCount") Integer saleCount,
                            @Param("saleVolume") float saleVolume,
                            @Param("netProfit") float netProfit);

    
    /**
     * 用于根据条件展示销售信息
     *
     * @param groupId 商品分类信息
     * @param startDate 搜索开始时间
     * @param endDate 搜索结束时间
     * @return TO集合
     */
    List<Sale> getSaleInfoByCondition(@Param("groupId") Integer groupId,
                                      @Param("startDate") String startDate,
                                      @Param("endDate") String endDate);
}
