package com.wxl.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxl.sms.bean.Product;
import com.wxl.sms.bean.Provider;
import com.wxl.sms.service.ProviderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author wxl on 2021/4/29 0:28
 */
@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Resource
    private ProviderService providerService;

    /**
     * 查询所有供应商信息
     *
     * @param pn 传入要查询的页码(要查询第几页的数据)
     * @param model 为了将数据传入到cookie域中方便取出数据
     * @return 返回provider.jsp页面
     */
    @RequestMapping("/providerList")
    public String getAllProviders(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                  Model model) {
        PageHelper.startPage(pn, 5);
        List<Provider> providers = providerService.getAllProviders();
        PageInfo<Provider> pageInfo = new PageInfo<>(providers, 5);
        model.addAttribute("pageInfo", pageInfo);

        return "provider/provider";
    }


    /**
     * 根据供应商id查询出该供应商所供应的所有商品列表集合
     *
     * @param pn 展示页码
     * @param providerId 供应商id
     * @param model 存储转发查询得到的数据
     * @return records.jsp记录页面
     */
    @RequestMapping("/provideProducts")
    public String showProductBySelectedProvider(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                                @RequestParam("providerId") Integer providerId,
                                                Model model) {
        PageHelper.startPage(pn, 5);
        List<Product> records = providerService.getProviderRecordsByProviderId(providerId);
        PageInfo<Product> pageInfo = new PageInfo<>(records, 5);
        model.addAttribute("pageInfo", pageInfo);

        Provider provider = providerService.getProviderByProviderId(providerId);
        model.addAttribute("provider", provider);

        return "provider/records";
    }

    /**
     * 根据搜索框里输入的供应商联系人电话进行查询后返回
     *
     * @param pn 页码
     * @param request 用于获取表单数据的原始API
     * @param model 封装数据
     * @return provider.jsp
     */
    @RequestMapping("/searchProvider")
    public String searchProvider(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                 HttpServletRequest request, Model model) {
        String phoneNumber = request.getParameter("phoneNumber");
        PageHelper.startPage(pn, 5);
        List<Provider> providers = providerService.getProviderByProviderPhoneNumber(phoneNumber);
        System.out.println("*****结果*****" + providers);
        PageInfo<Provider> pageInfo = new PageInfo<>(providers, 5);
        model.addAttribute("pageInfo", pageInfo);

        return "provider/provider";
    }


    /**
     * ===> 删除选定条目的供应商
     * 根据供应商id删除供应商
     *
     * @param providerId 供应商id
     * @return 重定向/provider/providerList
     */
    @RequestMapping("deleteProvider")
    public String deleteProvider(@RequestParam("providerId") Integer providerId) {
        int row = providerService.deleteProviderByProviderId(providerId);
        System.out.println("deleteProviderByProviderId ==> " + row);

        return "forward:/provider/providerList";
    }

    /**
     * 仅仅是跳转到添加到供应商界面
     *
     * @return providerAdd.jsp
     */
    @RequestMapping("/toAddProviderPage")
    public String toAddProviderPage() {

        return "provider/providerAdd";
    }

    /**
     * 添加供应商
     *
     * @param provider 表单封装的Provider对象
     * @return 重定向到/provider/providerList
     */
    @RequestMapping("/addProvider")
    public String addProvider(Provider provider) {
        int row = providerService.addProvider(provider);
        System.out.println("addProvider ==> " + row);

        return "forward:/provider/providerList";
    }

    /**
     * 跳转到修改供应商页面, 同时携带根据id查询出的信息进行回显
     *
     * @param providerId 供应商id
     * @param model 携带数据转发
     * @return providerUpdate.jsp
     */
    @RequestMapping("/toUpdateProviderPage")
    public String toUpdateProvider(@RequestParam("providerId") Integer providerId,
                                   Model model) {
        Provider selectedProvider = providerService.getProviderByProviderId(providerId);
        model.addAttribute("selectedProvider", selectedProvider);

        return "provider/providerUpdate";
    }

    /**
     * 更新供应商
     *
     * @param provider 表单封装的Provider实体类
     * @return 重定向到/provider/providerList
     */
    @RequestMapping("/updateProvider")
    public String updateProvider(Provider provider) {
        int row = providerService.updateProvider(provider);
        System.out.println("updateProvider => " + row);

        return "forward:/provider/providerList";
    }
}
