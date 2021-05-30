package com.wxl.sms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxl.sms.bean.User;
import com.wxl.sms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wxl on 2021/5/8 22:25
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 系统登录处理
     *
     * @param request 获取登录表单数据
     * @param model 存储显示登录状态信息
     * @param session 用于过滤器判断, 存储登录状态信息
     * @return login.jsp、main.jsp
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, HttpSession session) {
        String loginName;
        String loginPassword;
        String rememberMe;
        if (request.getParameter("loginName") != null
                && !request.getParameter("loginName").equals("")) {
            loginName = request.getParameter("loginName");
            loginPassword = request.getParameter("loginPassword");
        } else {
            model.addAttribute("ERROR_INFO", "请输入用户名！");
            return "../login";
        }

        System.out.println("username = " + loginName + ", password = " + loginPassword);

        User userInDB = userService.getUserByUsername(loginName);
        System.out.println("======> " + userInDB);
        if (userInDB == null) {
//            System.out.println("用户名输入错误!");
            model.addAttribute("ERROR_INFO", "用户名输入错误!");
            return "../login";
        } else if (!userInDB.getPassword().equals(loginPassword)) {
//            System.out.println("密码输入错误!");
            model.addAttribute("ERROR_INFO", "密码输入错误!");
            return "../login";
        } else {
//            System.out.println("登录成功！");
            model.addAttribute("ERROR_INFO", null);
            // 判断session中是否有User, 有的话就是已经登录 -> 即放行
            session.setAttribute("user", userInDB);

            rememberMe = request.getParameter("rm");
            if ("1".equals(rememberMe)) {
                // 设置勾选“记住我”之后保存登录状态为24h
                session.setMaxInactiveInterval(24 * 60 * 60);
            }

            return "other/main";
        }
    }

    /**
     * 处理退出系统
     *
     * @param session 用于清除系统登录状态
     * @return 登录页面
     */
    @RequestMapping("/exit")
    public String exitSystem(HttpSession session) {
        // 用户点击退出登录, 这里进行session中清除当前用户信息
        session.removeAttribute("user");

        return "../login";
    }


    /**
     * 获取当前系统中的所有用户用于信息显示
     *
     * @param pn 显示页码
     * @param model 为了将数据传入到cookie域中方便取出数据
     * @return user.jsp
     */
    @RequestMapping("/userList")
    public String getAllUser(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model
            /*, @RequestAttribute("ADD_USER_MSG") String msg*/) {
        PageHelper.startPage(pn, 5);
        List<User> allUser = userService.getAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(allUser, 5);
        model.addAttribute("pageInfo", pageInfo);

        // 显示添加用户信息操作
//        model.addAttribute("ADD_USER_STATE_MSG", msg);

        return "user/user";
    }

    /**
     * 仅仅是跳转到新增用户界面
     *
     * @return userAdd.jsp
     */
    @RequestMapping("toAddUser")
    public String toAddUserPage() {
        return "user/userAdd";
    }


    /**
     * 添加系统登录用户
     *
     * @param user 表单封装的用户对象
     * @return 用户列表
     */
    @RequestMapping("/addUser")
    public String addUser(User user, HttpServletRequest request) {
        // ****************添加用户时的用户名不可以与之前的重复*******************
        List<User> allUser = userService.getAllUser();
        for (User savedUser : allUser) {
            if (savedUser.getUsername().equals(user.getUsername())) {
                System.out.println("用户名已存在, 添加失败");
                request.setAttribute("ADD_USER_MSG", "用户名已存在, 添加失败");
//                model.addAttribute("ADD_USER_MSG", "用户名已存在, 添加失败");
//                session.setAttribute("ADD_USER_MSG", "用户名已存在, 添加失败");
                return "forward:userList";
            }
        }
        // *******************************************************************

        int addUser = userService.addUser(user);
        System.out.println("addUser ==> " + addUser);
        request.setAttribute("ADD_USER_MSG", "添加用户 " + user.getUsername() + " 成功");
//        model.addAttribute("ADD_USER_MSG", "添加用户 " + user.getUsername() + " 成功");
//        session.setAttribute("ADD_USER_MSG", "添加用户 " + user.getUsername() + " 成功");

        return "forward:userList";
    }

    /**
     * 重置所有用户的密码为123456
     *
     * @return 重定向到userList.jsp
     */
    @RequestMapping("/resetAllPassword")
    public String resetAllPassword() {
        int resetAllPassword = userService.resetAllPassword();
        System.out.println("resetAllPassword ==> " + resetAllPassword);

        return "forward:userList";
    }

    /**
     * 重置选中用户密码
     *
     * @param id 选中用户id
     * @return 重定向到userList.jsp
     */
    @RequestMapping("/resetSelectedUserPassword")
    public String resetSelectedUserPassword(@RequestParam("id") Integer id) {
        int resetPasswordByUserId = userService.resetPasswordByUserId(id);
        System.out.println("resetPasswordByUserId ==> " + resetPasswordByUserId);

        return "forward:userList";
    }

    /**
     * 删除选中用户
     *
     * @param id 选中用户id
     * @return 重定向到userList.jsp
     */
    @RequestMapping("/deleteSelectedUser")
    public String deleteSelectedUser(@RequestParam("id") Integer id, HttpSession session) {
        // ********************如果删除选中的用户是当前系统登录本人, 则删除失败
        User user = (User) session.getAttribute("user");
//        System.out.println("---> 当前系统登录用户：" + user);
        User userByUserId = userService.getUserByUserId(id);
//        System.out.println("---> 根据id查的用户：" + userByUserId);
        if (user.getId().equals(userByUserId.getId())) {
            System.out.println("您不可以删除您自己");
            return "forward:userList";
        }

        int deleteUserByUserId = userService.deleteUserByUserId(id);
        System.out.println("deleteUserByUserId ==> " + deleteUserByUserId);

        return "forward:userList";
    }

    /**
     * 注销当前用户并退出
     *
     * @param id 选中用户id
     * @return 注销后退出系统
     */
    @RequestMapping("/deleteCurrentUser")
    public String deleteCurrentUser(@RequestParam("id") Integer id) {
        // ******************如果当前用户是最后一个管理员账户, 设置不可删除
        List<User> allAdmin = userService.getAllAdmin();
        if (allAdmin.size() == 1) {
            System.out.println("您是当前系统的最后一位管理员, 禁止删除");
            return "forward:userList";
        }

        int deleteUserByUserId = userService.deleteUserByUserId(id);
        System.out.println("deleteUserByUserId ==> " + deleteUserByUserId);

        return "forward:/exit";
    }

    /**
     * 跳转到修改当前用户密码界面
     *
     * @return updatePassword.jsp
     */
    @RequestMapping("/toUpdateCurrentPasswordPage")
    public String toUpdateCurrentPasswordPage() {
        return "user/updatePassword";
    }

    /**
     * 修改当前用户密码
     *
     * @param request 获取输入的新密码
     * @param session 获取当前登录用户id
     * @param model 存储修改结果
     * @return main.jsp
     */
    @RequestMapping("/updateCurrentUserPassword")
    public String updateCurrentUserPassword(HttpServletRequest request, HttpSession session, Model model) {
        String password = request.getParameter("password");
        User user = (User) session.getAttribute("user");
        int updatePassword = userService.updatePassword(user.getId(), password);
        System.out.println("updatePassword ==> " + updatePassword);
        model.addAttribute("updatePassword", updatePassword);

        return "other/main";
    }

    // ********以下两个Controller是为了防止JSP页面跳转失败而加的, 无逻辑操作

    /**
     * 跳转到首页
     *
     * @return main.jsp
     */
    @RequestMapping("/home")
    public String toHomePage() {
        return "other/main";
    }

    /**
     * 跳转到结算业面
     *
     * @return checkout.jsp
     */
    @RequestMapping("/toCheckout")
    public String toCheckoutPage() {
        return "other/checkout";
    }
}
