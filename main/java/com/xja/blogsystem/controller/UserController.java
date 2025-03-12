package com.xja.blogsystem.controller;

import com.xja.blogsystem.pojo.User;
import com.xja.blogsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
     * @param account 账号
     * @param password 密码
     * @param realName 真实姓名
     * @param request 请求对象
     * @return 注册结果页面
     */
    @RequestMapping("/register")
    public String register(@RequestParam("account") String account,
                           @RequestParam("password") String password,
                           @RequestParam("realName") String realName,
                           HttpServletRequest request) {
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setRealName(realName);

        User existingUser = userService.queryUserByAccount(account);
        if (existingUser != null) {
            request.setAttribute("error", "账号已存在，请选择其他账号");
            return "forward:/jsp/register.jsp";
        }

        int result = userService.insertUser(user);
        if (result > 0) {
            return "redirect:/jsp/login.jsp";
        } else {
            request.setAttribute("error", "注册失败，请稍后重试");
            return "forward:/jsp/register.jsp";
        }
    }

    /**
     * 用户登录
     * @param account 账号
     * @param password 密码
     * @param session 会话对象
     * @return 登录结果页面
     */
    @RequestMapping("/login")
    public String login(@RequestParam("account") String account,
                        @RequestParam("password") String password,
                        HttpSession session) {
        User user = userService.queryUserByAccountAndPassword(account, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/jsp/index.jsp";
        } else {
            session.setAttribute("loginError", "账号密码错误，请重新输入");
            return "forward:/jsp/login.jsp";
        }
    }

    /**
     * 用户退出登录
     * @param session 会话对象
     * @return 登录页面
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/jsp/login.jsp";
    }

    /**
     * 查看个人资料
     * @param session 会话对象
     * @param request 请求对象
     * @return 个人资料页面
     */
    @RequestMapping("/profile")
    public String profile(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            User currentUser = userService.queryUserById(user.getId());
            request.setAttribute("user", currentUser);
            return "forward:/jsp/profile.jsp";
        } else {
            return "redirect:/jsp/login.jsp";
        }
    }

    /**
     * 修改个人资料
     * @param realName 真实姓名
     * @param session 会话对象
     * @param request 请求对象
     * @return 修改结果页面
     */
    @RequestMapping("/updateProfile")
    public String updateProfile(@RequestParam("realName") String realName,
                                HttpSession session,
                                HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            user.setRealName(realName);
            int result = userService.updateUserInfo(user);
            if (result > 0) {
                request.setAttribute("message", "个人资料修改成功");
            } else {
                request.setAttribute("error", "个人资料修改失败，请稍后重试");
            }
            return "forward:/jsp/profile.jsp";
        } else {
            return "redirect:/jsp/login.jsp";
        }
    }

    /**
     * 验证用户账号是否存在
     * @param account 账号
     * @return 验证结果
     */
    @RequestMapping("/userExist")
    @ResponseBody
    public Map<String, Integer> userExist(@RequestParam("account") String account) {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("exist", -1);
        User user = userService.queryUserByAccount(account);
        if (user != null) {
            hashMap.put("exist", 1);
        } else {
            hashMap.put("exist", 0);
        }
        return hashMap;
    }
}