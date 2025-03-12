package com.xja.blogsystem.service.impl;

import com.xja.blogsystem.mapper.BlogMapper;
import com.xja.blogsystem.pojo.Blog;
import com.xja.blogsystem.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> getAllBlogs() {
        return blogMapper.getAllBlogs();
    }

    @Override
    public Blog getBlogById(int id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public int insertBlog(Blog blog) {
        return blogMapper.insertBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int deleteBlogById(int id) {
        return blogMapper.deleteBlogById(id);
    }

    @Override
    public List<Blog> getBlogsByUserId(int userId) {
        return blogMapper.getBlogsByUserId(userId);
    }
}