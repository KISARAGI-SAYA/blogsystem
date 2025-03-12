package com.xja.blogsystem.mapper;

import com.xja.blogsystem.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    /**
     * 查询所有博客列表
     * @return 博客列表
     */
    List<Blog> getAllBlogs();

    /**
     * 根据博客 ID 查询博客
     * @param id 博客 ID
     * @return 博客对象
     */
    Blog getBlogById(@Param("id") int id);

    /**
     * 发布博客
     * @param blog 博客对象
     * @return 受影响的行数
     */
    int insertBlog(Blog blog);

    /**
     * 编辑博客
     * @param blog 博客对象
     * @return 受影响的行数
     */
    int updateBlog(Blog blog);

    /**
     * 删除博客
     * @param id 博客 ID
     * @return 受影响的行数
     */
    int deleteBlogById(@Param("id") int id);

    /**
     * 根据用户 ID 查询博客列表
     * @param userId 用户 ID
     * @return 博客列表
     */
    List<Blog> getBlogsByUserId(@Param("userId") int userId);
}