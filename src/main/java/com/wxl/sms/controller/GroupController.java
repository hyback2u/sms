package com.wxl.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxl.sms.bean.Group;
import com.wxl.sms.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxl on 2021/4/25 1:52
 */
@Controller
@RequestMapping("/group")
public class GroupController {
    @Resource
    private GroupService groupService;

    /*注释掉吧, 没用到*/
//    @RequestMapping("/getProductsByGroupName")
//    public String getGroupByGroupName(HttpServletRequest request,
//                                      @RequestParam(value = "pn", defaultValue = "1") Integer pn,
//                                      Model model) {
//        String groupName = request.getParameter("productGroup");
//        Group group = groupService.getGroupByGroupName(groupName);
//        PageHelper.startPage(pn, 5);
//        List<Product> products = group.getProducts();
//        PageInfo<Product> pageInfo = new PageInfo<>(products, 5);
//        model.addAttribute("pageInfo", pageInfo);
//
//        return "product/product";
//    }

    /**
     * 查询所有商品分类信息
     *
     * @param pn 传入要查询的页码(要查询第几页的数据)
     * @param model 为了将数据传入到cookie域中方便取出数据
     * @return 返回group.jsp页面
     */
    @RequestMapping("/groupList")
    public String getAllGroups(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                               Model model) {
        PageHelper.startPage(pn, 5);
        List<Group> groups = groupService.getAllGroups();
        PageInfo<Group> pageInfo = new PageInfo<>(groups, 5);
        model.addAttribute("pageInfo", pageInfo);

        return "group/group";
    }

    /**
     * 仅仅是跳转到添加商品分类页面
     *
     * @return groupAdd.jsp
     */
    @RequestMapping("/addGroupPage")
    public String toAddGroupPage() {
        return "group/groupAdd";
    }

    /**
     * ===> 执行新增商品分类逻辑
     * 新增商品分类
     *
     * @param group 表单封装后的Group对象
     * @return 通过重定向返回到group.jsp
     */
    @RequestMapping("/addGroup")
    public String addGroup(Group group) {
        int row = groupService.addGroup(group);
        System.out.println("addProduct()影响的行数：" + row);

        return "forward:/group/groupList";
    }

    /**
     * ===> 携带根据选中条目的id查询的数据(数据回显)跳转到修改商品分类界面
     * 跳转到修改商品分类页面(过程中根据id通过后台查询出选定id对应的商品分类信息用于展示)
     *
     * @param id 选中条目的分类id
     * @param model 用于封装根据id去后台查询出的数据
     * @return 商品分类信息修改页面
     */
    @RequestMapping("/toUpdateGroupPage")
    public String toUpdateGroupPage(@RequestParam("id") Integer id, Model model) {
        Group group = groupService.getGroupByGroupId(id);
        model.addAttribute("group", group);

        return "group/groupUpdate";
    }

    /**
     * ===> 执行修改商品分类信息逻辑
     * 修改商品分类信息
     *
     * @param group 表单封装的Group实体
     * @return 重定向到groupList
     */
    @RequestMapping("/updateGroup")
    public String updateGroup(Group group) {
        int row = groupService.updateGroup(group);
        System.out.println("updateGroup()影响的行数：" + row);

        return "forward:/group/groupList";
    }

    /**
     * ===> 根据选中条目的id删除分类信息
     * 根据分类id删除商品分类信息
     *
     * @param groupId 分类id
     * @return 重定向到groupList
     */
    @RequestMapping("/deleteGroup")
    public String deleteGroupBySelectedId(@RequestParam("groupId") Integer groupId) {
        int row = groupService.deleteGroupById(groupId);
        System.out.println("deleteGroupById()影响的行数：" + row);

        return "forward:/group/groupList";
    }
}
