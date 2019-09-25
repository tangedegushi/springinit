package com.springboot.springinit.controller;


import com.springboot.springinit.entity.Student;
import com.springboot.springinit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/hello")
    public String hello(){
        Student student = studentService.selectStudentById(1);
        return "this is the tangedegushi hello "+student.getName();
    }

}
