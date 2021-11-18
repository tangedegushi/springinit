package com.springboot.springinit.dao;

import com.springboot.springinit.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {

    @Select("SELECT * FROM my_student WHERE id = #{id}")
    Student selectStudentById(int id);

}
