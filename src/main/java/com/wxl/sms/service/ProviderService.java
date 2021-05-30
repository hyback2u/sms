package com.wxl.sms.service;

import com.wxl.sms.bean.Product;
import com.wxl.sms.bean.Provider;

import java.util.List;

/**
 * @author wxl on 2021/4/29 0:26
 */
public interface ProviderService {

    /**
     * 获取所有供应商List
     *
     * @return List
     */
    List<Provider> getAllProviders();

    /**
     * 根据供应商id查询出该供应商供应的商品
     *      生产意义: 查看各个商品对应的销量来衡量一个供应商
     *
     * @param providerId 供应商id
     * @return 商品集合, 这里抽取部分字段进行展示
     */
    List<Product> getProviderRecordsByProviderId(Integer providerId);

    /**
     * 根据供应商id查询出该供应商实体
     *
     * @param providerId 供应商id
     * @return 供应商实体
     */
    Provider getProviderByProviderId(Integer providerId);

    /**
     * 根据供应商联系人电话号码查询出该供应商
     *
     * @param phoneNumber 供应商联系人电话号码
     * @return 供应商实体
     */
    List<Provider> getProviderByProviderPhoneNumber(String phoneNumber);

    /**
     * 根据供应商id删除供应商
     *
     * @param providerId 供应商id
     * @return 影响的行数
     */
    int deleteProviderByProviderId(Integer providerId);

    /**
     * 添加供应商
     *
     * @param provider 供应商实体类
     * @return 影响的行数
     */
    int addProvider(Provider provider);

    /**
     * 修改供应商
     *
     * @param provider 供应商实体类
     * @return 影响的行数
     */
    int updateProvider(Provider provider);
}
