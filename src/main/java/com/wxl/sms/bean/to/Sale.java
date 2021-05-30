package com.wxl.sms.bean.to;

import com.wxl.sms.bean.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * AUTHORNB!
 * 用于销售统计页面的TO
 * 背景: 销售页面需要计算销售统计, 因此SQL聚合函数使用的较多, 因此, 不便于展示某一商品的单个详细信息
 *       因此封装Sale对象, 使用Sale对象里封装单个Product对象, 用于单个商品的展示
 *
 * @author wxl on 2021/5/4 23:48
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    // 商品id
    private Integer productId;
    // 销售量
    private float saleCount;
    // 销售净利润
    private float netProfit;

    // 用于展示单个商品信息的Product
    private Product product;
}
