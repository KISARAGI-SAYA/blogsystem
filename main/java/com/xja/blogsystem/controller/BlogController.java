package com.xja.blogsystem.controller;

import com.xja.blogsystem.pojo.Blog;
import com.xja.blogsystem.pojo.User;
import com.xja.blogsystem.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 查看博客列表
     * @param request 请求对象
     * @return 博客列表页面
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request) {
        List<Blog> blogs = blogService.getAllBlogs();
        request.setAttribute("blogs", blogs);
        return "forward:/jsp/blog/list.jsp";
    }

    /**
     * 查看博客详情
     * @param id 博客 ID
     * @param request 请求对象
     * @return 博客详情页面
     */
    @RequestMapping("/view")
    public String view(@RequestParam("id") int id, HttpServletRequest request) {
        Blog blog = blogService.getBlogById(id);
        request.setAttribute("blog", blog);
        return "forward:/jsp/blog/view.jsp";
    }

    /**
     * 发布博客页面
     * @param session 会话对象
     * @param request 请求对象
     * @return 发布博客页面
     */
    @RequestMapping("/addPage")
    public String addPage(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return "forward:/jsp/blog/add.jsp";
        } else {
            return "redirect:/jsp/login.jsp";
        }
    }

    /**
     * 发布博客
     * @param title 博客标题
     * @param content 博客内容
     * @param session 会话对象
     * @param request 请求对象
     * @return 发布结果页面
     */
    @RequestMapping("/add")
    public String add(@RequestParam("title") String title,
                      @RequestParam("content") String content,
                      HttpSession session,
                      HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setUserId(user.getId());

            int result = blogService.insertBlog(blog);
            if (result > 0) {
                return "redirect:/blog/list";
            } else {
                request.setAttribute("error", "发布博客失败，请稍后重试");
                return "forward:/jsp/blog/add.jsp";
            }
        } else {
            return "redirect:/jsp/login.jsp";
        }
    }

    /**
     * 编辑博客页面
     * @param id 博客 ID
     * @param session 会话对象
     * @param request 请求对象
     * @return 编辑博客页面
     */
    @RequestMapping("/editPage")
    public String editPage(@RequestParam("id") int id,
                           HttpSession session,
                           HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Blog blog = blogService.getBlogById(id);
            if (blog.getUserId() == user.getId()) {
                request.setAttribute("blog", blog);
                return "forward:/jsp/blog/edit.jsp";
            } else {
                request.setAttribute("error", "你没有权限编辑此博客");
                return "forward:/blog/list";
            }
        } else {
            return "redirect:/jsp/login.jsp";
        }
    }

    /**
     * 编辑博客
     * @param id 博客 ID
     * @param title 博客标题
     * @param content 博客内容
     * @param session 会话对象
     * @param request 请求对象
     * @return 编辑结果页面
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam("id") int id,
                       @RequestParam("title") String title,
                       @RequestParam("content") String content,
                       HttpSession session,
                       HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Blog blog = blogService.getBlogById(id);
            if (blog.getUserId() == user.getId()) {
                blog.setTitle(title);
                blog.setContent(content);

                int result = blogService.updateBlog(blog);
                if (result > 0) {
                    return "redirect:/blog/view?id=" + id;
                } else {
                    request.setAttribute("error", "编辑博客失败，请稍后重试");
                    request.setAttribute("blog", blog);
                    return "forward:/jsp/blog/edit.jsp";
                }
            } else {
                request.setAttribute("error", "你没有权限编辑此博客");
                return "forward:/blog/list";
            }
        } else {
            return "redirect:/jsp/login.jsp";
        }
    }

    /**
     * 删除博客
     * @param id 博客 ID
     * @param session 会话对象
     * @return 删除结果
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, String> delete(@RequestParam("id") int id, HttpSession session) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("delResult", "notexist");
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Blog blog = blogService.getBlogById(id);
            if (blog.getUserId() == user.getId()) {
                int result = blogService.deleteBlogById(id);
                if (result > 0) {
                    hashMap.put("delResult", "true");
                } else {
                    hashMap.put("delResult", "false");
                }
            }
        }
        return hashMap;
    }
}