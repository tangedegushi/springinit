package com.springboot.springinit.dao;

import com.springboot.springinit.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentDao {

    Student selectStudentById(int id);

}
