package com.springboot.springinit.dao;

import com.springboot.springinit.entity.Student;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    @Select("SELECT * FROM my_student WHERE id = #{id}")
    Student selectStudentById(int id);

    @Select("SELECT * FROM my_student")
    List<Student> selectStudentByAll();

    @Select("select * from my_student where age = #{age} and gender like #{gender}")
    List<Student> selectStudentByIdAndGender(int age, String gender);

}
