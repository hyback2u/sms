package com.wxl.sms.service.impl;

import com.wxl.sms.bean.Product;
import com.wxl.sms.bean.to.Sale;
import com.wxl.sms.dao.ProductMapper;
import com.wxl.sms.dao.SaleMapper;
import com.wxl.sms.service.SaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxl on 2021/5/2 1:22
 */
@Service
public class SaleServiceImpl implements SaleService {
    @Resource
    private SaleMapper saleMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public int insertSaleItem(Integer productId, Integer saleCount) {
        Product product = productMapper.getProductByProductId(productId);

        String productName = product.getProductName();
        Integer groupId = product.getGroupId();
        float purchasePrice = product.getPurchasePrice();
        float salePrice = product.getSalePrice();
        // saleVolume - 销售额
        float saleVolume = saleCount * purchasePrice;
        // netProfit - 净利润
        float netProfit = (salePrice - purchasePrice) * saleCount;

        return saleMapper.insertSaleItemFinal(productId, productName, groupId, purchasePrice, salePrice,
                saleCount, saleVolume, netProfit);
    }

    @Override
    public List<Sale> getSaleInfoByCondition(Integer groupId, String startDate, String endDate) {
        return saleMapper.getSaleInfoByCondition(groupId, startDate, endDate);
    }
}
