package com.wxl.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxl.sms.bean.Group;
import com.wxl.sms.bean.Product;
import com.wxl.sms.bean.Provider;
import com.wxl.sms.bean.to.Check;
import com.wxl.sms.service.GroupService;
import com.wxl.sms.service.ProductService;
import com.wxl.sms.service.ProviderService;
import com.wxl.sms.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品(Product)Controller
 * PageHelper的一些数据: PageInfo中封装了详细的分页信息, 包括我们查询出的数据
 *      - pageNum: 当前页码
 *      - PageSize: 每页有多少条记录
 *      - startRow: 开始查询的记录
 *      - endRow: 结束的记录
 *      - ToTal: 总记录数
 *      - Pages: 总页码
 *      - firstPage/lastPage: 第一页/最后一页
 *      - isFirstPage/isLastPage: 是否第一页/是否最后一页
 *      - isHasPreviousPage/isHasNextPage: 是否有前一页/是否有后一页
 *
 *
 * @author wxl on 2021/4/24 23:39
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @Resource
    private GroupService groupService;
    @Resource
    private SaleService saleService;
    @Resource
    private ProviderService providerService;


    /**
     * 查询所有商品库存信息(这里用到了PageHelper分页插件)
     *
     * @param pn 传入要查询的页码(要查询第几页的数据)
     * @param model 为了将数据传入到cookie域中方便取出数据
     * @return 返回product.jsp页面
     */
    @RequestMapping("/productList")
    public String getAllProducts(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                 Model model) {
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Product> products = productService.getAllProducts();
        // 使用PageInfo包装查询后的结果, 只需要将PageInfo交给页面就行了(param2:表示分页条显示的页数)
        PageInfo<Product> pageInfo = new PageInfo<>(products, 5);
        model.addAttribute("pageInfo", pageInfo);

        // 2021年4月30日01:40:08添加, 为了让条件查询更容容易, 这里由填写商品分类, 修改为选择下拉框
        List<Group> allGroups = groupService.getAllGroups();
        model.addAttribute("allGroups", allGroups);

        return "product/product";
    }


    /**
     * [Deprecated]
     * 用于搜索显示, 根据商品名称查询后显示 ---> 需要优化 ---> getProductInfoBySearch()
     *
     * @param request 从from表单中获取参数
     * @param model 存数据
     * @return 返回provider.jsp页面
     */
    @Deprecated
    @RequestMapping("/productName")
    public String getProductByProductName(HttpServletRequest request,
                                          Model model) {
        String productName = request.getParameter("productNameInForm");
        System.out.println(productName);
        Product product = productService.getProductByProductName(productName);
        // 虽然只有一条数据, 但是隔壁product.jsp取值还是从分页数据中取值的啊, 只好特殊对待了...(竟然能用)
        List<Product> productListOnlyOne = new ArrayList<>();
        productListOnlyOne.add(product);
        PageInfo<Product> pageInfo = new PageInfo<>(productListOnlyOne, 5);
        model.addAttribute("pageInfo", pageInfo);

        List<Group> allGroups = groupService.getAllGroups();
        model.addAttribute("allGroups", allGroups);

        return "product/product";
    }


    /**
     * [Deprecated]
     * 对最初的搜索商品信息的优化方案 ===> 用于搜索显示, 根据商品名称查询后显示(PLUS版)
     *      2021年5月10日13:22:04 ===> 方法有更新, 详情参阅Pro版
     *
     * @param pn 传入要查询的页码(要查询第几页的数据)
     * @param request 用于获取表单中填写的值
     * @param model 为了将数据传入到cookie域中方便取出数据
     * @return 返回product.jsp页面
     */
    @Deprecated
    @RequestMapping("/search")
    public String getProductInfoBySearch(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                         HttpServletRequest request,
                                         Model model) {
        String productName = "";
        Integer groupId = null;
        // 表单输入的商品名称
        if (request.getParameter("productNameInForm") != null) {
            productName = request.getParameter("productNameInForm");
        }
        if (request.getParameter("groupIdInForm") != null &&
                !request.getParameter("groupIdInForm").equals("0")) {
            // 表单输入的商品类别
            groupId = Integer.parseInt(request.getParameter("groupIdInForm"));
        }

//        System.out.println("~~~~~~++++++" + productName + "--> " + groupId);

        PageHelper.startPage(pn, 5);
        List<Product> products = productService.getProductByProductNameOrProductGroup(productName, groupId);
        PageInfo<Product> pageInfo = new PageInfo<>(products, 5);
        model.addAttribute("pageInfo", pageInfo);

        List<Group> allGroups = groupService.getAllGroups();
        model.addAttribute("allGroups", allGroups);

//        model.addAttribute("selectedProductGroupId", groupId);
//        // 根据id获取分类名
//        Group searchGroup = groupService.getGroupByGroupId(groupId);
//        model.addAttribute("selectedProductGroupName", searchGroup.getGroupName());
//
//        System.out.println("%%%%%%--->" + groupId);
//        System.out.println("%%%%%%--->" + searchGroup.getGroupName());
        return "product/product";

        // 2021年4月30日03:21:12(测试用)
//        return "searchProduct";
    }


    /**
     * “用于搜索显示, 根据商品名称查询后显示” 的Pro版
     *      1、原先的方法, 按照商品类别搜索后, 点击该类别下的第二页时, 页面实际显示结果为所有商品列表的第二页 --> BUG
     *      2、之前未解决的原因: 一直在想着怎么在点击超链接的时候提交表单
     *      3、解决思路: 想要点击超链接的时候提交表单, 也就是为了获取表单里的groupId数据, 这里将点击搜索时用的groupId
     *         以Model的格式传递到页面中(searchProduct.jsp), 下一次点击类似第二页的操作时, 获取到该分类的id号, 去取代
     *         从表单中获取的值
     *              - 前提条件: 做了优化, 默认分类不是食品(遍历第一项), 而是提示信息, 并且值为 0, 后端处理时, 若值为0
     *                则赋值productId为null, 此时MyBatis后端代码, 将不带有此条件(动态SQL);
     *              - 取代表单中获取的productId, 是有条件的, 如果用户什么也不填, 那Model返回的值就是null, 在方法处理时
     *                添加了postGroupId默认值也为0, 如果为0, 那么就不接管替代表单值;
     *
     * @param pn 传入要查询的页码(要查询第几页的数据)
     * @param request 用于获取表单中填写的值
     * @param model 为了将数据传入到cookie域中方便取出数据
     * @param postGroupId 存储上一次表单提交的商品分类值
     * @return searchProduct.jsp
     */
    @RequestMapping("/searchPro")
    public String getProductInfoBySearchPro(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                            HttpServletRequest request, Model model,
                                            @RequestParam(value = "groupId", defaultValue = "0") Integer postGroupId) {
        String productName = "";

        // 表单输入的商品名称
        if (request.getParameter("productNameInForm") != null) {
            productName = request.getParameter("productNameInForm");
        }

        Integer groupId = null;
        if (postGroupId == null || postGroupId == 0) {
            if (request.getParameter("groupIdInForm") != null &&
                    !request.getParameter("groupIdInForm").equals("0")) {
                // 表单输入的商品类别
                groupId = Integer.parseInt(request.getParameter("groupIdInForm"));
            }
        } else {
            groupId = postGroupId;
        }

        PageHelper.startPage(pn, 5);
        List<Product> products = productService.getProductByProductNameOrProductGroup(productName, groupId);
//        System.out.println("*********" + products);
        PageInfo<Product> pageInfo = new PageInfo<>(products, 5);
        model.addAttribute("pageInfo", pageInfo);

        List<Group> allGroups = groupService.getAllGroups();
        model.addAttribute("allGroups", allGroups);

        // 2021年5月10日12:59:04 优化搜索表单分页问题
        model.addAttribute("groupId", groupId);

        // 2021年4月30日03:21:12(测试用)
        return "product/searchProduct";
    }

    /**
     * ===> 携带系统分组信息及供应商信息跳转到添加商品页面
     *
     * (Pre)仅仅是跳转到添加商品页面
     *      -- 后面附加了用于新增商品时, 下拉选择“商品分类”和“供应商”, 而不是输入文本
     *
     * @return productAdd.jsp
     */
    @RequestMapping("/addProductPage")
    public String toAddProductPage(Model model) {
        // 2021年4月30日01:02:57 新增 attribute(为了让添加商品信息时, 显示出商品分类信息而不是分类id)
        List<Group> allGroups = groupService.getAllGroups();
        model.addAttribute("allGroups", allGroups);

        // 2021年5月4日17:44:43 新增 attribute(在新增供应商id关联后, 在新增时要体现供应商这一字段)
        List<Provider> allProviders = providerService.getAllProviders();
        model.addAttribute("allProviders", allProviders);

        return "product/productAdd";
    }


    /**
     * ===> 执行新增商品逻辑
     *
     * 执行添加数据操作, 之后返回到查询界面
     *     - 这里有报错 org.apache.ibatis.reflection.ReflectionException:
     *       There is no getter for property named 'product' in 'class com.wxl.sms.bean.Product'
     *     - 当时仅仅考虑到了是SpringMVC没有自动封装成JavaBean, 一直在表单上找原因, 搜了一些帖子后发现, 是
     *       mybatis方面的报错信息, 并且在这里先打印参数product的值, 也排除了不是没有封装成功的原因,
     *       所以呀, 看报错信息去精准定位!!!
     *
     * @param product Product
     * @return forward:/product/productList
     */
    @RequestMapping("/addProduct")
    public String addProduct(@ModelAttribute("productAddForm") Product product) {
        System.out.println(product);
        int row = productService.addProduct(product);
        System.out.println("影响的行数：" + row);

        return "forward:/product/productList";
    }


    /**
     * [Deprecated]
     * 商品结算找零服务
     *
     * @param request 原生requestAPI, 用于获取表单数据
     * @param model 存储最终计算的价格, 并放入request域中
     * @return checkout.jsp页面
     */
    @Deprecated
    @RequestMapping("/check")
    public String checkService(HttpServletRequest request, Model model) {
        Integer productId = Integer.parseInt(request.getParameter("productId"));
        int productNumber = Integer.parseInt(request.getParameter("productNumber"));

        float productSalePrice = productService.getProductSalePriceByProductId(productId);
        float checkPrice = productNumber * productSalePrice;
        model.addAttribute("checkPrice", checkPrice);

        return "other/checkout";
    }


    /**
     * [Deprecated]
     * 商品结算找零服务(Plus版)
     *
     * @param request 原生requestAPI, 用于获取表单数据
     * @param model 存储结算页面的抽象后封装的TO对象, 并放入request域中
     * @return checkout.jsp页面
     */
    @Deprecated
    @RequestMapping("/checkPlus")
    public String checkServicePlus(HttpServletRequest request, Model model) {
        Integer productId = Integer.parseInt(request.getParameter("productId"));
        Check checkObj = productService.getProductByProductIdToCheckService(productId);
        // 在这里完成最后对Check的初始化
        checkObj.setProductNumber(Integer.parseInt(request.getParameter("productNumber")));
        checkObj.setCheckPrice(checkObj.getProductNumber() * checkObj.getProductSalePrice());
        model.addAttribute("checkObj", checkObj);

        return "other/checkout";
    }


    /**
     * 商品结算找零服务(PlusPro版-2021年5月2日15:22:50)
     *
     * @param request 原生requestAPI, 用于获取表单数据
     * @param model 存储结算页面的抽象后封装的TO对象, 并放入request域中
     * @return checkout.jsp页面
     */
    @RequestMapping("/checkPlusPro")
    public String checkServicePlusPro(HttpServletRequest request, Model model) {
        Integer productId = null;
        if (request.getParameter("productId") != null) {
            // 从表单获取销售商品的id
            productId = Integer.parseInt(request.getParameter("productId"));
        }
        // 根据表单生成对应id的商品详细信息
        Check checkObj = productService.getProductByProductIdToCheckService(productId);
        // 获取后台此id的商品详细信息
        Product product = productService.getProductByProductId(productId);

        // 在这里完成最后对Check的初始化
        checkObj.setProductNumber(Integer.parseInt(request.getParameter("productNumber")));

        // (2021年5月2日01:05:20)增加购买数量与库存之间的判断
        if (checkObj.getProductNumber() > product.getStock()) {
            throw new RuntimeException("商品库存不足, 请联系管理员增加库存！");
        } else {
            checkObj.setCheckPrice(checkObj.getProductNumber() * checkObj.getProductSalePrice());
            model.addAttribute("checkObj", checkObj);

            // ***************************事务操作***************************
//            generateSaleItemAndUpdateStock(productId, checkObj.getProductNumber());
            generateSaleItemAndUpdateStockPlus(productId, checkObj.getProductNumber(), product);
            // *************************************************************

            return "other/checkout";
        }
    }


    @Deprecated
    @Transactional
    public void generateSaleItemAndUpdateStock(Integer productId, Integer saleCount) {
        int updateProductStock = productService.updateProductStock(productId, saleCount);
        System.out.println("***updateProductStock ==> " + updateProductStock);
        int insertSaleItem = saleService.insertSaleItem(productId, saleCount);
        System.out.println("***insertSaleItem ==> " + insertSaleItem);
    }


    /**
     * 事务操作, 启用Spring声明式事务管理
     *
     * @param productId 商品id
     * @param saleCount 销售量
     * @param product id查询到的商品
     */
    @Transactional
    public void generateSaleItemAndUpdateStockPlus(Integer productId, Integer saleCount, Product product) {
        Integer beforeStock = product.getStock();
        Integer stock = beforeStock - saleCount;
        Integer nowSaleCount = product.getSaleCount() + saleCount;

        int updateProductStock = productService.updateProductStockPlus(productId, nowSaleCount, stock);
        System.out.println("***updateProductStock ==> " + updateProductStock);
        int insertSaleItem = saleService.insertSaleItem(productId, saleCount);
        System.out.println("***insertSaleItem ==> " + insertSaleItem);
    }


    /**
     * ===> 删除选中条目的商品
     * 按照商品id删除商品
     *
     * @param productId 商品id
     * @return forward:/product/productList
     */
    @RequestMapping("/deleteProduct")
    public String deleteProductByProductId(@RequestParam("productId") Integer productId) {
        int row = productService.deleteProductByProductId(productId);
        System.out.println("删除操作影响的行数：" + row);
        return "forward:/product/productList";
    }


    /**
     * ===> 携带选中条目的id查询出的商品信息跳转到添加修改页面
     *
     * @param productId 从按钮上得到的商品id
     * @param model 用于表单提交时传给updateProductByProductId()方法id, 便于根据id修改
     * @return productUpdate.jsp
     */
    @RequestMapping("/toUpdateProductPage")
    public String toUpdateProductPage(@RequestParam("productId") Integer productId, Model model) {
//        model.addAttribute("productId", productId);
//        productService.getProductSalePriceByProductId()
        System.out.println("**********表单得到的id = " + productId);
        Product selectedProduct = productService.getProductByProductId(productId);
        System.out.println("===> " + selectedProduct);
        model.addAttribute("selectedProduct", selectedProduct);

        // 2021年4月30日01:06:43 新增 attribute(为了让修改商品信息时, 显示出商品分类信息而不是分类id)
        List<Group> allGroups = groupService.getAllGroups();
        model.addAttribute("allGroups", allGroups);

        return "product/productUpdate";
    }


    /**
     * ===> 执行更新商品信息逻辑
     * 根据商品id(表单提交封装后获取id)更新商品信息
     *
     * @param product 表单封装的product实体类
     * @return forward:/product/productList
     */
    @RequestMapping("/updateProductByProductId")
    public String updateProductByProductId(Product product) {
        System.out.println("******FORM:" + product);
        int row = productService.updateProductByProductId(product);
        System.out.println("影响的行数：" + row);

        return "forward:/product/productList";
    }

}
