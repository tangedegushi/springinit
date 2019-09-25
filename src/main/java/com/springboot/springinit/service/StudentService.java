package com.springboot.springinit.service;

import com.springboot.springinit.entity.Student;
import com.springboot.springinit.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public Student selectStudentById(int id) {
        return studentDao.selectStudentById(id);
    }

}
