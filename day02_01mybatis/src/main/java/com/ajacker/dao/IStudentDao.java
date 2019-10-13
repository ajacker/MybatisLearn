package com.ajacker.dao;

import com.ajacker.domain.Student;

import java.util.List;

/**
 * @author ajacker
 */
public interface IStudentDao {

    /**
     * 查询所有的学生和对应的老师
     *
     * @return
     */
    List<Student> getStudent();


}
