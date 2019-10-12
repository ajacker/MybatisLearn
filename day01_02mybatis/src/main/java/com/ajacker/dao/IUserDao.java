package com.ajacker.dao;

import com.ajacker.domain.QueryVo;
import com.ajacker.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ajacker
 * 用户的持久层接口
 */
@SuppressWarnings("ALL")
public interface IUserDao {
    /**
     * 查询所有
     *
     * @return 查询结果
     */
    List<User> findAll();

    /**
     * 保存用户
     *
     * @param user 要保存的用户
     */
    void saveUser(User user);

    /**
     * 插入用户，参数用map包装
     *
     * @param map
     */
    void saveUserUseMap(Map<String, Object> map);

    /**
     * 更新用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     *
     * @param userId
     */
    void deleteUser(int userId);

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    User findById(int userId);

    /**
     * 根据名字模糊查询
     *
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 查询总用户数
     *
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo中的条件模糊查询
     *
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 有限制的查询所有
     *
     * @param startIndex
     * @param limit
     * @return
     */
    List<User> findAllByLimit(@Param("startIndex") int startIndex, @Param("limit") int limit);
}
