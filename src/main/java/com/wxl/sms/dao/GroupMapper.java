package com.wxl.sms.dao;

import com.wxl.sms.bean.Group;

import java.util.List;

/**
 * @author wxl on 2021/4/25 1:20
 */
public interface GroupMapper {

    /**
     * 根据分类的id查询返回分类实体
     *
     * @param id 分类的id
     * @return 分类实体
     */
    Group getGroupByGroupId(Integer id);

//    /**
//     * 根据商品分类名称查询商品分类的时候还能把本类下的商品信息查询出来
//     *
//     * @param groupName 商品分类名称
//     * @return Group
//     */
//    Group getGroupByGroupName(String groupName);

    /**
     * 获取所有商品分类信息用于页面的展示
     *
     * @return List
     */
    List<Group> getAllGroups();

    /**
     * 新增商品分类
     *
     * @param group  group
     * @return 影响的行数
     */
    int addGroup(Group group);

    /**
     * 修改商品分类信息
     *
     * @param group 表单封装返回Group实体
     * @return 影响的行数
     */
    int updateGroup(Group group);

    /**
     * 根据id删除商品分类信息
     *
     * @param id 分类id
     * @return 影响的行数
     */
    int deleteGroupById(Integer id);

}
