package com.tangedegushi.model.service;

import com.tangedegushi.model.dao.StudentDao;
import com.tangedegushi.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public Student selectStudentById(int id) {
        return studentDao.selectStudentById(id);
    }

    public List<Student> selectStudentByAll() {
        System.out.println("StudentService test bean");
        return studentDao.selectStudentByAll();
    }

    public List<Student> selectStudentByIdAndGender(int age, String gender) {
        return studentDao.selectStudentByIdAndGender(age, gender);
    }

}
