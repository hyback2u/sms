package com.wxl.sms.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author wxl on 2021/4/25 1:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Group {
    private Integer id;
    private String groupName;

    // 新增属性, 一个商品类别下有很多商品
    private List<Product> products;
}
