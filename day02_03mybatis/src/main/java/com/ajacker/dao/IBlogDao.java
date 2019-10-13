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


    /**
     * 查询博客
     * 若提供title或author则忽略views条件
     * 否则按照views查询
     *
     * @param title  可为null
     * @param author 可为null
     * @param views  不可为null
     * @return 查询结果
     */
    List<Blog> queryBlogChoose(@Param("title") String title, @Param("author") String author, @Param("views") int views);

    /**
     * 更新博客，title和author为可选
     *
     * @param title  可为null
     * @param author 可为null
     * @param id     不可为null
     */
    void updateBlog(@Param("title") String title, @Param("author") String author, @Param("id") String id);

    /**
     * 查询指定id的所有博客
     *
     * @param ids 一个list存要查询的id
     * @return 查询结果
     */
    List<Blog> queryBlogForeach(@Param("ids") List<Integer> ids);
}
