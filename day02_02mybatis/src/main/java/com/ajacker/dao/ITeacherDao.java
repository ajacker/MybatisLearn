package com.ajacker.dao;

import com.ajacker.domain.Teacher;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ajacker
 */
public interface ITeacherDao {

    /**
     * 获得老师和对应的学生
     *
     * @return
     */
    List<Teacher> getTeachers();

    /**
     * 获得指定老师和他的学生
     *
     * @param id
     * @return
     */
    @Select("select * from teacher where id = #{id}")
    @Results({
            @Result(property = "students", column = "id",
                    many = @Many(select = "com.ajacker.dao.IStudentDao.getStudentsByTeacherId"))
    })
    Teacher getTeacher(int id);
}
