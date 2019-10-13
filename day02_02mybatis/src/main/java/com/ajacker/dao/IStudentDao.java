package com.ajacker.dao;

import com.ajacker.domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ajacker
 */
public interface IStudentDao {

    @Select("select * from student where tid = #{tid}")
    List<Student> getStudentsByTeacherId(int tid);

}
