package com.ajacker.dao;

import com.ajacker.domain.QueryVo;
import com.ajacker.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author ajacker
 * 用户的持久层接口
 */
@SuppressWarnings("ALL")
public interface IUserDao {
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "id")
    })

    /**
     * 查询所有
     * @return 查询结果
     */
    @Select({"select * from user"})
    @ResultMap("userMap")
    List<User> findAll();

    /**
     * 保存用户
     *
     * @param user 要保存的用户
     */
    @Insert("insert into user(username, address, sex, birthday) values(#{username},#{address},#{sex},#{birthday})")
    @SelectKey(statement = "select last_insert_id()",
            keyProperty = "userId", keyColumn = "id",
            before = false,
            resultType = Integer.class)
    void saveUser(User user);

    /**
     * 插入用户，参数用map包装
     *
     * @param map
     */
    @Insert("insert into user(username, address, sex, birthday) values(#{userId},#{userAds},#{userSex},#{userBirth})")
    void saveUserUseMap(Map<String, Object> map);

    /**
     * 更新用户
     *
     * @param user
     */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 根据id删除用户
     *
     * @param userId
     */
    @Delete("delete from user where id=#{uid}")
    void deleteUser(int userId);

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{uid}")
    @ResultMap("userMap")
    User findById(int userId);

    /**
     * 根据名字模糊查询
     *
     * @param name
     * @return
     */
    @Select("select * from user where username like #{uname}")
    @ResultMap("userMap")
    List<User> findByName(String name);

    /**
     * 查询总用户数
     *
     * @return
     */
    @Select("select count(id) from user")
    int findTotal();

    /**
     * 根据QueryVo中的条件模糊查询
     *
     * @param vo
     * @return
     */
    @Select("select * from user where username like #{user.username}")
    @ResultMap("userMap")
    List<User> findUserByVo(QueryVo vo);

    /**
     * 有限制的查询所有
     *
     * @param startIndex
     * @param limit
     * @return
     */
    @Select("select * from user limit #{startIndex},#{limit}")
    @ResultMap("userMap")
    List<User> findAllByLimit(@Param("startIndex") int startIndex, @Param("limit") int limit);
}
