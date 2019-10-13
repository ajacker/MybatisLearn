package com.ajacker.dao;

import com.ajacker.domain.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ajacker
 */
public interface IBlogDao {

    /**
     * 插入数据
     *
     * @param blog
     */
    void insertBlog(Blog blog);

    /**
     * 查询博客
     * 不提供条件则查询所有
     * 若提供条件则按条件查询
     *
     * @param title  可为null
     * @param author 可为null
     * @return 查询结果
     */
    List<Blog> queryBlogIf(@Param("title") String title, @Param("author") String author);
}
