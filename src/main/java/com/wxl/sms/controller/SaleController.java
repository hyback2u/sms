package com.wxl.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxl.sms.bean.Group;
import com.wxl.sms.bean.to.Sale;
import com.wxl.sms.service.GroupService;
import com.wxl.sms.service.ProductService;
import com.wxl.sms.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 销售统计Controller
 *
 * @author wxl on 2021/5/5 0:08
 */
@Controller
@RequestMapping("/sale")
public class SaleController {
    @Resource
    private SaleService saleService;
    @Resource
    private ProductService productService;
    @Resource
    private GroupService groupService;


    /**
     * 重定向后进入sale.jsp页面
     *
     * @return 重定向到sale.jsp
     */
    @RequestMapping("/toSale")
    public String toSalePage() {
        return "forward:/sale/saleInfo";
    }

    /**
     * 用于显示销售统计页面的信息
     *
     * @param pn 页码
     * @param request 原生API, 用于获取表单输入信息以进行条件查询
     * @param model 存储数据域中
     * @return sale.jsp
     */
    @Deprecated
    @RequestMapping("/saleInfo")
    public String showSaleInfo(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                               HttpServletRequest request,
                               Model model) {
        System.out.println("preGroupId ===> " + request.getParameter("groupIdInForm"));
        Integer groupId = null;
        String startDate = "";
        String endDate = "";

        if (request.getParameter("groupIdInForm") != null &&
                !request.getParameter("groupIdInForm").equals("0")) {
            // 表单输入的商品类别
            groupId = Integer.parseInt(request.getParameter("groupIdInForm"));
        }
        if (request.getParameter("startDate") != null &&
                request.getParameter("endDate") != null) {
            startDate = request.getParameter("startDate");
            endDate = request.getParameter("endDate");
        }

//        System.out.println("********************************");
//        System.out.println("groupId ===> " + groupId);
//        System.out.println("startDate ===> " + startDate);
//        System.out.println("endDate ===> " + endDate);
//        System.out.println("********************************");

        PageHelper.startPage(pn, 5);
        List<Sale> sales = saleService.getSaleInfoByCondition(groupId, startDate, endDate);
        for (Sale sale : sales) {
            sale.setProduct(productService.getProductByProductId(sale.getProductId()));
            sale.getProduct().setGroup(groupService.getGroupByGroupId(sale.getProduct().getGroupId()));
        }

        PageInfo<Sale> pageInfo = new PageInfo<>(sales, 5);
        model.addAttribute("pageInfo", pageInfo);

        // 2021年5月5日01:13:06添加, 为了让条件查询更容容易, 这里由填写商品分类, 修改为选择下拉框
        List<Group> allGroups = groupService.getAllGroups();
        model.addAttribute("allGroups", allGroups);

        return "sale/sale";
    }


    /**
     * 用于显示销售统计页面的信息
     *
     * @param pn 页码
     * @param request 原生API, 用于获取表单输入信息以进行条件查询
     * @param model 存储数据域中
     * @param postGroupId 存储上一次表单提交的商品分类值
     * @return sale.jsp
     */
    @RequestMapping("/saleInfoPro")
    public String showSaleInfoPro(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                  HttpServletRequest request,
                                  Model model,
                                  @RequestParam(value = "groupId", defaultValue = "0") Integer postGroupId) {
//        System.out.println("preGroupId ===> " + request.getParameter("groupIdInForm"));
        Integer groupId = null;
        String startDate = "";
        String endDate = "";

        if (postGroupId == 0) {
            if (request.getParameter("groupIdInForm") != null &&
                    !request.getParameter("groupIdInForm").equals("0")) {
                // 表单输入的商品类别
                groupId = Integer.parseInt(request.getParameter("groupIdInForm"));
            }
        } else {
            groupId = postGroupId;
        }

        if (request.getParameter("startDate") != null &&
                request.getParameter("endDate") != null) {
            startDate = request.getParameter("startDate");
            endDate = request.getParameter("endDate");
        }

        PageHelper.startPage(pn, 5);
        List<Sale> sales = saleService.getSaleInfoByCondition(groupId, startDate, endDate);
        for (Sale sale : sales) {
            sale.setProduct(productService.getProductByProductId(sale.getProductId()));
            sale.getProduct().setGroup(groupService.getGroupByGroupId(sale.getProduct().getGroupId()));
        }

        PageInfo<Sale> pageInfo = new PageInfo<>(sales, 5);
        model.addAttribute("pageInfo", pageInfo);

        // 2021年5月5日01:13:06添加, 为了让条件查询更容容易, 这里由填写商品分类, 修改为选择下拉框
        List<Group> allGroups = groupService.getAllGroups();
        model.addAttribute("allGroups", allGroups);

        // 2021年5月10日14:12:48 Pro版, 解决分页BUG
        model.addAttribute("groupId", groupId);

        return "sale/sale";
    }
}
